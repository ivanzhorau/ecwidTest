package deepclone.zhorau.by.copyutils;

import java.lang.reflect.*;
import java.util.*;

public class CopyUtils {
    private static final Set<Class<?>> primitiveTypes = new HashSet<>();

    static {
        primitiveTypes.add(Integer.class);
        primitiveTypes.add(Byte.class);
        primitiveTypes.add(Character.class);
        primitiveTypes.add(Boolean.class);
        primitiveTypes.add(Double.class);
        primitiveTypes.add(Float.class);
        primitiveTypes.add(Long.class);
        primitiveTypes.add(Short.class);
        primitiveTypes.add(Void.class);
    }

    private static boolean isPrimitive(Object object) {
        return primitiveTypes.contains(object.getClass());
    }

    private static boolean copyConstructorExist(Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredConstructors()).anyMatch(constructor ->
                Arrays.equals(constructor.getParameterTypes(), new Class[]{clazz}));
    }

    private static Object copyByCopyConstructor(Object object) {
        try {
            Constructor<?> constructor = object.getClass().getDeclaredConstructor(object.getClass());
            constructor.setAccessible(true);
            return constructor.newInstance(object);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object deepClone(Object object) {
        if (isPrimitive(object) || object.getClass().getName().contains("["))
            return object;
        if (copyConstructorExist(object.getClass())) {
            return copyByCopyConstructor(object);
        }
        if (Arrays.asList(object.getClass().getInterfaces()).contains(Cloneable.class)) {
            try {
                return object.getClass().getMethod("clone").invoke(object);
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
        return copyFields(object, getClassInstance(object.getClass()));
    }

    private static Object getClassInstance(Class<?> clazz) {
        Object clone;
        try {
            Constructor<?> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            clone = constructor.newInstance();
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return clone;
    }

    private static Object copyFields(Object source, Object clone) {
        Class<?> objectClass = source.getClass();
        for (Field field : objectClass.getDeclaredFields()) {
            try {
                field.setAccessible(true);
                field.set(clone, deepClone(field.get(source)));
            } catch (IllegalAccessException | InaccessibleObjectException e) {
                throw new RuntimeException(e);
            }
        }
        return clone;
    }


}

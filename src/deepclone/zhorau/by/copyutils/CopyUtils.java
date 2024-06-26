package deepclone.zhorau.by.copyutils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class CopyUtils {
    public static Object deepClone(Object object){
        Map<String, Object> fieldMap = createMapFromObject(object);
        return createObjectFromFieldMap(fieldMap, object.getClass());
    }

    private static Object createObjectFromFieldMap(Map<String, Object> fieldMap, Class<?> aClass) {
        Object clone = null;
        try {
            Constructor constructor = aClass.getDeclaredConstructor();
            constructor.setAccessible(true);
            clone = constructor.newInstance();
            for(String key : fieldMap.keySet()){
                Field field = aClass.getDeclaredField(key);
                field.setAccessible(true);
                field.set(clone,fieldMap.get(key));
            }
        } catch (InstantiationException | IllegalAccessException | NoSuchFieldException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return clone;
    }

    private static Map<String, Object> createMapFromObject(Object object) {
        Map<String, Object> fieldMap = new HashMap<>();
        Class objectClass = object.getClass();
        for(Field field : objectClass.getDeclaredFields()){
            String fieldName = field.getName();
            field.setAccessible(true);
            try {
                fieldMap.put(fieldName, field.get(object));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return fieldMap;
    }


}

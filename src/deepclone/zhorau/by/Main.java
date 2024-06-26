package deepclone.zhorau.by;

import deepclone.zhorau.by.copyutils.CopyUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Man man = new Man("Ivan", 22, new ArrayList<>(Arrays.asList("Paleskija Rabinzony", "Čałaviek idzie")),new int[]{3,2,5});
        Man copy = (Man) CopyUtils.deepClone(man);
        man.getFavoriteBooks().add("Halštuk-babačka");
        System.out.println(man +"\n"+ copy);
    }
    public static class Man {
        private String name;
        private int age;
        private List<String> favoriteBooks;
        private int[] array;
        public Man(){}
        public Man(String name, int age, List<String> favoriteBooks) {
            this.name = name;
            this.age = age;
            this.favoriteBooks = favoriteBooks;
        }
        public Man(String name, int age, List<String> favoriteBooks, int[] array) {
            this.name = name;
            this.age = age;
            this.favoriteBooks = favoriteBooks;
            this.array = array;
        }


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public List<String> getFavoriteBooks() {
            return favoriteBooks;
        }

        public void setFavoriteBooks(List<String> favoriteBooks) {
            this.favoriteBooks = favoriteBooks;
        }

        public String toString(){
            return String.format("""
                    Name: %s\s
                    Age: %d\s
                    Books: %s\s
                    Array: %s\s
                    """, name, age, favoriteBooks, IntStream.of(array).boxed().collect(Collectors.toList()));
        }
    }
}

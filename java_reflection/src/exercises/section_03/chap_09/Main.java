package exercises.section_03.chap_09;

import java.lang.reflect.Field;

public class Main {

    public static void main(String[] args) {
        System.out.println("=================================");
        printDeclaredFieldInfo(Movie.class);
        System.out.println("=================================\n");

        System.out.println("=================================");
        printDeclaredFieldInfo(Movie.MovieStats.class);
        System.out.println("=================================\n");

        System.out.println("=================================");
        printDeclaredFieldInfo(Category.class);
        System.out.println("=================================\n");
    }

    public static void printDeclaredFieldInfo(Class<?> clazz) {
        for(Field field : clazz.getDeclaredFields()) {
            System.out.println(String.format("필드명 : %s %n필드 타입 : %s",
                    field.getName(),
                    field.getType().getName()));

            // Synthetic 필드인지 아닌지 확인
            System.out.println(String.format("Synthetic 필드(t/f) : %s", field.isSynthetic()));

            System.out.println();
        }
    }

    public static class Movie extends Product {
        public static final double MINIMUM_PRICE = 10.99;

        private boolean isReleased;
        private Category category;
        private double actualPrice;

        public Movie(String name, int year, double price, boolean isReleased, Category category) {
            super(name, year);
            this.isReleased = isReleased;
            this.category = category;
            this.actualPrice = Math.max(price, MINIMUM_PRICE);
        }

        // Synthetic field 찾기
        // Nested class
        public class MovieStats {

            private double timesWatched;

            public MovieStats(double timesWatched) {
                this.timesWatched = timesWatched;
            }

            public double getRevenue() {
                return timesWatched * actualPrice;
            }
        }
    }

    // 부모(super)클래스
    public static class Product {
        protected String name;
        protected int year;
        protected double actualPrice;

        public Product(String name, int year) {
            this.name = name;
            this.year = year;
        }
    }

    public enum Category {
        ADVENTURE,
        ACTION,
        COMEDY
    }
}

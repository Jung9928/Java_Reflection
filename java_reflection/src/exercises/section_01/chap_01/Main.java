package exercises.section_01.chap_01;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        Class<String> stringClass = String.class;

        Map<String, Integer> mapObject = new HashMap<>();

        Class<?> hashMapClass = mapObject.getClass();

        Class<?> squareClass = Class.forName("exercises.section_01.chap_01.Main$Square");      // exercises는 패키지명

//        System.out.println("=================================================");
//        printClassInfo(stringClass, hashMapClass, squareClass);
//        System.out.println("=================================================");
//        System.out.println();

        var circleObject = new exercises.section_01.chap_01.Main.Drawable() {
            @Override
            public int getNumberOfCorners() {
                return 0;
            }
        };

        printClassInfo(Collections.class, boolean.class, int[][].class, exercises.section_01.chap_01.Main.Color.class, circleObject.getClass());
    }

    private static void printClassInfo(Class<?> ... classes) {

        for (Class<?> clazz : classes) {

            System.out.println(String.format("class name : %s, class package name : %s",
                    clazz.getSimpleName(),
                    clazz.getPackageName()));

            Class<?> [] implementedInterfaces = clazz.getInterfaces();

            for(Class<?> implementedInterface : implementedInterfaces) {
                System.out.println(String.format("class %s implements : %s",
                        clazz.getSimpleName(),
                        implementedInterface.getSimpleName()));
            }

            System.out.println("Is array : " + clazz.isArray());
            System.out.println("Is primitive : " + clazz.isPrimitive());
            System.out.println("Is enum : " + clazz.isEnum());
            System.out.println("Is interface : " + clazz.isInterface());
            System.out.println("Is anonymous : " + clazz.isAnonymousClass());

            System.out.println();
            System.out.println();
        }
    }

    private static class Square implements exercises.section_01.chap_01.Main.Drawable {

        @Override
        public int getNumberOfCorners() {
            return 4;
        }
    }

    private static interface Drawable {
        int getNumberOfCorners();
    }

    private enum Color {
        BLUE,
        RED,
        GREEN
    }
}

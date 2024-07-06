package exercises.section_02.chap_06;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws Exception {

        // Person 클래스의 생성자 정보 출력
        System.out.println("==========================");
        printConstructorData(Person.class);
        System.out.println("==========================");

        System.out.println();

        System.out.println("==========================");
        printConstructorData(Address.class);
        System.out.println("==========================");

//        System.out.println("==========================");
//        printConstructorData(Person.class);
//        System.out.println("==========================");
    }

    public static void printConstructorData(Class<?> clazz) throws NoSuchMethodException {

        // 매개변수로 받은 클래스 내의 정의된 메소드(필드)의 메타 데이터를 가져옴.
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();

        System.out.println(String.format("class %s has %d declared constructors", clazz.getSimpleName(), constructors.length));

        for(int i=0; i<constructors.length; i++) {
            Class<?>[] parameterTypes = constructors[i].getParameterTypes();

            List<String> parameterTypeNames = Arrays.stream(parameterTypes)
                    .map(type -> type.getSimpleName())
                    .collect(Collectors.toList());

            System.out.println(parameterTypeNames);
        }
    }

    public static class Person {
        private final Address address;
        private final String name;
        private final int age;

        public Person() {
            this.name = "anonymous";
            this.age = 0;
            this.address = null;
        }

        public Person(String name) {
            this.name = name;
            this.age = 0;
            this.address = null;
        }

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
            this.address = null;
        }

        public Person(Address address, String name, int age) {
            this.name = name;
            this.age = age;
            this.address = address;
        }
    }

    public static class Address {
        private String street;
        private int number;

        public Address(String street, int number) {
            this.street = street;
            this.number = number;
        }
    }
}

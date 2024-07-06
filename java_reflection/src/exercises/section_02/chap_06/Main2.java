package exercises.section_02.chap_06;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main2 {

    public static void main(String[] args) throws Exception {

        System.out.println("==================================");
        Address address = createInstanceWithArguments(Address.class, "First street", 10);
        Person person = createInstanceWithArguments(Person.class, address, "John", 20);
        System.out.println(person);
        System.out.println("==================================");

        System.out.println();

        System.out.println("==================================");
        Person person2= createInstanceWithArguments(Person.class);
        System.out.println(person2);
        System.out.println("==================================");

        System.out.println();

        System.out.println("==================================");
        Person person3 = createInstanceWithArguments(Person.class, "John", 20);
        System.out.println(person3);
        System.out.println("==================================");
    }

    public static <T> T createInstanceWithArguments(Class<T> clazz, Object ... args) throws Exception {
        for(Constructor<?> constructor : clazz.getDeclaredConstructors()) {
            if(constructor.getParameterTypes().length == args.length) {
                return (T) constructor.newInstance(args);
            }
        }
        System.out.println("생성자를 찾지 못했습니다.");
        return null;
    }

    public static void printConstructorData(Class<?> clazz) {
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

        @Override
        public String toString() {
            return "Person{" +
                    "address = " + address +
                    ", name = '" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    public static class Address {
        private String street;
        private int number;

        public Address(String street, int number) {
            this.street = street;
            this.number = number;
        }

        @Override
        public String toString() {
            return "Address{" +
                    "street = " + street + '\'' +
                    ", number = '" + number +
                    '}';
        }
    }
}

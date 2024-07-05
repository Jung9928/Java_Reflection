# Reflection을 사용하여 Java 클래스 인스턴스 생성하기

### 방법 1) Constructor<?> 사용하여 인스턴스 생성


```Java
public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        printConstructorData(Person.class);
    }

    public static void printConstructorData(Class<?> clazz) {
        Constructor<?>[] constructors = clazz.getDeclaredConstructor();
        
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
```
<br>

```text
// ======== 
// 실행결과
// ========
=================================================
class name : String, class package name : java.lang
class String implements : Serializable
class String implements : Comparable
class String implements : CharSequence
class String implements : Constable
class String implements : ConstantDesc
Is array : false
Is primitive : false
Is enum : false
Is interface : false
Is anonymous : false


class name : HashMap, class package name : java.util
class HashMap implements : Map
class HashMap implements : Cloneable
class HashMap implements : Serializable
Is array : false
Is primitive : false
Is enum : false
Is interface : false
Is anonymous : false


class name : Square, class package name : exercises
class Square implements : Drawable
Is array : false
Is primitive : false
Is enum : false
Is interface : false
Is anonymous : false


=================================================

class name : Collections, class package name : java.util
Is array : false
Is primitive : false
Is enum : false
Is interface : false
Is anonymous : false


class name : boolean, class package name : java.lang
Is array : false
[Is primitive : true]
Is enum : false
Is interface : false
Is anonymous : false


class name : int[][], class package name : java.lang
class int[][] implements : Cloneable
class int[][] implements : Serializable
[Is array : true]
Is primitive : false
Is enum : false
Is interface : false
Is anonymous : false


class name : Color, class package name : exercises
Is array : false
Is primitive : false
[Is enum : true]
Is interface : false
Is anonymous : false


class name : , class package name : exercises
class  implements : Drawable
Is array : false
Is primitive : false
Is enum : false
Is interface : false
[Is anonymous : true]



종료 코드 0(으)로 완료된 프로세스

```



### 방법 2) Reflection 사용하여 인스턴스 생성

```Java
public class Main {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, InvocationTargetException {
//        printConstructorData(Person.class);
        
        Address address = createInstanceWithArguments(Address.class);
        Person person = createInstanceWithArguments(Person.class);
        System.out.println(person);
    }
    
    public static Object createInstanceWithArguments(Class<T> clazz, Object ... args) {
        for(Constructor<?> constructor : clazz.getDeclaredConstructors()) {
            if(constructor.getParameterTypes().length == args.length) {
                return (T) constructor.newInstance(args);
            }
        }
        System.out.println("생성자를 찾지 못했습니다.");
        return null;
    }

    public static void printConstructorData(Class<T> clazz) {
        Constructor<?>[] constructors = clazz.getDeclaredConstructor();
        
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
```

# Reflection API 게이트웨이


```Java
public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        Class<String> stringClass = String.class;

        Map<String, Integer> mapObject = new HashMap<>();

        Class<?> hashMapClass = mapObject.getClass();

        Class<?> squareClass = Class.forName("exercises.Main$Square");      // exercises는 패키지명

        System.out.println("=================================================");
        printClassInfo(stringClass, hashMapClass, squareClass);
        System.out.println("=================================================");
        System.out.println();

        var circleObject = new Drawable() {
            @Override
            public int getNumberOfCorners() {
                return 0;
            }
        };

        printClassInfo(Collections.class, boolean.class, int[][].class, Color.class, circleObject.getClass());
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

    private static class Square implements Drawable {

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
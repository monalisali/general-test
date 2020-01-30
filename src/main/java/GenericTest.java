import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GenericTest {
    public static class  StringList{
        List strList = new ArrayList();

        public void add (String str)
        {
            strList.add(str);
        }

        public int size()
        {
            return strList.size();
        }

        public String get(int index)
        {
            return (String) strList.get(index);
        }
    }

    public static class Animal {
        public void foo(){
        }
    }

    public static class Cat extends Animal{
        @Override
        public void foo() {
            super.foo();
        }
    }

    public static class AnimalComparator implements Comparator<Animal>{
        @Override
        public int compare(Animal o1, Animal o2) {
            return 0;
        }
    }

    private static class CatComparator implements Comparator<Cat>{
        @Override
        public int compare(Cat o1, Cat o2) {
            return 0;
        }
    }
    public static void passObj(Animal an){ }

    public static void passArray(Animal[] animals){}

    public static void passList(List<Animal> animalList){}

    public static void arrayTypeSafetyTest(Object[] objects){
        objects[0] = 1;
    }

//    public static Integer max (Integer a, Integer b){
//        return a > b ? a: b;
//    }
//
//    public static double max (double a, double b){
//        return a > b ? a: b;
//    }
//
//    public static long max (long a, long b){
//        return a > b ? a: b;
//    }

    public static <T extends Comparable> T max(T a, T b){
        return a.compareTo(b) >= 0 ? a : b;
    }

    public static <A extends Comparable, B extends  Comparable> A max1 (A a, B b){
        return a.compareTo(b) >= 0 ? a : (A)b;
    }

    public static <T> void sort(List<T> list, Comparator<? super T> c){
        list.sort(c);
    }


    public static void main(String[] args) {
          StringList stringList = new StringList();
          stringList.add("aa");

          List rawList = new ArrayList();
          rawList.add(1);
          rawList.add("aaa");

          List<?> aa = new ArrayList<>();
          //这句代码会报错，需要让你为List指定一个类型
          //aa.add("");

           passObj(new Cat());
           passArray(new Cat[2]);
           passList(new ArrayList<Animal>());
           //这句代码会报错。因为泛型的擦除原则
           //passList(new ArrayList<Cat>());

           //绕过编译器的检查。编译不会报错，运行也就不会报错
           //示例1:
           List<Animal> animals = new ArrayList<>();
           List rawList1 = animals;
           rawList1.add(new Animal());
           rawList.add("bb");
           rawList.add(11);

           //示例2：
           passList((ArrayList)new ArrayList<Cat>());

           //示例3：数组真类型安全。编译不会报错，运行时还是会报错的
           //arrayTypeSafetyTest(new String[3]);

           max(1,1);
           max(2.0,2.0);
           max(11L,22L);

           sort(new ArrayList<Cat>(),new CatComparator());
           sort(new ArrayList<Cat>(),new AnimalComparator());
    }
}


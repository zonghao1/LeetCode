import java.util.TreeMap;

public class Test {

    public static void main(String[] args) {
        TreeMap<Integer, String> map = new TreeMap<>();
        map.put(3, "Three");
        map.put(5, "Five");
        map.put(10, "Ten");
        map.put(1, "One");
        map.put(2, "two");


        System.out.println(map.floorEntry(0).getValue());


    }



}
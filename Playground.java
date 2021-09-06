import java.util.Comparator;
import java.util.TreeSet;

class Playground {
    public static void main(String[ ] args) {
        TreeSet<int[]> set = new TreeSet<>(new Comparator<int[]>(){
            public int compare(int[] a, int[] b) {
                if (a[0] + Math.abs(a[1]) <= b[0]) return -1;
                if (b[0] + Math.abs(b[1]) <= a[0]) return 1;
                else return 0;
            }
        });
        set.add(new int[]{1,1});
        set.add(new int[]{1,-1});
        System.out.println(set.size());
    }
}
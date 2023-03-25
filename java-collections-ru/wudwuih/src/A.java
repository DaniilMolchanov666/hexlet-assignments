import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class A {

    public static void main(String[] args) {
        List<Integer> a = List.of(1,2,3,4,5);
        Integer[] b ={6,7,8};
        List<Integer> c = List.of(1,2,3,4,5);

        System.out.println(Arrays.toString(a.toArray(b)) + a.toArray(b).getClass() + a.toArray().getClass() + b.getClass() + b.length);
    }
}

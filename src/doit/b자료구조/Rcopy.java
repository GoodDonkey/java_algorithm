package doit.b자료구조;

import java.util.Arrays;

public class Rcopy {
    
    public static void main(String[] args) {
        int[] a = {5,5,3,6,4};
        int[] b = new int[]{};
        rcopy(a, b);
    }
    
    static void rcopy(int[] a, int[] b) {
        b = reverse(a);
        System.out.println("b" + Arrays.toString(b));
    }
    
    static void swap(int[] a, int idx1, int idx2) {
        int t = a[idx1];
        a[idx1] = a[idx2];
        a[idx2] = t;
    }
    
    static int[] reverse(int[] a) {
        for (int i = 0; i < a.length / 2; i++) {
            swap(a, i, a.length -1 -i);
        }
        return a;
    }
}

package problems;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class N013Tournament {

    @Test
    @DisplayName("test01")
    void test01() {

        int result = new N013Tournament().solution(8, 4, 5);
        assertEquals(3, result);

    }

    @Test
    @DisplayName("test02")
    void test02() {
        int result = new N013Tournament().solution3(8, 4, 5);
        assertEquals(3, result);
    }

    @Test
    @DisplayName("test03")
    void test03() {
        int result2 = new N013Tournament().solution3(8, 4, 7);
        assertEquals(3, result2);
    }

    @Test
    @DisplayName("test04")
    void test04() {
        int result3 = new N013Tournament().solution3(8, 1, 2);
        assertEquals(1, result3);
    }

    @Test
    @DisplayName("test05")
    void test05() {
        int result4 = new N013Tournament().solution3(8, 1, 8);
        assertEquals(3, result4);
    }

    @Test
    @DisplayName("test06")
    void test06() {
        int result1 = new N013Tournament().solution3(32, 1, 17);
        int result2 = new N013Tournament().solution3(32, 1, 32);
        assertEquals(5, result1);
        assertEquals(5, result2);
    }

    public int solution3(int n, int a, int b) {
        // 반으로 나눈 수보다 둘다 작거나 둘다 큰지?
        int count = 0;
        int pow = Math.getExponent(n);
        while (a < n && b < n || a > n && b > n) {
            n = n / 2;
            count++;
        }
        return pow - count;
    }


    public int solution2(int n, int a, int b) {
        int bigger = Math.max(a, b);
        int smaller = Math.min(a, b);
        int biggerPow = getPow(bigger);

        for (int i = 1; i < 21; i++) {
            if (biggerPow - (int) Math.pow(2, i) < smaller) {
                return i;
            }
        }
        return 0;
    }

    private int getPow(int x) {
        for (int i = 1; i <= 21; i++) {
            int pow = (int) Math.pow(2, i);
            if (x <= pow) {
                return i;
            }
        }
        return 0;
    }

    private int toEven(int a) {
        if (a % 2 == 0) {
            return a;
        }
        return a+1;
    }

    public int solution(int n, int a, int b) {

        // 짝수면 -> 다음에서 1/2, 홀수면 -> 다음에서 +1 1/2
        // b-a 또는 a-b 가 1이되는 때는?
        int round = 1;
        while (true) {
            a = getNext(a);
            b = getNext(b);
            round++;
            if ((a - b == 1) || (a - b == -1))
                break;
        }
        System.out.println(round);

        return round;
    }

    private int getNext(int x) {
        if (x % 2 == 0) {
            return x / 2;
        }
        return (x + 1) / 2;
    }
}

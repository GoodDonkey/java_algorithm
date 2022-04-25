package problems;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class N028Keypad {
    
    @Test
    @DisplayName("test01")
    void test01() {
        String solution = new N028Keypad().solution(new int[]{1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5}, "right");
        assertEquals("LRLLLRLLRRL", solution);
    
    }
    
    public String solution(int[] numbers, String hand) {
        String answer = "";
        int[][] phone = new int[10][2];
        int index = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                phone[index] = new int[]{i, j};
                index++;
            }
        }
        phone[0] = new int[]{3, 1};
        System.out.println(Arrays.deepToString(phone));
        
        StringBuilder sb = new StringBuilder();
        int[] left = new int[]{3, 0};
        int[] right = new int[]{3, 2};
        
        for (int i = 0; i < numbers.length; i++) {
            int curr = numbers[i];
            if (phone[curr][1] == 0) { // 왼쪽
                sb.append("L");
                left = phone[curr];
            } else if (phone[curr][1] == 2) { // 오른쪽
                sb.append("R");
                right = phone[curr];
            } else { // 중간
                int ldist = getDistance(left, phone[curr]);
                int rdist = getDistance(right, phone[curr]);
                if (ldist > rdist) {
                    sb.append("R");
                    right = phone[curr];
                } else if (ldist < rdist) {
                    sb.append("L");
                    left = phone[curr];
                } else {
                    // 거리가 같으면 잡이로
                    if (hand.equals("right")) {
                        sb.append("R");
                        right = phone[curr];
                    } else {
                        sb.append("L");
                        left = phone[curr];
                    }
                }
            }
        }
        answer = sb.toString();
        return answer;
    }
    
    private int getDistance(int[] thumb, int[] curr) {
        int xAbs = Math.abs(thumb[0] - curr[0]);
        int yAbs = Math.abs(thumb[1] - curr[1]);
        return xAbs + yAbs;
    }
    
    /* 1,4,7 -> L
    * 3, 6, 9 -> R
    * 현재 손가락의 위치를 기록
    * 1,2,3... -> "L"... -> 거리 ->
    *
    * */
}
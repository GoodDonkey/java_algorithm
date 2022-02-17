package problems;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class N008Spicy {

    public int solution(int[] scoville, int K) {
        if (scoville.length == 1) {return -1;}
        int answer = 0;
        Queue<Integer> scovilleQ = new PriorityQueue<>();
        for (int n : scoville) {
            scovilleQ.offer(n);
        }
        while (scovilleQ.peek() != null && scovilleQ.peek() < K) {
            if (scovilleQ.size() == 1) {return -1;}
            Integer a = scovilleQ.poll();
            if (scovilleQ.peek() == null) {return -1;}
            Integer b = scovilleQ.poll();
            int result = process(a, b);
            scovilleQ.offer(result);
            answer++;
        }
        return answer;
    }

    private int process(int a, int b) {
        return a + (b * 2);
    }

    @Test
    @DisplayName("test")
    void test() {
        Assertions.assertEquals(2, solution(new int[]{1, 2, 3, 9, 10, 12}, 7));
    }

    /* 정렬 -> 지수 낮은 것 뽑기 -> 처리 -> 다시 넣기 -> 정렬 */
}

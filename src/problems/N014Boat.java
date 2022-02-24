package problems;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class N014Boat {

    @Test
    @DisplayName("test01")
    void test01() {
        int result = new N014Boat().solution(new int[]{70, 50, 80, 50}, 100);
        assertEquals(3, result);
    }

    @Test
    @DisplayName("test02")
    void test21() {
        int result = new N014Boat().solution(new int[]{70, 80, 50}, 100);
        assertEquals(3, result);
    }

    public int solution(int[] people, int limit) {
        int answer = 0;

        LinkedList<Integer> dq = new LinkedList<>();
        for (int i = 0; i < people.length; i++) {
            dq.offer(people[i]);
        }
        Collections.sort(dq);

        // 모두 짝지을 수 없는 경우
        if (dq.get(0) + dq.get(1) > limit) {
            return dq.size();
        }
        // 모두 짝지을 수 있는 경우
        if (dq.get(dq.size() - 1) + dq.get(dq.size() - 2) <= limit) {
            return (int) Math.ceil((double)dq.size() / 2);
        }
        while (!dq.isEmpty()) {
            if (dq.peekFirst() + dq.peekLast() <= limit) {
                dq.pollFirst();
            }
            dq.pollLast();
            answer++;
        }
        return answer;
    }
}
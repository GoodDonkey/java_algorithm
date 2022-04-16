package problems;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class N022NExpression {
    
    @Test
    @DisplayName("test01")
    void test01() {
        int solution = new N022NExpression().solution(5, 12);
        assertEquals(solution, 4);
    
    }
    
    public int solution(int N, int number) {
        int answer = N;
        // number 가 될떄까지 N을 함수에 적용
        List<IntFunction<Integer>> list = new ArrayList<>();
        list.add(n -> n * 11);
        list.add(n -> n + N);
        list.add(n -> n - N);
        list.add(n -> n * N);
        list.add(n -> n / N);
        
        while (answer != number) {
            int maxTime = 1;
            for (int i = 0; i < 5; i++) {
            
            }
            answer = list.get(0).apply(answer);
        }
        
        return answer;
    }
    
    private int doCal(int number) {
        return number * 11;
    }
    /* 사용할 수 있는 연산
    * *11, +5, -5, *5, /5
    * 5(11/5 + 1/5) = (5 *11 +5) /5
    * (5 + 5) /5 +5 +5
    * 1번씩만, 2번씩만, 3번씩만 ...
    * */
}
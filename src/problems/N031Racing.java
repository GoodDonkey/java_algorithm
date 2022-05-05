package problems;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class N031Racing {
    
    @Test
    @DisplayName("test01")
    void test01() {
        int solution = new N031Racing().solution(new int[][]{{0, 0, 0, 0, 0, 0, 0, 1}, {0, 0, 0, 0, 0, 0, 0, 0},
                                                             {0, 0, 0, 0, 0, 1, 0, 0}, {0, 0, 0, 0, 1, 0, 0, 0},
                                                             {0, 0, 0, 1, 0, 0, 0, 1}, {0, 0, 1, 0, 0, 0, 1, 0},
                                                             {0, 1, 0, 0, 0, 1, 0, 0}, {1, 0, 0, 0, 0, 0, 0, 0}});
        assertEquals(3800, solution);
        
    }
    
    public int solution(int[][] board) {
        int answer = 0;
        
        
        return answer;
    }
    /*
    * 0,0 -> N-1, N-1 로 간다.
    * 25 * 25 이면 2^25
    * 비용이 최소화되는 경로를 찾기
    * 아래, 오른쪽은 갈수 있으면 간다.
    * 위, 왼쪽은 한정적으로 간다.
    *
    * 아래 마지막 인덱스까지 갈 수 있으면 간다.
    * 방향을 바꾸면
    * */
}
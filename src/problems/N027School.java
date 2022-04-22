package problems;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class N027School {
    
    @Test
    @DisplayName("test01")
    void test01() {
        int solution = new N027School().solution(4, 3, new int[][]{{2, 2}});
        assertEquals(4, solution);
    
    }
    
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        int[][] routes = new int[n+1][m+1];
        routes[1][1] = 1;
        for (int i = 0; i < puddles.length; i++) {
            routes[puddles[i][1]][puddles[i][0]] = -1;
        }
        
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                if (routes[i][j] == -1 || (i == 1 && j == 1))
                    continue;
                // puddle 포함되어 있으면 더하지 말것.
                if (routes[i -1][j] == -1) routes[i][j] += routes[i][j - 1] % 1_000_000_007;
                else if (routes[i][j - 1] == -1) routes[i][j] += routes[i - 1][j] % 1_000_000_007;
                else routes[i][j] = (routes[i - 1][j] + routes[i][j - 1]) % 1_000_000_007;
            }
        }
        answer = routes[n][m];
        return answer;
    }
    /*
    * [i][j] 까지의 경로수 = [i - 1][j] + [i][j - 1]
    *
    * */
}
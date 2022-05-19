package problems;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class N019Tiredness {
    
    @Test
    @DisplayName("test01")
    void test01() {
        int[][] d = {{80,20},{50,40},{30,10}};
        int result = new N019Tiredness().solution(80, d);
        assertEquals(3, result);
    
    }
    
    public static boolean visited[];
    public static int ans = 0;
    
    public int solution(int k, int[][] dungeons) {
        visited = new boolean[dungeons.length];
        dfs(k, dungeons, 0);
        return 0;
    }
    
    private void dfs(int tired, int[][] dungeons, int count) {
        for (int i = 0; i < dungeons.length; i++) {
            // 이 for 문은 너무 많이 돈다. 개선 방안이 없을까??
            // 던전 3개면 27번임
            if (!visited[i] && dungeons[i][0] <= tired) {
                visited[i] = true;
                dfs(tired - dungeons[i][1], dungeons, count + 1);
                visited[i] = false;
            }
        }
        ans = Math.max(ans, count);
    }
}
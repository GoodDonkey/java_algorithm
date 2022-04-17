package problems;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.CollectionUtils;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class N024ConnectingIslands {
    
    @Test
    @DisplayName("test01")
    void test01() {
        int n = 4;
        int[][] costs = {{0,1,1},{0,2,2},{1,2,5},{1,3,1},{2,3,8}};
        
        new N024ConnectingIslands().solution(n, costs);
    }
    
    public int solution(int n, int[][] costs) {
        int answer = 0;
        // 가중치 기준으로 정렬
        int[] rank = new int[n];
        int[] root = new int[n];
        for (int i = 0; i < n; i++) {
            root[i] = i;
        }
        System.out.println(Arrays.toString(rank));
        System.out.println(Arrays.toString(root));
        Arrays.sort(costs, Comparator.comparing(a -> a[2]));
        System.out.println(Arrays.deepToString(costs));
        
        int[] finalCosts = new int[n];
        for (int i = 0; i < n; i++) {
            if (root[costs[i][0]] != root[costs[i][1]]) {
                finalCosts[i] = costs[i][2];
                root[costs[i][1]] = root[costs[i][0]];
            }
        }
        System.out.println(Arrays.toString(finalCosts));
        answer = Arrays.stream(finalCosts).sum();
        return answer;
    }
    
    /* 비용의 합을 구하라
    *  */
}
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
        int[] rank = new int[n];
        int[] root = new int[n];
        for (int i = 0; i < n; i++) {
            root[i] = i;
        }
    
        // 가중치 기준으로 정렬
        Arrays.sort(costs, Comparator.comparing(a -> a[2]));
    
        int[] finalCosts = new int[n - 1];
        for (int j = 0; j < n - 1; j++) {
            finalCosts[j] = Integer.MAX_VALUE;
        }
    
        int index = 0;
        while (index < n - 1) {
            int[] link = costs[index];
            // 넣으려는 가중치가 기존에 있는 가중치보다 작으면 고려해본다.
                // root 값이 같으면 넘어가고 다르면 가중치를 사용한다.
            
                    // rank 값이 같으면 그냥 숫자가 큰것을 작은것의 root로 바꾼다.
                        // 큰 수와 작은 수 사이에 있는 모든 정점의 rank를 ++ 한다.
                    // rank 값이 더 작은 게 있으면 그것의 root값을 큰것의 root로 바꾼다.
                        // rank가 더 작은 수가 거쳐가는 모든 접점의 rank를 ++ 한다.
            if (finalCosts[index] > link[2]) {
            
                int less = Math.min(link[0], link[1]);
                int more = Math.max(link[0], link[1]);
                if (root[link[0]] != root[link[1]]) {
                
                    if (rank[less] == rank[more]) {
                        root[more] = root[less];
                        for (int i = root[less]; i < more; i++) {
                            rank[i]++;
                        }
                    } else {
                        int lowRank = rank[less] > rank[more] ? more : less;
                        int highRank = rank[less] > rank[more] ? less : more;
                        root[lowRank] = root[highRank];
                        for (int i = root[highRank]; i < highRank; i++) {
                            rank[i]++;
                        }
                    }
                    finalCosts[index] = link[2];
                }
            }
            index++;
        }
        
        for (int finalCost : finalCosts) {
            answer += finalCost;
        }
        System.out.println(answer);
    
        return answer;
    }
}
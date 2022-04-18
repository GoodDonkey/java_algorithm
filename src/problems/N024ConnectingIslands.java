package problems;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
    int[] parent;
    
    
    public int solution(int n, int[][] costs) {
        int answer = 0;
        int[] rank = new int[n];
        parent = new int[n];
        
        // make_set
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    
        // 가중치 기준으로 정렬
        Arrays.sort(costs, Comparator.comparing(a -> a[2]));
        
        int finalCosts = 0;
        
        // costs들을 모두 확인하여 적합한 가중치를 수집해보자
        for (int i = 0; i < costs.length; i++) {
            int root0 = findRoot(costs[i][0]);
            int root1 = findRoot(costs[i][1]);
            if (root0 == root1) {
                continue;
            }
            finalCosts += costs[i][2];
            parent[root1] = root0;
            
        }
    
        answer = finalCosts;
        System.out.println(answer);
    
        return answer;
    }
    
    private int findRoot(int node) {
        // parent가 자기자신이면 그대로 반환
        if (parent[node] == node) return node;
        
        // 아니면 뿌리를 찾을 때 까지 재귀 호출
        parent[node] = findRoot(parent[node]);
        return parent[node];
    }
}
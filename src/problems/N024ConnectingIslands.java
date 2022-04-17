package problems;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

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
        Stack<Integer> nodes = new Stack<>();
        nodes.push(0);
        
        Stack<Integer> cost = new Stack<>();
        
        int index = 0;
        while (index < n) {
            int target = nodes.peek() + 1;
            if (costs[index][1] == target) {
                if (costs[index][0] < costs[index][1]) {
                    nodes.push(costs[index][1]);
                    cost.push(costs[index][2]);
                }
                
            } else if (costs[index][1] == target - 1) {
                if (costs[index][0] < costs[index][1]) {
                    if (costs[index][2] < cost.peek()) {
                        cost.push(costs[index][2]);
                    }
                }
            }
            index++;
        }
        
        answer = cost.stream().mapToInt(Integer::intValue).sum();
        
        return answer;
    }
    
    /* 비용의 합을 구하라
    * 0-1, 1-2, 0-2,
    * Stack
    * 각 노드에서 2가지 대안 중 cost 가 더 낮은 것을 계속 선택해 나간다.
    * 최종적으로 n 개의 노드를 담아야 한다.
    *
    * nodes{0, 1, 2, 3}  cost{1, 2, 1}
    * [0,1,1] [0,2,2] [1,2,5] [1,3,1] [2,3,8]
    * 0 있으면 담아 1있으면 담아 cost도 담아
    * 2 있으면 담아, first가 2보다 작아? cost 담아
    * 3있으면 담아, fist가 3보다 작아? cost가 내 cost보다 작아? */
}
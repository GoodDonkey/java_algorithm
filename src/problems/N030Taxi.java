package problems;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class N030Taxi {
    
    @Test
    @DisplayName("test01")
    void test01() {
        int solution = new N030Taxi().solution(6,
                                               4,
                                               6,
                                               2,
                                               new int[][]{{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24},
                                                           {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}});
        assertEquals(82, solution);
    }
    
    @Test
    void test2() {
        int solution = new N030Taxi().solution(7,3,4,1,
                                               new int[][]{{5, 7, 9}, {4, 6, 4}, {3, 6, 1}, {3, 2, 3}, {2, 1, 6}});
    
        assertEquals(14, solution);
    }
    
    @Test
    void test3() {
        int solution = new N030Taxi().solution(6,
                                               4,
                                               5,
                                               6,
                                               new int[][]{{2,6,6}, {6,3,7}, {4,6,7}, {6,5,11}, {2,5,12}, {5,3,20}, {2,4,8}, {4,3,9}});
        assertEquals(18, solution);
    }
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        
        int answer = 0;
        long[][] graph = new long[n+1][n+1];
        for (int i = 0; i < n+1; i++) {
            for (int j = 0; j < n+1; j++) {
                if (i == j) {
                    graph[i][j] = 0;
                } else {
                    graph[i][j] = 10_000_000_000L;
                }
            }
        }
        
        for (int i = 0; i < fares.length; i++) {
            int[] cur = fares[i];
            graph[cur[0]][cur[1]] = cur[2];
            graph[cur[1]][cur[0]] = cur[2];
        }

        // 거쳐가는 노드
        for (int i = 1; i < n+1; i++) {
            // 시작노드
            for (int j = 1; j < n+1; j++) {
                // 도착노드
                for (int k = 1; k < n + 1; k++) {
                    if (graph[j][k] > graph[j][i] + graph[i][k]) {
                        graph[j][k] = graph[j][i] + graph[i][k];
                    }
                }
            }
        }
        
        // 각각을 하차지점이라고 생각하고 경로를 구한다.
        // i까지 같이 간다음 각자의 목적지로 간다.
        long min = 10_000_000_000L * 3L;
        for (int i = 1; i < n + 1; i++) {
            if (min > graph[s][i] + graph[i][a] + graph[i][b]) {
                min = graph[s][i] + graph[i][a] + graph[i][b];
            }
        }
        
        

        return (int) min;
    }
    
    /*
    * s -> a ,b  가 무조건 존재한다.
    * a 경로 어레이, b경로 어레이 하나씩.
    * 무향그래프
    * 택시비 최소화
    * 합승하면 1개로 침
    *
    *
    * */
}
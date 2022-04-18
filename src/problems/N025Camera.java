package problems;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class N025Camera {
    
    @Test
    @DisplayName("test01")
    void test01() {
        int[][] routes = {{-20,-15}, {-14,-5}, {-18,-13}, {-5,-3}};
        int solution = new N025Camera().solution(routes);
        assertEquals(2, solution);
    }
    
    @Test
    @DisplayName("test02")
    void test02() {
        int[][] routes = {{2, 2}, {0, 1}, {-10, 2}};
        int solution = new N025Camera().solution(routes);
        assertEquals(2, solution);
    }
    
    @Test
    @DisplayName("test03")
    void test03() {
        int[][] routes = {{0, 2}, {2, 3}, {3, 4}, {4, 6}};
        int solution = new N025Camera().solution(routes);
        assertEquals(2, solution);
    }
    
    @Test
    @DisplayName("test04")
    void test04() {
        int[][] routes = {{0, 0}};
        int solution = new N025Camera().solution(routes);
        assertEquals(1, solution);
    }
    
    @Test
    @DisplayName("test05")
    void test05() {
        int[][] routes = {{0, 1}, {0, 1}, {1, 2}};
        int solution = new N025Camera().solution(routes);
        assertEquals(1, solution);
    }
    
    @Test
    @DisplayName("test05")
    void test06() {
        int[][] routes = {{0, 1}, {1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 6}, {6, 7}, {7, 8}, {8, 9}, {9, 10}, {10, 11},
                          {11, 12}, {12, 13}, {13, 14}, {14, 15}};
        int solution = new N025Camera().solution(routes);
        assertEquals(8, solution);
    }
    
    public int solution(int[][] routes) {
        int answer = 0;
        Arrays.sort(routes, Comparator.comparing(route -> route[1]));
        int i = 0;
        int j = 1;
        
        // 비교대상이 있을 때만 성립됨.
        while (i + j < routes.length) {
            int curr = routes[i][1];
            while (routes[i + j][0] <= curr && routes[i + j][1] >= curr) {
                j++;
                // j 를 올렸는데 범위에서 벗어나면 break.
                if (i + j >= routes.length) break;
            }
            i += j; // jump: curr에 포함이 안됐던 자동차로
            j = 1;
            answer++;
        }
        
        // 비교 대상이 없다면 하나 추가 설치
        if (i + j == routes.length) {
            answer++;
        }
        return answer;
    }
}
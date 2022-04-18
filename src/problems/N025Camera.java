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
        int[][] routes = {{2, 2},{0, 1},{-10,9}};
        int solution = new N025Camera().solution(routes);
        assertEquals(2, solution);
    }
    
    public int solution(int[][] routes) {
        int answer = 0;
        int cnt =  0;
        int index = 0;
        // 정렬: 시작 지점 기준
        Arrays.sort(routes, Comparator.comparing(route -> route[0]));
        
        int j = 1;
        while (index + j < routes.length) {
            int[] now = routes[index];
            int[] another = routes[index + j];
            // 범위에 포함되는 동안 반복
            while (now[0] <= another[0] && now[1] >= another[0]) {
                // 다음 확인할 인덱스가 범위에서 벗어나면 끝낸다.
                j++;
                if (index + j >= routes.length) {
                    j--;
                    break;
                }
                another = routes[index + j];
            }
            cnt += 1;
            index += j; // jump
            j = 1; // j 초기화
        }
        answer = cnt;
        return answer;
    }
    
}
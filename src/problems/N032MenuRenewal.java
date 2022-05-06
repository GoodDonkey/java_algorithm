package problems;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class N032MenuRenewal {
    
    @Test
    @DisplayName("test01")
    void test01() {
//        new N032MenuRenewal().solution();
        
    }
    
    @Test
    void test() {
        String string = "ABCDE";
        int[] arr = new int[]{1, 2, 3, 4, 5};
        combination(arr, new boolean[5], 0, 5, 2);
    }
    
    public void combination(int[] arr, boolean[] visited, int start, int n, int r) {
        if (r == 0) {
            System.out.println(Arrays.toString(arr));
            return;
        }
    
        for (int i = start; i < n; i++) {
            visited[i] = true;
            combination(arr, visited, i + 1, n, r - 1);
            visited[i] = false;
        }
    }
    
    public String[] solution(String[] orders, int[] course) {
        String[] answer = {};
        
        
    
        for (int i = 0; i < orders.length; i++) {
    
            for (int j = 0; j < course.length; j++) {
                
                if (course[j] <= orders[i].length()) {
                    // j 번째 숫자 이상 긴 문자열이라면 조합 저장
                    
                } else {
                    // 더이상 탐색할 필요 없다.
                    
                    break;
                }
            }
        }
        
    
        return answer;
    }
    /*
    * 최소 2명에게서 2가지 이상 메뉴
    * course 의 숫자만큼 메뉴를 뽑을 수 있다.
    * n 개의 문자를 orders 에서 뽑았을 때 가능한 경우의 수?
    *
    * orders 문자열은 중복되지 않는다.
    * 같은 손님들에게서 일정한 배열의 메뉴가 선택된 경우가 여러가지이면, 가장 긴 것만 센다.
    * 개수를 가지고 문자들을 완전 탐색?
    *
    *
    * */
}
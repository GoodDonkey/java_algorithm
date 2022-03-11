package problems;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class N020CheckIn {
    
    @Test
    @DisplayName("test01")
    void test01() {
        int n = 6;
        int[] times = {7, 10};
        new N020CheckIn().solution(n, times);
    }
    
    public long solution(int n, int[] times) {
        long answer = 0;
        Arrays.sort(times);
        
        long left = (long) times[0] * (n / times.length);
        long right = (long) times[times.length - 1] * (n / times.length);
        
        while (left <= right) {
            long mid = (left + right) / 2;
            long passed = 0;
            for (int i = 0; i < times.length; i++) {
                passed += mid / times[i];
            }
    
            if (passed < n) {
                left = mid + 1;
            } else {
                right = mid - 1;
                answer = mid;
            }
        }
    
        System.out.println(answer);
        return answer;
    }
    /*
    * 이진 탐색
    * 1. 이진 탐색의 대상은 정답이 될 수 있는 범위이다.
    * 2. 답이 나올 수 있는 최소, 최대의 범위를 구한다.
    * 3. 최소, 최대 값의 범위에 따라 중간값을 도출한다.
    * 4. 중간값을 문제의 조건에 따라 검사해보고 최소, 최대값을 중간값 +- 1 로 업데이트한다.
    * */
}
package problems;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class N023BigNumber {
    
    @Test
    @DisplayName("test01")
    void test01() {
        String solution = new N023BigNumber().solution("1924", 2);
        assertEquals(solution, "94");
    }
    
    public String solution(String number, int k) {
        String answer = "";
        int nonK = number.length() - k;
        int max = 0;
        int index = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nonK; i++) { // nonK 자리를 뽑을 것이다.
            max = 0; // 현 loop 에서 가장 큰 숫자를 뽑기 위한 기준
            for (int j = index; j <= k + i; j++) {
                // 지난번 가장 큰 숫자의 다음 인덱스부터 탐색함
                // 앞으로 찾아야 할 최소 자리의 숫자는 남겨둔 범위까지만 탐색함
                int target = Character.getNumericValue(number.charAt(j));
                if (target == 9) {
                    max = 9;
                    index = j + 1;
                    break;
                }
                if (max < target) {
                    max = target;
                    index = j + 1;
                }
            }
            sb.append(max);
        }
        answer = sb.toString();
        
        return answer;
    }
}
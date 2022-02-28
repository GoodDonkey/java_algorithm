package problems;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class N017124 {
    
    @Test
    @DisplayName("test01")
    void test01() {
        int n = 1;
        String result = new N017124().solution(n);
//        assertEquals("1", result);
    }
    
    @Test
    @DisplayName("test01")
    void test02() {
        int n = 40;
        String result = new N017124().solution(n);
//        assertEquals("1", result);
        N017124 obj = new N017124();
        for (int i = 1; i < 100; i++) {
            assertEquals(obj.solution(i), obj.solution2(i));
        }
    }
    public String solution(int n) {
        String[] numbers = {"4", "1", "2"};
        String answer = "";
        
        int num = n;
        
        while(num > 0){
            int remainder = num % 3;
            num /= 3;
            
            if(remainder == 0) num--;
            
            answer = numbers[remainder] + answer;
        }
        
        return answer;
    }
    
    public String solution2(int n) {
        String answer = "";
        int i = 0;
        int m = n;
        
        StringBuilder sb = new StringBuilder();
        while (true) {
            i++;
            double pow = Math.pow(3, i);
            m = (int) (m - pow);
            if (m <= 0) {
                m = (int) (m + pow);
                break;
            }
        }
        
        for (int j = i - 1; j > 0; j--) {
            double pow = Math.pow(3, j);
            int v = (int) ((m - 1) / pow); // 몫
            if (v == 0) {
                sb.append(1);
            } else if (v == 1) {
                m = (int) (m - (pow * 1));
                sb.append(2);
            } else if (v == 2){
                m = (int) (m - (pow * 2));
                sb.append(4);
            }
        }
        
        // 나머지로 마지막 숫자 결정
        int left = m % 3;
        if (left == 0) {
            sb.append(4);
        } else if (left == 1) {
            sb.append(1);
        } else if (left == 2){
            sb.append(2);
        }
        answer = sb.toString();
        return answer;
    }
    // 3, 9, 27, 81
    // 3, 12, 39, 120...
}
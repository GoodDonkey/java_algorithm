package problems;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class N022NExpression {
    
    @Test
    @DisplayName("test01")
    void test01() {
        int solution = new N022NExpression().solution(5, 12);
        assertEquals(4, solution);
    
    }
    @Test
    @DisplayName("test02")
    void test02() {
        int solution = new N022NExpression().solution(2, 11);
        assertEquals(3, solution);
    
    }
    @Test
    @DisplayName("test02")
    void test03() {
//        int solution = new N022NExpression().solution(5, 9);
//        assertEquals(4, solution);
        // (55+5+5+5)/5
        for (int i = 1; i < 15; i++) {
            System.out.println(i + " = " + new N022NExpression().solution(5, i));
        }
    }
    
    int norm;
    int cnt;
    
    public int solution(int N, int number) {
        int answer = 0;
        cnt = 0;
        norm = N;
        check(number);
        answer = cnt - 1;
        if (answer >= 8) {
            answer = -1;
        }
        return answer;
    }
    
    private int check(int number) {
        // (5*11+5)/5
//        System.out.println(number);
        if (number == 0) {
            return 0;
        }
        if (number >= norm) {
            if (number % norm == 0) { // 나누어떨어지면, N의 배수이거나 N의 제곱수이면
                if (Math.sqrt(number) % norm == 0) {
                    number = number / norm;
                    cnt++;
                    return check(number);
                } else { // 제곱수가 아니고 배수이면
                    if (number % 11 == 0) {
                        number /= 11;
                        cnt+=2;
                        return check(number);
                    } else {
                        number -= norm;
                        cnt++;
                        return check(number);
                    }
                }
            } else { // 나누어 떨어지지 않으면
                if (number - norm > norm) { // N을 뺏을 때도 N보다 크면
                    number *= norm;
                    cnt++;
                    return check(number);
                } else { // N 보다 작아지면
                    number -= norm;
                    cnt++;
                    return check(number);
                }
            }
        } else { // N 보다 작으면
            if (norm - number == 1) {
                number += 1;
                cnt+=2;
                return check(number);
            } else {
                number -= 1;
                cnt += 2;
            }
            return check(number);
        }
        
    }
    
    /* 사용할 수 있는 연산
    * *11, +5, -5, *5, /5
    * 5(11/5 + 1/5) = (5 *11 +5) /5
    * (5 + 5) /5 +5 +5
    * 1번씩만, 2번씩만, 3번씩만 ...
    *
    * 24 = 5 * 5 + 5/5
    * 2 = 5/5 + 5/5 = (5+5)/5
    * 24 = (5 / 5 + 5) * 5
    * 사용할 수 있는 값
    * 5, 5/5, a*5, a/5, a-5, +55,
    * 이때 5/5, 55 는 2개로 친다.
    *
    * 5 = 5
    * 6 = 5 + (5/5)
    * 7 = 5 + (5/5) + (5/5)
    * 8 = 5 +  (5/5) + (5/5) +  (5/5)
    * 9 = 5 + 5 - (5/5)
    * 10 = 5 + 5
    * 11 = 55 / 5 = 5+5+(5/5)
    * 12 = (55 + 5) / 5
    * 13 = (55 + 5) / 5 + (5/5)
    * 14 = (55 + 5) / 5 + (5/5) + (5/5)
    * 15 = 5+5+5
    * 1
    * N 보다 큰데 나누어 떨어지지 않으면 *N
    *   그 상태에서 N으로 표현할 수 있는지 확인
    *
    * N 의 배수는 덧셈으로
    * N 의 제곱수는 곱셈으로
    * N 보다 작으면 -N, /N 으로
    * 1은 (N/N) 으로
    *
    * */
}
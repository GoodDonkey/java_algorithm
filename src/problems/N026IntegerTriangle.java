package problems;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class N026IntegerTriangle {
    
    @Test
    @DisplayName("test01")
    void test01() {
        int[][] tri = {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}};
        int solution = new N026IntegerTriangle().solution(tri);
        assertEquals(30, solution);
    }
    
    public int solution(int[][] triangle) {
        int answer = 0;
        int maxRow = triangle.length;
        int[][] sums = new int[maxRow][];
        for (int i = 0; i < sums.length; i++) {
            sums[i] = new int[(int) triangle[i].length];
        }
        
        sums[0][0] = triangle[0][0];
        System.out.println(Arrays.deepToString(sums));
        
        for (int i = 0; i < maxRow-1; i++) { // i 층까지만 고려했을 때
            for (int j = 0; j < triangle[i].length; j++) {
                int i1 = sums[i][j] + triangle[i + 1][j];
                int i2 = sums[i][j] + triangle[i + 1][j + 1];
                sums[i + 1][j] = Math.max(sums[i + 1][j], i1);
                sums[i + 1][j + 1] = Math.max(sums[i + 1][j + 1], i2);
            }
        }
        
        int maxValue = 0;
        for (int i = 0; i < sums[maxRow-1].length; i++) {
            if (sums[maxRow-1][i] > maxValue) {
                maxValue = sums[maxRow-1][i];
            }
        }
        answer = maxValue;
        return answer;
    }
    
    /* 전략
    * 0,0
    * 7, 7+3, 7+8, 7+3+8, 7+3+1, 7+8+1, 7+8+0 ...
    * 그리디: - 위에서부터 그때그떄 더 큰 숫자만 쫒아간다.
    *       - 각 층에서 가장 큰 숫자를 뽑고 이어질 때 까지 일정 규칙에 따라 선택을 바꾼다.
    * 완전탐색: 위에서부터 아래로 모든 경우의 수를 계산.
    *
    * 상향식 접근:
    * 4,5,2,6,5 를 마지막에 더할건데, 그 이전층 까지 더한 값들 중에 더 큰것에다 더하면 된다.
    * 이전층들도 마찬가지로, 2,7,4,4, 를 더할 건데, 그 이전층까지 더한 것 중에 더 큰 것에다 더하면 된다.
    * dp(현재) = dp(현재 - 1) + triangle(현재)
    *
    * */
}
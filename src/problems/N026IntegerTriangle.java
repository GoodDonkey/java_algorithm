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
    int[][] tri;
    int[][] sums;
    
    public int solution(int[][] triangle) {
        int answer = 0;
        int length = triangle.length;
        tri = triangle;
        sums = new int[length][];
        
        for (int i = 0; i < length; i++) {
            sums[i] = new int[5];
        }
        
        answer = dp(0, 0);
        System.out.println(Arrays.deepToString(sums));
        return answer;
    }
    
    private int dp(int row, int col) {
        System.out.println(Arrays.deepToString(sums));
        if (row == tri.length - 1) {
            System.out.println(sums[row][col]);
            return tri[row][col];
        }
        if (sums[row][col] != 0) {
            System.out.println(sums[row][col]);
            return sums[row][col];
        }
        sums[row][col] = Math.max(dp(row + 1, col), dp(row + 1, col + 1)) + tri[row][col];
        System.out.println(sums[row][col]);
        return sums[row][col];
    }
    
}
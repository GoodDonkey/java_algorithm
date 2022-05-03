package problems;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class N031Racing {
    
    @Test
    @DisplayName("test01")
    void test01() {
        int solution = new N031Racing().solution(new int[][]{{0, 0, 0, 0, 0, 0, 0, 1}, {0, 0, 0, 0, 0, 0, 0, 0},
                                                             {0, 0, 0, 0, 0, 1, 0, 0}, {0, 0, 0, 0, 1, 0, 0, 0},
                                                             {0, 0, 0, 1, 0, 0, 0, 1}, {0, 0, 1, 0, 0, 0, 1, 0},
                                                             {0, 1, 0, 0, 0, 1, 0, 0}, {1, 0, 0, 0, 0, 0, 0, 0}});
        assertEquals(3800, solution);
        
    }
    
    public int solution(int[][] board) {
        int answer = 0;
        // n-1 n-1  될때까지
        // 우, 하 둘중 하나 끝까지
        // 좌, 상 은 우, 하로 끝까지 못가는 동안에만.
        int end = board.length - 1;
        int[] position = new int[]{0, 0};
        int cost = 0;
        while (!Arrays.equals(position, new int[]{end, end})) {
            // i 번째 행의 원소들이 0인지 확인하기
            int row = position[0];// 행
            for (int j = position[1]; j < board[row].length; j++) { // 열
                if (board[row][j] == 1) { // 막혀있으면
                    position[1] = j - 1;
                    cost += 100 * (j - position[j]) + 500;
                    break;
                } else if (j == board.length - 1) {
                    position[1] = j;
                    cost += 100 * (j - position[j]) + 500;
                    break;
                }
            }
            
            int col = position[1];// 열
            for (int j = position[0]; j < board[col].length; j++) { // 행
                if (board[j][col] == 1) {
                    position[0] = j - 1;
                    cost += 100 * (j - position[j]) + 500;
                    break;
                } else if (j == board.length - 1){
                    position[0] = j;
                    cost += 100 * (j - position[j]) + 500;
                    break;
                }
            }
    
            // 현재 열에서 끝행까지 뚫려있는지 거꾸로
            int curCol = position[1];
            while (position[0] != board.length - 1) {
                for (int j = position[0]; j < board.length; j++) {
                    if (j == board.length - 1) {
                        position[0] = j;
                        position[1] = curCol;
                        cost += 100 * (position[j] - j) + 500;
                        break;
                    } else if (board[curCol][j] == 1) {
                        break;
                    }
                }
                curCol--;
            }
            
            // 위로
            
            System.out.println(Arrays.toString(position));
        }
        return answer;
    }
    /*
    * 0,0 -> N-1, N-1 로 간다.
    * 25 * 25 이면 2^25
    * 비용이 최소화되는 경로를 찾기
    * 아래, 오른쪽은 갈수 있으면 간다.
    * 위, 왼쪽은 한정적으로 간다.
    *
    * 아래 마지막 인덱스까지 갈 수 있으면 간다.
    * 방향을 바꾸면
    * */
}
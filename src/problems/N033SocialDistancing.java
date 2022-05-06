package problems;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class N033SocialDistancing {
    
    @Test
    @DisplayName("test01")
    void test01() {
        new N033SocialDistancing().solution(new String[][]{{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
                                                           {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
                                                           {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
                                                           {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
                                                           {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}});
        
    }
    
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static boolean[][] visit;
    static int[] answer;
    
    public int[] solution(String[][] places) {
        answer = new int[]{1, 1, 1, 1, 1};
    
        for (int i = 0; i < places.length; i++) {
            visit = new boolean[5][5];
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    if (places[i][j].charAt(k) == 'P') {
                        visit[j][k] = true;
                        dfs(i, j, k, 0, places[i]);
                        visit[j][k] = false;
                    }
                }
            }
        }
        return answer;
    }
    
    private void dfs(int roomNum, int row, int col, int distance, String[] place) {
        if (distance > 2) return; // 3 칸 부터는 확인할 필요 없다.
        if (distance > 0 && distance <= 2 && place[row].charAt(col) == 'P') {
            // 2칸 내에 P 가 있으면 거리두기 실패. 파티션은 미리 확인 하였음.
            answer[roomNum] = 0;
            return;
        }
        
        // 상우하좌를 확인한다.
        for (int i = 0; i < 4; i++) {
            int nx = row + dx[i];
            int ny = col + dy[i];
    
            // 배열 범위 내에 있으면서 파티션이 아니면
            if (nx >= 0 && nx < 5 && ny >= 0 && ny < 5 && place[nx].charAt(ny) != 'X') {
                if (visit[nx][ny]) continue;
                visit[nx][ny] = true; // 재귀 확인에서 이 문자는 확인할 필요가 없다.
                dfs(roomNum, nx, ny, distance + 1, place);
                visit[nx][ny] = false;
            }
        }
    }
}
/*
 * 대기실은 5개, 5*5 크기
 * 효율 x
 * 어떤 P 가 있을 때 거리두기를 어기는 조건이 고정되어 있다.
 * 거리두기를 어기는 조건을 발견했다면 다음 대기실을 확인하면 된다.
 *
 * 거리가 2일때
 *   거리가 1인 지점에
 *
 * */
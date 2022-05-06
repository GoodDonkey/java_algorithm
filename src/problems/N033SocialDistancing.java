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
    
    public int[] solution(String[][] places) {
        int[] answer = new int[]{1, 1, 1, 1, 1};
        
        for (int i = 0; i < places.length; i++) {
            // 2차원 배열을 만들어서 확인
            String[][] room = new String[5][5];
            for (int j = 0; j < 5; j++) { // 대기실 행
                for (int k = 0; k < 5; k++) { // 대기실 열
                    room[j][k] = String.valueOf(places[i][j].charAt(k));
                }
            }
            // P를 찾아서 주변을 살핀다.
            System.out.println(Arrays.deepToString(room));
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    if (answer[i] == 0) break;
                    if (room[j][k].equals("P")) {
                        // 인덱스 범위 체크
                        if (j + 1 < 5) {
                            if (room[j + 1][k].equals("P")) {
                                answer[i] = 0;
                            }
                        }
                        if (k + 1 < 5) {
                            if (room[j][k + 1].equals("P")) {
                                answer[i] = 0;
                            }
                        }
                        if (j + 1 < 5 && k + 1 < 5) {
                            if (room[j + 1][k + 1].equals("P")) {
                                if (!room[j][k + 1].equals("X") || !room[j + 1][k].equals("X")) {
                                    System.out.println(j + ":" + k);
                                    answer[i] = 0;
                                }
                            }
                        }
                        if (j + 2 < 5) {
                            if (room[j + 2][k].equals("P")) {
                                if (!room[j + 1][k].equals("X")) {
                                    System.out.println(j + ":" + k);
                                    answer[i] = 0;
                                }
                            }
                        }
                        if (k + 2 < 5) {
                            if (room[j][k + 2].equals("P")) {
                                if (!room[j][k + 1].equals("X")) {
                                    System.out.println(j + ":" + k);
                                    answer[i] = 0;
                                }
                            }
                        }
                        if (j + 1 < 5 && k - 1 >= 0) {
                            if (room[j + 1][k - 1].equals("P")) {
                                if (!room[j][k - 1].equals("X") || !room[j + 1][k].equals("X")) {
                                    System.out.println(j + ":" + k);
                                    answer[i] = 0;
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println(Arrays.toString(answer));
        return answer;
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
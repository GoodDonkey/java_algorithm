package problems;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class N012Delivery {

    @Test
    @DisplayName("test")
    void test() {
        int result = new N012Delivery().solution(5,
                                                 new int[][]{{1, 2, 1},
                                                   {2, 3, 3},
                                                   {5, 2, 2},
                                                   {1, 4, 2},
                                                   {5, 3, 1},
                                                   {5, 4, 2}},
                                                 3);
        Assertions.assertEquals(4, result);
    }

    @Test
    @DisplayName("test2")
    void test2() {
        int result2 = new N012Delivery().solution(6,
                                                  new int[][]{{1, 2, 1}, {1, 3, 2}, {2, 3, 2}, {3, 4, 3}, {3, 5, 2}, {3, 5, 3}, {5, 6, 1}},
                                                  4);
        Assertions.assertEquals(4, result2);


    }

    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        long INFINITY = Integer.MAX_VALUE;

        long[][] fw = new long[N][N];

        // 직접 경로로 초기화: 대칭적
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                if (i == j)
                    continue;
                for (int[] each : road) {
                    if ((i + 1 == each[0] && j + 1 == each[1]) || (j + 1 == each[0] && i + 1 == each[1])) {
                        if (fw[i][j] > each[2] || fw[j][i] > each[2]) {
                            fw[i][j] = each[2];
                            fw[j][i] = each[2];
                        } else  {
                            fw[i][j] = each[2];
                            fw[j][i] = each[2];
                        }
                    } else {
                        fw[i][j] = INFINITY;
                        fw[j][i] = INFINITY;
                    }
                }
            }
        }
        for (long[] each : fw) {
            System.out.println(Arrays.toString(each));
        }

        // 2, 3, ...   를 거쳐가는 방식으로 수정하기 -> 더 빠른 경로가 존재할 수 있다.
        // 전체 탐색
        boolean[][] flags = new boolean[N][N]; // 모두 false가 될떄까지 수행하면됨
        boolean done = false;
        while (!done) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    flags[i][j] = false;
                    if (i == j) {
                        continue;
                    }
                    for (int k = 0; k < N; k++) {
                        if (k == i || k == j) {
                            continue;
                        }
                        long alternative = fw[i][k] + fw[k][j];
                        if (fw[i][j] > alternative) {
                            fw[i][j] = alternative;
                            flags[i][j] = true;
                        }
                    }
                }
            }
            done = true;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (flags[i][j]) {
                        done = false;
                    }
                }
            }
        }

        for (long[] each : fw) {
            System.out.println(Arrays.toString(each));
        }

        // 1번 마을이 K 시간 이하로 배달 가능한 마을의 수는?
        for (int i = 0; i < N; i++) {
            if (fw[0][i] <= K) {
                answer++;
                System.out.println(fw[0][i]);
            }
        }

        System.out.println(answer);

        return answer;
    }


}

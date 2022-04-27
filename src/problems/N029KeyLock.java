package problems;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class N029KeyLock {
    
    @Test
    @DisplayName("test01")
    void test01() {
        int[][] key = new int[][]{{0, 0, 0}, {1, 0, 0}, {0, 1, 1}};
        int[][] lock = new int[][]{{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
        boolean solution = new N029KeyLock().solution(key, lock);
        assertTrue(solution);
    }
    
    HashSet<int[]> keyLocations = new HashSet<>();
    HashSet<int[]> lockLocations = new HashSet<>();
    int lockLength;
    int keyLength;
    
    public boolean solution(int[][] key, int[][] lock) {
        boolean answer = false;
        lockLength = lock.length;
        keyLength = key.length;
        
        // lock 이 항상 크므로 같이 조회 가능.
        for (int i = 0; i < lock.length; i++) {
            for (int j = 0; j < lock.length; j++) {
                if (key[i][j] == 1) keyLocations.add(new int[]{i, j});
                if (lock[i][j] == 0) lockLocations.add(new int[]{i, j});
            }
        }
        System.out.println(keyLocations);
        System.out.println(lockLocations);
        
        int maxLength = lockLength + keyLength - 1;
        toFirst(keyLocations); // 0,0 부터 완전 탐색
        int rowCount = 1;
        
        while (rowCount <= maxLength) {
            System.out.println(rowCount);
            for (int i = 0; i < maxLength; i++) { // x축 1씩 이동
                for (int j = 0; j < 4; j++) { // 4 번씩 돌려본다.
                    for (int k = 0; k < keyLength; k++) { // key 의 돌기 위치들을 확인함
                        if (keyLocations.containsAll(lockLocations)) { // key들이 모든 lock 홈에 들어가고,
                            Set<int[]> notContaining = keyLocations.stream()
                                    .filter(arr -> !lockLocations.contains(arr))
                                    .collect(Collectors.toSet());
                            System.out.println("notContaining" + notContaining);
                            for (int[] item : notContaining) {
                                if (item[0] > lockLength || item[1] > lockLength) {
                                    answer = true;
                                    rowCount = 20;
                                    break;
                                }
                            }
                        }
                    }
                    spin(keyLocations);
                }
                xPlus(keyLocations);
            }
            yPlus(keyLocations);
            rowCount++;
        }
        
        
        // 완전탐색
        // lock 길이 - 1만큼 key 의 모든 돌기를 뺀다.(길이 3이면 모든 좌표를 2씩 뺀다.)
        // 그다음 각 좌표를 회전 시킨 4가지 경우의 수 만큼 lock 의 홈에 맞는지 비교
        // 없으면 key의 x 또는 y 좌표를 1씩 더하면서 모든 경우의 수를 탐색
        // 최대 20*20, 20*20 일 때 40*40 *4 = 6400
        
        return answer;
    }
    private void yPlus(HashSet<int[]> keyLocations) {
        for (int[] keyLocation : keyLocations) {
            keyLocation[0]++;
        }
    }
    
    private void xPlus(HashSet<int[]> keyLocations) {
        for (int[] keyLocation : keyLocations) {
            keyLocation[1]++;
        }
    }
    
    private void toFirst(HashSet<int[]> keyLocations) {
        for (int[] keyLocation : keyLocations) {
            keyLocation[0] -= keyLength - 1;
            keyLocation[1] -= keyLength - 1;
            System.out.println("first" + Arrays.toString(keyLocation));
        }
    }
    
    private void spin(HashSet<int[]> keyLocations) {
        for (int[] keyLocation : keyLocations) {
            int row = keyLocation[0];
            keyLocation[0] = keyLength - keyLocation[1] - 1;
            keyLocation[1] = row;
            System.out.println(Arrays.toString(keyLocation));
        }
    }
}
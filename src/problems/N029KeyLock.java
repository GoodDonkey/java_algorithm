package problems;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

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
    class Locations{
        List<int[]> locations = new ArrayList<>();
        int lockLength;
        
        public Locations(int[][] key, int lockLength) {
            for (int i = 0; i < key.length; i++) {
                for (int j = 0; j < key[i].length; j++) {
                    if (key[i][j] == 1) {
                        locations.add(new int[]{i, j});
                    }
                }
            }
            this.lockLength = lockLength;
        }
        
        public Locations spin() {
            int length = this.locations.size();
            for (int[] location : this.locations) {
                int row = location[0];
                location[0] = length - location[1] - 1;
                location[1] = row;
            }
            locations.forEach(arr -> System.out.println(Arrays.toString(arr)));
            return this;
        }
    
        public void move(int x, int y) {
            this.toFirst();
            for (int i = 0; i < locations.size(); i++) {
                locations.get(i)[0] += x;
                locations.get(i)[1] += y;
            }
        }
        
        public void toFirst() {
            for (int i = 0; i < locations.size(); i++) {
                locations.get(i)[0] -= lockLength - 1;
                locations.get(i)[1] -= lockLength - 1;
            }
        }
    
        public boolean containsAll(Collection<int[]> collection) {
            return locations.containsAll(collection);
        }
    }
    
    public boolean solution(int[][] key, int[][] lock) {
        boolean answer = false;
        // 좌표를 저장해야함.
        Locations locations = new Locations(key, lock.length);
        locations.toFirst();
    
        Set<int[]> empties = new HashSet<>();
        for (int i = 0; i < lock.length; i++) {
            for (int j = 0; j < lock[i].length; j++) {
                if (lock[i][j] == 0) {
                    empties.add(new int[]{i, j});
                }
            }
        }
        int x = 0;
        int y = 0;
        while (x < lock.length * 2 || y < lock.length * 2) {
            System.out.println(x);
            System.out.println(y);
            for (int i = 0; i < 4; i++) {
                if (locations.spin().containsAll(empties)) {
                    answer = true;
                }
            }
            if (x == lock.length * 2) {
                x = 0;
                y++;
            }
            x++;
            locations.move(x,y);
        }
        
        // 완전탐색
        // lock 길이 - 1만큼 key 의 모든 돌기를 뺀다.(길이 3이면 모든 좌표를 2씩 뺀다.)
        // 그다음 각 좌표를 회전 시킨 4가지 경우의 수 만큼 lock 의 홈에 맞는지 비교
        // 없으면 key의 x 또는 y 좌표를 1씩 더하면서 모든 경우의 수를 탐색
        // 최대 20*20, 20*20 일 때 40*40 *4 = 6400
        
        return answer;
    }
    /*
    * 효율성은 그다지?
    *
    * 돌리거나 움직일 때 좌표의 변화는?
    *   돌리는 경우의 수: 4
    *   이동하는 경우의 수:
    * lock 크기를 기준으로 key의 좌표를 표시해야함.
    * */
}
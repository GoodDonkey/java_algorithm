package problems;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class N037LightRoute {
    
    @Test
    @DisplayName("test01")
    void test01() {
        String[] grid = {"SL", "LR"};
        String[] grid2 = {"R","R"};
        String[] grid3 = {"S"};
        int[] solution = new N037LightRoute().solution(grid);
        int[] solution2 = new N037LightRoute().solution(grid2);
        int[] solution3 = new N037LightRoute().solution(grid3);
        assertEquals(new int[]{16}, solution);
        assertEquals(new int[]{4,4}, solution2);
        assertEquals(new int[]{1,1,1,1}, solution3);
    }
    
    public int[] solution(String[] grid) {
        int[] answer = {};
        Node[][] nodeArray = new Node[grid.length][grid[0].length()];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length(); j++) {
                nodeArray[i][j] = new Node(Direction.fromString(grid[i].substring(j, j + 1)));
            }
        }
        System.out.println(Arrays.toString(nodeArray));
        return answer;
    }
    
    class Node{
        final Direction direction;
        boolean[][] directions = new boolean[2][4];
        // in, out = 0, 1
        // L, N, R, S = 0, 1, 2, 3
        
        Node (Direction direction) {
            this.direction = direction;
        }
    
        boolean update(IO io, Direction direction) {
            if (directions[io.index][direction.index] = true) {
                return false;
            }
            directions[io.index][direction.index] = true;
            return true;
        }
    }
    
    enum IO {
        IN(0), OUT(1);
        
        final private int index;
        
        IO(int index) {
            this.index = index;
        }
    
        static IO fromIndex(int index) {
            if (index == 0) {
                return IN;
            } else {
                return OUT;
            }
        }
    }
    
    enum Direction {
        LEFT(0), NORTH(1), RIGHT(2), SOUTH(3);
        
        private final int index;
    
        Direction(int index) {
            this.index = index;
        }
    
        static Direction fromString(String str) {
            if (str.equals("S")) {
                return SOUTH;
            } else if (str.equals("L")) {
                return LEFT;
            } else {
                return RIGHT;
            }
        }
        
        static Direction fromIndex(int index) {
            if (index == 0) {
                return LEFT;
            } else if (index == 1) {
                return NORTH;
            } else if (index == 2) {
                return RIGHT;
            } else {
                return SOUTH;
            }
        }
    }
}

/*
* 들어온 방향
* 나가는 방향
*
* 행렬 격자 기본적으로 주어짐
* 모든 경우의 수는 행렬 격자점 * 4
*
* 격자점 당
*   동서남북
*   in / out
*   위치
*
* */
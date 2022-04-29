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
    
    public boolean solution(int[][] key, int[][] lock) {
        boolean answer = false;
        
        
        return answer;
    }
}
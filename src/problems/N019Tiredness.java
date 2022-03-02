package problems;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class N019Tiredness {
    
    private List<List<String>> perms = new ArrayList<>();
    
    
    @Test
    @DisplayName("test01")
    void test01() {
        int[][] d = {{80,20},{50,40},{30,10}};
        int result = new N019Tiredness().solution(80, d);
        assertEquals(3, result);
    
    }
    
    public int solution(int k, int[][] dungeons) {
        int answer = 0;
        // 최소 필요 피로도가 큰 순서대로 돌아야 함.
        // 다음 던전을 돌 수 없는 경우, 최근 던전을 취소하고 (최소는 항상 만족하므로)
        // 소모 피로도가 가장 작은것 or 최근 던전을 간신히 돌 수 있는 던전을 돌면 된다.
        // 그런데 던전 개수가 많이 주어지지 않으므로 모두 탐색하는 것도 괜찮다.
        List<String> arr = new ArrayList<>();
        for (int i = 0; i < dungeons.length; i++) {
            arr.add(String.valueOf(i));
        }
        recursion(arr, new ArrayList<>(), dungeons.length, dungeons.length);
        System.out.println(perms);
    
        return answer;
    }
    
    private void recursion(List<String> arr, List<String> result, int n, int r) {
        
        if (r == 0) {
            perms.add(result);
        }
        
        for (int i = 0; i < n; i++) {
            
            result.add(arr.remove(i));
            recursion(arr, result, n - 1, r - 1);
            arr.add(i, result.remove(result.size() - 1));
        }
    }
}
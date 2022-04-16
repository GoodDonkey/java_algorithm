package problems;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class N023BigNumber {
    
    @Test
    @DisplayName("test01")
    void test01() {
        String solution = new N023BigNumber().solution("1924", 2);
        assertEquals(solution, "94");
        
    }
    
    public String solution(String number, int k) {
        String answer = "";
        int nonK = number.length() - k;
        
        List<int[]> indexes = getCombs(number.length(), nonK);
        ArrayList<String> list = new ArrayList<>();
        
        for (int[] index : indexes) {
            StringBuilder sb = new StringBuilder();
            for (int i : index) {
                sb.append(number.charAt(i));
            }
            list.add(sb.toString());
        }
        
        int max = 0;
        for (String sb : list) {
            int now = Integer.parseInt(sb);
            if (now > max) {
                max = now;
            }
        }
        answer = String.valueOf(max);
        
        return answer;
    }
    public List<int[]> getCombs(int n, int r) {
        List<int[]> combinations = new ArrayList<>();
        combination(combinations, new int[r], 0, n - 1, 0);
        return combinations;
    }
    
    private void combination(List<int[]> combinations, int[] data, int start, int end, int index) {
        if (index == data.length) {
            int[] combination = data.clone();
            combinations.add(combination);
        } else if (start <= end) {
            data[index] = start;
            combination(combinations, data, start + 1, end, index + 1);
            combination(combinations, data, start + 1, end, index);
        }
    }
}
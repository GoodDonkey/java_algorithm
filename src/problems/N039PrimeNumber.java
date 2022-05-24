package problems;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

public class N039PrimeNumber {
    
    @Test
    @DisplayName("test01")
    void test01() {
        new N039PrimeNumber().solution("17");
        new N039PrimeNumber().solution("011");
        
    }
    
    Set<Integer> output = new LinkedHashSet<>();
    
    public int solution(String numbers) {
        int answer = 0;
        String[] ns = new String[numbers.length()];
        for (int i = 0; i < numbers.length(); i++) {
            ns[i] = numbers.substring(i, i + 1);
        }
        
        for (int i = 1; i <= numbers.length(); i++) {
            permutation(ns, new String[ns.length], new boolean[ns.length], 0, i);
        }
    
        // 제일 큰수는?
        int large = Collections.max(output);
        boolean[] check = new boolean[large+1];
        
        check[0] = check[1] = false;
    
        for (int i = 2; i <= large; i++) {
            check[i] = true;
        }
    
        for (int i = 2; i * i <= large; i++) { // 2부터 각 수들의 모든 배수는 소수가 아니다.
            for (int j = i * i; j <= large; j+=i) {
                check[j] = false;
            }
        }
        
        List<Integer> primes = new ArrayList<>();
        for (int i = 0; i < check.length; i++) {
            if (check[i]) {
                primes.add(i);
            }
        }

        ArrayList<Integer> outputList = new ArrayList<>(output);
        long count1 = outputList.stream()
                .filter(n -> primes.contains(n)).count();
        
        answer = (int) count1;
    
    
        return answer;
    }
    
    private void permutation(String[] ns, String[] outputArr, boolean[] visited, int depth, int n) {
        if (depth == n) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                sb.append(outputArr[i]);
            }
            output.add(Integer.valueOf(sb.toString()));
            System.out.println(output);
        }
    
        for (int i = 0; i < ns.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                outputArr[depth] = ns[i];
                permutation(ns, outputArr, visited, depth + 1, n);
                outputArr[depth] = "";
                visited[i] = false;

            }
        }
        
    }
}

/*
* 소수: 1 과 자기자신 말고는 나눌 수 없음.
*
* 짝수로 나눠지면 안됨
* 작은 수 부터 절반까지 나눠봤으면 그 이상은 확인안해도 됨
*
* */
package doit.b자료구조;

public class PrimeNumber3 {
    
    public static void main(String[] args) {
        
        // 기본적으로 소수로 나누어보는 과정은 같다.
        // 특정 n 을 나누어보는 시행에서 n 제곱근 보다 큰수로는 나누어볼 필요가 없다.
        // n 의 제곱근을 기점으로 이미 그보다 작은 수로 나누어 보면서 시행했던 결과이기 때문.
        // 따라서 제수 i의 제곱이 n보다 작은 i 만 사용해보자.
        
        int counter = 0;
        int ptr = 0;
        int[] prime = new int[500];
        
        prime[ptr++] = 2;
        prime[ptr++] = 3;
    
        for (int n = 5; n <= 1000; n+=2) {
            boolean flag = false;
            for (int i = 1; prime[i] * prime[i] <= n; i++) {
                counter += 2; // 제곱해서 i 판단하기 + n 을 prime[i] 로 나누어 보기
                if (n % prime[i] == 0) {
                    flag = true;
                    break;
                }
            }
            if (!flag) { // 나누어 떨어지지 않았으면
                prime[ptr++] = n;
                counter++;
            }
        }
    
        for (int i = 0; i < ptr; i++) {
            System.out.println(prime[i]);
        }
        System.out.println("곱셈과 나눗셈을 수행한 횟수: " + counter);
    }
}

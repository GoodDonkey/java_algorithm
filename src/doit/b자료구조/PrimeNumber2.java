package doit.b자료구조;

public class PrimeNumber2 {
    
    public static void main(String[] args) {
        int counter = 0;
        int ptr = 0;
        int[] prime = new int[500];
        
        // 기본적으로 n 보다 작고 2 이상인 자연수로 나누어 떨어지지 않는 n은 소수이다.
        // 여기에 n 보다 작은 소수로 나누어 떨어지는 수는 소수가 아니다.
        // 따라서 2 부터 차례로 소수들로만 나누어보고 나누어떨어지지 않으면 소수라고 판단한다.
    
        prime[ptr++] = 2; // 첫번째 소수는 2 저장
    
        for (int n = 3; n < 1000; n+=2) {
            int i;
            for (i = 1; i < ptr; i++) {
                counter++;
                // 소수로 나눠보기
                if (n % prime[i] == 0) break;
            }
            // 현재까지 아는 소수를 모두 사용해 보았으면 그 수는 소수이다.
            if (ptr == i)
                prime[ptr++] = n;
        }
    
        for (int i = 0; i < ptr; i++) {
            System.out.println(prime[i]);
        }
    
        System.out.println("나눗셈을 시행한 횟수: " + counter);
        
        
    }
}

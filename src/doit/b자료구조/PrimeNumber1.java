package doit.b자료구조;

public class PrimeNumber1 {
    
    public static void main(String[] args) {
        int counter = 0;
        // 1000 이하의 소수를 구한다.
        // 어떤 숫자(n)를 그 숫자보다 작은 숫자(i)로 나누었을 때 나누어 떨어지는 숫자가 있으면 소수가 아니다.
        // n 당 존재하는 i 을 모두 수행하여 i == n 이 되면 그 숫자는 소수이다.
        for (int n = 2; n <= 1000; n++) {
            int i;
            for (i = 2; i < n; i++) {
                counter++;
                if (n % i == 0)
                    break;
            }
            if (i == n) System.out.println(i);
        }
        System.out.println("나눗셉을 행한 횟수 : " + counter);
    }
}

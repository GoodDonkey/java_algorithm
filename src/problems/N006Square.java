package problems;

public class N006Square {

    public static void main(String[] args) {
        System.out.println(new N006Square().solution(8, 8));
    }

    public long solution(int w, int h) {
        long total = (long) w * h;
        long answer;
        long gcd = gcd(w, h);

        answer = total - (((w / gcd) + (h / gcd) - 1) * gcd);
        return answer;

    }
    private int gcd(int a, int b) {
        if (a % b == 0) {
            return b;
        }
        return gcd(b, a % b);
    }
}

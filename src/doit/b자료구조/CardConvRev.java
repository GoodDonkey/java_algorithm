package doit.b자료구조;

public class CardConvRev {
    
    static int cardConvR(int x, int r, char[] d) {
        int digits = 0;
        String dchar = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        // r진수로 표현하면 나머지 인덱스에 위치하는 숫자가 r 진수일떄 숫자이다.
        System.out.print(x + "를 " + r + "진수로 바꾸면 ");
        
        do {
            System.out.print(dchar.charAt(x % r));
            d[digits++] = dchar.charAt(x % r);
            x /= r;
        } while (x != 0);
        System.out.println("입니다");
        return digits;
    }
    
    public static void main(String[] args) {
        int n = cardConvR(59, 16, new char[32]);
        System.out.println("n = " + n);
    }
}

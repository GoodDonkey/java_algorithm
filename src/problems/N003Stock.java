package problems;

import java.util.Arrays;
import java.util.Stack;

public class N003Stock {

    public static void main(String[] args) {
//        new N003Stock().solution(new int[]{1, 2, 3, 2, 3});
        new N003Stock().solution(new int[]{1, 2, 3, 2, 3, 1});
    }

    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        Stack<Integer> stack = new Stack<>();

        System.out.println("answer = " + Arrays.toString(answer));
        for (int i = 0; i < prices.length; i++) {
            // 현재 주식이 다음 날 주식과 비교해서 떨어졌으면 인덱스 차이를 answer를 저장한다.
            while (!stack.isEmpty() && prices[i] < prices[stack.peek()]) {
                answer[stack.peek()] = i - stack.peek();
                System.out.println("i, answer = " + i + Arrays.toString(answer));
                stack.pop(); // 하락하는 날 기준으로 이전 주식들의 인덱스를 모두 업데이트하였음.
                System.out.println(stack);
            }

            // 다음날 인덱스를 넣는다.
            stack.push(i);
            System.out.println(stack);
        }
        System.out.println("for done");

        while (!stack.isEmpty()) {
            answer[stack.peek()] = prices.length - stack.peek() - 1;
            stack.pop();
            System.out.println(stack);
        }
        System.out.println(Arrays.toString(answer));
        return answer;
    }
}

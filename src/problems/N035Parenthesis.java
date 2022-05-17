package problems;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class N035Parenthesis {
    
    @Test
    @DisplayName("test01")
    void test01() {
        int solution = new N035Parenthesis().solution("[](){}");
        assertEquals(3, solution);
        
    }
    
    String[] opened = {"(", "{", "["};
    String[] closed = {")", "}", "]"};
    
    public int solution(String s) {
        int count = 0;
        Queue<String> target = new LinkedList<>();
        
        String[] split = s.split("");
        
        // 초기값
        for (int i = 0; i < s.length(); i++) {
            target.offer(split[i]);
        }
        
        // 회전해서 문자열 검사
        for (int i = 0; i < s.length(); i++) {
            rotate(target);
            String[] targetArray = target.toArray(new String[0]);
            System.out.println(Arrays.toString(targetArray));
            if (check(targetArray)) {
                count++;
            }
        }
        
        return count;
    }
    
    private boolean check(String[] target) {
        Stack<String> stack = new Stack<>();
        
        for (int i = 0; i < target.length; i++) { // 하나씩 순회
            String curr = target[i];
    
            // 열린 괄호 -> 담는다
            for (int j = 0; j < 3; j++) {
                if (opened[j].equals(curr)) {
                    stack.push(curr);
                } else if (closed[j].equals(curr)) {
                    if (stack.isEmpty()) {
                        return false;
                    }
                    if (stack.peek().equals(opened[j])) {
                        stack.pop();
                    } else {
                        return false;
                    }
                }
            }
        }
        return stack.isEmpty();
    }
    
    private void rotate(Queue<String> target) {
        target.offer(target.poll());
    }
}

/*
 * 회전 시켰을 때 몇번 올바른 괄호 문자열이 나오는가?
 * 1. 회전
 * 2. 올바른 괄호 판단하기
 *   -> 스택 반복문
 * 열린 괄호 -> 담는다.
 * 닫힌 괄호 -> stack 가장 최근 과 비교해서 짝지어지는지 -> 안되면 x 되면 계속 진행
 * */

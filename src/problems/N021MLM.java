package problems;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class N021MLM {
    
    @Test
    @DisplayName("test01")
    void test01() {
        String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
        String[] referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
        String[] seller = {"young", "john", "tod", "emily", "mary"};
        int[] amount = {12, 4, 2, 5, 10};
    
        int[] result = new N021MLM().solution(enroll, referral, seller, amount);
        int[] answer = {360, 958, 108, 0, 450, 18, 180, 1080};
        assertArrayEquals(answer, result);
    }
    
    @Test
    @DisplayName("test02")
    void test02() {
        String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
        String[] referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
        String[] seller = {"sam", "emily", "jaimie", "edward"};
        int[] amount = {2, 3, 5, 4};
    
        int[] result = new N021MLM().solution(enroll, referral, seller, amount);
        int[] answer = {0, 110, 378, 180, 270, 450, 0, 0};
        assertArrayEquals(answer, result);
    }
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];
        int[] sales = new int[amount.length];
        for (int i = 0; i < amount.length; i++) {
            sales[i] = amount[i] * 100;
        }
    
        HashMap<String, Integer> enrollIndexMap = new HashMap<>();
        for (int i = 0; i < enroll.length; i++) {
            enrollIndexMap.put(enroll[i], i);
        }
        
        for (int i = 0; i < sales.length; i++) {
            int index = enrollIndexMap.get(seller[i]);
            answer[index] += Math.floor(sales[i] * 0.9);
            double profit = getProfit(sales[i]);
            
            while (!referral[index].equals("-")) {
                Integer refIndex = enrollIndexMap.get(referral[index]);
                if (getProfit(profit) < 1) { // 다음 referral 에게 줄 이익이 1이하
                    answer[refIndex] += profit;
                    break;
                } else {
                    answer[refIndex] += profit - getProfit(profit);
                }
                index = refIndex;
                profit = getProfit(profit);
            }
        }
        return answer;
    }
    
    public double getProfit(double sale) {
        double saleFloor = Math.floor(sale * 0.1);
        return saleFloor;
    }
}
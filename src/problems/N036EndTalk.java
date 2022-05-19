package problems;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class N036EndTalk {
    
    @Test
    @DisplayName("test01")
    void test01() {
        String[] words = {"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"};
        int[] solution = new N036EndTalk().solution(3, words);
        assertArrayEquals(new int[]{3,3}, solution);
    
    }
    
    @Test
    void test02() {
        String[] words = {"hello", "observe", "effect", "take", "either", "recognize", "encourage", "ensure", "establish", "hang", "gather", "refer", "reference", "estimate", "executive"};
        int[] solution = new N036EndTalk().solution(5, words);
        assertArrayEquals(new int[]{0, 0}, solution);
    }
    
    @Test
    void test03() {
        String[] words = {"hello", "one", "even", "never", "now", "world", "draw"};
        int[] solution = new N036EndTalk().solution(2, words);
        assertArrayEquals(new int[]{1,3}, solution);
    }
    
    public int[] solution(int n, String[] words) {
        int[] answer = new int[2];
        String prev = words[0];
        HashSet<String> set = new HashSet<>();
        
        int count = 0;
        int person = 0;
        for (int i = 1; i < words.length; i++) {
            set.add(prev);
            String curr = words[i];
            if (set.contains(curr)) {
                count = i / n + 1;
                person = i % n + 1;
                break;
            }
            if (curr.charAt(0) != prev.charAt(prev.length() - 1)) {
                // 누구의, 몇번째인지 계산한다.
                count = i / n + 1;
                person = i % n + 1;
                break;
            }
            prev = words[i];
        }
        answer[0] = person;
        answer[1] = count;
        
        return answer;
    }
}

// 0번 인덱스는 이전 단어가 없다.
// 현재 단어의 첫 == 이전 단어의 마지막?
// 현재 단어의 인덱스 => (몇번째 사람, 몇번째 단어)
// 같은 단어가 나오면 안된다.
// 마지막 단어 다음 인덱스 == 첫번째 인덱스
// 탈락자 없을 시 {0,0}

// 단어 개수 100 이하
// 단어 길이 2 ~ 50
//

package problems;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class N018AEIOU {
    
    @Test
    @DisplayName("test01")
    void test01() {
        String word = "AAAE";
        new N018AEIOU().solution(word);
        
    }
    
    public int solution(String word) {
        AeiouIterator ai = new AeiouIterator();
    
        int index = 1;
        while (!word.equals(ai.next())) {
            index++;
        }
    
        return index;
    }
    
    class AeiouIterator {
        
        private StringBuilder sb = new StringBuilder();
        private StringBuilder aeiou = new StringBuilder("AEIOU");
        
        
        public char nextOf(char c) {
            if (c == 'U')
                return aeiou.charAt(0);
            return aeiou.charAt(aeiou.indexOf(String.valueOf(c)) + 1);
        }
        
        public String next() {
            if (sb.length() < 5) {
                sb.append('A');
            } else {
                // 마지막 것의 다음 문자로 바꾼다.
                // 마지막 문자가 U 이면 하나 더 지우고 바꾼다.
                checkUAndReplace(sb);
            }
            return sb.toString();
        }
        
        public StringBuilder checkUAndReplace(StringBuilder sb) {
            int lastIndex = sb.length() - 1;
            char last = sb.charAt(lastIndex);
            if (last == 'U') {
                sb.deleteCharAt(lastIndex);
                return checkUAndReplace(sb);
            }
            sb.deleteCharAt(lastIndex);
            sb.append(nextOf(last));
            return sb;
        }
    }
}
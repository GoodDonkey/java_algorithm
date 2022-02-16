package problems;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class N007Parenthesis {
    public static void main(String[] args) {
//        new N007Parenthesis().solution(")(())(");
        System.out.println(new N007Parenthesis().solution("()))((()"));
    }

    // ())( /  )( ())(
    public String solution(String v) {
        if (v.equals("")) return "";
        Integer i = getFirstBalancedIndex(v);
        String u = v.substring(0, i);
        String nextV = v.substring(i);
        if (v.startsWith(")")) {
            String reversed = reverseParens(removeSides(u));
            String concat = "(" + solution(nextV) + ")" + reversed;
            return concat;
        }

        String concat = u + solution(nextV);
        return concat;
    }

    private String reverseParens(String str) {
        ArrayList<Integer> indexes = new ArrayList<>();
        String target = str.replaceAll("[(]", ")");
        while (!str.equals(target)) {
            int j = str.indexOf('(');
            indexes.add(j);
            str = str.replaceFirst("[(]", ")");
        }

        String replaced;
        StringBuffer sb = new StringBuffer(str);
        for (int i = 0; i < str.length(); i++) {
            if (indexes.contains(i)) {continue;}
            sb.setCharAt(i, '(');
        }
        replaced = sb.toString();
        return replaced;
    }

    private String removeSides(String s) {
        String sub = s.substring(1, s.length() - 1);
        return sub;
    }

    private Integer getFirstBalancedIndex(String p) {
        Map<Character, Integer> counts = new HashMap<>();
        counts.put('(', 0);
        counts.put(')', 0);
        int cnt = 0;
        do {
            counts.put(p.charAt(cnt), counts.get(p.charAt(cnt)) + 1);
            cnt++;
        } while (!counts.get('(').equals(counts.get(')')) && cnt < p.length());
        return cnt;
    }

    @Test
    public void test() {
    }

    // )(())(
    // u, v 분리하기
    /* ")(())("
    * */
}

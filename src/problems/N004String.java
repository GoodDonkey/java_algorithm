package problems;

public class N004String {

    public static void main(String[] args) {
        new N004String().solution("aabbaccc");
    }

    public int solution(String s) {
        if (s.length() == 1) return 1;
        int answer = 1001;
        // 절반 길이를 시작으로 반복 문자열을 찾아나간다. 길이는 1씩 줄여나간다.
        for (int length = s.length() / 2; length > 0; length--) {
            System.out.println("---length = " + length);
            String current, next = "", result = "";
            int repeat = 1;
            // 몇회 반복? 현 length가 s의 길이를 넘어가지 않도록 count를 올린다.
            for (int count = 0; count <= s.length() / length; count++) {
                current = next;
                System.out.println("current = " + current);
                System.out.println("next = " + next);
                System.out.println("count = " + count);
                int start = count * length; // 문자열이 시작하는 위치
                int end = Math.min(length * (count + 1), s.length()); // 문자열 끝나는 위치(반복횟수 만큼)
                next = s.substring(start, end); // 반복 단위를 자른다.
                System.out.println("current = " + current);
                System.out.println("next = " + next);

                // 현재 확인하는 문자열이 다음 문자열과 같으면 반복횟수 숫자를 증가시킨다.
                if (current.equals(next)) {
                    repeat++;
                    System.out.println("repeat = " + repeat);
                } else {
                    // 반복 횟수를 붙인 문자열을 만든다. repeat를 다시 1로 만들어 둔다.
                    result += (getRepeatCountAsString(repeat) + current);
                    repeat = 1;
                }
                System.out.println("result = " + result);
            }
            result += (getRepeatCountAsString(repeat) + next);
            answer = Math.min(answer, result.length());
            System.out.println("result = " + result);
            System.out.println("answer = " + answer);
        }
        System.out.println("answer = " + answer);
        return answer;
    }

    private String getRepeatCountAsString(int hit) {
        return hit > 1 ? String.valueOf(hit) : "";
    }
}

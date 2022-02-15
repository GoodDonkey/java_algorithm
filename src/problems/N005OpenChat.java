package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class N005OpenChat {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new N005OpenChat().solution(
                new String[]{"Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo", "Change uid4567 Ryan"})));
    }

    public String[] solution(String[] record) {
        String[] answer = {};
        Map<String, String> nameMap = new HashMap<>();
        ArrayList<String> left = new ArrayList<>();
        int length = record.length;
        for (String r : record) {
            if (r.startsWith("Leave")) {
                continue;
            }
            String[] split = r.split("\\s");
            String uid = split[1];
            String name = split[2];
            if (r.startsWith("Enter")) {
                nameMap.put(uid, name);
            }
            if (r.startsWith("Change")) {
                nameMap.put(uid, name);
                length -= 1;
            }
        }
        String[] result = new String[length];
        int i = 0;
        for (String r : record) {
            if (r.startsWith("Change")) {
                continue;
            }
            String[] split = r.split("\\s");
            String uid = split[1];

            if (r.startsWith("Enter")) {
                result[i] = nameMap.get(uid) + "님이 들어왔습니다.";
            }
            if (r.startsWith("Leave")) {
                result[i] = nameMap.get(uid) + "님이 나갔습니다.";
            }
            i++;
        }
        answer = result;
        return answer;
    }

    /*
    * 들어온 사람 기준 uid: 닉네임 작성 nameMap
    * 나간 사람 기준 다시 들어오면 uid 수정
    * change 가 있으면 uid 수정
    * 최종 nameMap 을 기준으로 result 를 작성한다.
    *
    * result 작성법
    * enter, leave 순서에 맞게 작성한다.
    * 이름은 uid 기준으로 일치시킨다.
    * result 는 String[] */
}

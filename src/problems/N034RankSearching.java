package problems;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.Inet4Address;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class N034RankSearching {
    
    @Test
    @DisplayName("test01")
    void test01() {
        new N034RankSearching().solution(new String[]{"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"},
                                         new String[]{"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"});
        
    }
    static HashMap<String, ArrayList<Integer>> infoMap;
    
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        // 각 경우마다 해시맵으로 키를 만들어둠.
        // 그다음 키에 맞게 점수를 저장한다.
        // 그다음 점수 기준으로 쿼리해서 뽑아낸다.
        // key 는 탐색시간 없고, 점수 기준 정렬 + 이진 탐색 -> 매우 빠른 탐색
        String[] lang = {"cpp", "java", "python", "-"};
        String[] part = {"backend", "frontend", "-"};
        String[] career = {"junior", "senior", "-"};
        String[] food = {"chicken", "pizza", "-"};
        infoMap = new HashMap<>();
        for (String l : lang) {
            for (String p : part) {
                for (String s : career) {
                    for (String f : food) {
                        infoMap.put(l + p + s + f, new ArrayList<>());
                    }
                }
            }
        }
    
        for (String i : info) {
            String[] alter = new String[4];
            String[] infoStrings = i.split(" ");
            int score = Integer.parseInt(infoStrings[4]);
            dfs(0, alter, infoStrings, score);
        }
    
        for (String s : infoMap.keySet()) {
            infoMap.get(s).sort(Comparator.comparing(k -> k));
        }
    
        for (int i = 0; i < query.length; i++) {
            String q = query[i];
            String[] queryStrings = q.split(" and | ");
            StringBuilder sb = new StringBuilder();
            sb.append(queryStrings[0]);
            sb.append(queryStrings[1]);
            sb.append(queryStrings[2]);
            sb.append(queryStrings[3]);
            ArrayList<Integer> scores = infoMap.get(sb.toString());
            int target = Integer.parseInt(queryStrings[4]);
            int left = 0;
            int right = scores.size() - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (scores.get(mid) < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            answer[i] = scores.size() - left;
    
        }
        return answer;
    }
    
    private void dfs(int level, String[] alter, String[] infoStrings, int score) {
        if (level == 4) { // 다 채워졌으면
            String string = String.join("", alter);
            infoMap.get(string).add(score);
        } else {
            alter[level] = infoStrings[level]; // 주어진대로
            dfs(level + 1, alter, infoStrings, score);
            alter[level] = "-"; // -를 포함하는 키에도 넣음
            dfs(level + 1, alter, infoStrings, score);
        }
    }
}

/*
* 쿼리 내용 -> info 검색 : 효율성 fail
*
*
* */
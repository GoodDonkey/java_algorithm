package problems;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class N015GemShopping {

    @Test
    @DisplayName("test01")
    void test01() {
        new N015GemShopping().solution(new String[]{"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"});
    }
    @Test
    @DisplayName("test02")
    void test02() {
        new N015GemShopping().solution(new String[]{"AA", "AB", "AC", "AA", "AC"});
    }
    @Test
    @DisplayName("test03")
    void test03() {
        new N015GemShopping().solution(new String[]{"XYZ", "XYZ", "XYZ"});
    }
    @Test
    @DisplayName("test04")
    void test04() {
        new N015GemShopping().solution(new String[]{"ZZZ", "YYY", "NNNN", "YYY", "BBB"});
    }
    
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        LinkedList<String> gemsList = new LinkedList<>(Arrays.asList(gems));
        List<Search> targets = new ArrayList<>();
        Set<String> uniques = new HashSet<>(gemsList);
        for (String gem : uniques) {
            targets.add(new Search(gem, gemsList.indexOf(gem), "back"));
            targets.add(new Search(gem, gemsList.lastIndexOf(gem), "front"));
        }
    
        Map<Search, Integer> results = new HashMap<>();
        while (!targets.isEmpty()) {
            HashSet<Object> gemSet = new HashSet<>();
            Search search = targets.get(0);
            String direction = search.direction;
            int searchingIndex = search.index;
            results.put(search, 0);
            
            while (searchingIndex > 0 && searchingIndex < gemsList.size() - 1 && !gemSet.containsAll(uniques)) {
                if (direction.equals("back")) {
                    gemSet.add(gemsList.get(searchingIndex++));
                } else {
                    gemSet.add(gemsList.get(searchingIndex--));
                }
            }

            results.put(search, searchingIndex);
            
            
            targets.remove(0);
            int finalDestination = searchingIndex;
            targets.removeIf(t -> t.gemName.equals(gemsList.get(finalDestination)) &&
                                  t.direction.equals(opposite(direction)) &&
                                  t.index == finalDestination);
        }
        System.out.println(results);
        
        answer[0] = uniques.size();
        answer[1] = gems.length;
        for (Search key : results.keySet()) {
            int startIndex = key.index;
            int endIndex = results.get(key);
            if (key.direction.equals("front")) {
                startIndex = results.get(key);
                endIndex = key.index;
            }
            int length = Math.abs(startIndex - endIndex);
            
            if (Math.abs(answer[1]-answer[0]) > length && answer[0] > startIndex) {
                answer[0] = startIndex + 1;
                answer[1] = endIndex + 1;
            }
        }
        System.out.println(Arrays.toString(answer));
        
    
        return answer;
    }
    
    private String opposite(String direction) {
        if (direction.equals("back")) {
            return "front";
        } else {
            return "back";
        }
    }
    
    class Search {
        String gemName;
        int index;
        String direction;
    
        public Search(String gemName, int index, String direction) {
            this.gemName = gemName;
            this.index = index;
            this.direction = direction;
        }
    
        @Override
        public String toString() {
            return "Search{" + "gemName='" + gemName + '\'' + ", index=" + index + ", direction='" + direction + '\'' + '}';
        }
    }

    public int[] solution2(String[] gems) {
        int[] answer = new int[2];
        LinkedList<String> gemsList = new LinkedList<>(Arrays.asList(gems));
        // 넣을때 순서 보장
        LinkedHashSet<String> uniques = new LinkedHashSet<>(gemsList);
        LinkedHashMap<String, Integer> sizeMap = new LinkedHashMap<>();

        for (String gem : uniques) {
            int start = gemsList.indexOf(gem);
            LinkedHashSet<String> uniqueFront = (LinkedHashSet<String>) uniques.clone();
            int frontIndex;
            // 앞으로 탐색
            if (start > uniques.size() - 1) {
                frontIndex = start; // 시작 인덱스
                for (int i = frontIndex; i >= 0; i--) {
                    if (uniqueFront.contains(gemsList.get(i))) {
                        uniqueFront.remove(gemsList.get(i)); // 없으면 넘어감?
                        frontIndex = i;
                    }
                    if (uniqueFront.isEmpty()) {
                        sizeMap.put(gem + "_front", start - frontIndex);
                        break;
                    }
                }
            }

            LinkedHashSet<String> uniqueBack = (LinkedHashSet<String>) uniques.clone();
            int backIndex;
            // 뒤로 탐색
            if (uniques.size() < gemsList.size() - start) {
                backIndex = start; // 시작 인덱스
                for (int i = backIndex; i <= gemsList.size() - 1; i++) {
                    if (uniqueBack.contains(gemsList.get(i))) {
                        uniqueBack.remove(gemsList.get(i)); // 없으면 넘어감?
                        backIndex = i;
                    }
                    if (uniqueBack.isEmpty()) {
                        sizeMap.put(gem + "_back", backIndex - start);
                        break;
                    }
                }
            }
        }
        System.out.println("sizeMap = " + sizeMap);
        // value 가 가장 작은 것을 뽑아서 시작 인덱스를 계산한다.
        // 여러개일 수도 있음.
        // map에 저장할 때 순서 보장하므로 같은 값이 있으면 먼저 저장된 값이 나올 듯
        String min = Collections.min(sizeMap.keySet(), Comparator.comparing(sizeMap::get));
        String[] split = min.split("_");

        int startIndex = gemsList.indexOf(split[0]);
        int endIndex;
        if (split[1].equals("back")) {
            endIndex = startIndex + sizeMap.get(min);
            answer[0] = startIndex+1;
            answer[1] = endIndex+1;
        } else {
            endIndex = startIndex - sizeMap.get(min);
            answer[0] = endIndex+1;
            answer[1] = startIndex+1;
        }
        System.out.println(Arrays.toString(answer));
        return answer;
    }

    // gems : 1 ~ 100,000
    // 고르는 보석 수는 가장 적게
    // 시작 보석 인덱스는 가장 작게(반환은 1부터 셈)

    // 어떤 보석의 시작 인덱스에서 앞으로 혹은 뒤로 모든 보석을 포함할 때까지 탐색했을 때 길이가 가장 짧은 것은?
    // 앞쪽으로 탐색을 시작할 인덱스는 set의 길이보다는 커야한다.
    // 뒤쪽으로 탐색을 시작할 인덱스는 전체 - 시작 인덱스 > set길이
    // a b e c a b e c d b c a e e

    // 뒤로 먼저 탐색하고, 끝나는 점이 그 원소의 시작값이면 같은 길이의 앞으로는 탐색할 필요가 없음.
    //
}
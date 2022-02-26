package problems;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class N016CandidateKey {
    
    @Test
    @DisplayName("test01")
    void test01() {
        int solution = new N016CandidateKey().solution(new String[][]{{"100", "ryan", "music", "2"}, {"200", "apeach", "math", "2"}, {"300", "tube", "computer", "3"}, {"400", "con", "computer", "4"}, {"500", "muzi", "music", "3"}, {"600", "apeach", "music", "2"}});
    
        assertEquals(2, solution);
    }
    
    @Test
    @DisplayName("test02")
    void test02() {
        String[][] relation = {{"a", "1", "aaa", "c", "ng"}, {"a", "1", "bbb", "e", "g"}, {"c", "1", "aaa", "d", "ng"}, {"d", "2", "bbb", "d", "ng"}};
        int solution = new N016CandidateKey().solution(relation);
        assertEquals(5, solution);
    
    }
    
    @Test
    @DisplayName("test03")
    void test03() {
        String[][] relation = {{"a","1","aaa","c","ng"},{"b","1","bbb","c","g"},{"c","1","aaa","d","ng"},{"d","2","bbb","d","ng"}};
        int solution = new N016CandidateKey().solution(relation);
        assertEquals(3, solution);
    }
    public int solution(String[][] relation) {
        int answer = 0;
        Set<Integer> attributes = new HashSet<>();
        for (int i = 0; i < relation[0].length; i++) {
            boolean anyEquals = false;
//            if (attributes.contains(i)) {
//                System.out.println("skipping " + i);
//                continue;
//            }
            HashSet<Integer> leastCheck = new HashSet<>();
            HashSet<Integer> excludeCheck = new HashSet<>();
            for (int j = 0; j < relation.length; j++) {
                for (int k = j + 1; k < relation.length; k++) {
                    if (relation[j][i].equals(relation[k][i])) {
                        anyEquals = true;
                        int next = i+1;
                        for (Integer integer : leastCheck) {
                            if (relation[j][integer].equals(relation[k][integer])) {
                                excludeCheck.add(integer);
                            }
                        }
                        System.out.println("leastCheck = " + leastCheck);
                        System.out.println("excludeCheck = " + excludeCheck);
                        if (excludeCheck.size() != leastCheck.size()) {
                            leastCheck.removeAll(excludeCheck);
                            continue;
                        }
                        while (next < relation[0].length) {
                            if (relation[j][next].equals(relation[k][next])) {
                                next++;
                            } else if (!excludeCheck.contains(next)){
                                leastCheck.add(i);
                                leastCheck.add(next);
                                next++;
                            } else {
                                next++;
                            }
                        }
                    }
                    boolean condition = j == relation.length - 2 && k == relation.length - 1 && !anyEquals;
                    System.out.println("condition = " + condition);
                    if (condition) { // 다 돌았으면
                        System.out.println("leastCheck = " + leastCheck);
                        attributes.addAll(leastCheck);
                        answer++;
                    }
                }
            }
        }
        return answer;
    }
        
        
        
        public int solution3(String[][] relation) {
        int answer = 0;
        int numberOfAttributes = relation[0].length;
        Set<Integer> attributes = IntStream.range(0, numberOfAttributes).boxed().collect(Collectors.toSet());
    
        ArrayList<List<String>> matrix = new ArrayList<>();
        for (String[] each : relation) {
            matrix.add(new ArrayList<>(Arrays.asList(each)));
        }
        System.out.println("matrix = " + matrix);
    
        HashMap<String, Integer> howMany = new HashMap<>();
    
        for (int attribute = 0; attribute < numberOfAttributes; attribute++) {
            HashSet<String> temp = new HashSet<>();
            for (int tupleIndex = 0; tupleIndex < relation.length; tupleIndex++) {
                String s = relation[tupleIndex][attribute];
                temp.add(s);
                howMany.put(s, howMany.getOrDefault(s, 0) + 1);
            }
            System.out.println("temp = " + temp);
            if (temp.size() == relation.length) {
                answer++;
                attributes.remove(attribute);
            }
        }
        
        // 두개 이상인 값의 인덱스를 구해서 다른 속성의 같은 인덱스를 비교해본다.
        // 다르면 answer++
        
//        for (Integer attribute : attributes) {
//            for (int tupleIndex = 0; tupleIndex < relation.length; tupleIndex++) {
//                for (int another = tupleIndex + 1; another < relation.length; another++) {
//                    if (relation[tupleIndex][attribute] == relation[another][attribute]) {
//                        int next = attribute + 1;
//                        while (true) {
//                            if (relation[tupleIndex][next] != relation[another][next]) {
//                                answer++;
//                                attributes.remove(attribute);
//                                attributes.remove(next);
//                            }
//                            next++;
//                        }
//                    }
//                }
//            }
//        }
        
        return answer;
    }
    
    
    public int solution2(String[][] relation) {
        int answer = 0;
        int numberOfAttributes = relation[0].length;
        Set<Integer> attributes = IntStream.range(0, numberOfAttributes).boxed().collect(Collectors.toSet());
        HashMap<Integer, Map<String, Integer>> attributeValueMap = new HashMap<>();
        for (int attribute = 0; attribute < numberOfAttributes; attribute++) { // attribute 하나씩
            Map<String, Integer> valueMap = new HashMap<>();
            for (String[] tuple : relation) { // 튜플 하나씩
                String value = tuple[attribute];
                valueMap.put(value, valueMap.getOrDefault(value, 0) + 1);
            }
            attributeValueMap.put(attribute, valueMap);
            System.out.println("valueMap = " + valueMap);
            // 하나의 attribute가 후보키라면 제거한다.
            if (valueMap.size() == numberOfAttributes) {
                attributes.remove(attribute);
                attributeValueMap.remove(attribute);
            }
        }
        System.out.println("attributeValueMap = " + attributeValueMap);
        // 2개 이상의 attribute로 후보키를 생성한다.
        for (Integer attribute : attributes) {
            Map<String, Integer> valueMap = attributeValueMap.get(attribute);
            while (valueMap.size() != numberOfAttributes) {
                for (String k : valueMap.keySet()) {
                    if (valueMap.get(k) > 1) {
                        String[] strings = relation[attribute + 1];
                    }
                }
            }
        }
        
        
        
        return answer;
    }
    
    // 1개의 attribute 씩 검사해본다.
    // 개수를 늘려가며 검사한다.
    // 해당 attribute나 그 조합에서 후보키가 나오면 각 attribute에 대한 검사는 더 진행하지 않는다.
    
    // 검사: 원소의 개수가 튜플의 개수와 같거나 큰지 검사.
    // 2개 이상의 조합을 검사할 때에는
}
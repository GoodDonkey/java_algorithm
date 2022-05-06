package problems;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
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
    
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        Repository repository = new Repository();
    
        for (int i = 0; i < info.length; i++) {
            repository.add(new InfoQuery(info[i]));
        }
        repository.sort();
    
        LinkedList<InfoQuery> queries = new LinkedList<>();
        for (int i = 0; i < query.length; i++) {
            queries.add(new InfoQuery(query[i]));
        }
    
        for (int i = 0; i < queries.size(); i++) {
            InfoQuery q = queries.get(i);
            answer[i] = repository.countByQuery(q);
        }
        System.out.println(Arrays.toString(answer));
    
        return answer;
    }
    
    class Repository {
        private LinkedList<InfoQuery> candidates;
        
        public Repository() {
            this.candidates = new LinkedList<>();
        }
        
        public void add(InfoQuery info) {
            candidates.add(info);
        }
        
        public void sort() {
            candidates.sort(Comparator.comparing(k -> k.score));
        }
        
        public int countByQuery(InfoQuery query) {
            LinkedList<InfoQuery> result = new LinkedList<>();
            result.addAll(candidates.stream()
                                  .filter(can -> can.score >= query.score)
                                  .filter(can -> query.lang.equals("-") || can.lang.equals(query.lang))
                                  .filter(can -> query.part.equals("-") || can.part.equals(query.part))
                                  .filter(can -> query.career.equals("-") || can.career.equals(query.career))
                                  .filter(can -> query.food.equals("-") || can.food.equals(query.food))
                                  .collect(Collectors.toList()));
            
            return result.size();
        }
    }
    
    class InfoQuery {
        private String lang;
        private String part;
        private String career;
        private String food;
        private int score;
        
        InfoQuery(String info) {
            String[] infoString = info.split("\\s(and)?\\s?");
            this.lang = infoString[0];
            this.part = infoString[1];
            this.career = infoString[2];
            this.food = infoString[3];
            this.score = Integer.parseInt(infoString[4]);
        }
    }
}

/*
* 쿼리 내용 -> info 검색 : 효율성 fail
*
*
* */
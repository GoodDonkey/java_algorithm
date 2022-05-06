package problems;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class N034RankSearching {
    
    @Test
    @DisplayName("test01")
    void test01() {
        new N034RankSearching().solution(new String[]{"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"},
                                         new String[]{"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"});
        
    }
    static String[] lang;
    static String[] job;
    static String[] career;
    static String[] food;
    static int[] score;
    static int infoLength;
    
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        lang = new String[info.length];
        job = new String[info.length];
        career = new String[info.length];
        food = new String[info.length];
        score = new int[info.length];
        infoLength = info.length;
    
        for (int i = 0; i < info.length; i++) {
            String[] infoString = info[i].split("\\s");
            lang[i] = infoString[0];
            job[i] = infoString[1];
            career[i] = infoString[2];
            food[i] = infoString[3];
            score[i] = Integer.parseInt(infoString[4]);
        }
    
        for (int i = 0; i < query.length; i++) {
            String[] queryString = query[i].split("\\s(and)?\\s?");
            answer[i] = doQuery(queryString);
        }
        return answer;
    }
    
    private int doQuery(String[] queryString) {
        int howMany = 0;
        for (int i = 0; i < infoLength; i++) {
            if (queryString[0].equals("-") || lang[i].equals(queryString[0])) {
                if (queryString[1].equals("-") || job[i].equals(queryString[1])) {
                    if (queryString[2].equals("-") || career[i].equals(queryString[2])) {
                        if (queryString[3].equals("-") || food[i].equals(queryString[3])) {
                            if (Integer.parseInt(queryString[4]) <= score[i]) {
                                howMany++;
                            }
                        }
                    }
                }
            }
        }
        return howMany;
    }
}

/*
* 쿼리 내용 -> info 검색 : 효율성 fail
*
*
* */
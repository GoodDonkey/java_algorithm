package problems;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class N009Parking {

    public int[] solution(int[] fees, String[] records) {
        int[] answer = {};

        Map<Integer, Integer> parkingDuration = new LinkedHashMap<>();
        List<Record> recordList = new ArrayList<>();

        for (String record : records) {
            Record recordObj = new Record(record);
            System.out.println(recordObj);
            recordList.add(recordObj);
        }
        System.out.println(recordList);

        Collections.sort(recordList);
        System.out.println(recordList);

        for (int i = 0; i < recordList.size(); i++) {
            Record curr = recordList.get(i);
            if (curr.inOut.equals("IN")) {
                if (i == recordList.size() - 1) {
                    if (parkingDuration.get(curr.carNumber) == null) {
                        parkingDuration.put(curr.carNumber, Record.getMinutes(new String[]{"23", "59"}) - curr.time);
                    } else {
                        parkingDuration.put(curr.carNumber,
                                            parkingDuration.get(curr.carNumber) + (Record.getMinutes(new String[]{"23", "59"}) - curr.time));
                    }
                    continue;
                }
                Record next = recordList.get(i + 1);
                if (next.inOut.equals("OUT") && next.carNumber.equals(curr.carNumber)) {
                    if (parkingDuration.get(curr.carNumber) == null) {
                        parkingDuration.put(curr.carNumber, next.time - curr.time);
                    } else {
                        parkingDuration.put(curr.carNumber,
                                            parkingDuration.get(curr.carNumber) + (next.time - curr.time));
                    }
                } else if (!next.inOut.equals("OUT")) {
                    if (parkingDuration.get(curr.carNumber) == null) {
                        parkingDuration.put(curr.carNumber, Record.getMinutes(new String[]{"23", "59"}) - curr.time);
                    } else {
                        parkingDuration.put(curr.carNumber,
                                            parkingDuration.get(curr.carNumber) + (Record.getMinutes(new String[]{"23", "59"}) - curr.time));

                    }
                }
            }
        }
        System.out.println(parkingDuration);

        LinkedList<Integer> results = new LinkedList<>();
        for (Integer carNumber : parkingDuration.keySet()) {
            Integer duration = parkingDuration.get(carNumber);
            if (duration < fees[0]) {
                results.add(fees[1]);
            } else {
                float overtime = duration - fees[0];
                int overFee = fees[3] * ((int) Math.ceil(overtime / fees[2]));
                results.add(fees[1] + overFee);
            }
        }
        answer = results.stream().mapToInt(n -> n).toArray();
        System.out.println(Arrays.toString(answer));
        return answer;
    }

    static class Record implements Comparable<Record> {

        private Integer time;
        private Integer carNumber;
        private String inOut;

        public Record(String record) {
            String[] recordSplit = record.split("\\s");
            this.time = toMinute(recordSplit[0]);
            this.carNumber = toCarNumber(recordSplit[1]);
            this.inOut = toInOut(recordSplit[2]);
        }

        private static int getMinutes(String[] timeSplit) {
            return (Integer.parseInt(timeSplit[0]) * 60) + Integer.parseInt(timeSplit[1]);
        }

        private String toInOut(String s) {
            return s;
        }

        private Integer toCarNumber(String s) {
            return Integer.parseInt(s);
        }

        private Integer toMinute(String s) {
            String[] timeSplit = s.split("[:]");
            Integer minutes = getMinutes(timeSplit);
            return minutes;
        }

        @Override
        public String toString() {
            return "Record{" + "time=" + time + ", carNumber=" + carNumber + ", inOut='" + inOut + '\'' + '}';
        }


        @Override
        public int compareTo(Record o) {
            return this.carNumber.compareTo(o.carNumber);
        }
    }

    @Test
    @DisplayName("test")
    void test() {
        int[] result = new N009Parking().solution(new int[]{180, 5000, 10, 600},
                                                  new String[]{"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"});

        int[] result2 = new N009Parking().solution(new int[]{120, 0, 60, 591},
                                                   new String[]{"16:00 3961 IN", "16:00 0202 IN", "18:00 3961 OUT", "18:00 0202 OUT", "23:58 3961 IN"});

        int[] result3 = new N009Parking().solution(new int[]{1, 461, 1, 10}, new String[]{"00:00 1234 IN"});
        assertArrayEquals(new int[]{14600, 34400, 5000}, result);
        assertArrayEquals(new int[]{0, 591}, result2);
        assertArrayEquals(new int[]{14841}, result3);
    }

    /* 차량번호가 작은 순서대로 주차 요금 반환하기
     * fees 는 길이가 4인 배열
     * resords는 1이상 1000 이하
     *
     * IN 기록과 OUT 기록을 대조해서 몇시간이나 있었나 계산
     * 같은 차가 다시 들어올 수도 있다.
     * 나가지 않았으면 23:59 에 나간 것으로 간주
     * */
}

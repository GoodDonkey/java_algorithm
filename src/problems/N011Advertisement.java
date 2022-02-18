package problems;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

public class N011Advertisement {

    @Test
    @DisplayName("test")
    void test() {
        String result = new N011Advertisement().solution("02:03:55",
                                                           "00:14:15",
                                                           new String[]{"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"});
        Assertions.assertEquals("01:30:59", result);
    }

    @Test
    @DisplayName("test2")
    void test2() {
        String result2 = new N011Advertisement().solution("99:59:59",
                                                          "25:00:00",
                                                          new String[]{"69:59:59-89:59:59", "01:00:00-21:00:00", "79:59:59-99:59:59", "11:00:00-31:00:00"});
        Assertions.assertEquals("01:00:00", result2);
    }

    public String solution(String play_time, String adv_time, String[] logs) {

        List<Duration> logList = new ArrayList<>();
        for (String log : logs) {
            logList.add(new Duration(log));
        }
        Collections.sort(logList);

        Map<Duration, Integer> candidates = new TreeMap<>();
        candidates.put(new Duration("00:00:00", adv_time), 0);
        for (Duration log : logList) {
            candidates.put(new Duration(log.startTime.asTimeString(), adv_time), 0);
        }

        for (Duration candidate : candidates.keySet()) {
            if (!candidate.endTime.isEarlierThan(new Time(play_time)))
                continue;
            for (Duration log : logList) {
                Integer coverage = Math.abs(candidate.getTimeCoverageWith(log));
                candidates.compute(candidate, ((key, value) -> value + coverage));
            }
        }

        Duration max = Collections.max(candidates.keySet(), Comparator.comparing(candidates::get));


        return max.startTime.asTimeString();
    }

    class Duration implements Comparable<Duration> {
        public Time startTime;
        public Time endTime;

        public Duration(String s) {
            setTimes(s);
        }

        public Duration(String startTime, String duration) {
            this.startTime = new Time(startTime);
            this.endTime = new Time(startTime).add(new Time(duration));
        }


        private void setTimes(String s) {
            String[] split = s.split("-");
            this.startTime = new Time(split[0]);
            this.endTime = new Time(split[1]);
        }

        public Integer getTimeCoverageWith(Duration other) {

            if (hasNoCoverage(other)) {
                return 0;
            }

            if (this.startTime.isEarlierThan(other.startTime)) {
                if (this.endTime.isEarlierThan(other.endTime)) {
                    return this.endTime.time - other.startTime.time;
                } else {
                    return other.endTime.time - other.startTime.time;
                }
            }

            if (other.startTime.isEarlierThan(this.startTime)) {
                if (other.endTime.isEarlierThan(this.endTime)) {
                    return other.endTime.time - this.startTime.time;
                } else {
                    return this.endTime.time - this.startTime.time;
                }
            }
            return 0;
        }

        private boolean hasNoCoverage(Duration other) {
            return this.endTime.isEarlierThan(other.startTime) || other.endTime.isEarlierThan(this.startTime);
        }

        @Override
        public String toString() {
            return "Duration{" + "startTime=" + startTime + ", endTime=" + endTime + '}'+"\n";
        }

        @Override
        public int compareTo(Duration other) {
            return this.startTime.compareTo(other.startTime);
        }
    }

    class Time implements Comparable<Time>{

        public Integer time;

        public Time(String time) {
            this.time = parseTime(time);
        }

        private Integer parseTime(String time) {
            String[] split = time.split(":");
            Integer hour = Integer.parseInt(split[0]) * 60 * 60;
            Integer min = Integer.parseInt(split[1]) * 60;
            Integer sec = Integer.parseInt(split[2]);
            return hour + min + sec;
        }

        @Override
        public int compareTo(Time other) {
            return this.time.compareTo(other.time);
        }

        public boolean isEarlierThan(Time other) {
            return this.time <= other.time;
        }

        public Time add(Time other) {
            this.time = this.time + other.time;
            return this;
        }

        public String asTimeString() {
            int hour = this.time / (60 * 60);
            int min = (this.time - hour * (60 * 60)) / 60;
            int sec = (this.time - hour * (60 * 60)) % 60;
            String timeString = String.format("%02d:%02d:%02d", hour, min, sec);
            return timeString;
        }

        @Override
        public String toString() {
            return "Time{" + "time=" + this.asTimeString() + '}';
        }
    }

    /* 누적 광고 재생 시간이 가장 크게되는 광고 시작 시각은?
    * */
}

package problems;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

public class N010Matrix {

    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = {};

        Numbers numbers = new Numbers(rows, columns);

        answer = numbers.batchRelocate(queries);
        return answer;
    }

    class Numbers {

        private List<Number> numbers = new ArrayList<>();

        public Numbers(int rows, int columns) {
            makeNumbers(rows, columns);
        }

        private void makeNumbers(int rows, int columns) {
            Integer number = 0;
            for (int i = 1; i <= rows; i++) {
                for (int j = 1; j <= columns; j++) {
                    number++;
                    numbers.add(new Number(number, i, j));
                }
            }
        }

        public int[] batchRelocate(int[][] queries) {
            int[] minArray = new int[queries.length];
            int n = 0;
            for (int[] query : queries) {
                Optional<Number> min = numbers.stream()
                        .filter(number -> number.relocate(query))
                        .min(Comparator.comparing(obj -> obj.number));
                minArray[n] = min.isPresent() ? min.get().number : -1;
                n++;
            }
            return minArray;
        }

        @Override
        public String toString() {
            return "Numbers{" + "numbers=" + numbers + '}';
        }
    }

    class Number {
        final Integer number;
        Integer row;
        Integer col;

        public Number(Integer number, Integer row, Integer col) {
            this.number = number;
            this.row = row;
            this.col = col;
        }

        public boolean relocate(int[] query) {
            if (this.col == query[1] && this.row > query[0] && this.row <= query[2]) {
                this.row--;
                return true;
            }
            if (this.col < query[3] && this.col >= query[1] && this.row == query[0]) {
                this.col++;
                return true;
            }
            if (this.col == query[3] && this.row < query[2] && this.row >= query[0]) {
                this.row++;
                return true;
            }
            if (this.col > query[1] && this.col <= query[3] && this.row == query[2]) {
                this.col--;
                return true;
            }
            return false;
        }

        @Override
        public String toString() {
            return "Number{" + "number=" + number + ", row=" + row + ", col=" + col + '}';
        }
    }

    @Test
    @DisplayName("test")
    void test() {
        int[] result = new N010Matrix().solution(6, 6, new int[][]{{2, 2, 5, 4}, {3, 3, 6, 6}, {5, 1, 6, 3}});
        int[] result2 = new N010Matrix().solution(3, 3, new int[][]{{1,1,2,2},{1,2,2,3},{2,1,3,2},{2,2,3,3}});

        Assertions.assertArrayEquals(new int[]{8, 10, 25}, result);
        Assertions.assertArrayEquals(new int[]{1,1,5,3}, result2);

    }
}

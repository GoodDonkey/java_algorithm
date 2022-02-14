package problems;

import java.util.LinkedList;
import java.util.Queue;

public class N001Printer {

    public static void main(String[] args) {
        System.out.println(new N001Printer().solution(new int[]{2, 1, 3, 2}, 2));
    }

    class Task {
        int location;
        int priority;
        public Task(int location, int priority) {
            this.location = location;
            this.priority = priority;
        }
    }
    public int solution(int[] priorities, int location) {
        int answer = 0;
        Queue<Task> queue = new LinkedList<>();
        for (int i = 0; i < priorities.length; i++) {
            queue.add(new Task(i, priorities[i]));
        }

        int now=0;

        while (!queue.isEmpty()) {
            Task curr = queue.poll();
            boolean flag = false;

            for (Task task : queue) {
                if (task.priority > curr.priority) {
                    flag=true;
                }
            }
            if (flag) {
                queue.add(curr);
            } else {
                now++;
                if (curr.location == location) {
                    answer = now;
                    break;
                }
            }
        }

        return answer;
    }
}

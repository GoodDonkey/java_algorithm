package basic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PQ {

    public static void main(String[] args) {
        var pq = new PriorityQueue<>();

        pq.offer(3);
        pq.offer(1);
        pq.offer(5);
        pq.offer(2);
        pq.offer(4);

        System.out.println(pq);
        Object obj = null;

        // 순서가 맞춰져서 뽑힌다.
        while ((obj = pq.poll()) != null) {
            System.out.println(obj);
        }
    }

    @Test
    @DisplayName("pq poll 과 remove 거의 비슷함. remove는 원소 없으면 오류를 밷음.")
    void pq() {
        PriorityQueue<Object> pq = new PriorityQueue<>();
        pq.offer(3);
        pq.offer(1);
        pq.offer(2);
        Object remove = pq.remove();

        PriorityQueue<Object> pq2 = new PriorityQueue<>();
        pq2.offer(3);
        pq2.offer(1);
        pq2.offer(2);
        Object poll = pq2.poll();

        System.out.println(remove);
        System.out.println(poll);

        assertEquals(remove, poll);

    }

    @Test
    @DisplayName("addoffer 다른점은? -> 같다. add는 큐가 꽉차있으면 IllegalStateException 밷는다고함")
    void addoffer() {
        PriorityQueue<Object> pq = new PriorityQueue<>();
        pq.offer(3);
        pq.offer(1);
        pq.offer(2);

        PriorityQueue<Object> pq2 = new PriorityQueue<>();
        pq2.add(3);
        pq2.add(1);
        pq2.add(2);

        System.out.println(pq);
        System.out.println(pq2);

        boolean offer = pq.offer(4);
        boolean add = pq2.add(4);
        System.out.println(offer);
        System.out.println(add);

        System.out.println(pq);
        System.out.println(pq2);
    }
}

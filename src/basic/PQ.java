package basic;

public class PQ {

    public static void main(String[] args) {
        var pq = new java.util.PriorityQueue<>();

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
}

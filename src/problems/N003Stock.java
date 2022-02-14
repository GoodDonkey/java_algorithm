package problems;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class N003Stock {

    public static void main(String[] args) {
//        new N003Stock().solution(new int[]{1, 2, 3, 2, 3});
        new N003Stock().solution(new int[]{1, 2, 3, 2, 3, 1});

    }

    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];

        List<Price> priceList = new LinkedList<>();
        for (int i = 0; i < prices.length; i++) {
            priceList.add(new Price(i, prices[i]));
        }

        for (Price price : priceList) {
            Queue<Price> others = priceList.stream()
                    .filter(p -> p.index > price.index)
                    .collect(Collectors.toCollection(LinkedList::new));
            if (others.isEmpty())
                break;
            while (!others.isEmpty()) {
                Price curr = others.poll();
                if (curr.price >= price.price)
                    price.retained++;
                if (curr.price < price.price){
                    price.retained++;
                    break;
                }
            }
        }
        System.out.println("priceList = " + priceList);
        for (Price price : priceList) {
            answer[price.index] = price.retained;
        }

        System.out.println("answer = " + answer);
        return answer;
    }

    class Price{
        int index;
        int price;
        int retained = 0;

        public Price(int index, int price) {
            this.index = index;
            this.price = price;
        }

        @Override
        public String toString() {
            return "Price{" + "index=" + index + ", price=" + price + ", retained=" + retained + "}\n";
        }
    }
}

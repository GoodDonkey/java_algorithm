package problems;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class N002Truck {

    public static void main(String[] args) {
        System.out.println("start");
        System.out.println(new N002Truck().solution(2, 10, new int[]{7,4,5,6}));
    }

    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;

        Queue<Truck> trucksWaiting = new LinkedList<>();
        for (int i = 0; i < truck_weights.length; i++) {
            trucksWaiting.add(new Truck(truck_weights[i]));
        }

        Bridge bridge = new Bridge(weight, bridge_length);
        while (bridge.trucksPassedBridge.size() != truck_weights.length) {
            Truck truck = trucksWaiting.poll();
            bridge.putTruck(truck);
            while (bridge.isAddable(truck))
                bridge.moveTrucks();
        }

        return bridge.time;
    }

    class Truck {
        int location = 0;
        int weight;

        public Truck(int weight) {
            this.weight = weight;
        }

        public void addLocation() {
            this.location++;
            System.out.println("location = " + location);
        }
    }

    class Bridge {
        int MAX_WEIGHT;
        int BRIDGE_LENGTH;
        int currWeight = 0;
        int time = 0;
        List<Truck> trucksOnBridge = new LinkedList<>();
        List<Truck> trucksPassedBridge = new LinkedList<>();

        public Bridge(int MAX_WEIGHT, int BRIDGE_LENGTH) {
            this.MAX_WEIGHT = MAX_WEIGHT;
            this.BRIDGE_LENGTH = BRIDGE_LENGTH;
        }

        // time + 1
        public void putTruck(Truck truck) {
            System.out.println(currWeight);

            if (currWeight+truck.weight < MAX_WEIGHT && trucksOnBridge.size()+1 < BRIDGE_LENGTH) {
                trucksOnBridge.add(truck);
                currWeight += truck.weight;
                time++;
            } else {
                moveTrucks();
            }
            System.out.println("time = " + time);
        }

        public boolean isAddable(Truck truck) {
            return currWeight + truck.weight < MAX_WEIGHT;
        }


        public void moveTrucks() {
            trucksOnBridge.forEach(Truck::addLocation);
            time++;
            System.out.println("trucksPassedBridge = " + trucksPassedBridge);

            // truck 들을 움직이고 나서 location이 MAX 이상이면 건넌 것으로 처리
            List<Truck> trucksPassed = trucksOnBridge.stream()
                                                    .filter(truck -> truck.location == BRIDGE_LENGTH)
                                                    .collect(Collectors.toList());
            trucksPassedBridge.addAll(trucksPassed);

            // 현재 무게를 뺀다.
            List<Integer> trucksWeight = trucksPassed.stream()
                    .map(truck -> truck.weight)
                    .collect(Collectors.toList());
            for (Integer weight : trucksWeight) {
                currWeight -= weight;
            }
        }
    }
}

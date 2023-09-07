package 구현.스택큐;


import java.util.*;
class P42583_다리지나가는트럭 {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> bridge = new LinkedList<>();
        int bridgeWeight = 0;

        for(int i=0;i<bridge_length;i++){
            bridge.add(0);
        }

        int time =0;
        int truckIndex = 0;

        while(truckIndex < truck_weights.length){
            bridgeWeight -= bridge.poll();

            int truckWeight = truck_weights[truckIndex];
            if(bridgeWeight+truckWeight<=weight){
                bridge.add(truckWeight);
                bridgeWeight+=truckWeight;
                truckIndex++;
            }else{
                bridge.add(0);
            }
            time++;
        }

        while(bridgeWeight>0){
            bridgeWeight -= bridge.poll();
            time++;
        }

        return time;
    }
}

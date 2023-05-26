package 해시;

import java.util.*;

class P42578_의상 {
    public int solution(String[][] clothes) {

        Map<String,Integer> clothesType = new HashMap<>();
        for(String[] cloth : clothes){
            int types = clothesType.getOrDefault(cloth[1], 0);
            clothesType.put(cloth[1],types+1);
        }

        int combinations = 1;
        for(int types : clothesType.values()){
            combinations *= types+1;
        }
        return combinations-1;
    }
}

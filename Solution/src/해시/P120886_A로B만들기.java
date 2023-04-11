package 해시;

import java.util.HashMap;
import java.util.Map;

class P120886_A로B만들기 {
    private Map<Character, Integer> toMap(String word){
        Map<Character, Integer> map = new HashMap<>();
        for(char c : word.toCharArray()){
            map.put(c, map.getOrDefault(c,0)+1);
        }
        return map;
    }

    public int solution(String before, String after) {
        return toMap(before).equals(toMap(after)) ? 1:0;
    }
}
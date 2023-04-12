package 해시;


import java.util.HashMap;
import java.util.Map;

class P42576_완주하지못한선수 {
    public String solution(String[] participant, String[] completion) {
        Map<String, Integer> map = new HashMap<>();
        for(String person : participant){
            map.put(person,map.getOrDefault(person,0)+1);
        }

        for(String person : completion){
            int value = map.get(person) -1;
            map.put(person, value);
            if(value ==0) map.remove(person);
        }
        return map.keySet().iterator().next();
    }
}
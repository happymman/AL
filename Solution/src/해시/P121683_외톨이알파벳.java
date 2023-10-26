package 해시;
import java.util.*;

public class P121683_외톨이알파벳{
    static Map<String, Integer> map = new HashMap<>();
    static Set<String> result = new TreeSet<>();

    public String solution(String input_string){

        String[] inputStr = input_string.split("");
        String prev="";
        for(String s : inputStr){
            map.put(s, map.getOrDefault(s,0)+1); //해당문자 개수 추가
            // -> 외톨이알파벳으로 추가
            if(map.get(s) >= 2 && !s.equals(prev)) result.add(s);
            prev = s; //이전char 업데이트
        }

        return result.size()==0 ? "N" : String.join("",result);

    }
}
package 구현.카카오;
/*
1차풀이 - 28m

피드백 :
- 문제읽기 - 이해X -> input예시로 이해하기

 */
import java.util.*;

public class P118666_성격유형검사하기 {
    static Map<String, Integer> map = new HashMap<>();

    public String solution(String[] survey, int[] choices) {

        for(int i=0;i<survey.length;i++){ //서베이 항목 선택
            String item = survey[i];

            String[] types = item.split(""); //성격유형들
            String key="";
            int option = choices[i];

            if(option>4){
                key = types[1];
                option -= 4;
            }else if(option<4){
                key = types[0];
                option = 4-option;
            }
            map.put(key, map.getOrDefault(key, 0)+option);
        }

        String answer="";
        if(map.getOrDefault("R",0) >= map.getOrDefault("T",0)){
            answer+="R";
        }else{
            answer+="T";
        }
        if(map.getOrDefault("C",0) >= map.getOrDefault("F",0)){
            answer+="C";
        }else{
            answer+="F";
        }
        if(map.getOrDefault("J",0) >= map.getOrDefault("M",0)){
            answer+="J";
        }else{
            answer+="M";
        }
        if(map.getOrDefault("A",0) >= map.getOrDefault("N",0)){
            answer+="A";
        }else{
            answer+="N";
        }

        return answer;
    }
}


/*
2차풀이
map.get() -> key값이 없을수도 있나?
 */
//import java.util.*;
//
//class Solution {
//
//    public String solution(String[] survey, int[] choices) {
//        Map<String, Integer> type = new HashMap<>();
//        for(int i=0;i<choices.length;i++){
//            int choice = choices[i];
//
//            String type1 = survey[i].split("")[0];
//            String type2 = survey[i].split("")[1];
//
//            if(choice >= 5){
//                type.put(type2, type.getOrDefault(type2,0)+choice-4);
//            }else if(choice <= 3){
//                type.put(type1, type.getOrDefault(type1,0)+4-choice);
//            }
//        }
//
//        String rt = type.getOrDefault("R",0) >= type.getOrDefault("T",0) ? "R" : "T";
//        String cf = type.getOrDefault("C",0) >= type.getOrDefault("F",0) ? "C" : "F";
//        String jm = type.getOrDefault("J",0) >= type.getOrDefault("M",0) ? "J" : "M";
//        String an = type.getOrDefault("A",0) >= type.getOrDefault("N",0) ? "A" : "N";
//        String answer = rt+cf+jm+an;
//        return answer;
//    }
//
//}
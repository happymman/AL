package 구현;
//
//import java.util.*;
//
//public class P86048_입실퇴실 {
//    public int[] solution(int[] enter, int[] leave) {
//        int[] answer = new int[enter.length];
//        List<Integer>list = new ArrayList();
//        int idx=0;
//
//        //한명 들어올때마다
//        for(int i=0; i<answer.length;i++){
//            list.add(enter[i]);
//
//            //반드시 만난 사람 숫자 count
//            for(int j=0; j<list.size(); j++){
//                if(enter[i]==list.get(j)){ //방금 들어온 본인은
//                    answer[list.get(j)-1] = list.size()-1; //본인 제외 방에 있는 사람수만큼 마주쳤다.
//                }else{
//                    answer[list.get(j)-1]++; //원래 있던 사람들은 마주친사람이 한명 늘었다.
//                }
//            }
//
//            //내보낼 수 있는 조건이 충족되면 -> 최대한 빠르게 내보낸다. - 이유 : 그래야 만날 수도 있는 사람이 count안되고, 반드시 만날 수 밖에 없는 사람만 카운트 됨.
//            while(idx<leave.length && list.contains(leave[idx])){ //떠날 사람이 방에 있어야만 떠날까말까가 가능
//                list.remove(Integer.valueOf(leave[idx]));
//                idx++;
//            }
//        }
//
//        return answer;
//    }
//}
//
//package 구현;
//
//        import java.util.*;
//
//public class P86048_입실퇴실 {
//    public int[] solution(int[] enter, int[] leave) {
//        int[] meet = new int[enter.length];
//
//        List<Integer> room = new ArrayList<>();
//
//        int idx =0;
//        for(int i=0;i<enter.length;i++){
//            room.add(enter[i]);
//
//            for(int j=0;j<room.size();j++){
//                if(enter[i] == room.get(j)){ //본인
//                    meet[enter[i]-1] = room.size()-1;
//                }else{
//                    meet[room.get(j)-1]++;
//                }
//            }
//
//            while(idx < leave.length-1 && room.contains(leave[idx])){
//                room.remove(Integer.valueOf(leave[idx]));
//                idx++;
//            }
//        }
//
//        return meet;
//    }
//}

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.List;

//import java.util.*;
//
//class Solution {
//    public int[] solution(int[] enter, int[] leave) {
//        List<Integer> room = new ArrayList<>();
//        int[] result = new int[enter.length];
//
//        int idx=0;
//        for(int i=0;i<enter.length;i++){
//            room.add(enter[i]);
//            for(int j=0;j<room.size();j++){
//                if(room.get(j) == enter[i]){
//                    result[enter[i]-1] += room.size()-1;
//                }else{
//                    result[room.get(j)-1]++;
//                }
//            }
//
//            while(!room.isEmpty() && room.contains(leave[idx])){
//                room.remove(Integer.valueOf(leave[idx]));
//                idx++;
//            }
//        }
//        return result;
//    }
//}

public class P86048_입실퇴실 {
    public static void main(String[] args) {
        Solution s = new Solution();

//        int[] enter = {1,4,2,3};
//        int[] leave = {2,1,3,4};


//        int[] enter = {3,2,1};
//        int[] leave = {1,3,2};

//        int[] enter = {3,2,1};
//        int[] leave = {2,1,3};

        int[] enter = {1,4,2,3};
        int[] leave = {2,1,4,3};

        int[] result = s.solution(enter, leave);
        for(int i=0;i<result.length;i++){
            System.out.println(result[i]);
        }
    }
}

class Solution {
    public static int[] solution(int[] enter, int[] leave) {
        /*

        meet배열 초기화 //meet = [0,,0]

        for enter배열 -> room 하나 들어오게하고 //enter = [1,3,2], leave = [1,2,3]
            이번에 room 들어온애 -> room현재인원-1만큼 만난사람+
            기존 room 있었던애 -> +1만큼 만난사람+

            while 나갈 수 있는 애들 모조리 내보내기

        return meet배열

         */

        int[] meet = new int[enter.length];
        List<Integer> room = new ArrayList<>();


        int cursor =0;
        for(int i=0;i<enter.length;i++){
            room.add(enter[i]);

            for(int j=0;j<room.size();j++){
                if(room.get(j) != enter[i]){
                    meet[room.get(j)-1]++;
                }else{
                    meet[room.get(j)-1] += room.size()-1;
                }
            }

            while(cursor < leave.length && room.contains(leave[cursor]) ){
                room.remove(Integer.valueOf(leave[cursor]));
                cursor++;
            }

        }

        return meet;
    }
}
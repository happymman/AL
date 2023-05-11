package 구현;

import java.util.*;

public class P86048_입실퇴실 {
    public int[] solution(int[] enter, int[] leave) {
        int[] answer = new int[enter.length];
        List<Integer>list = new ArrayList();
        int idx=0;

        //한명 들어올때마다
        for(int i=0; i<answer.length;i++){
            list.add(enter[i]);

            //반드시 만난 사람 숫자 count
            for(int j=0; j<list.size(); j++){
                if(enter[i]==list.get(j)){ //방금 들어온 본인은
                    answer[list.get(j)-1] = list.size()-1; //본인 제외 방에 있는 사람수만큼 마주쳤다.
                }else{
                    answer[list.get(j)-1]++; //원래 있던 사람들은 마주친사람이 한명 늘었다.
                }
            }

            //내보낼 수 있는 조건이 충족되면 -> 최대한 빠르게 내보낸다. - 이유 : 그래야 만날 수도 있는 사람이 count안되고, 반드시 만날 수 밖에 없는 사람만 카운트 됨.
            while(idx<leave.length && list.contains(leave[idx])){ //떠날 사람이 방에 있어야만 떠날까말까가 가능
                list.remove(Integer.valueOf(leave[idx]));
                idx++;
            }
        }

        return answer;
    }
}
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


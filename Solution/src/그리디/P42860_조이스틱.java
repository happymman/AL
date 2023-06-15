package 그리디;

public class P42860_조이스틱 {
    public static void main(String[] args) {
        P42860_조이스틱_Solution s = new P42860_조이스틱_Solution();
        System.out.println(s.solution("JEROEN"));
    }
}
class P42860_조이스틱_Solution {

    public int solution(String name){

        int count=0;
        int move=name.length()-1;
        for(int i=0;i<name.length();i++){
            count += Math.min(name.charAt(i)-'A', 'Z'+1-name.charAt(i));
            if(i<name.length()-1 && name.charAt(i+1)=='A'){
                int endA = i+1;
                while(endA<name.length() && name.charAt(endA)=='A')
                    endA++;

                move = Math.min(move, i*2+name.length()-endA);
                move = Math.min(move,(name.length()-endA)*2+i);
            }
        }
        return count+move;
    }
}

//2회 풀이
//import java.util.*;
//
//public class Solution{
//    public int solution(String name){
//        int upDownCount=0;
//
//        for(int i=0;i<name.length();i++){
//            upDownCount += Math.min(name.charAt(i)-'A', 'Z'+1-name.charAt(i));
//        }
//        // System.out.println(upDownCount);
//
//        int leftRightCount = name.length()-1;
//        for(int i=0;i<name.length();i++){
//            if(i+1<name.length() && name.charAt(i+1)=='A'){
//                int endA = i+1;
//                while(endA<name.length() && name.charAt(endA) == 'A'){
//                    endA++;
//                }
//                leftRightCount = Math.min(leftRightCount,i*2+name.length() - endA);
//                // System.out.println(i*2+name.length() - endA);
//                leftRightCount = Math.min(leftRightCount,i+(name.length()-endA)*2);
//                // System.out.println(i+(name.length()-endA)*2);
//            }
//        }
//        return upDownCount+leftRightCount;
//    }
//}
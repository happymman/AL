package 완전탐색;

import java.util.*;

//처음풀이
/*
class Solution {
    public int[] solution(int[] answers) {
        int[] first_array = {1,2,3,4,5};
        int[] second_array = {2,1,2,3,2,4,2,5};
        int[] third_array = {3,3,1,1,2,2,4,4,5,5};

        int[] count = new int[3];
        int[] answer = new int[3];

        Arrays.fill(count,0);

        int first;
        int second;
        int third;

        for(int i=0;i<answers.length;i++){
            first = first_array[i%5];
            second = second_array[i%8];
            third = third_array[i%10];
            if(answers[i]==first) count[0]++;
            if(answers[i]==second) count[1]++;
            if(answers[i]==third) count[2]++;
        }

        int max = Math.max(Math.max(count[0],count[1]),count[2]);
        int index=0;
        for(int i=0;i<3;i++){
            if(count[i]==max) answer[index++] = i+1;
        }
        return Arrays.copyOf(answer, index);
    }
}
*/

//import java.util.stream.IntStream;
//
//class P42840_모의고사 {
//    //속성 : 변하지 않는 대상
//    private static final int[][] RULES = {
//            {1,2,3,4,5},
//            {2,1,2,3,2,4,2,5},
//            {3,3,1,1,2,2,4,4,5,5},
//    };
//
//    private int getPicked(int person, int problem){
//        int[] rule = RULES[person];
//        int index = problem % rule.length;
//        return rule[index];
//    }
//
//    public int[] solution(int[] answers) {
//        int[] corrects = new int[3];
//        int max = 0;
//
//        for(int problem =0;problem<answers.length;problem++){
//            int answer = answers[problem];
//
//            for(int person =0;person<3;person++){
//                int picked = getPicked(person, problem);
//                if(answer == picked){
//                    if(++corrects[person] > max){
//                        max = corrects[person];
//                    }
//                }
//            }
//        }
//        final int maxCorrects = max;
//        return IntStream.range(0,3)
//                .filter(i->corrects[i] == maxCorrects)
//                .map(i->i+1)
//                .toArray();
//    }
//}

import java.util.stream.IntStream;

class P42840_모의고사 {
    private static final int[][] RULES ={
            {1,2,3,4,5},
            {2,1,2,3,2,4,2,5},
            {3,3,1,1,2,2,4,4,5,5}
    };

    private static int getPicked(int person, int problem){
        int[] list = RULES[person];
        int index = problem%list.length;
        return list[index];
    }

    public int[] solution(int[] answers) {
        int[] corrects = new int[3];

        for(int person =0; person<3; person++){
            for(int problem =0;problem<answers.length;problem++){
                int picked = getPicked(person, problem);
                int answer = answers[problem];
                if(answer==picked){
                    corrects[person]++;
                }
            }
        }
        final int maxCorrects = Math.max(Math.max(corrects[0], corrects[1]), corrects[2]);
        return IntStream.range(0,3)
                .filter(i->corrects[i] == maxCorrects)
                .map(i->i+1)
                .toArray();
    }
}

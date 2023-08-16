package 구현;

import java.util.*;
public class P150368_이모티콘할인행사 {
    public static void main(String[] args) {
        P150368_이모티콘할인행사Solution s = new P150368_이모티콘할인행사Solution();
        int[] result = s.solution(new int[][]{{40, 10000}, {25, 10000}},	new int[]{7000, 9000});
        System.out.println();
    }
}



class P150368_이모티콘할인행사Solution {
    static int[] discountRatio = {10, 20, 30, 40};
    static List<int[]> ratioList = new ArrayList<>();

    public int[] solution(int[][] users, int[] emoticons) {


        recursion(0, new int[emoticons.length], emoticons.length);

        int[] max = new int[2];
        for(int[] emoticonRatio : ratioList){

            for(int i=0;i<emoticonRatio.length;i++){
                System.out.println(emoticonRatio[i]);
            }
            System.out.println();

            int[] result= new int[2];
            for(int[] user : users){
                int userSum=0;
                for(int i=0;i<emoticons.length;i++){
                    if(emoticonRatio[i] >= user[0]){
                        userSum+=emoticons[i]*(100-emoticonRatio[i])/100;
                    }
                }

                if(userSum>=user[1]){
                    result[0]++;
                }else{
                    result[1]+=userSum;
                }
            }

            //result갱신
            if(max[0] < result[0]){
                max[0]=result[0];
                max[1]=result[1];
            }else if(max[0]==result[0] && max[1]<result[1]){
                max[1]=result[1];
            }

        }
        return max;

    }

    static void recursion(int depth, int[] ratio, int maxLength){
        if(depth==maxLength){
            ratioList.add(Arrays.copyOf(ratio, maxLength));
            return;
        }

        for(int i=0;i<4;i++){
            ratio[depth] = discountRatio[i];
            recursion(depth+1, ratio, maxLength);
        }
    }
}
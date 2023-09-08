package 구현.카카오;

import java.util.*;
public class P150368_이모티콘할인행사 {
    public static void main(String[] args) {
        P150368_이모티콘할인행사Solution s = new P150368_이모티콘할인행사Solution();
        int[] result = s.solution(new int[][]{{40, 10000}, {25, 10000}},	new int[]{7000, 9000});
        System.out.println();
    }
}


/*
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
 */

class P150368_이모티콘할인행사Solution {

    static List<int[]> ratioList = new ArrayList<>();
    static int[] ratios = new int[]{10, 20, 30, 40};

    public int[] solution(int[][] users, int[] emoticons) {
        //비율 리스트 전부 제작
        int[] r=new int[emoticons.length];
        recursion(0, r, emoticons.length);

        int maxJoin=0;
        int maxSum=0;
        for(int[] ratio : ratioList){
            int join=0;
            int sum=0;
            for(int[] user : users){ //유저별
                for(int i=0;i<emoticons.length;i++){
                    if(ratio[i] >= user[0]){ //유저 희망 할인율 이상이면
                        sum += emoticons[i]*(100-ratio[i])/100;
                    }
                }

                if(sum>= user[1]){
                    join++; // 가입자수++
                    sum=0;
                }
            }
            if(maxJoin < join){ // 최대가입자수 < 가입자수
                maxJoin = join;
                maxSum = sum;
            }else if(maxJoin == join){ // 최대가입자수 == 가입자수
                if(maxSum < sum) maxSum=sum; //최대총합 < 총합
            }
        }

        return new int[]{maxJoin, maxSum};


    }

    static void recursion(int depth, int[] ratio, int maxLength){
        if(depth==maxLength){
            ratioList.add(Arrays.copyOf(ratio, maxLength));
            return;
        }

        for(int i=0;i<4;i++){
            ratio[depth] = ratios[i];
            recursion(depth+1, ratio, maxLength);
        }
    }

}


/*
2차풀이 - 39m

출력오류 -> 원인 : 기준 할인율보다 높을때만 산다는 기준을 까먹었음

 */
//import java.util.*;
//
//class Solution {
//
//    static List<int[]> discountList = new ArrayList<>(); //discoutList : 할인비율조합 리스트
//    static int[] discount=new int[]{10, 20, 30, 40}; //discount : 할인율 배열
//    static int emoticonLength;
//
//    public int[] solution(int[][] users, int[] emoticons) {
//        emoticonLength = emoticons.length;
//        recur(new int[emoticons.length], 0);
//
//        int maxJoin=0; //maxJoin : 최대가입자수
//        int maxSum=0; //maxSum : 최대가입자수일때 이모티콘 구매비용
//
//        for(int[] discount : discountList){ //할인율 조합 선택
//
//            int join=0; //join : 할인율 조합별 가입자수
//            int 조합Sum=0; //조합sum : 할인율 조합별 이모티콘 구매비용
//
//            for(int i=0;i<users.length;i++){ //유저 선택
//                int userSum=0; //유저별로 구매합계가 초기화돼야함
//                for(int j=0;j<emoticons.length;j++){ //이모티콘 선택
//                    if(users[i][0] <= discount[j]) userSum+=(emoticons[j]*(100-discount[j])/100);//이모티콘 가격*(100-할인율);
//                }
//
//                if(userSum >= users[i][1]){ //유저sum이 유저기준 이상이면 -> 이모티콘 플러스 가입
//                    join++;
//                }else{
//                    조합Sum+=userSum;
//                }
//            }
//
//            if(maxJoin < join){ //최대가입자수보다 더 많을때
//                maxJoin = join;
//                maxSum = 조합Sum;
//            }else if(maxJoin==join){ //최대가입자수랑 같을때
//                maxSum = Math.max(maxSum, 조합Sum);//이모티콘 구매비용 최대치 갱신
//            }
//
//        }
//
//        return new int[]{maxJoin, maxSum};
//
//    }
//
//    static void recur(int[] route, int depth){
//        if(depth==emoticonLength){ //
//            discountList.add(route.clone()); //deepCopy
//            return;
//        }
//
//        for(int i=0;i<4;i++){ //할인율 선택
//            route[depth] = discount[i];
//            recur(route, depth+1);
//        }
//    }
//
//}

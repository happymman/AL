package 완전탐색.백트래킹;

import java.util.Scanner;

/*
1차풀이 - 솔루션(완전탐색 - 백트래킹)

처음에 단순 그리디인줄 알았던 이유 - 관찰시, 모든 경우의수를 생각하지 못했음
ex :
aCounts - 2,1,1,1,0,0,0,0,0,0,0
lCounts - 3,2
          0,2,2,0,1
          0,0,2,2,1

일부러 0점으로 지는 개수가 하나씩 증가하는 경우의수만 생각해서 -> 3,0,2,0와 같은 경우의수를 생각하지 못했다.
지금까지 풀었던 백트래킹 문제에서는 탐색공간이 구체적으로 '배열공간'으로 주어졌었다. 그리하여 방문처리, BT(); 방문해제와 같은 유형만 접했었다.

시간복잡도 계산
- 데이터 개수 최대 가정 ex : n=10
 */
public class P92342_양궁대회{
    static private int[] lCounts = new int[11];//점수차가 최대일때 라이언이 쏜 화살배열
    static private int[] answer;//정답배열
    static private int max = Integer.MIN_VALUE;//최대값

    public static int[] solution(int n, int[] aCounts) {
        BT(0,n,aCounts);

        if(max<=0) {//라이언이 어피치를 못 이길떄
            answer = new int[]{-1};
        }
        return answer;
    }

    public static void BT(int depth, int n, int[] aCounts) { //depth : 현재까지 쏜 라이언 화살개수, n : 쏠 수 있는 화살개수
        if(depth==n) { //화살 다 꽂았을때(탐색 종료조건검사)
            int diff = getDiff(aCounts);//점수차 구하기
            if(max<=diff) {//점수차 최대값 갱신
                max = diff;
                answer = lCounts.clone();
            }
            return;
        }

        //lCounts[i]<=aCounts[i] -> 10-i점 과녁에 라이언이 화살을 더 많이 맞췄을때 다음점수 과녁으로 이동(다음index실행(continue))
        //이유 : 해당 과녁의 점수 획득 최소조건만 충족하고 다른 과녁에 화살을 쏨으로써 최대점수를 얻기위해
        for(int i = 0; i<aCounts.length && lCounts[i]<=aCounts[i]; i++) {
            lCounts[i] += 1;
            BT(depth+1, n, aCounts);
            lCounts[i] -= 1; //return이후에는 10-i점 과녁 라이언화살개수를 줄이고 다음점수 과녁으로 이동
        }
    }

    //점수차 구하기
    public static int getDiff(int[] aCounts) {
        int aScore=0, lScore=0;
        for(int i = 0; i< lCounts.length; i++) {
            if(aCounts[i]==0 && lCounts[i]==0) continue;//(10-i)점 과녁에 둘다 0개 맞췄을땐 무시.
            if(aCounts[i]>= lCounts[i]) aScore += (10-i);
            else lScore += (10-i);
        }

        return lScore - aScore;
    }
}

//1차풀이 - 접근방법 틀린 풀이
//import java.util.*;
//
//class Solution {
//    public int[] solution(int n, int[] info) {
//
//        int[] answer = new int[11]; //정답배열
//        int max = Integer.MIN_VALUE; //최고 점수 차이
//
//        int zero=0;
//        while(true){
//            int restZero=zero; //restZero : 이길수 있음에도 불구하고 넣는 0개수
//            int restlCount=n; //시도 다시할때마다 라이언 화살개수 충전
//
//            int[] lCounts= new int[11];
//
//            for(int i=0;i<info.length;i++){ //어피치 화살개수 선택
//                int aCount = info[i];
//                if(restlCount>aCount && restZero ==0){ //이길수 있는 경우
//                    restlCount -= (aCount+1); //1점만 더 이길수있도록
//                    lCounts[i] = aCount+1; //라이언 점수 배열에 등록
//                }else{ //라이언이 어쩔수없이 지거나, 일부러 져서 0개 쏘는경우
//                    if(restZero!=0){ //일부러 지는 경우
//                        restZero--;
//                    }
//                    if(i!=10){
//                        lCounts[i] = 0; //라이언개수배열에 0점 등록
//                    }else{
//                        lCounts[i] = restlCount; //라이언개수배열에 남은 라이언화살 모두쏘기
//                    }
//                }
//            }
//
//            int diff = getDiff(lCounts, info); //차이 구하기
//
//            if(max>diff){
//                break; //max보다 더 작아지면 중단
//            }else if(max<diff){
//                answer = Arrays.copyOf(lCounts, lCounts.length);
//                max = diff;
//            }else{ //같은 경우
//                boolean change = hasMoreLowScore(answer, lCounts); //가장 낮은 점수를 맞힌 개수가 더 많은곳이 어딘지 판단
//                if(change){
//                    answer = Arrays.copyOf(lCounts, lCounts.length); //교체
//                }
//            }
//            zero++; //다음턴에는 zero개수 증가시키기
//        }
//
//        if(max<=0){ //제일 좋은 성적이 지거나 무승부일때
//            return new int[]{-1};
//        }
//        return answer;
//
//    }
//
//    static int getDiff(int[] lCounts, int[] info){ //라이언, 어피치 점수 차이 구하기
//        int aScore=0;
//        int lScore=0;
//        for(int i=0;i<=10;i++){
//            if(info[i] >= lCounts[i]){
//                if(info[i]>0) aScore += 10-i; //0점이 아닌 경우에만
//            }else{
//                lScore += 10-i; //더 클경우만 점수따므로 0점이여서 따는 경우는 없음
//            }
//        }
//        return lScore-aScore;
//    }
//
//    static boolean hasMoreLowScore(int[] answer, int[] lCounts){
//        for(int i=10;i>=0;i--){
//            if(answer[i] <lCounts[i]){
//                return true;
//            }else if(answer[i] > lCounts[i]){
//                return false;
//            }
//        }
//        return false; //여기까지 안옴
//    }
//}


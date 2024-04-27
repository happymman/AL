/*
    521
    - 선물더 많이 준사람+1
    - 선물기록 = -> 선물지수 큰사람 +1
    목표 : 다음달 선물가장 많이 받을 사람의 선물수
*/
import java.util.*;

class Solution {
    
    static int[][] gift;
    static int[] nextGift;
    
    public int solution(String[] friendsArr, String[] records) {
        List<String> friends = new ArrayList<>();
        Collections.addAll(friends, friendsArr);
        
        gift = new int[friendsArr.length][friendsArr.length];
        nextGift = new int[friendsArr.length];
        
        for(String record : records){ //선물기록 선택
            String give = record.split(" ")[0];
            String take = record.split(" ")[1];
            
            //선물준사람, 선물받은 인덱스찾기를 이용해서 배열에 선물배열 완성하기
            gift[friends.indexOf(give)][friends.indexOf(take)] ++; 
        }
        
        //50만
        for(int i=0;i<gift.length;i++){ //선물준사람 선택
            for(int j=0;j<gift.length;j++){ //선물받은사람 선택
                if(i==j) continue; //본인제외
                if(gift[i][j] > gift[j][i]){ //i가 선물 더 많이 줬을때
                    nextGift[i] ++;
                }else if(gift[i][j] < gift[j][i]){ //j가 더 많이 줬을때
                    nextGift[j] ++;
                }else{ //선물준개수 같을때
                    giftScore(i);
                    giftScore(j);
                
                    if(giftScore(i) > giftScore(j)){ //선물지수 i가 크다면
                        nextGift[i] ++;
                    }else if(giftScore(i) < giftScore(j)){ //선물지수 j가 크다면
                        nextGift[j] ++;
                    }
                }
            }
        }
        
        int max=-1;
        for(int i=0;i<nextGift.length;i++){
            max = Math.max(max, nextGift[i]);
        }
        return max/2;
    }
    
    static int giftScore(int idx){ //선물지수 계산
        int give=0;
        int take=0;
        
        for(int i=0;i<gift.length;i++){
            take += gift[i][idx];
        }
        for(int j=0;j<gift.length;j++){
            give += gift[idx][j];
        }
        return give-take;
    }
}
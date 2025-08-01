import java.util.*;
class Solution {
    
    static List<int[]> 조합들 = new ArrayList<>();
    static int[] 후보들;
    static int[] 조합 = new int[5];
    
    public int solution(int n, int[][] qs, int[] correct) {
        //후보들 초기화
        후보들 = new int[n];
        for(int i=0;i<후보들.length;i++) 후보들[i]=i+1;
        조합만들기(0,후보들,조합,0);
        
        int ans=0;
        for(int[] 조합 : 조합들){ //조합선택
            int[] cnt = new int[correct.length]; //cnt : 
            int[] resultCnt = new int[n+1];
            //resultCnt 채우기
            for(int j=0;j<5;j++) resultCnt[조합[j]]++;
            
            for(int i=0;i<qs.length;i++){ //입력모음 선택
                int[] q = qs[i];
                //qCnt 채우기
                int[] qCnt = new int[n+1];
                for(int j=0;j<5;j++) qCnt[q[j]]++;
                
                for(int k=1;k<=n;k++){
                    if(qCnt[k] !=0 && qCnt[k]==resultCnt[k]) cnt[i]++;
                    
                }
            }
            
            if(Arrays.equals(cnt,correct)) ans++;
        }
        return ans;
    }
    
    static void 조합만들기(int depth, int[] 후보들, int[] temp, int start){
        if(depth==5){
            조합들.add(temp.clone());
            return;
        }
        
        for(int i=start;i<후보들.length;i++){
            temp[depth] = 후보들[i];
            조합만들기(depth+1, 후보들, temp, i+1);
        }
    }
}
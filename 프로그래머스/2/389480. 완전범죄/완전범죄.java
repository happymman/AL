import java.util.*;
class Solution {
    static final int INF = 100000;
    public int solution(int[][] info, int n, int m) {
        int[][] dp = new int[info.length+1][m];
        
        for(int i=0;i<=info.length;i++) Arrays.fill(dp[i], INF);
        
        dp[0][0] = 0;
        
        for(int i=1;i<=info.length;i++){
            int a = info[i-1][0]; //a : A도둑 훔쳤을때 남기는 흔적
            int b = info[i-1][1]; //b : B도둑 훔쳤을때 남기는 흔적
            
            for(int j=0;j<m;j++){
                //A도둑 선택
                dp[i][j] = Math.min(dp[i][j], dp[i-1][j]+a);
                
                if(j+b<m) dp[i][j+b] = Math.min(dp[i][j+b], dp[i-1][j]); //B도둑이 훔친경우는 a도둑 흔적개수 변함이 없기때문에, i번쨰 물건 훔치기전 a도둑 최소흔적개수를 그대로 가져올 수 있음
            }
        }
        
        int min = INF;
        for(int i=0;i<m;i++) min = Math.min(min, dp[info.length][i]);
        
        
        return min>=n ? -1 : min;
    }
}
package DP;

class P42898_등굣길 {
    public int solution(int m, int n, int[][] puddles) {
        int[][] map = new int[m][n];

        map[0][0] = 1;
        for(int i=0;i<puddles.length;i++){
            map[puddles[i][0]-1][puddles[i][1]-1] = -1;
        }

        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[0].length;j++){
                if(map[i][j] == -1) continue;

                if(i-1>=0 && map[i-1][j] != -1){
                    map[i][j] = (map[i][j]+map[i-1][j])%1000000007;
                }
                if(j-1>=0 && map[i][j-1] != -1){
                    map[i][j] = (map[i][j]+map[i][j-1])%1000000007;
                }
            }
        }
        return map[m-1][n-1] % 1000000007;
    }
}

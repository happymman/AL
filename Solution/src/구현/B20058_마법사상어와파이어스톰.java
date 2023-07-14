package 구현;

import java.util.Scanner;

public class B20058_마법사상어와파이어스톰 {

    private static int N;
    private static int MCount;
    private static int M;

    private static int[] MStorage;

    private static int[][] map;
    private static int[][] minusTemp;
    private static int[][] temp;

    private static boolean[][] isVisited;
    private static int maxCount=0;
    private static int area=0;

    private static int[] dx = {-1,0,1,0};
    private static int[] dy = {0,-1,0,1};


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        MCount = sc.nextInt();
        MStorage = new int[MCount];

        //N,M에 의거해서 배열 할당
        map = new int[(int)Math.pow(2,N)][(int)Math.pow(2,N)];

        minusTemp = new int[(int)Math.pow(2,N)][(int)Math.pow(2,N)];

        for(int r=0;r<map.length;r++){
            for(int c=0;c<map.length;c++){
                map[r][c] = sc.nextInt();
            }
        }

        for(int i=0;i<MCount;i++){
            MStorage[i] = sc.nextInt();
        }

        //MStroage만큼 반복
        for(int count=0;count<MStorage.length;count++){
            M = MStorage[count];
            temp = new int[(int)Math.pow(2,M)][(int)Math.pow(2,M)];

            //회전
            //temp 2차원배열에 규칙에 맞게 복사 - 어떻게 떠올렸지. 빨리 떠올리고 싶다.
            for(int r=0; r<map.length; r+=temp.length){
                for(int c=0; c<map.length; c+=temp.length){

                    //회전한거 temp배열에 저장
                    for(int i=r;i<r+temp.length;i++){
                        for(int j=c;j<c+temp.length;j++){
                            temp[j-c][(temp.length-1)-(i-r)] = map[i][j];
                        }
                    }

                    //temp -> map 복사
                    for(int i=0;i<temp.length;i++){
                        for(int j=0;j<temp.length;j++){
                            map[i+r][j+c] = temp[i][j];
                        }
                    }
                }
            }

            //회전후 얼음 녹이기
            //주변 얼음 2개이하 -> 1씩 줄어들기
            for(int r=0;r<map.length;r++){
                for(int c=0;c<map.length;c++){

                    int iceCount=0;
                    for(int index=0;index<4;index++){
                        int nr = r+dx[index];
                        int nc = c+dy[index];

                        if(nr <0 || nr>=map.length || nc < 0 || nc>=map.length) continue;
                        if(map[nr][nc] > 0) iceCount++;
                    }

                    if(iceCount < 3) minusTemp[r][c] = -1;
                }
            }

            for(int r=0;r<map.length;r++){
                for(int c=0;c<map.length;c++){
                    if(minusTemp[r][c] == -1){
                        if(map[r][c] > 0) map[r][c] --;
                        minusTemp[r][c] =0;
                    }
                }
            }

        }

        int iceSum=0;
        for(int i=0;i< map.length;i++) {
            for (int j = 0; j < map.length; j++) {
                iceSum += map[i][j];
            }
        }
        System.out.println(iceSum);


        isVisited = new boolean[map.length][map.length];
        for(int i=0;i< map.length;i++){
            for(int j=0;j<map.length;j++){

                if(isVisited[i][j]) continue; //방문검사
                if(map[i][j] <= 0) continue; //
                isVisited[i][j] = true;

                dfs(i,j);
                if(maxCount < area) maxCount=area;
                area=0; //area초기화
            }
        }
        System.out.println(maxCount);

    }

    static void dfs(int r, int c){
        area++;

        for(int i=0;i<4;i++){
            int nr=r+dx[i];
            int nc=c+dy[i];

            if(nr <0 || nr>=map.length || nc < 0 || nc>=map.length) continue;
            if(map[nr][nc] <= 0) continue;

            if(isVisited[nr][nc]) continue;
            isVisited[nr][nc] = true;

            dfs(nr, nc);
        }
    }
}

package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B14503_로봇청소기 {

    static int[][] map;
    static boolean[][] isCleaned;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //N,M 입력받기;
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int startR = Integer.parseInt(st.nextToken());
        int startC = Integer.parseInt(st.nextToken());
        int startD = Integer.parseInt(st.nextToken());
        //map 입력받기
        map = new int[N][M];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //isCleaned 초기화
        isCleaned = new boolean[N][M];

        int currentR = startR;
        int currentC = startC;
        int currentD = startD;
        while(true){
            //현재칸 청소가능검사
            if(canCleanCurrentPosition(currentR, currentC)){
                isCleaned[currentR][currentC] = true; //현재칸 청소
            }

            //주변청소 불가능
            if(!canCleanAround(currentR, currentC)){
                //후진가능검사
                if(canGoBack(currentR, currentC, currentD)){
                    //한칸후진
                    int backD = (currentD+2)%4;
                    currentR +=dx[backD];
                    currentC +=dy[backD];

                    //1번으로 돌아감
                }else{
                    break;
                }
            }else{ //주변청소 가능
                //반시계 방향 90%회전
                int rotatedD = (currentD+3)%4;
                currentD = rotatedD;

                if(canCleanFoward(currentR, currentC, currentD)){ //앞쪽칸 청소가능검사
                    //한칸전진
                    currentR +=dx[currentD];
                    currentC +=dy[currentD];
                }
                //1번으로 돌아감
            }
        }

        int cleanSum =0;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(isCleaned[i][j]) cleanSum++;
            }
        }
        System.out.println(cleanSum);
    }

    static boolean canCleanCurrentPosition(int r, int c){
        if(!isCleaned[r][c]) return true;
        else return false;
    }

    static boolean canCleanAround(int r, int c){
        for(int i=0;i<4;i++){
            int nr = r+dx[i];
            int nc = c+dy[i];

            if(map[nr][nc] != 1 && isCleaned[nr][nc] == false) return true;
        }
        return false;
    }

    static boolean canGoBack(int currentR, int currentC, int currentD){
        int backD = (currentD+2)%4;
        int backR = currentR+dx[backD];
        int backC = currentC+dy[backD];
        if(map[backR][backC] != 1) return true;
        return false;
    }

    static boolean canCleanFoward(int currentR, int currentC, int currentD){
        int fowardR = currentR+dx[currentD];
        int fowardC = currentC+dy[currentD];

        if(map[fowardR][fowardC] != 1 && isCleaned[fowardR][fowardC] == false) return true;
        return false;
    }
}

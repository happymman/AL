package 완전탐색.백트래킹;

import java.util.Scanner;

/*
1차풀이 - 2h 55m
- 백트래킹 - 조합(2차원)
-
 */

public class B15683_감시 {
    static int N;
    static int M;
    static int min=Integer.MAX_VALUE;
    static int camera=0;
    static int[] typesLimit = {0,4,2,4,4,1};
    static int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}}; //우하좌상
    static int[][] map;
    static int[][] typeMap;
    static int[][] temp;
    static int[][] typeTemp;
    static boolean[][] isCamera;

    public static void main(String[] args) {

        input();

        BT(0, 0);
        System.out.println(min);
    }

    static void input(){
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        map=new int[N][M];
        typeMap=new int[N][M];
        temp=new int[N][M];
        typeTemp=new int[N][M];
        isCamera = new boolean[N][M];

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                map[i][j] = sc.nextInt();
                if(1<= map[i][j] && map[i][j] <= 5){
                    isCamera[i][j] = true;
                    camera++; //카메라 숫자세기
                }
            }
        }
    }
    static void BT(int depth, int start){
        if(depth==camera){
            temp_deepCopy(); //temp 딥카피
            typeTemp_deepCopy(); //typeTemp 딥카피

            전체카메라_spread();
            int result = 사각지대_세기();//사각지대 세기
            min = Math.min(min, result);//최솟값 갱신
            return;
        }

        for(int index=start;index<N*M;index++){
            int i = index/M;
            int j = index%M;

            if(!isCamera[i][j]) continue;//카메라 여부 검사(벽, 빈공간 제외) - 유효성검사
            //방문검사+방문처리X

            int type = map[i][j]; //type : 카메라 종류
            for(int d = 1; d<=typesLimit[type]; d++){ //카메라 방향선택
                typeMap[i][j] = d; //상태 변경 - 카메라 종류별로 제한된 방향종류를 할당

                BT(depth+1, index+1);

                //상태 해제 - 필요X - 이유 : 항상 모든 카메라의 타입이 선택된 상태에서 전체카메라_spreadAll()이 진행되므로
            }
        }
    }

    static void temp_deepCopy(){
        for(int i=0;i<N;i++){
            temp[i] = map[i].clone();
        }
    }
    static void typeTemp_deepCopy() {
        for(int i=0;i<N;i++){
            typeTemp[i] = typeMap[i].clone();
        }
    }

    static int 사각지대_세기(){
        int count=0;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(temp[i][j] == 0) count++;
            }
        }
        return count;
    }

    static void 전체카메라_spread(){
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(1<= temp[i][j] && temp[i][j] <=5){ //카메라이면
                    //카메라 종류별, type번호별로 allowedDirs만들기
                    boolean[] allowedDirs = allowingDirs(temp[i][j], typeTemp[i][j]); //카메라 종류, 방향종류
                    한개카메라_spread(i,j, allowedDirs);
                }
            }
        }
    }

    static void 한개카메라_spread(int startX, int startY, boolean[] allowedDirs){

        for(int d=0;d<4;d++){
            //허용 방향 여부 검사
            if(!allowedDirs[d]) continue;

            int cx = startX;
            int cy = startY;
            while(true){
                int nx = cx+dir[d][0];
                int ny = cy+dir[d][1];

                if(nx<0||ny<0||nx>=N||ny>=M) break; //범위검사
                if(temp[nx][ny] == 6) break; //벽->중단, 빈공간, 카메라, 카메라범위 -> 진행 - 유효성 검사

                //temp등록 - 상태변경 - except 감시카메라
                if(temp[nx][ny] == 0) temp[nx][ny]=7;//맵등록

                //cx, cy업데이트
                cx = nx;
                cy = ny;
            }
        }
    }

    static boolean[] allowingDirs(int camera, int dir){
        if(camera ==1){
            if(dir==1){
                return new boolean[]{true,false,false,false};
            }else if(dir==2){
                return new boolean[]{false,true,false,false};
            }else if(dir==3){
                return new boolean[]{false,false,true,false};
            }else if(dir==4){
                return new boolean[]{false,false,false,true};
            }
        }else if(camera ==2){
            if(dir==1){
                return new boolean[]{true,false,true,false};
            }else if(dir==2){
                return new boolean[]{false,true,false,true};
            }
        }else if(camera ==3){
            if(dir==1){
                return new boolean[]{true,true,false,false};
            }else if(dir==2){
                return new boolean[]{false,true,true,false};
            }else if(dir==3){
                return new boolean[]{false,false,true,true};
            }else if(dir==4){
                return new boolean[]{true,false,false,true};
            }
        }else if(camera ==4){
            if(dir==1){
                return new boolean[]{false,true,true,true};
            }else if(dir==2){
                return new boolean[]{true,false,true,true};
            }else if(dir==3){
                return new boolean[]{true,true,false,true};
            }else if(dir==4){
                return new boolean[]{true,true,true,false};
            }
        }else if(camera ==5){
            if(dir==1)return new boolean[]{true,true,true,true};
        }
        return null;
    }
}

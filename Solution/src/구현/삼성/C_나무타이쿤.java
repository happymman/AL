package 구현.삼성;

import java.util.Arrays;
import java.util.Scanner;

/*
1차풀이 - 55m
- 실수 : 1~8방향 -> 0~7로 인덱스 변경해줬어야함
 */
public class C_나무타이쿤 {

    static int N;
    static int M;
    static int[][] map;
    static boolean[][] nutrition; //nutrition : 해당년도 영양제 위치

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        input(sc);
        pro(sc);
    }

    static void input(Scanner sc){
        N = sc.nextInt();
        M = sc.nextInt();

        map=new int[N][N];
        nutrition = new boolean[N][N];

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                map[i][j] = sc.nextInt();
            }
        }
    }

    static void pro(Scanner sc){

        영양제_초기화();
        while(M-->0){ //M번 반복
            int d = sc.nextInt(); //d : 영양제 이동방향
            int p = sc.nextInt(); //p : 영양제 이동칸수

            영양제_이동(d,p);
            대각선키우기();
            특수영양제_올려놓기();
        }

        System.out.println(높이세기());
    }

    static void 영양제_초기화(){
        nutrition[N-1][0] = true;
        nutrition[N-2][0] = true;
        nutrition[N-1][1] = true;
        nutrition[N-2][1] = true;
    }

    static void 영양제_이동(int d, int p){
        int[][] dir={{0,1},{-1,1},{-1,0},{-1,-1},{0,-1},{1,-1},{1,0},{1,1}};
        boolean[][] temp = new boolean[N][N];

        int xMove = dir[d-1][0]*p;
        int yMove = dir[d-1][1]*p;

        //영양제 이동 결과배열 temp에 할당
        for(int i=0;i<N;i++){ //행선택
            for(int j=0;j<N;j++){ //열선택
                if(!nutrition[i][j]) continue; //해당년도 영양제가 있는 땅이 아니면 패스
                temp[((i+xMove)+N)%N][((j+yMove)+N)%N] = true;
            }
        }

        //temp -> nutrition복사
        nutrition = new boolean[N][N];
        for(int i=0;i<N;i++){
            nutrition[i] = Arrays.copyOf(temp[i],temp[i].length);
        }

        //영양제 이동한 땅 +1
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(nutrition[i][j]){
                    map[i][j] +=1;
                }
            }
        }

    }

    static void 대각선키우기(){
        /*
        영양제 땅 중에
        대각선 높이 1이상 리브로스 개수 만큼 높이 증가시키기
         */
        int[][] inc = new int[N][N];

        int[][] diag = {{-1,-1},{1,1},{-1,1},{1,-1}};

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(!nutrition[i][j]) continue;
                int count=0;
                for(int d=0;d<4;d++){ //대각방향 선택
                    int nx = i+diag[d][0];
                    int ny = j+diag[d][1];
                    if(nx<0||ny<0||nx>=N||ny>=N) continue; //범위 검사
                    if(map[nx][ny] >= 1) count++; //대각방향 1넘으면 count++; 유효성검사
                }
                inc[i][j] += count; //count만큼 더해주기
            }
        }

        //inc 계산된만큼 더해주기
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                map[i][j] += inc[i][j];
            }
        }
    }

    static void 특수영양제_올려놓기(){

        boolean[][] temp = new boolean[N][N]; //임시배열 초기화

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(nutrition[i][j]) continue; //해당년도 영양제 땅 제외
                if(map[i][j] < 2) continue; //높이 1이하 땅 제외
                map[i][j] -= 2; //높이 2자르고
                temp[i][j] = true; //해당 땅위에 영양제
            }
        }

        //임시배열 영양제땅으로 복사(temp -> nutrition)
        nutrition = new boolean[N][N];
        for(int i=0;i<N;i++){
            nutrition[i] = Arrays.copyOf(temp[i],temp[i].length);
        }

    }

    static int 높이세기(){
        int sum=0;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                sum+= map[i][j];
            }
        }
        return sum;
    }
}

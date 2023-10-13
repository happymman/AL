package 구현.시뮬.삼성;

import java.util.Arrays;
import java.util.Scanner;

/*
1차풀이 - 2h 48m
- 문제읽기 - 중요조건 - 좌표관련 -> 천천히 그림대응하기 ex : (r, 1)은 (r,2)와 (r,m)과 인접합니다.
- 임시배열 - 상황 : 2차원 배열 값 복사 회전
- deepCopy문법 ex) 원판[i] = Arrays.copyOf(배열, 배열길이);
- 배열값 삭제 0으로 할때 조심해야할 것. 실제로 값을 갖고 있는 것이 아닌데, 갖고 있는 것으로 오해될 수도 있음 ex)값이 같을때 같음여부배열에 등록
- %(모듈러 연산) -> 1.모듈러 범위만들기(0~m-1)
              -> 2.이동
              -> 3.((~)+m)%m (모듈러더하고 모듈러 나머지 구하기) - 이유 : 결과값이 음수가 나오지 않도록 조정
              -> 4.원래범위 전환(1~m)
              ex : 원판[i][((j-1+k)+m)%m+1]
 */
/*
x -> 원판번호 x의배수일 경우 회전

회전(q번){
    같은 수 있는지 검사
    지워짐
    안지워짐 -> 정규화 - 평균구하기(소숫점 버림)
                   -> i)평균보다 큰수, ii)평균보다 작은수 iii)평균이랑 같은 수
}
남아있는 총합 구하기

 */
public class C_이상한다트게임 {
    static int n;
    static int m;
    static int q;
    static int[][] 원판;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        input(sc);
        pro(sc);

    }

    static void input(Scanner sc){
        n=sc.nextInt();
        m=sc.nextInt();
        q=sc.nextInt();

        원판 = new int[n+1][m+1];

        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                원판[i][j] = sc.nextInt();
            }
        }
    }

    static void pro(Scanner sc){
        while(q-->0){ //q번 회전
            int x=sc.nextInt();
            int d=sc.nextInt();
            int k=sc.nextInt();

            if(d==0){
                시계회전(x, k);
            }else{
                반시계회전(x,k);
            }
            if(!canErase()){
                정규화();
            }
        }

        System.out.println(총합구하기());

    }

    static void 시계회전(int x, int k){ // 3,2

        int[][] temp = new int[n+1][m+1];

        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(i%x==0){ //행번호가 x의 배수이면
                    temp[i][(j+k-1+m)%m+1] = 원판[i][j];
                }
            }
            if(i%x==0) 원판[i] = Arrays.copyOf(temp[i], temp[i].length);
        }
    }
    static void 반시계회전(int x, int k){ // 2,1,1
        int[][] temp = new int[n+1][m+1];

        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(i%x==0){ //행번호가 x의 배수이면
                    temp[i][(j-k-1+m)%m+1] = 원판[i][j];
                }
            }
            if(i%x==0) 원판[i] = Arrays.copyOf(temp[i], temp[i].length);
        }
    }

    static boolean canErase(){

        boolean flag=false;
        boolean[][] 중복원판 = new boolean[n+1][m+1];

        //가로 중복 찾기
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(원판[i][j]!=0 && 원판[i][j] == 원판[i][j%m+1]){ //j가 m인경우 ex : 4
                    중복원판[i][j] = true;
                    중복원판[i][j%m+1] = true;
                }
            }
        }

        //세로 중복 찾기
        for(int i=1;i<n;i++){ //행선택
            for(int j=1;j<=m;j++){ //열선택
                if(원판[i][j]!=0 && 원판[i][j] == 원판[i+1][j]){
                    중복원판[i][j] = true;
                    중복원판[i+1][j] = true;
                }
            }
        }

        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(중복원판[i][j]){
                    원판[i][j]=0;
                    flag = true;
                }
            }
        }

        return flag;

    }

    static void 정규화(){
        int sum=0;
        int count=0;
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(원판[i][j] != 0){
                    sum+=원판[i][j];
                    count++;
                }
            }
        }
        if(count==0) return; //원판에 남은 수 없을때는 정규화 진행X
        float avg = (float)sum/count;

        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(원판[i][j] != 0){
                    if(원판[i][j] < avg){
                        원판[i][j]++;
                    }else if(원판[i][j] > avg){
                        원판[i][j]--;
                    }
                }
            }
        }
    }

    static int 총합구하기(){
        int sum=0;
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                sum+=원판[i][j];
            }
        }
        return sum;
    }
}

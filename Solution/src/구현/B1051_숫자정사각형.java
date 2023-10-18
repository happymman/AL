package 구현;

import java.util.Scanner;

public class B1051_숫자정사각형 {
    static int N;
    static int M;
    static long[][] a;
    public static void main(String[] args) {
        input();
        int result = pro();
        System.out.println(result);
    }

    static void input(){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        a = new long[N][M];

        for(int i=0;i<N;i++){
            String input = sc.next();
            String[] inputStr = input.split("");

            for(int j=0;j<M;j++){
                a[i][j] = Integer.parseInt(inputStr[j]);
            }
        }
    }

    static int pro(){

        int length = Math.min(N,M); //벽길이 초기화
        while(length != 1){ //1이 아닐 때까지 반복

            //좌상 좌표 선택
            for(int i=0;i<N-(length-1);i++){
                for(int j=0;j<M-(length-1);j++){
                    if(allSame(i,j,length)) return length*length;//꼭짓점 숫자 모두 같은지 검사
                }
            }

            length --;
        }
        return 1;

    }

    static boolean allSame(int i, int j, int length){
        if(a[i][j]==a[i+(length-1)][j]
                && a[i][j]==a[i][j+(length-1)]
                && a[i][j]==a[i+(length-1)][j+(length-1)]
        ) return true;
        return false;
    }

}

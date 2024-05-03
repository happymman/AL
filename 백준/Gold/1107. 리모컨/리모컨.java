
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int minV = Integer.MAX_VALUE;
    static int N;
    static int M;
    static boolean[] exist = new boolean[10];

    public static void main(String[] args) {
        input();
        pro();
    }
    static void input(){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        Arrays.fill(exist, true);
        for(int i=0;i<M;i++){
            int notExist = sc.nextInt();
            exist[notExist] = false;
        }
    }
    static void pro(){
        minV = Math.min(minV, Math.abs(100 - N));
        dfs(0,0);

        System.out.println(minV);
    }

    static void dfs(int cnt, int num){ //cnt : 자릿수, num : 숫자
        if(cnt > 0){ //갱신
            minV = Math.min(minV, Math.abs(N-num)+cnt); //차이 갱신
        }
        if(cnt > Integer.toString(N).length()) return;

        for(int i=0;i<10;i++){
            if(!exist[i]) continue;
            dfs(cnt+1, num*10+i);
        }
    }

}

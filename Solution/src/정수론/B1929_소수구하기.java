package 정수론;

import java.util.Scanner;

public class B1929_소수구하기 {

    public static void main(String[] args){
        //n입력 m입력
        Scanner sc = new Scanner(System.in);
        int M = sc.nextInt();
        int N = sc.nextInt();

        //1~m배열 만들기
        int[] arr = new int[N+1];
        for(int i=2;i<=N;i++){
            arr[i] = i;
        }

        for(int i=2;i<Math.sqrt(N);i++){
            if(arr[i] ==0) continue;
            int k=1;
            while(k*i <= N){
                arr[k*i] =0;
                k++;
            }
        }

        for(int i=M;i<=N;i++){
            if(arr[i] != 0) System.out.println(arr[i]);
        }
    }
}

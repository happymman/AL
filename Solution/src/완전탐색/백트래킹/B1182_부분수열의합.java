package 완전탐색.백트래킹;

import java.util.Scanner;

public class B1182_부분수열의합 {
    static int N;
    static int M;
    static int count;
    static int[] a;

    public static void main(String[] args) {
        input();
        pro();
        System.out.println(count);
    }

    static void input(){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        a= new int[N];

        for(int i=0;i<N;i++){
            a[i] = sc.nextInt();
        }
    }

    static void pro(){
        for(int i=1;i<=N;i++){
            BT(i, 0,0,new int[N]);
        }

    }

    static void BT(int max, int depth, int start, int[] route){
        if(depth==max){
            if(sum(route) == M) count++;
            return;
        }

        for(int i=start;i<route.length;i++){
            route[depth] = a[i];
            BT(max, depth+1, i+1, route);
        }
    }

    static int sum(int[] route){
        int sum=0;
        for(int num : route){
            sum+= num;
        }
        return sum;
    }
}

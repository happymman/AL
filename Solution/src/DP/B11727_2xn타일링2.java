package DP;
//
//import java.util.Scanner;
//
//public class B11727_2xn타일링2 {
//    public static void solution(){
//        Scanner sc = new Scanner(System.in);
//        int n= sc.nextInt();
//        int[] dp = new int[n+1];
//        dp[0] = 1;
//        dp[1] = 1;
//        for(int i=2;i<=n;i++){
//            dp[i]=(dp[i-1]+2*dp[i-2])%10007;
//        }
//        System.out.println(dp[n] %10007);
//        sc.close();
//    }
//}


import java.util.Scanner;

public class B11727_2xn타일링2 {
    public static void solution(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 3;
        for(int i=3;i<=n;i++){
            dp[i] = (2*dp[i-2]+dp[i-1])%10007;
        }
        System.out.println(dp[n]);
        sc.close();
    }
}

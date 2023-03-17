package DP;//백준11726 - 2xn 타일링
import java.util.Scanner;
public class B11726_2xn타일링 {
    public static void solution(){
        Scanner sc = new Scanner(System.in);
        int n=0;
        n = sc.nextInt();
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i=2;i<=n;i++){
            dp[i]=(dp[i-1]+dp[i-2])%10007;
        }
        System.out.println(dp[n] %10007);
        sc.close();
    }
}

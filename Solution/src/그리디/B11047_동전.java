package 그리디;

import java.util.Scanner;

//public class B11047_동전 {
//    public static void main(String[] args){
//        Scanner sc = new Scanner(System.in);
//
//        int n= sc.nextInt();
//        int k = sc.nextInt();
//        int[] coins = new int[n];
//        for(int i=0;i<n;i++){
//            coins[i] = sc.nextInt();
//        }
//
//        int count=0;
////        for(int i=n-1;i>=0;i--){
////            while(k>= coins[i]){
////                k -= coins[i];
////                count++;
////            }
////            if(k==0) break;
////        }
//
//        int i=n-1;
//        while(k>0){
//            while(k>=coins[i]){
//                k -= coins[i];
//                count++;
//            }
//            i--;
//        }
//
//        System.out.println(count);
//    }
//}


public class B11047_동전 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();

        int[] arr = new int[N];
        for(int i=0;i<N;i++){
            arr[i] = sc.nextInt();
        }

        int coinCount = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            while (arr[i] <= K) {
                K -= arr[i];
                coinCount++;
            }
        }
        System.out.println(coinCount);
    }
}

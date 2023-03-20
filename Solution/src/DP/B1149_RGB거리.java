package DP;

import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.StringTokenizer;
//
//import static java.lang.Math.min;
//
//public class B1149_RGB거리 {
//    public static void solution() throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st;
//        int n= Integer.parseInt(br.readLine());
//
//        int[][] cost = new int[n+1][3+1];
//
//        for(int i=1;i<=n;i++){
//            st = new StringTokenizer(br.readLine()," ");
//
//            cost[i][1] = Integer.parseInt(st.nextToken());
//            cost[i][2] = Integer.parseInt(st.nextToken());
//            cost[i][3] = Integer.parseInt(st.nextToken());
//        }
//
//        for(int i=2;i<=n;i++){
//            cost[i][1] += min(cost[i-1][2], cost[i-1][3]);
//            cost[i][2] += min(cost[i-1][1], cost[i-1][3]);
//            cost[i][3] += min(cost[i-1][1], cost[i-1][2]);
//        }
//        System.out.println(min(min(cost[n][1],cost[n][2]),cost[n][3]));
//        br.close();
//    }
//}

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

import static java.lang.Math.min;

public class B1149_RGB거리 {
    public static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[][] cost = new int[n][3];

        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine(), " ");
            cost[i][0] = Integer.parseInt(st.nextToken());
            cost[i][1] = Integer.parseInt(st.nextToken());
            cost[i][2] = Integer.parseInt(st.nextToken());
        }

        for(int i=1;i<n;i++){
            cost[i][0] = cost[i][0]+Math.min(cost[i-1][1], cost[i-1][2]);
            cost[i][1] = cost[i][1]+ Math.min(cost[i-1][0], cost[i-1][2]);
            cost[i][2] = cost[i][2]+Math.min(cost[i-1][1], cost[i-1][0]);
        }
        System.out.println(Math.min(cost[n-1][0], Math.min(cost[n-1][1], cost[n-1][2])));
        br.close();
    }
}
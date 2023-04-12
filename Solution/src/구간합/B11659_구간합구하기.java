package 구간합;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.StringTokenizer;
//
//public class B11659_구간합구하기 {
//
//    public static void main(String[] args) throws IOException{
//        //줄 입력, 합 배열 개수 입력
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        int suNo = Integer.parseInt(st.nextToken());
//        int quizNo = Integer.parseInt(st.nextToken());
//
//        //합배열 만들기
//        long[] S = new long[suNo+1];
//        st = new StringTokenizer(br.readLine());
//        for(int i=1;i<=suNo;i++) {
//            S[i] = S[i - 1] + Integer.parseInt(st.nextToken());
//        }
//
//        for(int i=0;i<quizNo;i++){
//            st = new StringTokenizer(br.readLine());
//            int end = Integer.parseInt(st.nextToken());
//            int start = Integer.parseInt(st.nextToken());
//            System.out.println(S[end] - S[start-1]);
//        }
//    }
//}

import java.util.*;
import java.io.*;

class B11659_구간합구하기 {
    static int[] array;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken());
        int m=Integer.parseInt(st.nextToken());

        st=new StringTokenizer(br.readLine());
        array=new int[n+1];
        for(int i=1;i<=n;i++) { // i까지의 누적합 구하기
            array[i]=array[i-1]+Integer.parseInt(st.nextToken());
        }

        for(int i=0;i<m;i++) {
            st=new StringTokenizer(br.readLine());
            int a=Integer.parseInt(st.nextToken());
            int b=Integer.parseInt(st.nextToken());
            // a, b사이의 구간합은 array[b]-array[a-1]과 같다
            System.out.println(array[b]-array[a-1]);
        }
    }


}

package 이진탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class B1300_K번째수 {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int N = in.nextInt();
        int K = in.nextInt();

        long left = 1; //탐색범위 최솟값
        long right = K; //탐색범위 최댓값 11

        while(left <= right) {
            long mid = (left + right) / 2;	// 임의의 x(mid)를 중간 값으로 잡는다.
            long count = 0; //count : mid이하 값의 개수가 count개

            for(int i = 1; i <= N; i++) {
                count += Math.min(mid / i, N); //i번째행에서 x값이하 값의 개수는 x/i이거나 N개
            }

            // count가 많다는 것은 임의의 x(mid)이하 값의 개수가 B[K]보다 많다는 뜻
            if(K <= count) { //탐색값(mid)이 조건충족 = mid이하 값의 개수가 K개이상이다 = K번째수 이상이다.
                right = mid-1;
            }else{
                left = mid+1;
            }
        }
        System.out.println(left);
    }
}

/*
1차풀이 - 솔루션 확인

피드백 :
- 이진탐색은
 */
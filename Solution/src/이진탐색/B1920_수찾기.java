package 이진탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

/*
1차풀이
public class B1920_수찾기 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] A = new int[N];
        for(int i=0;i<N;i++){
            A[i] = sc.nextInt();
        }
        Arrays.sort(A);

        int M = sc.nextInt();
        for(int i=0;i<M;i++){ //1을 찾으려고 한다.
            boolean find = false;
            int target = sc.nextInt();

            int start =0;
            int end = A.length -1;
            while(start<=end){
                int midi = (start+end)/2;
                int midV = A[midi];

                if(midV > target){
                    end = midi-1;
                }else if(midV < target){
                    start = midi+1;
                }else{
                    find = true;
                    break;
                }
            }
            if(find){
                System.out.println(1);
            }else{
                System.out.println(0);
            }

        }
    }
}
 */

//public class B1920_수찾기 {
//    public static void main(String[] args) throws IOException {
//
//        //N입력받기
//        Scanner sc = new Scanner(System.in);
//        int N = sc.nextInt();
//
//        int[] A = new int[N];
//        for(int i=0;i<N;i++){
//            A[i] = sc.nextInt();//N개 입력받기
//        }
//        Arrays.sort(A); //정렬 - 이유 : 이진탐색의 탐색범위는 정렬되어있는 상태여야 가능
//
//        //M입력받기
//        int M = sc.nextInt();
//        int[] targets= new int[M];
//        for(int i=0;i<M;i++){
//            targets[i] = sc.nextInt();//M개 입력받기
//        }
//
//        for(int target : targets){ //테스트숫자 선택
//            int left = 0; //left : 탐색범위 최솟값
//            int right = N-1; //right : 탐색범위 최댓값
//
//            boolean find = false;
//            while(left<=right){
//                int mid = (left+right)/2;//mid 설정
//
//                if(target < A[mid]){ //값이 크다면
//                    right = mid-1;
//                }else if(target == A[mid]){ //값을 찾았다면
//                    find = true; // 찾았음
//                    break;
//                }else{ //값이 작다면
//                    left = mid+1;
//                }
//            }
//            if(find){
//                System.out.println(1);//1 출력
//            }else{
//                System.out.println(0);//0출력
//            }
//
//        }
//
//    }
//}

public class B1920_수찾기 {
    static int[] A;
    static int N;
    static int M;
    public static void main(String[] args) {

        //N입력받기
        Scanner sc =new Scanner(System.in);
        N = sc.nextInt();

        //배열 입력받기
        A=new int[N];
        for(int i=0;i<N;i++){
            A[i]=sc.nextInt();
        }
        //정렬
        Arrays.sort(A);

        //M입력받기
        M = sc.nextInt();

        for(int i=0;i<M;i++){ //M번
            int target = sc.nextInt();//입력받기
            int result = binarySearch(target);
            System.out.println(result); //출력
        }

    }

    static int binarySearch(int target){
        int left=0;
        int right=N-1; //가장 큰 값?

        while(left<=right){
            int mid = (left+right)/2;

            if(target < A[mid]){ //
                right = mid-1;
            }else if(target > A[mid]){
                left = mid+1;
            }else{ //찾음
                return 1;
            }
        }
        return 0;
    }
}

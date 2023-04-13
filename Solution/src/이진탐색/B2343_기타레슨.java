package 이진탐색;

import java.util.Scanner;

public class B2343_기타레슨 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //N입력 ,M입력
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[] A = new int[N];
        //강의 분량 입력받기
//        int total=0;
//        for(int i=0;i<N;i++){
//            A[i] = sc.nextInt();
//            total+=A[i];
//        }
//        //start, end초기화
//        int start = A[N-1];
//        int end = total;
        int start = 0;
        int end =0;
        for(int i=0;i<N;i++){
            A[i] = sc.nextInt();
            if(start < A[i]) start = A[i];
            end = end+A[i];
        }

        while(start<=end){
            int mid = (start+end)/2;

            int count=0;
            int sum=0;
            for(int i=0;i<N;i++){
                if(sum+A[i] > mid){
                    sum=0;
                    count++;
                }
                sum+=A[i];
            }
            if(sum!=0) count++;

            if(count > M){
                start = mid+1;
            }else{
                end = mid-1;
            }
        }
        //최소 블루레이 분량 출력
        System.out.println(start);
    }
}

package 이진탐색;

import java.util.Scanner;

public class B2343_기타레슨 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[] A = new int[N]; //인덱스 자체에 의미를 넣었을때 가독성이 좋은 경우는 아니니 N+!이 아니라 그냥 N으로 선언

        int total=0;
        for(int i=0;i<N;i++){
            A[i] = sc.nextInt();
            total+=A[i];
        }

        int start = A[N-1]; //9
        int end = total; //45


        while(start <= end){
            int mid = (start+end)/2;
            int sum=0;

            int count =0;
            for(int i=0;i<N;i++){
                if(sum+A[i] > mid){
                    sum=0;
                    count++;
                }
                sum=sum+A[i];
            }
            if(sum!=0) count++;

            //3개 모두 저장 불가능하면 -> 중앙값+1;
            if(count > M){
                start = mid+1;
            }else{ //3개 모두 저장가능하면 -> 중앙값-1;
                end = mid-1;
            }
        }
        System.out.println(start);

    }
}

//            for(int i=0;i<N;i++){
//        if(sum+A[i] >= mid){
//        sum=0;
//        count++;
//        }else{
//        sum=sum-A[i];
//        }
//        }
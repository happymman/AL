package 이진탐색;

//import java.util.Scanner;
//public class B2343_기타레슨 {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        //N입력 ,M입력
//        int N = sc.nextInt();
//        int M = sc.nextInt();
//        int[] A = new int[N];
//        //강의 분량 입력받기
////        int total=0;
////        for(int i=0;i<N;i++){
////            A[i] = sc.nextInt();
////            total+=A[i];
////        }
////        //start, end초기화
////        int start = A[N-1];
////        int end = total;
//        int start = 0;
//        int end =0;
//        for(int i=0;i<N;i++){
//            A[i] = sc.nextInt();
//            if(start < A[i]) start = A[i]; //
//            end = end+A[i];
//        }
//
//        while(start<=end){
//            int mid = (start+end)/2;
//
//            int count=0;
//            int sum=0;
//            for(int i=0;i<N;i++){
//                if(sum+A[i] > mid){
//                    sum=0;
//                    count++;
//                }
//                sum+=A[i];
//            }
//            if(sum!=0) count++;
//
//            if(count > M){
//                start = mid+1;
//            }else{
//                end = mid-1;
//            }
//        }
//        //최소 블루레이 분량 출력
//        System.out.println(start);
//    }
//}

//public class B2343_기타레슨 {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//
//        int N = sc.nextInt();
//        int M = sc.nextInt();
//        int[] A = new int[N];
//
//        //N개수 만큼 입력 받기
//        int left =0; //최댓값
//        int right=0; //총합
//        for(int i=0;i<N;i++){
//            A[i] = sc.nextInt();
//            if(left < A[i]) left = A[i];
//            right+=A[i];
//        }
//
//        //입력 받으면서 max값을 left값으로 계속 초기화, 총합 계산
//        //left, right값 설정
//
//        while(left<=right){
//            int mid = (left+right)/2;
//            int count=0;
//
//            int sum=0;
//            for(int i=0;i<N;i++){ //모든 레슨 배열 순회하고
//                if(sum+A[i] > mid){ //초과할때만 count++하고
//                    count++;
//                    sum=0;
//                }
//                sum += A[i];
//            }
//            if(sum!=0) count++; //남아있으면 count++
//
//            //못담으면
//            if(count > M){ //ex : 2
//                left = mid+1;
//            }else{ //다담을 수 있으면
//                right = mid-1;
//            }
//        }
//        System.out.println(left);
//    }
//}

//import java.util.Scanner;
//public class B2343_기타레슨 {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int N = sc.nextInt();
//        int M = sc.nextInt();
//        int[] lectures = new int[N];
//
//        int left =0;
//        int right=0;
//        for(int i=0;i<N;i++){
//            lectures[i] = sc.nextInt();
//
//            if(left<lectures[i]) left = lectures[i];
//            right += lectures[i];
//        }
//        right -= 1;
//        //left 설정(max값 : 입력받으면서 갱신)
//        //right 설정(강의 합-1)
//
//        while(left<=right){
//            //mid값 계산
//            int mid = (left+right)/2;
//            int count=0;
//
//            //mid값 검증
//            int rest=0;
//            for(int i=0;i<N;i++){
//                if(lectures[i] > rest){
//                    rest=mid;
//                    count++;
//                }
//                rest -= lectures[i];
//            }
//
//            //충족
//            if(count<=M){
//                right = mid-1;
//            }else{ //불충족
//                left = mid+1;
//            }
//        }
//        System.out.println(left);
//
//    }
//}

import java.util.Scanner;

public class B2343_기타레슨{
    public static void main(String[] args) {

        //N, M입력받기
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();

        int[] lectures = new int[N];//배열 선언

        int max=0;
        long sum=0;
        for(int i=0;i<N;i++){ //강의담기
            lectures[i] = sc.nextInt();
            if(max < lectures[i]) max = lectures[i];
            sum+= lectures[i];
        }

        long left = max; //탐색범위 최솟값
        long right = sum; //탐색범위 최댓값

        while(left<=right){
            long mid = (left+right)/2;

            //조건충족여부 검사
            int blueRayCount = getCount(mid, lectures);

            if(blueRayCount <= M){ //탐색값(mid)가 조건충족하는 경우 = 블루레이 개수가 M개 이하인 경우
                right = mid-1;
            }else{ //조건불충족
                left = mid+1;
            }
        }
        System.out.println(left);//left 출력

    }

    static int getCount(long size, int[] lectures){
        int count=1; //count : 블루레이 개수
        long rest=size; //rest : 남은 블루레이 용량

        for(int i=0;i<lectures.length;i++){ //강의 선택
            if(rest >= lectures[i]){ //강의를 담을 수 있다면
                rest -= lectures[i]; //강의를 담고, 블루레이 용량은 줄고
            }else{ //못담으면
                count++; //블루레이 개수 늘리고
                rest=size; //블루레이 용량은 초기화
                i--; //못담은 강의를 넘기지 않기 위해서는 인덱스를-1해주는 것이 필
            }
        }
        //rest가 0<= <size인 상황

        return count;

    }
}
/*
틀린 부분
구체코드 - 이후 '천천히 테케 돌리기'안해서 else{}이후에 ㅇ
 */
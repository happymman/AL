package 이진탐색;

import java.util.Scanner;

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

public class B2343_기타레슨 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();
        int[] A = new int[N];

        //N개수 만큼 입력 받기
        int left =0; //최댓값
        int right=0; //총합
        for(int i=0;i<N;i++){
            A[i] = sc.nextInt();
            if(left < A[i]) left = A[i];
            right+=A[i];
        }

        //입력 받으면서 max값을 left값으로 계속 초기화, 총합 계산
        //left, right값 설정

        while(left<=right){
            int mid = (left+right)/2;
            int count=0;

            int sum=0;
            for(int i=0;i<N;i++){ //모든 레슨 배열 순회하고
                if(sum+A[i] > mid){ //초과할때만 count++하고
                    count++;
                    sum=0;
                }
                sum += A[i];
            }
            if(sum!=0) count++; //남아있으면 count++

            //못담으면
            if(count > M){ //ex : 2
                left = mid+1;
            }else{ //다담을 수 있으면
                right = mid-1;
            }
        }
        System.out.println(left);
    }
}
//
//import java.io.*;
//        import java.math.*;
//        import java.security.*;
//        import java.text.*;
//        import java.util.*;
//        import java.util.concurrent.*;
//        import java.util.function.*;
//        import java.util.regex.*;
//        import java.util.stream.*;
//        import static java.util.stream.Collectors.joining;
//        import static java.util.stream.Collectors.toList;
//
//class Result {
//
//    /*
//     * Complete the 'climbingLeaderboard' function below.
//     *
//     * The function is expected to return an INTEGER_ARRAY.
//     * The function accepts following parameters:
//     *  1. INTEGER_ARRAY ranked
//     *  2. INTEGER_ARRAY player
//     */
//
//    public static List<Integer> climbingLeaderboard(List<Integer> ranked, List<Integer> player) {
//
//        List<Integer> answer = new ArrayList<>();
//        List<Integer> uniqueRanked = new ArrayList<>();
//        uniqueRanked.add(Integer.MAX_VALUE);
//        for(int i=0;i<ranked.size();i++){
//            if(!uniqueRanked.contains(ranked.get(i))) uniqueRanked.add(ranked.get(i));
//        }
//        uniqueRanked.add(Integer.MIN_VALUE);
//
//        System.out.println(uniqueRanked);
//
//
//        for(int i=0;i<player.size();i++){
//            int target = player.get(i);
//
//            int start=1;
//            int end = uniqueRanked.size()-2;
//
//            while(start<=end){
//                int midIndex = (start+end)/2;
//                int midValue = uniqueRanked.get(midIndex);
//
//                if(target < midValue){
//                    start = midIndex+1;
//                }else if(midValue <= target){
//                    end = midIndex-1;
//                }
//            }
//            answer.add(start);
//        }
//
//        return answer;
//    }
//}
//
//public class Solution {
//    public static void main(String[] args) throws IOException {
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
//
//        int rankedCount = Integer.parseInt(bufferedReader.readLine().trim());
//
//        List<Integer> ranked = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
//                .map(Integer::parseInt)
//                .collect(toList());
//
//        int playerCount = Integer.parseInt(bufferedReader.readLine().trim());
//
//        List<Integer> player = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
//                .map(Integer::parseInt)
//                .collect(toList());
//
//        List<Integer> result = Result.climbingLeaderboard(ranked, player);
//
//        bufferedWriter.write(
//                result.stream()
//                        .map(Object::toString)
//                        .collect(joining("\n"))
//                        + "\n"
//        );
//
//        bufferedReader.close();
//        bufferedWriter.close();
//    }
//}
//
//

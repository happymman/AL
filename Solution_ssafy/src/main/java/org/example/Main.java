package org.example;

/*
 * N명의 선수들에 대해서 선수들의 실력 값이 차례대로 주어진다.
 * 이 선수들 중 일부를 골라서 팀 구성
 * 실력 차이가 K를 초과하는 선수들이 팀에 선발되면 X
 * 조건을 만족하며 최대 인원이 되도록 팀을 구성할 때 그 인원수 제시
 * 3
 * 4 2
 * 6 4 2 3
 * 4 3
 * 1 2 3 4
 * 4 1
 * 1 3 7 5
 *
 * #1 3
 * #2 4
 * #3 1
 */
import java.util.Scanner;

class Solution
{
    public static void main(String args[]) throws Exception
    {

        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++)
        {
            int N = sc.nextInt(); //1~500이하
            int K = sc.nextInt(); //1~1000이하
            int[] nums = new int[N];
            int[] counts = new int[N];
            boolean[] canVisited = new boolean[N];
            for(int i=0;i<N;i++) {
                nums[i] = sc.nextInt();
            }
            for(int i=0;i<N;i++) {
                for(int j=0;j<N;j++) {
                    if(Math.abs(nums[i]-nums[j])<=K) counts[i] ++;
                }
            }

            //max값 후보 계산
            int 개수;
            for(int max=N;max>=0;max--){
                int count=0;
                for(int i=0;i<N;i++){
                    if(counts[i] >= max) count++;
                }
                if(count >= max){
                    개수=max;
                    break;
                }
            }

            while(flag) {
                int num =0; //x넘는 개수
                for(int i=0;i<N;i++) {
                    if(counts[i] >= count) {
                        canVisited[i] = true;
                        num++;
                    }
                    else canVisited[i] = false;
                }

                if(num < count) break;

                recur();//2개넘는애들끼리 짝짓기

                //min, max차이가 k이하인지 확인
                //min, max 갱신

                //반복하다가 ok면 max++, count++하고  flag=true 다시 while문 돌기
            }
            System.out.println("#" + test_case+" "+max);
        }
        sc.close(); // 사용이 끝난 스캐너 객체를 닫습니다.
    }

    static void recur() {
        if() {
            return;
        }

        for() {

        }
    }
}
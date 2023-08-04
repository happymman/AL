package 스택큐;

import java.util.Scanner;
import java.util.Stack;

public class P6549_히스토그램에서가장큰직사각형 {
    public static void main(String[] args) {

        //Sc 초기화
        Scanner sc = new Scanner(System.in);

        while(true){
            int N = sc.nextInt();
            if(N==0) break;

            Stack<Integer> s = new Stack<>();
            //heights 배열 생성
            long[] heights = new long[N+1];
            //N 입력받기
            for(int i=0;i<N;i++){
                heights[i] = sc.nextLong(); //heights 초기화
            }
            heights[N] = -1;

            s.push(-1);

            long max =0;
            for(int right=0;right<=N;right++){
                while(s.size()>1 && heights[s.peek()] > heights[right]){

                    long height = heights[s.pop()];
                    long left = s.peek();

                    max = Math.max((right-1-left)*height, max);
                }
                s.push(right);
            }
            System.out.println(max);
        }


    }
}

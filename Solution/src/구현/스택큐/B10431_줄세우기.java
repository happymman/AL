package 구현.스택큐;

import java.util.Scanner;
import java.util.Stack;

public class B10431_줄세우기 {
    public static void main(String[] args) {

        //n입력받기
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        for(int i=1;i<=N;i++){

            int testCaseNum = sc.nextInt();

            Stack<int[]> s = new Stack<>(); //누적스택 생성
            Stack<int[]> wait = new Stack<>(); //대기스택 생성

            for(int j=0;j<20;j++){ //20회 입력받기
                int input = sc.nextInt();

                while(!s.isEmpty() && s.peek()[0] > input){ // 누적스택들어있을때 & 스택맨위가 넣을 숫자보다 크면,
                    int[] now = s.pop();
                    now[1] ++; //이동 횟수+1
                    wait.add(now);//대기스택에 넣기
                }

                s.add(new int[]{input,0});

                while(!wait.isEmpty()){ //대기스택 들어있을때
                    s.add(wait.pop());//pop() -> 누적스택에 다시 넣기
                }
            }

            int sum=0;
            while(!s.isEmpty()){ //누적스택 빌때까지
                sum += s.pop()[1];//pop()해서 sum누적
            }

            System.out.println(testCaseNum+" "+sum);

        }

    }
}

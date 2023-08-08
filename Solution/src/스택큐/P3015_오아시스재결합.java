package 스택큐;

import java.util.Scanner;
import java.util.Stack;

public class P3015_오아시스재결합 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        long[] heights = new long[N+1];

        for(int i=0;i<N;i++){
            heights[i] = sc.nextLong();
        }
        heights[N] = Long.MAX_VALUE;

        Stack<Integer> s = new Stack<>();

        long sum=0;
        for(int i=0;i<=N; i++){
            //i의 높이가 s.peek()의 높이보다 더크면, s.peek()블록은 i+1블록과 볼 수 없으므로
            //오른쪽으로 볼 수 있는 사람수만 더하고 pop한다.
            //pop되는 블록 오른쪽에는 pop블록과 높이가 같거나, 작은애들 뿐이므로 ->
            while(!s.isEmpty() && heights[s.peek()] < heights[i]){

                sum += i!=N ? i-s.peek() : i-1-s.peek();
                s.pop();
            }
            s.push(i);
        }
        System.out.println(sum);
    }

}

//import java.io.*;
//import java.util.*;
//
//public class P3015_오아시스재결합 {
//
//    static int N;
//    static Stack<Pair> stack = new Stack<>();
//    static long cnt;
//
//    public static void main(String[] args) throws Exception {
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//
//        N = Integer.parseInt(br.readLine());
//        for (int i = 0; i < N; i++) {
//            int n = Integer.parseInt(br.readLine());
//            Pair pair = new Pair(n, 1);
//
//            while (!stack.empty() && stack.peek().height <= n) {
//                Pair pop = stack.pop();
//                cnt += pop.cnt;
//                if (pop.height == n)
//                    pair.cnt += pop.cnt;
//            }
//
//            if (!stack.empty())
//                cnt++;
//
//            stack.push(pair);
//        }
//
//        bw.write(cnt + "\n");
//        bw.flush();
//    }
//
//    static class Pair {
//
//        int height;
//        int cnt;
//
//        Pair(int height, int cnt) {
//            this.height = height;
//            this.cnt = cnt;
//        }
//    }
//
//}

package 우선순위큐;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B2075_N번째큰수 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Long> maxQueue = new PriorityQueue<>(Comparator.reverseOrder());

        StringTokenizer st;
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                maxQueue.add(Long.parseLong(st.nextToken()));
            }
        }

        for(int i=0;i<N-1;i++){
            maxQueue.poll();
        }
        System.out.println(maxQueue.poll());
    }
}

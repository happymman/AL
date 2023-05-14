package 우선순위큐;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;


public class B11279_최대힙 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int input;
        PriorityQueue<Integer> maxQueue = new PriorityQueue<Integer>(Comparator.reverseOrder());
        for (int i = 0; i < n; i++) {
            input = Integer.parseInt(br.readLine());
            if (input>0) {
                maxQueue.add(input);
            } else {
                if (!maxQueue.isEmpty()) {
                    System.out.println(maxQueue.poll());
                }
                else{
                    System.out.println(0);
                }
            }
        }
        br.close();
    }
}
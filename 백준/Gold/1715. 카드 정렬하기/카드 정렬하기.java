

import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    static int n;
    static PriorityQueue<Integer> pq = new PriorityQueue<>();
    public static void main(String[] args) {
        input();
        pro();
    }

    static void input(){
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for(int i=0;i<n;i++){
            pq.add(sc.nextInt());
        }
    }

    static void pro(){
        int result =0;

        while(pq.size() > 1){
            int num1 = pq.poll();
            int num2 = pq.poll();

            int sum = num1 + num2;
            result += sum;

            pq.add(sum);
        }

        System.out.println(result);

    }
}

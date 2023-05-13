package 우선순위큐;

import java.util.Arrays;
import java.util.Scanner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class B1927_최소힙 {
    public static void main(String[] args) throws NumberFormatException, IOException   {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int input;
        PriorityQueue<Integer> minQueue = new PriorityQueue<Integer>();
        for (int i = 0; i < n; i++) {
            input = Integer.parseInt(br.readLine());
            if (input>0) {
                minQueue.add(input);
            } else {
                if (!minQueue.isEmpty()) {
                    System.out.println(minQueue.poll());
                }
                else{
                    System.out.println(0);
                }
            }
        }
        br.close();
    }
}
//
//public class B1927_최소힙 {
//
//    private static int index =0;
//    private static int[] heap = new int[100001];
//
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int N = sc.nextInt();
//
//        setMinHeap();
//        for(int i=0;i<N;i++) {
//            int value = sc.nextInt();
//            if (value == 0) {
//                System.out.println(getMin());
//            } else {
//                addValue(value);
//            }
//        }
//    }
//
//    private static void setMinHeap(){
//        Arrays.fill(heap,Integer.MAX_VALUE);
//    }
//
//    private static void addValue(int value){
//        heap[++index] = value;
//        int i=index;
//        boolean flag = true;
//        while(flag && i!=1){
//            flag = false;
//            if(heap[i/2] > Math.min(heap[i], heap[i+1])){
//                if(heap[i] <= heap[i+1]){
//                    int temp = heap[i/2];
//                    heap[i/2] = heap[i];
//                    heap[i] = temp;
//                }else{
//                    int temp = heap[i/2];
//                    heap[i/2] = heap[i+1];
//                    heap[i+1] = temp;
//                }
//                flag = true;
//                i = i/2;
//            }
//
//        }
//    }
//
//    private static int getMin(){
//        if (heap[1] == Integer.MAX_VALUE) return 0;
//
//        int min = heap[1];
//        heap[1] = heap[index];
//        heap[index--] = Integer.MAX_VALUE;
//
//        if(index != -1){
//            int i=index;
//            boolean flag = true;
//            while(flag && 2*i+1<100001){
//                flag = false;
//                if(heap[i] > Math.min(heap[2*i], heap[2*i+1])){
//                    if(heap[2*i] <= heap[2*i+1]){
//                        int temp = heap[i];
//                        heap[i] = heap[2*i];
//                        heap[2*i] = temp;
//                        i = 2*i+1;
//                    }else{
//                        int temp = heap[i];
//                        heap[i] = heap[2*i+1];
//                        heap[2*i+1] = temp;
//                        i = 2*i;
//                    }
//                    flag = true;
//                }
//            }
//        }
//        return min;
//    }
//}

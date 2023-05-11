package 이진트리;

import java.util.ArrayList;
import java.util.Scanner;

public class B1068_트리 {
    private static int leafNode=0;
    private static int M;
    private static ArrayList<Integer> tree[];
    private static boolean[] isVisited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        tree = new ArrayList[N];
        isVisited = new boolean[N];
        isVisited[0] = true;

        for(int i=0;i<N;i++){
            tree[i] = new ArrayList<>();
        }

        for(int i=0;i<N;i++){
            int parent = sc.nextInt();
            if(parent != -1)
                tree[parent].add(i);
        }
        M = sc.nextInt();

        DFS(0);

        System.out.println(leafNode);

    }

    private static void DFS(int parentNode){
        if(parentNode == M) return;

        int childNodeCount=0;
        for(int childNode : tree[parentNode]){
            if(childNode == M) continue;
            if(isVisited[childNode]) continue;
            isVisited[childNode] = true;

            childNodeCount++;
            DFS(childNode);
        }
        if(childNodeCount ==0) leafNode++;
    }
}

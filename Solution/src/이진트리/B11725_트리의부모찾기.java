package 이진트리;

import java.util.ArrayList;
import java.util.Scanner;

public class B11725_트리의부모찾기 {
    private static ArrayList<Integer> tree[];
    private static boolean[] isVisited;
    private static int[] parents;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        tree = new ArrayList[N+1];
        isVisited = new boolean[N+1];
        parents = new int[N+1];

        for(int i=1;i<N+1;i++){
            tree[i] = new ArrayList<>();
        }

        for(int i=0;i<N-1;i++){
            int first = sc.nextInt();
            int second = sc.nextInt();

            tree[first].add(second);
            tree[second].add(first);
        }

        DFS(1);

        for(int i=2;i<N+1;i++){
            System.out.println(parents[i]);
        }
    }

    private static void DFS(int v1){
        for(Integer v2 : tree[v1]){
            if(isVisited[v2]) continue;
            isVisited[v2] = true;

            parents[v2] = v1;

            DFS(v2);
        }
    }


}


//BufferedReader - 뭉탱이, 분리없이 사용필요
//Scanner - 뭉탱이, 바로 분리필요


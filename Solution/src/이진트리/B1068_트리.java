package 이진트리;

import java.util.ArrayList;
import java.util.Scanner;

public class B1068_트리 {
    private static int answer =0;
    private static int deleteNode=0;
    private static ArrayList<Integer>[] tree;
    private static boolean[] isVisited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        tree = new ArrayList[N];
        isVisited = new boolean[N];

        for(int i=0;i<N;i++){
            tree[i] = new ArrayList<>();
        }

        int root =0;
        for(int i=0;i<N;i++){
            int p = sc.nextInt();
            if(p != -1){
                tree[p].add(i);
                tree[i].add(p);
            }else{
                root = i;
            }

        }
        deleteNode = sc.nextInt();

        if(deleteNode != root){
            DFS(root);
            System.out.println(answer);
        }else{
            System.out.println(0);
        }

    }

    private static void DFS(int parentNode){
        isVisited[parentNode] = true;

        int childNodeCount=0;
        for(int childNode : tree[parentNode]){
            if(childNode == deleteNode) continue;
            if(isVisited[childNode]) continue;

            childNodeCount++;
            DFS(childNode);
        }
        if(childNodeCount ==0) answer++;
    }
}

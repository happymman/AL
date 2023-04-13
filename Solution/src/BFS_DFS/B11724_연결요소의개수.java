package BFS_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B11724_연결요소의개수 {
    private static ArrayList<Integer>[] A;
    private static boolean[] isVisited;

    private static void DFS(int v){
        if(isVisited[v]) return;
        isVisited[v] = true;

        for(int i : A[v]){
            if(isVisited[i]) continue;
            DFS(i);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        A = new ArrayList[N+1];
        isVisited = new boolean[N+1];

        for(int i=1;i<=N;i++){
            A[i] = new ArrayList<>();
        }

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            A[first].add(second);
            A[second].add(first);
        }

        int count=0;
        for(int i=1;i<=N;i++){
            if(isVisited[i]) continue;
            count++;
            DFS(i);

        }
        System.out.println(count);
    }
}

package 그리디.MST;

import java.util.PriorityQueue;
import java.util.Scanner;

public class P1197_최소신장트리 {


    private static PriorityQueue<pEdge> pq = new PriorityQueue<>();

    private static int[] parent;

    private static int useEdge = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        parent = new int[n+1];
        for(int i=1;i<n;i++){
            parent[i] = i;
        }

        for(int i=0;i<m;i++){
            int s= sc.nextInt();
            int e= sc.nextInt();
            int w= sc.nextInt();
            pq.add(new pEdge(s,e,w));
        }

        int weightSum=0;
        while(useEdge<n-1){
            pEdge now = pq.poll();

            if(find(now.s) != find(now.e)){
                union(now.s, now.e);
                weightSum += now.w;
                useEdge++;
            }
        }
        System.out.println(weightSum);
    }

    static int find(int v){
        if(parent[v] == v){
            return v;
        }else{
            return parent[v] = find(parent[v]);
        }
    }

    static void union(int v1, int v2){ //부모다르면, 부모끼리 연결
        int v1Parent = find(v1);
        int v2Parent = find(v2);
        if(v2Parent != v1Parent){
            parent[v2Parent] = v1Parent;
        }
    }
    static class pEdge implements Comparable<pEdge>{
        int s;
        int e;
        int w;

        public pEdge(int s, int e, int w){
            this.s=s;
            this.e=e;
            this.w=w;
        }

        @Override
        public int compareTo(pEdge o){
            return this.w - o.w;
        }

    }
}

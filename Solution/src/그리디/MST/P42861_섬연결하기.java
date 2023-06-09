package 그리디.MST;

import java.util.PriorityQueue;

public class P42861_섬연결하기{

    private static int[] parent;
    private static PriorityQueue<pEdge> pq = new PriorityQueue<>();
    private static int useEdge =0;
    private static int weightSum =0;

    public int solution(int n, int[][] costs) {

        parent = new int[n+1];
        for(int i=1;i<=n;i++){
            parent[i]=i;
        }

        for(int[] cost : costs){
            pq.add(new pEdge(cost[0], cost[1], cost[2]));
        }


        while(useEdge< n-1){
            pEdge now = pq.poll();

            if(find(now.s) != find(now.e)){
                union(now.s, now.e);
                useEdge++;
                weightSum+=now.w;
            }
        }

        return weightSum;
    }

    private static int find(int v){
        if(v == parent[v]){
            return v;
        }else{
            return parent[v] = find(parent[v]);
        }
    }

    private static void union(int v1, int v2){
        int v1Parent = find(v1);
        int v2Parent = find(v2);

        if(v1Parent != v2Parent){
            parent[v2Parent] = v1Parent;
        }
    }

    private static class pEdge implements Comparable<pEdge>{
        int s;
        int e;
        int w;

        pEdge(int s, int e, int w){
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
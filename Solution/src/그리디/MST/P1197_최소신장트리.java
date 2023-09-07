package 그리디.MST;

import java.util.PriorityQueue;
import java.util.Scanner;

//public class P1197_최소신장트리 {
//    private static PriorityQueue<pEdge> pq = new PriorityQueue<>((o1,o2)->o1.w-o2.w);
//
//    private static int[] parent;
//    private static int useEdge = 0;
//
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//
//        int n = sc.nextInt();
//        int m = sc.nextInt();
//
//        parent = new int[n+1];
//        for(int i=1;i<n;i++){
//            parent[i] = i;
//        }
//
//        for(int i=0;i<m;i++){
//            int s= sc.nextInt();
//            int e= sc.nextInt();
//            int w= sc.nextInt();
//            pq.add(new pEdge(s,e,w));
//        }
//
//        int weightSum=0;
//        while(useEdge<n-1){ //mst의 최대 엣지개수는 n-1
//            pEdge now = pq.poll();
//
//            if(find(now.s) != find(now.e)){ //부모가 다르면 -> 이어져있지않으면
//                union(now.s, now.e); //이음
//                weightSum += now.w; //
//                useEdge++;
//            }
//        }
//        System.out.println(weightSum);
//    }
//
//    //해당 vertex의 (대표노드 나올때까지) 대표노드 찾기
//    static int find(int v){
//        if(parent[v] == v){ //본인이 대표인 경우
//            return v; //본인 return
//        }else{
//            return parent[v] = find(parent[v]);// 대표노드 저장하고 return
//        }
//    }
//
//    //대표노드끼리 합치기
//    static void union(int v1, int v2){
//        int v1Parent = find(v1);
//        int v2Parent = find(v2);
//
//        if(v2Parent != v1Parent){
//            parent[v2Parent] = v1Parent;
//        }
//    }
//    static class pEdge{
//        int s;
//        int e;
//        int w;
//
//        public pEdge(int s, int e, int w){
//            this.s=s;
//            this.e=e;
//            this.w=w;
//        }
//    }
//}

public class P1197_최소신장트리 {

    static int[] parent;
    static class Edge{
        int s;
        int e;
        int w;

        Edge(int s, int e, int w){
            this.s=s;
            this.e=e;
            this.w=w;
        }
    }

    public static void main(String[] args) {
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1,o2)->o1.w-o2.w);

        Scanner sc = new Scanner(System.in);
        int V= sc.nextInt();
        int E = sc.nextInt();

        parent = new int[V+1];
        for(int i=1;i<=V;i++){
            parent[i]=i;
        }

        while(E-->0){ //E만큼 등록
            int s=sc.nextInt();
            int e=sc.nextInt();
            int w=sc.nextInt();
            pq.add(new Edge(s, e, w));
        }

        int useEdge=0;
        long edgeSum=0;
        while(useEdge<V-1){
            Edge now = pq.poll();

            if(find(now.s) != find(now.e)){
                union(now.s, now.e);
                edgeSum+=now.w;
                useEdge++;
            }
        }
        System.out.println(edgeSum);
    }

    //대표노드 찾기(-> 싸이클 여부 확인)
    static int find(int v){
        if(parent[v]==v){
            return v;
        }else{ //대표노드가 본인이 아닌 경우
            return parent[v] = find(parent[v]);
        }
    }

    static void union(int v1, int v2){
        int v1Parent=find(v1);
        int v2Parent=find(v2);

        if(v1Parent != v2Parent){
            parent[v2Parent]= v1Parent;
        }
    }

}

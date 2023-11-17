package 그리디.다익스트라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//public class B1916_최소비용구하기 {
//
//    static int[] distance;
//    static boolean[] isVisited;
//    static ArrayList<Edge>[] list;
//    static class Edge{
//        int vertex;
//        int value;
//
//        public Edge(int vertex, int value){
//            this.vertex = vertex;
//            this.value = value;
//        }
//    }
//
//    public static void main(String[] args) throws IOException {
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st;
//
//        int N = Integer.parseInt(br.readLine());
//        int M = Integer.parseInt(br.readLine());
//
//        distance = new int[N+1];
//        isVisited = new boolean[N+1];
//        Arrays.fill(distance, Integer.MAX_VALUE);
//
//        list = new ArrayList[N+1];
//        for(int i=1;i<N+1;i++){
//            list[i] = new ArrayList<>();
//        }
//
//        for(int i=0;i<M;i++){
//            st = new StringTokenizer(br.readLine());
//            int start = Integer.parseInt(st.nextToken());
//            int end = Integer.parseInt(st.nextToken());
//            int weight = Integer.parseInt(st.nextToken());
//
//            list[start].add(new Edge(end, weight));
//        }
//
//        st = new StringTokenizer(br.readLine());
//        int start = Integer.parseInt(st.nextToken());
//        int end = Integer.parseInt(st.nextToken());
//
//        PriorityQueue pq = new PriorityQueue<Edge>((o1,o2) -> o1.value - o2.value);
//        pq.add(new Edge(start, 0));
//        distance[start] = 0;
//
//        while(!pq.isEmpty()){
//            Edge now = (Edge)pq.poll();
//            int cVertex = now.vertex;
//
//            if(isVisited[cVertex]) continue;
//            isVisited[cVertex] = true;
//
//            for(int i=0;i<list[cVertex].size();i++){
//                Edge temp = list[cVertex].get(i);
//
//                int next = temp.vertex;
//                int weight = temp.value;
//
//                if(isVisited[next]) continue;
//                if(distance[next] > distance[cVertex]+weight){
//                    distance[next] = distance[cVertex]+weight;
//                    pq.add(new Edge(next, distance[next]));
//                }
//            }
//        }
//
//        System.out.println(distance[end]);
//    }
//}

public class B1916_최소비용구하기 {

    static int[] distance;
    static boolean[] isVisited;
    static ArrayList<Edge>[] tree;
    static PriorityQueue<Edge> pq = new PriorityQueue<>((o1,o2)->o1.w-o2.w);

    static int start;
    static int target;
    static class Edge{
        int v;
        int w;
        Edge(int v, int w){
            this.v=v;
            this.w=w;
        }
    }
    public static void main(String[] args) {
        input();
        pro();
        System.out.println(distance[target]);
    }

    static void input(){
        Scanner sc = new Scanner(System.in);
        int v=sc.nextInt();
        int e=sc.nextInt();

        isVisited = new boolean[v+1];
        distance = new int[v+1];
        tree = new ArrayList[v+1];

        for(int i=0;i<tree.length;i++){
            tree[i] = new ArrayList<>();
        }
        for(int i=0;i<e;i++){
            int v1 = sc.nextInt();
            int v2 =sc.nextInt();
            int w=sc.nextInt();

            tree[v1].add(new Edge(v2,w));
        }

        start = sc.nextInt();
        target = sc.nextInt();

    }

    static void pro(){
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;

        pq.add(new Edge(start,0));

        while(!pq.isEmpty()){
            Edge now = pq.poll();
            if(isVisited[now.v]) continue;
            isVisited[now.v] = true;

            for(Edge next : tree[now.v]){
                if(distance[next.v] > distance[now.v]+next.w){
                    distance[next.v] = distance[now.v]+next.w;
                    pq.add(new Edge(next.v, distance[next.v]));
                }
            }
        }
    }
}

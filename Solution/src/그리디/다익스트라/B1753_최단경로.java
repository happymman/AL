package 그리디.다익스트라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//public class B1753_최단경로 {
//
//    static ArrayList<Edge>[] list;
//    static int[] distance;
//    static class Edge{
//        int vertex;
//        int weight;
//
//        public Edge(int vertex, int weight){
//            this.vertex = vertex;
//            this.weight = weight;
//        }
//    }
//
//    public static void main(String[] args) throws IOException {
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st;
//
//        st = new StringTokenizer(br.readLine());
//        int v = Integer.parseInt(st.nextToken());
//        int e = Integer.parseInt(st.nextToken());
//
//        int start = Integer.parseInt(br.readLine());
//
//        list = new ArrayList[v+1];
//        distance = new int[v+1];
//
//        for(int i=1;i<v+1;i++){
//            list[i] = new ArrayList<>();
//        }
//
//        for(int i=0;i<e;i++){
//            st = new StringTokenizer(br.readLine());
//            int v1=  Integer.parseInt(st.nextToken());
//            int v2=  Integer.parseInt(st.nextToken());
//            int w=  Integer.parseInt(st.nextToken());
//
//            list[v1].add(new Edge(v2, w));
//        }
//
//        Arrays.fill(distance, Integer.MAX_VALUE);
//        distance[start] = 0;
//
//        PriorityQueue pq = new PriorityQueue<Edge>((o1,o2)->o1.weight - o2.weight);
//        pq.add(new Edge(start,0));
//
//        while(!pq.isEmpty()){
//            Edge current = (Edge) pq.poll(); //해당 점까지의 최단거리 확정
//            int c_v = current.vertex;
//
//            for(int i = 0; i<list[c_v].size(); i++){ //(해당 점과 연결된) 엣지 선택
//                Edge temp = list[c_v].get(i);
//                int next = temp.vertex;
//                int weight = temp.weight;
//                if(distance[next] > distance[c_v]+weight){ //해당 엣지를 통한길이 최단거리이면
//                    distance[next] = distance[c_v]+weight; //업데이트
//                    pq.add(new Edge(next, distance[next]));
//                }
//            }
//        }
//
//        for(int i=1;i<v+1;i++){
//            if(distance[i] != Integer.MAX_VALUE){
//                System.out.println(distance[i]);
//            }else{
//                System.out.println("INF");
//            }
//        }
//
//
//    }
//}

public class B1753_최단경로 {
    static boolean[] visited;
    static int[] distance;
    static ArrayList<Edge>[] tree;
    static PriorityQueue<Edge> pq = new PriorityQueue<Edge>((o1, o2) -> o1.w-o2.w);
    static int v;
    static int e;
    static int start;
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

        for(int i=1;i<distance.length;i++){
            if(distance[i]!=Integer.MAX_VALUE) System.out.println(distance[i]);
            else System.out.println("INF");
        }
    }

    static void input(){
        Scanner sc = new Scanner(System.in);
        v = sc.nextInt();
        e = sc.nextInt();
        start = sc.nextInt();

        distance = new int[v+1];
        visited = new boolean[v+1];
        tree = new ArrayList[v+1];
        for(int i=1;i<tree.length;i++){
            tree[i] = new ArrayList<>();
        }

        for(int i=0;i<e;i++){
            int s=sc.nextInt();
            int e=sc.nextInt();
            int w=sc.nextInt();

            tree[s].add(new Edge(e,w));
        }
    }
    static void pro(){
        Arrays.fill(distance,Integer.MAX_VALUE);
        distance[start] =0;

        pq.add(new Edge(start,0));

        while(!pq.isEmpty()){
            Edge now = pq.poll();
            if(visited[now.v]) continue;
            visited[now.v] = true;

            for(Edge next : tree[now.v]){
                if(distance[next.v] > distance[now.v]+next.w){
                    distance[next.v] = distance[now.v]+next.w;
                    pq.add(new Edge(next.v, distance[next.v]));
                }
            }
        }
    }
}

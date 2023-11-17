package 그리디.다익스트라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//public class B1854_K번째최단거리 {
//
//        static class Edge{
//            int vertex;
//            int value;
//            Edge(int vertex, int value){
//                this.vertex = vertex;
//                this.value = value;
//            }
//        }
//
//        static int n;
//        static int k;
//        static PriorityQueue<Integer>[] distance;
//        static boolean[] canVisited;
//
//    public static void main(String[] args) throws IOException {
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        //n,m,k입력받기
//        StringTokenizer st;
//        st= new StringTokenizer(br.readLine());
//        n= Integer.parseInt(st.nextToken());
//        int m= Integer.parseInt(st.nextToken());
//        k= Integer.parseInt(st.nextToken());
//
//
//        distance = new PriorityQueue[n+1];
//        for(int i=1;i<=n;i++){
//            distance[i] = new PriorityQueue<Integer>(k, Comparator.reverseOrder());
//        }
//
//        //br, st로 list채우기
//        ArrayList<Edge>[] list = new ArrayList[n+1];
//        for(int i=1;i<=n;i++){
//            list[i] = new ArrayList<>();
//        }
//        for(int i=0;i<m;i++){
//            st=new StringTokenizer(br.readLine());
//            int s= Integer.parseInt(st.nextToken());
//            int e= Integer.parseInt(st.nextToken());
//            int w= Integer.parseInt(st.nextToken());
//
//            list[s].add(new Edge(e,w));
//        }
//
//        PriorityQueue pq = new PriorityQueue<Edge>((o1,o2) -> o1.value - o2.value); //오름차순
//        pq.add(new Edge(1,0));
//        distance[1].add(0);
//
//        while(!pq.isEmpty()){
////            if(isAllOverK()) break;
//
//            Edge now = (Edge)pq.poll();
//            int c_v = now.vertex; //해당 vertex까지
//            int total = now.value; //경로 총합이 value인 Edge가 있다.
//
//            for(int i=0;i<list[c_v].size();i++){
//                Edge temp = list[c_v].get(i);
//                int next = temp.vertex;
//                int weight = temp.value;
//
//                if(distance[next].size()<k){
//                    distance[next].add(total+weight);
//                    pq.add(new Edge(next, total+weight));
//                }else if(distance[next].peek() > total+weight){
//                    distance[next].poll();
//                    distance[next].add(total+weight);
//                    pq.add(new Edge(next, total+weight));
//                }
//            }
//        }
//
//        for(int i=1;i<=n;i++) {
//            if (distance[i].size() == k) {
//                System.out.println(distance[i].peek());
//            } else {
//                System.out.println(-1);
//            }
//        }
//    }
//}

public class B1854_K번째최단거리 {
    static PriorityQueue<Integer>[] distance;
    static PriorityQueue<Edge> pq = new PriorityQueue<>((o1,o2)->o1.w - o2.w);
    static ArrayList<Edge>[] tree;
    static int N;
    static int M;
    static int K;

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
        for(int i=1;i<=N;i++){
            if(distance[i].size()<K){
                System.out.println(-1);
            }else{
                System.out.println(distance[i].peek());
            }
        }
    }

    static void input(){
        Scanner sc = new Scanner(System.in);
        N= sc.nextInt();
        M=sc.nextInt();
        K=sc.nextInt();

        tree = new ArrayList[N+1];
        distance = new PriorityQueue[N+1];
        for(int i=1;i<N+1;i++){
            tree[i] = new ArrayList<>();
        }
        for(int i=1;i<N+1;i++){
            distance[i] = new PriorityQueue<>((o1, o2) -> o2-o1);
        }

        for(int i=0;i<M;i++){
            int s=sc.nextInt();
            int e=sc.nextInt();
            int w= sc.nextInt();

            tree[s].add(new Edge(e,w));
        }
    }

    static void pro(){
        pq.add(new Edge(1,0));
        distance[1].add(0);

        while(!pq.isEmpty()){
            Edge now = pq.poll();

            for(Edge next : tree[now.v]){

                if(distance[next.v].size() < K){ //꽉차있는게 아닐때
                    distance[next.v].add(now.w + next.w); //넣는다
                    pq.add(new Edge(next.v, now.w+next.w));
                }else if(distance[next.v].size() == K && distance[next.v].peek() > now.w+next.w){
                    distance[next.v].poll(); //빼내고
                    distance[next.v].add(now.w+next.w); //넣는다
                    pq.add(new Edge(next.v, now.w+next.w));
                }
            }
        }
    }
}
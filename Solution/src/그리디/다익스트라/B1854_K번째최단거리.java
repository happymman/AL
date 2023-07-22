package 그리디.다익스트라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B1854_K번째최단거리 {

        static class Edge{
            int vertex;
            int value;
            Edge(int vertex, int value){
                this.vertex = vertex;
                this.value = value;
            }
        }

        static int n;
        static int k;
        static PriorityQueue<Integer>[] distance;
        static boolean[] canVisited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //n,m,k입력받기
        StringTokenizer st;
        st= new StringTokenizer(br.readLine());
        n= Integer.parseInt(st.nextToken());
        int m= Integer.parseInt(st.nextToken());
        k= Integer.parseInt(st.nextToken());


        distance = new PriorityQueue[n+1];
        for(int i=1;i<=n;i++){
            distance[i] = new PriorityQueue<Integer>(k, Comparator.reverseOrder());
        }

        //br, st로 list채우기
        ArrayList<Edge>[] list = new ArrayList[n+1];
        for(int i=1;i<=n;i++){
            list[i] = new ArrayList<>();
        }
        for(int i=0;i<m;i++){
            st=new StringTokenizer(br.readLine());
            int s= Integer.parseInt(st.nextToken());
            int e= Integer.parseInt(st.nextToken());
            int w= Integer.parseInt(st.nextToken());

            list[s].add(new Edge(e,w));
        }

        PriorityQueue pq = new PriorityQueue<Edge>((o1,o2) -> o1.value - o2.value); //오름차순
        pq.add(new Edge(1,0));
        distance[1].add(0);

        while(!pq.isEmpty()){
//            if(isAllOverK()) break;

            Edge now = (Edge)pq.poll();
            int c_v = now.vertex; //해당 vertex까지
            int total = now.value; //경로 총합이 value인 Edge가 있다.

            for(int i=0;i<list[c_v].size();i++){
                Edge temp = list[c_v].get(i);
                int next = temp.vertex;
                int weight = temp.value;

                if(distance[next].size()<k){
                    distance[next].add(total+weight);
                    pq.add(new Edge(next, total+weight));
                }else if(distance[next].peek() > total+weight){
                    distance[next].poll();
                    distance[next].add(total+weight);
                    pq.add(new Edge(next, total+weight));
                }
            }
        }

        for(int i=1;i<=n;i++) {
            if (distance[i].size() == k) {
                System.out.println(distance[i].peek());
            } else {
                System.out.println(-1);
            }
        }
    }
}

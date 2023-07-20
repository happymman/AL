package 그리디.다익스트라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B1753_최단경로 {

    static ArrayList<Edge>[] list;
    static int[] distance;
    static class Edge{
        int vertex;
        int weight;

        public Edge(int vertex, int weight){
            this.vertex = vertex;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        int start = Integer.parseInt(br.readLine());

        list = new ArrayList[v+1];
        distance = new int[v+1];

        for(int i=1;i<v+1;i++){
            list[i] = new ArrayList<>();
        }

        for(int i=0;i<e;i++){
            st = new StringTokenizer(br.readLine());
            int v1=  Integer.parseInt(st.nextToken());
            int v2=  Integer.parseInt(st.nextToken());
            int w=  Integer.parseInt(st.nextToken());

            list[v1].add(new Edge(v2, w));
        }

        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;

        PriorityQueue pq = new PriorityQueue<Edge>((o1,o2)->o1.weight - o2.weight);
        pq.add(new Edge(start,0));

        while(!pq.isEmpty()){
            Edge current = (Edge) pq.poll();
            int c_v = current.vertex;

            for(int i = 0; i<list[c_v].size(); i++){

                Edge temp = list[c_v].get(i);
                int next = temp.vertex;
                int weight = temp.weight;
                if(distance[next] > distance[c_v]+weight){
                    distance[next] = distance[c_v]+weight;
                    pq.add(new Edge(next, distance[next]));
                }
            }
        }

        for(int i=1;i<v+1;i++){
            if(distance[i] != Integer.MAX_VALUE){
                System.out.println(distance[i]);
            }else{
                System.out.println("INF");
            }
        }


    }
}

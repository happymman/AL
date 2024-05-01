import java.util.PriorityQueue;
import java.util.Scanner;
public class Main {

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
package 그리디.다익스트라;


/*
1차풀이 - 솔루션
 */
// import java.util.*;
// public class P118669_등산코스정하기 {
//     static ArrayList<Node>[] list;
//     static PriorityQueue<Node> pq = new PriorityQueue<>((o1,o2)-> o1.value - o2.value);
//     static int[] maxIntensities;
//
//     static class Node{
//         int vertex;
//         int value;
//
//         Node(int vertex, int value){
//             this.vertex = vertex;
//             this.value = value;
//         }
//     }
//
//     public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
//         //list초기화
//         list = new ArrayList[n+1];
//         for(int i=1;i<n+1;i++){
//             list[i] = new ArrayList<>();
//         }
//
//         //list초기화
//         maxIntensities = new int[n+1];
//         Arrays.fill(maxIntensities, Integer.MAX_VALUE);
//
//         //list에 양방향으로 등록
//         for(int[] path : paths){
//             int s = path[0];
//             int e = path[1];
//             int w = path[2];
//
//             if(isGate(s, gates) || isSummit(e,summits) ){
//                 list[s].add(new Node(e, w));
//             }else if(isSummit(s,summits) || isGate(e,gates)){
//                 list[e].add(new Node(s, w));
//             }else{
//                 list[s].add(new Node(e, w));
//                 list[e].add(new Node(s, w));
//             }
//         }
//
//         for(int gate : gates){
//             pq.add(new Node(gate, 0));
//         }
//
//         while(!pq.isEmpty()){
//             Node now = pq.poll();
//             if(now.value > maxIntensities[now.vertex]) continue;
//
//             for(int i=0;i<list[now.vertex].size();i++){
//                 Node next = list[now.vertex].get(i);
//
//                 int max = Math.max(next.value, now.value);
//
//                 if(maxIntensities[next.vertex] > max){
//                     maxIntensities[next.vertex] = max;
//                     pq.add(new Node(next.vertex, max));
//                 }
//             }
//         }
//
//         int mi = Integer.MAX_VALUE;
//         int mv = Integer.MAX_VALUE;
//         Arrays.sort(summits);
//         for(int summit : summits){
//             if(mv > maxIntensities[summit]){
//                 mv = maxIntensities[summit];
//                 mi = summit;
//             }
//         }
//
//         return new int[]{mi, mv};
//     }
//
//         static boolean isGate(int check, int[] gates){
//             for(int gate : gates){
//                 if(check == gate) return true;
//             }
//             return false;
//         }
//
//         static boolean isSummit(int check, int[] summits){
//             for(int summit : summits){
//                 if(check == summit) return true;
//             }
//             return false;
//         }
// }

/*
2차풀이 - 솔루션
최소 maxIntensity 배열 사용에 대한 생각을 하지 못했음.
 */
import java.util.*;

public class P118669_등산코스정하기 {
    static ArrayList<Node>[] graph;
    static int[] intensity;
    static PriorityQueue<Node> pq = new PriorityQueue<>((o1,o2)->o1.value-o2.value);

    static int minSummit = Integer.MAX_VALUE;
    static int minIntensity = Integer.MAX_VALUE;

    static class Node{
        int v;
        int value;
        Node(int v, int value){
            this.v=v;
            this.value=value;
        }
    }

    public int[] solution(int n , int[][] paths, int[] gates, int[] summits){
        intensity = new int[n+1];
        graph = new ArrayList[n+1];
        for(int i=1;i<=n;i++){
            graph[i] = new ArrayList<Node>();
        }

        for(int[] path : paths){
            int s = path[0];
            int e = path[1];
            int w = path[2];

            if(isGate(s,gates) || isSummit(e,summits)){ //s가 출입구, e가 산봉우리
                graph[s].add(new Node(e,w));
            }else if(isSummit(s,summits) || isGate(e,gates)){
                graph[e].add(new Node(s,w));
            }else{
                graph[s].add(new Node(e,w));
                graph[e].add(new Node(s,w));
            }
        }

        Arrays.fill(intensity, Integer.MAX_VALUE);
        for(int gate : gates){ //출입구 선택
            pq.add(new Node(gate, 0));
            intensity[gate] = 0;
        }

        while(!pq.isEmpty()){
            Node now = pq.poll();
            if(now.value > intensity[now.v]) continue;

            for(Node next : graph[now.v]){
                int maxI = Math.max(next.value, now.value);

                if(intensity[next.v] > maxI){
                    intensity[next.v] = maxI;
                    pq.add(new Node(next.v, maxI));
                    updateToSummit(next.v, maxI, summits);
                }

            }
        }
        return new int[]{minSummit, minIntensity};
    }

    static void updateToSummit(int v, int maxI,int[] summits){
        if(isSummit(v, summits)){
            if(minIntensity > maxI){ //최소 maxI 갱신(+산봉우리)
                minIntensity = maxI;
                minSummit = v;
            }else if(minIntensity == maxI && minSummit > v){
                minSummit = v;
            }
        }
    }

    static boolean isGate(int check, int[] gates){
        for(int gate : gates){
            if(check == gate) return true;
        }
        return false;
    }


    static boolean isSummit(int check, int[] summits){
        for(int summit : summits){
            if(check == summit) return true;
        }
        return false;
    }
}

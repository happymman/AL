package 그리디.다익스트라;
import java.util.*;

public class P49189_가장먼노드 {
    public static void main(String[] args) {
        P49189_가장먼노드Solution s = new P49189_가장먼노드Solution();

        int n=6;
        int[][] edge = {{3,6},{4,3},{3,2},{1,3},{1,2},{2,4},{5,2}};
        System.out.println(s.solution(n, edge));

    }

}


class P49189_가장먼노드Solution {

    static boolean[][] arr;
    static int[] distance;
    static boolean[] isVisited;

    static class Node {
        int vertex;
        int value;
        Node(int vertex, int value){
            this.vertex = vertex;
            this.value = value;
        }
    }

    public int solution(int n, int[][] edge) {

        arr = new boolean[n+1][n+1];
        distance = new int[n+1];
        isVisited = new boolean[n+1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[0] = 0;
        distance[1] = 0;

        for(int[] e : edge){
            arr[e[0]][e[1]] = true;
            arr[e[1]][e[0]] = true;
        }

        Queue q = new LinkedList<>();
        q.add(new Node(1,0));

        while(!q.isEmpty()){
            Node now = (Node)q.poll();
            int c_v = now.vertex;

            if(isVisited[c_v]) continue;
            isVisited[c_v] = true;

            for(int adjNode=1 ; adjNode<arr[c_v].length;adjNode++){
                if(arr[c_v][adjNode]){
                    if(distance[adjNode] > 1 + now.value){
                        distance[adjNode] = 1+now.value;
                        q.add(new Node(adjNode, now.value+1));
                    }
                }
            }
        }

        int max = 0;
        int maxCount=0;
        for(int i=0;i<distance.length;i++){
            if(distance[i] > max) max = distance[i];
        }

        for(int i=0;i<distance.length;i++){
            if(distance[i] == max) maxCount++;
        }
        return maxCount;
    }
}
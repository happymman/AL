package 완전탐색.DFS;
import java.util.*;

public class P86971_전력망둘로나누기 {
    public static void main(String[] args) {
        P86971_전력망둘로나누기Solution s = new P86971_전력망둘로나누기Solution();

        int[][] wires = {{1,3},{2,3},{3,4},{4,5},{4,6},{4,7},{7,8},{7,9}};
        System.out.println(s.solution(9, wires));
    }
}


class P86971_전력망둘로나누기Solution {
    private static boolean[] isVisited;
    private static ArrayList<Integer>[] tree;

    public int solution(int n, int[][] wires) {

        tree = new ArrayList[n+1];
        for(int i=1;i<=n;i++){
            tree[i] = new ArrayList<Integer>();
        }

        for(int[] wire : wires){
            tree[wire[0]].add(wire[1]);
            tree[wire[1]].add(wire[0]);
        }

        int min = Integer.MAX_VALUE;
        for(int[] wire : wires){
            isVisited = new boolean[n+1];
            Set<Integer> wireSet = new HashSet<>();
            for(int node : wire){
                wireSet.add(node);
            }

            dfs(1,wireSet);
            int visitCount=0;
            for(boolean check : isVisited){
                if(check == true) visitCount++;
            }
            int diff = Math.abs((n-visitCount)-visitCount);
            if(min> diff) min = diff;
        }

        return min;
    }

    static void dfs(int v1, Set<Integer> wireSet){
        isVisited[v1] = true;
        for(int v2 : tree[v1]){
            if(isVisited[v2]) continue;
            if(wireSet.contains(v1) && wireSet.contains(v2)) continue;
            dfs(v2, wireSet);
        }
    }
}

//2회 풀이
//import java.util.*;
//
//public class Solution{
//    static boolean[] isVisited;
//    static ArrayList<Integer>[] tree;
//
//    public int solution(int n, int[][] wires){
//        tree = new ArrayList[n+1];
//
//        for(int i=1;i<tree.length;i++){
//            tree[i] = new ArrayList<>();
//        }
//
//        for(int[] wire : wires){
//            tree[wire[0]].add(wire[1]);
//            tree[wire[1]].add(wire[0]);
//        }
//
//        int min = Integer.MAX_VALUE;
//        for(int[] wire : wires){
//            isVisited = new boolean[n+1];
//
//            Set<Integer> wireNodes = new HashSet<>();
//            wireNodes.add(wire[0]);
//            wireNodes.add(wire[1]);
//
//            isVisited[1] = true;
//            dfs(1, wireNodes);
//
//            int visitCount=0;
//            for(int i=0;i<isVisited.length;i++){
//                if(isVisited[i]) visitCount++;
//            }
//            int diff  = Math.abs((n-visitCount)-visitCount);
//            if(min>diff) min = diff;
//        }
//        return min;
//    }
//
//    static void dfs(int v1, Set<Integer> wireNodes){
//        for(int v2 : tree[v1]){
//            if(isVisited[v2]) continue;
//            if(wireNodes.contains(v1)&& wireNodes.contains(v2)) continue;
//            isVisited[v2] = true;
//            dfs(v2, wireNodes);
//        }
//    }
//}
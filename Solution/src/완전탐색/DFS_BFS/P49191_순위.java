package 완전탐색.DFS_BFS;


import java.util.*;

public class P49191_순위 {

    static ArrayList<Integer>[] winGraph;
    static ArrayList<Integer>[] loseGraph;
    static boolean[] isVisited;

    static int win=0;
    static int lose=0;

    public int solution(int n, int[][] results) {

        winGraph = new ArrayList[n+1];
        loseGraph = new ArrayList[n+1];
        for(int i=1;i<=n;i++){
            winGraph[i] = new ArrayList<>();
            loseGraph[i] = new ArrayList<>();
        }

        for(int[] result : results){
            int win = result[0];
            int lose = result[1];

            winGraph[win].add(lose);
            loseGraph[lose].add(win);
        }

        isVisited = new boolean[n+1];

        int answer=0;
        for(int node=1;node<=n;node++){

            winDfs(node);
            for(int i=1;i<=n;i++){
                if(isVisited[i]) win++;
            }
            Arrays.fill(isVisited, false);

            loseDfs(node);
            for(int i=1;i<=n;i++){
                if(isVisited[i]) lose++;
            }
            Arrays.fill(isVisited, false);

            //내가 이기는사람, 내가 지는사람의 합이 전체-1라면 그사람은 순위가 정해진다.
            if(win+lose == n-1) answer++;
            win=0;
            lose=0;
        }

        return answer;
    }

    static void winDfs(int node){

        for(int i=0;i<winGraph[node].size();i++){
            int next = winGraph[node].get(i);

            if(isVisited[next]) continue;
            isVisited[next] = true;
            winDfs(next);
        }
    }

    static void loseDfs(int node){

        for(int i=0;i<loseGraph[node].size();i++){
            int next = loseGraph[node].get(i);

            if(isVisited[next]) continue;
            isVisited[next] = true;

            loseDfs(next);
        }
    }
}
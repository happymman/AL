package 완전탐색.DFS_BFS;

import java.util.*;

/*
1차풀이 - 45m
 */
public class B1260_DFS와BFS {
    static int N;
    static int M;
    static int V;
    static ArrayList<Integer>[] tree;
    static boolean[] isVisited;
    static String dfsResult="";
    static String bfsResult="";
    public static void main(String[] args) {

        //N, M, V입력받기
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); //정
        M = sc.nextInt();
        V = sc.nextInt();

        //tree 메모리할당
        tree = new ArrayList[N+1];
        //tree 리스트 초기화
        for(int i=1;i<=N;i++){
            tree[i] = new ArrayList<>();
        }
        isVisited = new boolean[N+1];
        while(M-->0){ //M번
            int s = sc.nextInt();
            int e = sc.nextInt();
            tree[s].add(e);
            tree[e].add(s);
        }
        for(int i=1;i<=N;i++){
            Collections.sort(tree[i]);
        }

        isVisited[V]=true;
        dfs(V);

        isVisited = new boolean[N+1];
        isVisited[V]=true;
        bfs(V);

        System.out.println(dfsResult.trim());
        System.out.println(bfsResult.trim());
    }

    static void dfs(int node){
        dfsResult+=(node+" ");//노드처리

        for(int i=0;i<tree[node].size();i++){ //연결노드 선택 - 범위검사, 유효성검사
            int next = tree[node].get(i);

            if(isVisited[next]) continue;//방문검사
            isVisited[next]=true;//방문처리

            dfs(next);
        }
    }

    static void bfs(int node){ //bfs는 재귀가아니므로 파라미터인 node는 탐색시작에만 관여하고, for문에서는 poll한 now노드의 연결노드 리스트를 대상으로 bfs진행했었어야했음.

        Queue<Integer> q=new LinkedList<>();
        q.add(node);

        while(!q.isEmpty()){
            int now = q.poll();
            bfsResult+=(now+" ");//노드처리

            for(int i=0;i<tree[now].size();i++){ //연결노드 선택 - 범위검사, 유효성검사
                int next = tree[now].get(i);

                if(isVisited[next]) continue;//방문검사
                isVisited[next]=true;//방문처리

                q.add(next);
            }
        }
    }
}

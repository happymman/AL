package BFS_DFS;

import java.util.Stack;

public class P43162_네트워크 {
    private void visitAll(int computer, int[][] computers, boolean[] isVisited){
        Stack<Integer> s = new Stack<>();
        s.push(computer);

        while(!s.empty()){
            int c = s.pop();

            if(isVisited[c]) continue;
            isVisited[c] = true;

            for(int next =0; next < computers[c].length;next++){
                if(computers[c][next]==0) continue;
                s.push(next);
            }
        }
    }

    public int solution(int n, int[][] computers) {
        boolean[] isVisited = new boolean[n];
        int answer=0;

        for(int i=0;i<n;i++){
            if(isVisited[i]) continue;
            visitAll(i, computers, isVisited);
            answer++;
        }
        return answer;
    }
}

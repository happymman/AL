package BFS_DFS;

import java.util.Stack;

public class P43162_네트워크 {
    private void visitAll(int computer, int[][] computers, boolean[] isVisited){
        Stack<Integer> s = new Stack<>(); //Q.왜 스택을 여기에 넣는거지?
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

//import java.util.Stack;
//
//public class Solution{
//    private void visitAll(int computer, int[][] computers, boolean[] isVisited){
//        Stack<Integer> s = new Stack<>();
//
//        s.push(computer);
//        while(!s.empty()){
//            int c = s.pop();
//
//            for(int i=0;i<computers[0].length;i++){
//                if(isVisited[i]) continue; //방문X
//                if(computers[c][i]==0) continue; //방문X(불가능)
//                isVisited[i] = true;
//                s.push(i);
//            }
//
//        }
//    }
//
//    public int solution(int n, int[][] computers){
//        boolean[] isVisited = new boolean[n];
//        int networkCount =0;
//
//        for(int computer=0;computer<n;computer++){
//            if(isVisited[computer]) continue; //방문X
//            isVisited[computer] = true; //방문처리
//            visitAll(computer, computers, isVisited);
//            networkCount++;
//        }
//        return networkCount;
//    }
//}

//1.방문배열 - 필요X상황 : 상태전이 방향이 고정이여서 중복방문여부를 체크할 필요가 없을때
//2.초기 세팅(스택, 큐)

//3.pop -> 방문, 정답검사
//4.범위검사(테두리, 내부) -> 상태 전이


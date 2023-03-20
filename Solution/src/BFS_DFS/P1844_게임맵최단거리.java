package BFS_DFS;

import java.util.Queue;
import java.util.LinkedList;

class P1844_게임맵최단거리 {

    private static class State{
        private final int x;
        private final int y;
        private final int step;

        public State(int x, int y, int step){
            this.x = x;
            this.y = y;
            this.step = step;
        }
    }

    private static final int[] dx = {0,1,0,-1};
    private static final int[] dy = {-1,0,1,0};

    public int solution(int[][] maps) {
        boolean[][] isVisited = new boolean[maps.length][maps[0].length];
        Queue<State> queue = new LinkedList<>();

        queue.add(new State(0,0,1));
        isVisited[0][0] = true;

        while(!queue.isEmpty()){
            State state = queue.poll();

            if(state.y == maps.length-1 && state.x == maps[0].length-1)
                return state.step;

            for(int i=0;i<4;i++){
                int nx = state.x + dx[i];
                int ny = state.y + dy[i];


                if(ny < 0 || ny > maps.length-1 || nx < 0 || nx > maps[0].length-1) continue;
                if(isVisited[ny][nx]) continue;
                if(maps[ny][nx] == 0) continue;

                isVisited[ny][nx] = true;
                queue.add(new State(nx, ny, state.step+1));
            }
        }

        return -1;
    }
}
package 완전탐색.DFS_BFS;


import java.util.*;
public class P87694_아이템줍기 {

    private static int[] dx = {0,1,0,-1};
    private static int[] dy = {-1,0,1,0};

    private static class Node{
        int x;
        int y;
        int count;

        Node(int x, int y, int count){
            this.x =x;
            this.y=y;
            this.count=count;
        }
    }

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {

        Boolean[][] map = new Boolean[102][102];

        for(int i=0;i<rectangle.length;i++){

            for(int j=0;j<4;j++){
                rectangle[i][j] *=2;
            }

            int[] nowRec = rectangle[i];

            for(int x=nowRec[0]; x<=nowRec[2]; x++){
                for(int y=nowRec[1]; y<=nowRec[3]; y++){
                    map[y][x] = (x==nowRec[0] || x==nowRec[2]||y==nowRec[1]||y==nowRec[3]) && map[y][x] != Boolean.FALSE;
                }
            }
        }

        characterX *=2;
        characterY *=2;
        itemX *=2;
        itemY *=2;

        Queue<Node> q = new LinkedList<>();
        map[characterY][characterX] = Boolean.FALSE;
        q.offer(new Node(characterX, characterY, 0));

        int min = Integer.MAX_VALUE;
        while(!q.isEmpty()){
            Node node = q.poll();

            if(node.x == itemX && node.y == itemY && node.count < min){
                min = node.count;
            }

            for(int i=0;i<4;i++){
                int ny = node.y+dy[i];
                int nx = node.x+dx[i];

                if(ny<2 || ny>102 ||nx<2 ||nx>102) continue;
                if(map[ny][nx] != Boolean.TRUE) continue;

                map[ny][nx] = Boolean.FALSE;

                q.add(new Node(nx, ny, node.count+1));
            }
        }

        return min/2;
    }
}
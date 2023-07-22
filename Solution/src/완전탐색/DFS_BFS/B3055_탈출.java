package 완전탐색.DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B3055_탈출 {

    static int[] dx = {0,1,0,-1};
    static int[] dy = {-1,0,1,0};

    static boolean[][] isGosuemVisited;
    static boolean[][] isWaterVisited;

    static class Spread{
        int x;
        int y;
        Spread(int x, int y){
            this.x=x;
            this.y=y;
        }
    }

    static class Gosuem extends Spread{
        int step;
        Gosuem(int x, int y, int step){
            super(x, y);
            this.step = step;
        }
    }

    static class Water extends Spread{
        Water(int x, int y){
            super(x, y);
        }
    }

    public static void main(String[] args) throws IOException {

        /*
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        //R, C입력받기
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        */

        Scanner sc = new Scanner(System.in);
        int R = sc.nextInt();
        int C = sc.nextInt();

        isGosuemVisited = new boolean[R][C];
        isWaterVisited = new boolean[R][C];

        int[] target = new int[2];
        int[] start = new int[2];
        List<int[]> waters = new ArrayList<>();

        /*
        for(int i=0;i<R;i++){
            st=new StringTokenizer(br.readLine());
            String line = st.nextToken();
            for(int j=0;j<C;j++){

                char c = line.charAt(j);
                if(c == 'D'){
                    target[0] = i;
                    target[1] = j;
                    isWaterVisited[i][j] = true;
                }else if(c == 'S'){
                    start[0] = i;
                    start[1] = j;
                }else if(c == 'X'){
                    isWaterVisited[i][j] = true;
                    isGosuemVisited[i][j] = true;
                }else if(c == '*'){
                    int[] water = {i,j};
                    waters.add(water);
                }
            }
        }
        */

        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                String s = sc.next();
                if(s.equals("D")){
                    target[0] = i;
                    target[1] = j;
                    isWaterVisited[i][j] = true;
                }else if(s.equals("S")){
                    start[0] = i;
                    start[1] = j;
                }else if(s.equals("X")){
                    isWaterVisited[i][j] = true;
                    isGosuemVisited[i][j] = true;
                }else if(s.equals("*")){
                    int[] water = {i,j};
                    waters.add(water);
                }
            }
        }

        Queue q = new LinkedList<Spread>();
        for(int[] water : waters){
            q.add(new Water(water[0], water[1]));
            isWaterVisited[water[0]][water[1]] = true;
            isGosuemVisited[water[0]][water[1]] = true;
        }

        q.add(new Gosuem(start[0], start[1],0));
        isGosuemVisited[start[0]][start[1]] = true;

        while(!q.isEmpty()){
            Spread now = (Spread)q.poll();
            int x = now.x;
            int y = now.y;

            if(now instanceof Gosuem){
                //굴 도착
                if(x==target[0] && y==target[1]){
                    System.out.println(((Gosuem) now).step);
                    return;
                }
            }

            for(int i=0;i<4;i++){
                int nx = x +dx[i];
                int ny = y +dy[i];

                if(now instanceof Water){
                    if (nx <0 || nx>=R || ny<0 || ny>=C) continue;
                    if(isWaterVisited[nx][ny]) continue;
                    isWaterVisited[nx][ny]=true;
                    isGosuemVisited[nx][ny]=true; //고슴도치는 물이 있는 곳에 올수 없고

                    q.add(new Water(nx, ny));

                }else if(now instanceof Gosuem){
                    if (nx <0 || nx>=R || ny<0 || ny>=C) continue;
                    if(nx != target[0] || ny != target[1]){ //타겟이 아닌조건에서만
                        if (isWaterVisited[nx][ny]) continue;
                    }
                    if (isGosuemVisited[nx][ny]) continue;
                    isGosuemVisited[nx][ny]=true; //방문처리 //물은 고슴도치가 있는 곳에 올 수 있으므로

                    Gosuem gosuem = (Gosuem)now;
                    q.add(new Gosuem(nx, ny, gosuem.step+1));
                }
            }
        }

        System.out.println("KAKTUS");


    }
}

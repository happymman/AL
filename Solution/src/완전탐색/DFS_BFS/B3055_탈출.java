package 완전탐색.DFS_BFS;

//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.*;
//
//public class B3055_탈출 {
//
//    static int[] dx = {0,1,0,-1};
//    static int[] dy = {-1,0,1,0};
//
//    static boolean[][] isGosuemVisited;
//    static boolean[][] isWaterVisited;
//
//    static class Spread{
//        int x;
//        int y;
//        Spread(int x, int y){
//            this.x=x;
//            this.y=y;
//        }
//    }
//
//    static class Gosuem extends Spread{
//        int step;
//        Gosuem(int x, int y, int step){
//            super(x, y);
//            this.step = step;
//        }
//    }
//
//    static class Water extends Spread{
//        Water(int x, int y){
//            super(x, y);
//        }
//    }
//
//    public static void main(String[] args) throws IOException {
//
//        /*
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st;
//
//        st = new StringTokenizer(br.readLine());
//        //R, C입력받기
//        int R = Integer.parseInt(st.nextToken());
//        int C = Integer.parseInt(st.nextToken());
//        */
//
//        Scanner sc = new Scanner(System.in);
//        int R = sc.nextInt();
//        int C = sc.nextInt();
//
//        isGosuemVisited = new boolean[R][C];
//        isWaterVisited = new boolean[R][C];
//
//        int[] target = new int[2];
//        int[] start = new int[2];
//        List<int[]> waters = new ArrayList<>();
//
//        /*
//        for(int i=0;i<R;i++){
//            st=new StringTokenizer(br.readLine());
//            String line = st.nextToken();
//            for(int j=0;j<C;j++){
//
//                char c = line.charAt(j);
//                if(c == 'D'){
//                    target[0] = i;
//                    target[1] = j;
//                    isWaterVisited[i][j] = true;
//                }else if(c == 'S'){
//                    start[0] = i;
//                    start[1] = j;
//                }else if(c == 'X'){
//                    isWaterVisited[i][j] = true;
//                    isGosuemVisited[i][j] = true;
//                }else if(c == '*'){
//                    int[] water = {i,j};
//                    waters.add(water);
//                }
//            }
//        }
//        */
//
//        for(int i=0;i<R;i++){
//            for(int j=0;j<C;j++){
//                String s = sc.next();
//                if(s.equals("D")){
//                    target[0] = i;
//                    target[1] = j;
//                    isWaterVisited[i][j] = true;
//                }else if(s.equals("S")){
//                    start[0] = i;
//                    start[1] = j;
//                }else if(s.equals("X")){
//                    isWaterVisited[i][j] = true;
//                    isGosuemVisited[i][j] = true;
//                }else if(s.equals("*")){
//                    int[] water = {i,j};
//                    waters.add(water);
//                }
//            }
//        }
//
//        Queue q = new LinkedList<Spread>();
//        for(int[] water : waters){
//            q.add(new Water(water[0], water[1]));
//            isWaterVisited[water[0]][water[1]] = true;
//            isGosuemVisited[water[0]][water[1]] = true;
//        }
//
//        q.add(new Gosuem(start[0], start[1],0));
//        isGosuemVisited[start[0]][start[1]] = true;
//
//        while(!q.isEmpty()){
//            Spread now = (Spread)q.poll();
//            int x = now.x;
//            int y = now.y;
//
//            if(now instanceof Gosuem){
//                //굴 도착
//                if(x==target[0] && y==target[1]){
//                    System.out.println(((Gosuem) now).step);
//                    return;
//                }
//            }
//
//            for(int i=0;i<4;i++){
//                int nx = x +dx[i];
//                int ny = y +dy[i];
//
//                if(now instanceof Water){
//                    if (nx <0 || nx>=R || ny<0 || ny>=C) continue;
//                    if(isWaterVisited[nx][ny]) continue;
//                    isWaterVisited[nx][ny]=true;
//                    isGosuemVisited[nx][ny]=true; //고슴도치는 물이 있는 곳에 올수 없고
//
//                    q.add(new Water(nx, ny));
//
//                }else if(now instanceof Gosuem){
//                    if (nx <0 || nx>=R || ny<0 || ny>=C) continue;
//                    if(nx != target[0] || ny != target[1]){ //타겟이 아닌조건에서만
//                        if (isWaterVisited[nx][ny]) continue;
//                    }
//                    if (isGosuemVisited[nx][ny]) continue;
//                    isGosuemVisited[nx][ny]=true; //방문처리 //물은 고슴도치가 있는 곳에 올 수 있으므로
//
//                    Gosuem gosuem = (Gosuem)now;
//                    q.add(new Gosuem(nx, ny, gosuem.step+1));
//                }
//            }
//        }
//
//        System.out.println("KAKTUS");
//
//
//    }
//}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B3055_탈출 {

    static int[] dx={0,-1,0,1};
    static int[] dy={-1,0,1,0};

    static boolean[][] gosuemCannotVisited; //돌+ +물+고슴
    static boolean[][] waterCannotVisited; //돌+굴+물

    static class Spread{
        int x;
        int y;
        int step;
        String type;

        Spread(int x, int y, int step, String type){
            this.x=x;
            this.y=y;
            this.step=step;
            this.type=type;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //R, C 입력받기
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        gosuemCannotVisited = new boolean[R][C]; //gosuemCannotVisited 메모리 할당;
        waterCannotVisited= new boolean[R][C]; //waterCannotVisited 메모리 할당;

        int targetX=0; int targetY=0;
        int gosuemX=0; int gosuemY=0;
        List<int[]> waterList = new ArrayList<>();
        for(int i=0;i<R;i++){ //gosuemCannotVisited 세팅((돌)  +물+고슴)
                              //WaterCannotVisited 세팅((돌+굴)+물)
            st = new StringTokenizer(br.readLine());
            String[] arr = st.nextToken().split("");
            for(int j=0;j<C;j++){
                if(arr[j].equals("D")){ //굴
                    waterCannotVisited[i][j] = true; //물은 굴에 못 들어감
                    targetX = i;
                    targetY = j;
                }else if(arr[j].equals("*")){ //물
                    gosuemCannotVisited[i][j] = true;
                    waterCannotVisited[i][j] = true; //cannotVisited처리가 true되있어도 처음에 add한 초기 물위치 노드는 방문가능
                    waterList.add(new int[]{i,j});
                }else if(arr[j].equals("X")){ //돌
                    gosuemCannotVisited[i][j] = true;
                    waterCannotVisited[i][j] = true;
                }else if(arr[j].equals("S")){ //고슴도치
                    gosuemX=i;
                    gosuemY=j;
                }

            }
        }

        Queue<Spread> q = new LinkedList<>(); // 큐 생성
        for(int[] water : waterList){ //물 삽입
            q.add(new Spread(water[0], water[1], 0,"water"));
        }
        q.add(new Spread(gosuemX, gosuemY,0, "gosuem")); //고슴도치 삽입

        while(!q.isEmpty()){
            Spread now = q.poll(); //원소 꺼내기

            if(now.type.equals("water")){ //물인 경우
                //탐색종료검사X

                for(int i=0;i<4;i++){
                    int nx = now.x+dx[i];
                    int ny = now.y+dy[i];

                    if(nx<0 || nx>=R || ny<0 || ny>=C) continue;//범위검사
                    if(waterCannotVisited[nx][ny]) continue; //돌, 굴 불가, 유효성검사+방문검사
                    waterCannotVisited[nx][ny] = true; //방문처리
                    gosuemCannotVisited[nx][ny] = true;

                    q.add(new Spread(nx, ny, 0, "water")); //다음노드 방문예약
                }
            }else if(now.type.equals("gosuem")){ //고슴도치인 경우
                if(now.x==targetX && now.y==targetY){//굴위치 도착, 탐색종료검사
                    System.out.println(now.step); //거리 출력
                    return;
                }

                for(int i=0;i<4;i++){
                    int nx = now.x+dx[i];
                    int ny = now.y+dy[i];

                    if(nx<0 || nx>=R || ny<0 || ny>=C) continue;//범위검사
                    if(gosuemCannotVisited[nx][ny]) continue; //돌, 물 불가, 유효성검사+방문검사
                    gosuemCannotVisited[nx][ny] = true;//방문처리

                    q.add(new Spread(nx, ny, now.step+1, "gosuem")); //다음노드 방문예약
                }
            }
        }

        System.out.println("KAKTUS");//고슴도치 굴에 도달하지 못하는 경우
    }
}

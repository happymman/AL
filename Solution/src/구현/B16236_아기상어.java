package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class B16236_아기상어 {
    static int N;
    static boolean[][] isVisitedForBigFish;
    static boolean[][] isVisitedForSmallFish;
    static boolean[][] canNotVisited;
    static int[][] fishMap;
    static int sharkX;
    static int sharkY;
    static int sharkSize=2;
    static int sharkFishCount=0;

    static int[] dx={0,-1,0,1};
    static int[] dy={1,0,-1,0};
    static PriorityQueue<Fish> pq = new PriorityQueue<>();
    static class Fish implements Comparable<Fish>{
        int distance;
        int x;
        int y;

        Fish(int distance, int x, int y){
            this.distance = distance;
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Fish o) {
            if(this.distance != o.distance){
                return Integer.compare(this.distance, o.distance);
            }else if(this.x !=o.x){
                return Integer.compare(this.x, o.x);
            }else{
                return Integer.compare(this.y, o.y);
            }
        }
    }

    static class Node{
        int x;
        int y;
        int step;

        Node(int x, int y, int step){
            this.x=x;
            this.y=y;
            this.step=step;
        }
    }
    static int time=0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        fishMap = new int[N][N];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                int input = Integer.parseInt(st.nextToken());
                if(input != 9){
                    fishMap[i][j] = input;
                }else{
                    sharkX = i;
                    sharkY = j;
                }
            }
        }

        boolean sizeChanged=true;
        while(true){
            isVisitedForBigFish = new boolean[N][N];
            isVisitedForSmallFish = new boolean[N][N];
            pq = new PriorityQueue<>();

            //DFS해서 크기큰 물고기 canVisited에 등록하기
            if(sizeChanged){ //사이즈 변했을때만 하는 이유 : 안변했으면, 못가는데는 똑같아서
                //이때만 초기화시키는 것
                canNotVisited = new boolean[N][N];

                findBigFish(sharkX, sharkY);
                sizeChanged = false;
            }
            //DFS해서 크기작은 물고기 pq에 넣기 - 크기가 안변해도 다시하는 이유 : 상어와의 거리는 달라지니깐 다시 pq에 넣어야함
            findSmallFish(sharkX, sharkY);

            if(pq.isEmpty()){
                break;
            }else{
                Fish eat = pq.poll();
                //물고기 먹기
                fishMap[eat.x][eat.y] = 0;

                //상어 좌표 이동
                sharkX = eat.x;
                sharkY = eat.y;
                //이동하는데 걸린 시간
                time += eat.distance;
                //상어 먹은물고기++
                sharkFishCount++;

                //상어 먹은물고기 == 상어크기
                if(sharkFishCount == sharkSize){
                    //먹은물고기 초기화
                    sharkFishCount=0;
                    //상어크기++;
                    sharkSize++;
                    sizeChanged = true;
                 }
            }
        }
        System.out.println(time);
    }

    static void findBigFish(int x, int y){
        if(fishMap[x][y] > sharkSize){
            canNotVisited[x][y] = true;
        }

        for(int i=0;i<4;i++){
            int nx = x+dx[i];
            int ny = y+dy[i];

            if(nx<0 || nx>=N || ny<0 ||ny>=N) continue;
            if(isVisitedForBigFish[nx][ny]) continue;
            isVisitedForBigFish[nx][ny] = true;

            findBigFish(nx, ny);
        }
    };

    static void findSmallFish(int x, int y){

        Queue q = new LinkedList<>();
        q.add(new Node(sharkX, sharkY, 0));

        while(!q.isEmpty()){
            Node now = (Node)q.poll();

            if(fishMap[now.x][now.y] < sharkSize && fishMap[now.x][now.y]!=0){
                pq.add(new Fish(now.step, now.x, now.y));
            }

            for(int i=0;i<4;i++){
                int nx = now.x+dx[i];
                int ny = now.y+dy[i];

                if(nx<0 || nx>=N || ny<0 ||ny>=N) continue;
                if(canNotVisited[nx][ny]) continue;
                if(isVisitedForSmallFish[nx][ny]) continue;
                isVisitedForSmallFish[nx][ny] = true;

                q.add(new Node(nx, ny, now.step+1));
            }
        }
    };


}

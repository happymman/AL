package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B20056_마법사상어와파이어볼 {
    static List<Visit> visitsList = new ArrayList<>();
    static List<Ball> ballList = new ArrayList<>();

    static int[] newDSame = {0,2,4,6};
    static int[] newDNot = {1,3,5,7};
    static int[] dx = {-1,-1,0,1,1,1,0,-1};
    static int[] dy = {0,1,1,1,0,-1,-1,-1};

    static class Ball{
        int r;
        int c;
        int m;
        int s;
        int d;

        public Ball(int r, int c,int m,int s,int d){
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }

    static class Visit{
        int r;
        int c;
        int count;

        public Visit(int r, int c,int count){
            this.r = r;
            this.c = c;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        //m만큼 ball정보 입력받기 -> ballList등록
        for(int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            ballList.add(new Ball(r, c, m, s, d));
        }

        //K번 움직이기
        for(int move=0;move<K;move++){
            //1.이동
            for(Ball now : ballList){
                int cx = now.r;
                int cy = now.c;

                //방향대로 움직이기
                int nx = ((cx+dx[now.d]*now.s)%N+N)%N;
                int ny = ((cy+dy[now.d]*now.s)%N+N)%N;

                boolean find = false;
                for(Visit visit : visitsList){
                    if(visit.r != nx || visit.c != ny) continue;
                    find = true;
                    visit.count++;
                }
                if(!find){
                    visitsList.add(new Visit(nx, ny, 1));
                }

                now.r = nx;
                now.c = ny;
            }

            //2.합치기
            //같은칸 파이어볼 있는지 여부 검사
            for(Visit visit : visitsList){
                if(visit.count <=1) continue;
                int i=visit.r;
                int j=visit.c;
                int ballCount = visit.count;

                int oldMSum = 0;
                String firstD="";
                int changeCount=0;
                int oldSSum =0;
                for(Ball ball : ballList){
                    if(ball.r == i && ball.c ==j){
                        oldMSum += ball.m;
                        //합쳐지는 파이어볼 방향 모두홀수 or 짝수여부 검사
                        String oldD;
                        if(ball.d %2==0){
                            oldD = "even";
                        }else{
                            oldD ="odd";
                        }
                        if(firstD != oldD){
                            firstD = oldD;
                            changeCount++;
                        }
                        oldSSum += ball.s;
                    }
                }

                for(int count=0;count<ballList.size();count++){
                    Ball ball = ballList.get(count);
                    if(ball.r == i && ball.c ==j){
                        ballList.remove(ball);
                        count--;
                    }
                }

                int newM = oldMSum / 5; //내림
                if(newM ==0) continue; //질량0이면 기존것 삭제만하고 new add를 안하는

                int newS = oldSSum / ballCount; //내림

                for(int count=0;count<4;count++){ //4개의 파이어볼로 나누어지니깐
                    if(changeCount ==1){
                        ballList.add(new Ball(i, j, newM, newS, newDSame[count]));
                    }else{
                        ballList.add(new Ball(i, j, newM, newS, newDNot[count]));
                    }
                }
            }

            //3.vists초기화
            visitsList.clear();
        }

        //파이어볼 질량합 계산
        int mSum=0;
        for(Ball ball : ballList){
            mSum += ball.m;
        }
        System.out.println((mSum));

    }
}

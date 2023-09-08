package 완전탐색.DFS_BFS;

import java.util.*;
/*
1차풀이 -

실수 :
 */
public class B9205_맥주마시면서걸어가기 {
    static int N;
    static boolean[] isVisited; //방문배열 -> 중복방문을 막기위함 - 갔던 곳을 다시 가는 것이 아무의미X, (BFS여서 무한루프는 일어나지 않지만 비효율)
    static int[][] dists;
    static class Node{
        int x;
        int y;
        Node(int x, int y){
            this.x=x;
            this.y=y;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int t=sc.nextInt();//t입력받기
        while(t-->0){ //t번
            N = sc.nextInt();//n입력받기 / n : 편의점개수
            isVisited = new boolean[N];//isVisited 초기화
            dists = new int[N][2];//info 초기화
            //집 좌표 입력받기
            int houseX = sc.nextInt();
            int houseY = sc.nextInt();
            for(int i=0;i<N;i++){ //n번
                dists[i][0] = sc.nextInt();
                dists[i][1] = sc.nextInt();
            }
            //페스티발 좌표 입력받기
            int festivalX = sc.nextInt();
            int festivalY = sc.nextInt();

            Queue<Node> q=new LinkedList<>(); //큐 생성
            q.add(new Node(houseX, houseY)); //큐 add

            boolean arrival = false;
            while(!q.isEmpty()){
                Node now = q.poll();//큐원소 꺼내기

                if(canArrival(now.x, now.y, festivalX, festivalY)){ //거리, 맥주계산 - 페스티벌 도달가능 여부 검사 - 다음탐색 종료여부 검사
                    System.out.println("happy");
                    arrival = true;
                    break;
                }

                for(int i=0;i<isVisited.length;i++){ //편의점 선택, 범위검사
                    if(!canArrival(now.x, now.y, dists[i][0], dists[i][1])) continue;//거리, 맥주계산 - 유효성검사
                    if(isVisited[i]) continue; //방문검사
                    isVisited[i]=true; //방문처리

                    q.add(new Node(dists[i][0], dists[i][1]));//다음노드 방문예약
                }
            }
            if(arrival) continue;
            System.out.println("sad");
        }
    }

    static boolean canArrival(int currentX, int currentY, int targetX, int targetY){
        int dist = (Math.abs(currentX-targetX)+ Math.abs(currentY-targetY));
        int beerRequired = dist%50==0 ? dist/50 : dist/50+1; //
        return 20 >= beerRequired;
    }
}

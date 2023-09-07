package 완전탐색.DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
1차풀이 - 45m

풀이 과정중에서 했던 질문
- 방법론 가정 : 0개수 변화 없으면 종료
    0개수 변화가 없는 상황이면 어떤 상황일까? 모든 경우의수 가정하기
    상황분류하기 i)1개 소스일때 -> 도달가능한 노드가 없는 상태 = 익게할 수 있는 토마토가 없는 상태
    ii)2개이상 소스일때 -> 마찬가지
- 의문 : 멀티소스인데, 하루 체크를 어떻게?
    by Step
효율적 방법
-> '세세한 로직을 먼저 생각하기보다는'기존방법론 수도코드 작성후,(BFS)관찰을 통해서,
- 그대로 사용할지, 변형할지, 추가할지를 결정하면 된다.
- 기존방법론 수도코드 작성을 하기전에 알려면, 기존방법론 수도코드(BFS)에서 어떻게 변형되어서 어떤 것을 할 수 있는지와 같은 정보를 확장적으로 연구했으면 가능.
- 이미 연구가 된 상태가 아니라면, 기존방법론 수도코드 작성후 그것에 대한 관찰을 통해서 추가/변형 여부 결정하는 것이 효율적으로 보여짐
- 이유는 '근거'가 되는 정보가 기존방법론 수도코드이기 때문.
- 우선순위는 '근거'가 되는 정보

Q.애초에 기존방법론 자체를 통째로 바꿔야하는 상황이면 수도코드 작성에 시간을 낭비하게되는 것인데 어떡하나?
- 수도코드 작성전에 미리 알 수 있는 대상은 1.시간복잡도 2.사전에 연구된 기존방법론에 대한 확장이므로, 불가피한 처사

 */
//public class B7576_토마토 {
//
//    static boolean[][] cannotSpread; //-1:토마토가 없는곳, 1:이미 익은토마토가 된 곳
//
//    static int[] dx={0,-1,0,1};
//    static int[] dy={-1,0,1,0};
//    static class State{
//        int x;
//        int y;
//        int step;
//
//        State(int x, int y, int step){
//            this.x=x;
//            this.y=y;
//            this.step=step;
//        }
//    }
//    public static void main(String[] args) throws IOException {
//
//        //N, M 입력받기
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        int N = Integer.parseInt(st.nextToken());
//        int M = Integer.parseInt(st.nextToken());
//
//        //cannotSpread 메모리 할당
//        cannotSpread = new boolean[M][N];
//
//        int notRipe=N*M; //notRipe : 익지않은 토마토
//        List<int[]> tomatoList = new ArrayList<>();
//
//        for(int i=0;i<M;i++){
//            st = new StringTokenizer(br.readLine());
//            for(int j=0;j<N;j++){
//                int input = Integer.parseInt(st.nextToken());
//                if(input==1){ //토마토이면
//                    tomatoList.add(new int[]{i,j}); //tomatoList에 추가
//                    cannotSpread[i][j] = true; //cannotSpread에 추가
//                }else if(input==-1){ //토마토 없으면
//                    cannotSpread[i][j] = true; //cannotSpread에 추가
//                    notRipe--; //전체면적 - 토마토없는곳. 토마토가 있는곳은 q.add()이후 해당노드 방문할때 노드처리에서 notRipe--됨.
//                }
//            }
//        }
//
//        Queue<State> q = new LinkedList<>();
//        for(int[] tomato : tomatoList){ //익은토마토들 큐에 삽입
//            q.add(new State(tomato[0], tomato[1], 0));
//        }
//
//        while(!q.isEmpty()){
//            State now = q.poll();
//
//            notRipe--;//노드처리
//            if(notRipe==0){ //탐색종료여부 검사
//                System.out.println(now.step); //일수 출력
//                return;
//            }
//
//            for(int i=0;i<4;i++){ //4방향
//                int nx=now.x+dx[i];
//                int ny=now.y+dy[i];
//
//                if(nx<0||nx>=M||ny<0||ny>=N) continue;//범위검사
//                if(cannotSpread[nx][ny]) continue;//유효성검사+방문검사
//                cannotSpread[nx][ny] = true;//익은토마토가 되었으니깐 - 방문처리
//
//                q.add(new State(nx, ny, now.step+1));//다음노드방문예약
//            }
//        }
//
//        System.out.println(-1); //-1출력, 익지않은 토마토가 있는 상태
//
//    }
//}

/*
2차풀이 - 1h 25m

- 방문처리를 노드처리하는 시간에 했음 - 이유 :
 */
public class B7576_토마토 {
    static int[][] map;
    static int[][] dir= {{-1,0},{1,0},{0,-1},{0,1}};
    static int N;
    static int M;
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

    public static void main(String[] args) {

        //M,N 입력받기
        Scanner sc = new Scanner(System.in);
        M = sc.nextInt();
        N = sc.nextInt();
        //map 초기화
        map = new int[N][M];
        //큐 생성
        Queue<Node> q = new LinkedList<>();

        int zeroCount=N*M;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                int input = sc.nextInt();
                if(input==1){
                    q.add(new Node(i,j,0));
                    map[i][j]=1;
                }else if(input==-1){
                    map[i][j]=-1;
                    zeroCount--;
                }
            }
        }


        while(!q.isEmpty()){
            Node now = q.poll();

            zeroCount--; //노드처리

            if(zeroCount==0){ //다음탐색 종료여부 검사
                System.out.println(now.step); //일수 출력
                return;
            }

            for(int d=0;d<4;d++){ //방향선택
                int nx = now.x+dir[d][0];
                int ny = now.y+dir[d][1];

                if(nx<0||ny<0||nx>=N||ny>=M) continue; //범위검사
                if(map[nx][ny]==-1 || map[nx][ny]==1) continue;//유효성검사, 방문검사
                map[nx][ny] = 1; //방문처리

                q.add(new Node(nx, ny, now.step+1));//다음노드 방문예약
            }
        }
        System.out.println(-1); //zeroCount가 0이 안되는 상황 -> -1출력

    }
}

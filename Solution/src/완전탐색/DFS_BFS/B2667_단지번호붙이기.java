package 완전탐색.DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//public class B2667_단지번호붙이기 {
//
//    private static int[][] marking;
//    private static int[][] maps;
//    private static boolean[][] isVisited;
//
//    private static int[] dx = {0,1,0,-1};
//    private static int[] dy = {-1,0,1,0};
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        int N = Integer.parseInt(br.readLine());
//
//        marking = new int[N][N];
//        maps = new int[N][N];
//        isVisited = new boolean[N][N];
//
//       for(int i=0;i<N;i++){
//           String[] arr = br.readLine().split("");
//           for(int j=0;j<N;j++){
//               maps[i][j] = Integer.parseInt(arr[j]);
//           }
//       }
//
//       int mark=1;
//       for(int i=0;i<N;i++){
//           for(int j=0;j<N;j++){
//               if(maps[i][j] == 0 )continue;
//               if(isVisited[i][j]) continue;
//               isVisited[i][j] = true;
//
//               DFS(i,j,mark);
//               mark++;
//           }
//       }
//
//       //mark세서 반환하기
//       int[] markCount = new int[mark];
//       for(int i=0;i<N;i++){
//           for(int j=0;j<N;j++){
//               if(marking[i][j] ==0) continue;
//               markCount[marking[i][j]]++;
//           }
//       }
//
//       Arrays.sort(markCount);
//        System.out.println(mark-1);
//       for(int i=1;i<markCount.length;i++){
//           System.out.println(markCount[i]);
//       }
//    }
//
//    private static void DFS(int y, int x, int mark){
//        marking[y][x] = mark;
//
//        for(int i=0;i<4;i++){
//            int ny = y+dy[i];
//            int nx = x+dx[i];
//
//            if(ny<0 || ny>=maps.length || nx<0 || nx>=maps[0].length) continue;
//            if(maps[ny][nx] == 0) continue;
//            if(isVisited[ny][nx]) continue;
//            isVisited[ny][nx] = true;
//
//            DFS(ny, nx, mark);
//        }
//    }
//}

/*
2차풀이

피드백 :
- 풀이법을 완벽하게 알고 있다고 착각 -> 생각을 거의 거치지 않고, 손가는대로 코딩
-> 모든 문제를 할 수 있는한 가장 느리게 풀이하며, 쳐해있는 상황에서 고려할만한 모든 요소들을 고려하고, 해야될 모든 행위를 하는 것이 중요하다.
더이상 천천히 할 수 없는 속도로 코딩을 반복하는 것이 가장 빠르게 문제해결을 체화시키는 방법.
*쳐해있는 상황에서 해야할 모든 행위
ex)코드 - 코드이유, 변수 -> 의미, 변수할당 -> 변수의미 주석쓰기
배열관련 코드 -> 배열그림(or 공책그림)

 */
public class B2667_단지번호붙이기 {
    static int[][] map;
    static int[][] mark;
    static boolean[][] isVisited;
    static int[] danjiCount;

    static int danji=1;
    static int N;

    static int[] dx={0,1,0,-1};
    static int[] dy={-1,0,1,0};

    public static void main(String[] args) throws IOException {

        //N입력받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        mark = new int[N][N];
        map = new int[N][N];
        isVisited = new boolean[N][N];

       for(int i=0;i<N;i++){
           String[] arr = br.readLine().split("");
           for(int j=0;j<N;j++){
               map[i][j] = Integer.parseInt(arr[j]);
           }
       }

        for(int i=0;i<N;i++){ //행선택
            for(int j=0;j<N;j++){ //열선택
                if(map[i][j] ==0) continue; //유효성검ㅑㄹ
                if(isVisited[i][j]) continue;//방문검사
                isVisited[i][j] = true; //방문처리

                dfs(i,j,danji);
                danji++; //단지 번호 증가
            }
        }

        //danjiCount 메모리 할당
        danjiCount = new int[danji];
        countDanji();//단지번호 세기
        Arrays.sort(danjiCount); //단지번호 배열 정렬
        System.out.println(danji-1);
        for(int i=1;i<danjiCount.length;i++){ //순서대로 출력하기
            System.out.println(danjiCount[i]);
        }
    }

    static void dfs(int x, int y, int danji){

        mark[x][y] = danji; //단지번호 마킹

        for(int i=0;i<4;i++){
            int nx = x+dx[i];
            int ny = y+dy[i];

            if(nx<0 || nx>=N || ny<0 || ny>=N) continue; //범위검사
            if(map[nx][ny] ==0) continue; //유효성검사
            if(isVisited[nx][ny]) continue; //방문검사
            isVisited[nx][ny] = true; //방문처리

            dfs(nx, ny, danji);
        }

    }

    static void countDanji(){
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(mark[i][j]!=0){
                    danjiCount[mark[i][j]] ++;
                }
            }
        }
    }

}


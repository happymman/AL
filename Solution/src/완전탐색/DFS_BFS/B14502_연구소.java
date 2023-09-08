package 완전탐색.DFS_BFS;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//틀린풀이 - 10% 테케 통과못하는
//public class B14502_연구소 {
//    static int N;
//    static int M;
//    static int max=0;
//    static int[][] map;
//    static int[][] dfsMap;
//    static boolean[][] isVisited;
//    static List<int[]> virusList= new ArrayList<>();
//    static List<int[]> visitList= new ArrayList<>();
//    static int[][] dir = {{0,-1},{1,0},{0,1},{-1,0}};
//
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        N = sc.nextInt();//N입력받기
//        M = sc.nextInt();//M입력받기
//
//        //map초기화
//        map = new int[N][M];
//        for(int i=0;i<N;i++){
//            for(int j=0;j<M;j++){
//                int input = sc.nextInt();
//                map[i][j]=input;
//                if(input==0) visitList.add(new int[]{i,j}); //빈벽이었던 곳만 새벽 놓는 후보가 될 수 있도록
//                if(input==2) virusList.add(new int[]{i,j});
//            }
//        }
//
//        BT(0,0);
//        System.out.println(max);
//    }
//
//    static void BT(int start, int depth){
//        if(depth==3){ //벽의 개수가 3개일때 바이러스 DFS시작 - 노드처리+다음탐색 종료여부 검사
//            arrayDeepCopy(); //map을 dfsMap에 deepCopy - map은 훼손되면 안되니깐
//            spread();
//            int result = countSafe();
//            max = Math.max(max, result);
//            return;
//        }
//
//        for(int index=start+1;index<visitList.size();index++){
//            int i = visitList.get(index)[0];
//            int j = visitList.get(index)[1];
//
//            //유효성검사X - map[][]==1,2인 곳을 꺼내지 않는다.
//            map[i][j]=3; //쉬운 디버깅을 위해서 3으로 처리
//            //방문검사X
//            //방문처리X
//
//            BT(index, depth+1);
//            map[i][j]=0;
//
//        }
//    }
//
//    private static void arrayDeepCopy() {
//        dfsMap = new int[N][M];
//        for(int i=0;i<N;i++){
//            for(int j=0;j<M;j++){
//                dfsMap[i][j] = map[i][j];
//            }
//        }
//    }
//
//    static void spread(){
//        isVisited = new boolean[N][M];
//        for(int[] virus : virusList) { //처음 입력됐던 바이러스 대상으로 DFS실시
//            isVisited[virus[0]][virus[1]] = true;
//            DFS(virus[0],virus[1]);
//        }
//    }
//
//    static int countSafe(){
//        int safe=0;
//        for(int i=0;i<N;i++){
//            for(int j=0;j<M;j++){
//                if(dfsMap[i][j]==0) safe++;
//            }
//        }
//        return safe;
//    }
//    static void DFS(int r, int c){ //
//        dfsMap[r][c] = 2; //노드처리
//
//        for(int d=0;d<4;d++){
//            int nr=r+dir[d][0];
//            int nc=c+dir[d][1];
//
//            if(nr<0||nc<0||nr>=N||nc>=M) continue; //범위검사
//            if(dfsMap[nr][nc]==1 ||dfsMap[nr][nc]==2|| dfsMap[nr][nc]==3) continue; //유효성검사 - 벽, 새벽 있는 곳은 바이러스 침투 불가, 바이러스 있는 곳은 침투필요X
//            if(isVisited[nr][nc]) continue; //방문검사
//            isVisited[nr][nc]=true;//방문처리
//
//            DFS(nr, nc);
//        }
//    }
//}

//틀린풀이 - 예시테케도 통과 못하는
//public class B14502_연구소 {
//    static int N;
//    static int M;
//    static int max=0;
//    static int[][] map;
//    static int[][] dfsMap;
//    static boolean[][] isVisited;
//    static List<int[]> virusList= new ArrayList<>();
//    static List<int[]> visitList= new ArrayList<>();
//    static int[][] dir = {{0,-1},{1,0},{0,1},{-1,0}};
//
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        N = sc.nextInt();//N입력받기
//        M = sc.nextInt();//M입력받기
//
//        //map초기화
//        map = new int[N][M];
//        for(int i=0;i<N;i++){
//            for(int j=0;j<M;j++){
//                int input = sc.nextInt();
//                map[i][j]=input;
//                if(input==0) visitList.add(new int[]{i,j}); //빈벽이었던 곳만 새벽 놓는 후보가 될 수 있도록
//                if(input==2) virusList.add(new int[]{i,j});
//            }
//        }
//
//        BT(0,0);
//        System.out.println(max);
//    }
//
//    static void BT(int start, int depth){
//        if(depth==3){ //벽의 개수가 3개일때 바이러스 DFS시작 - 노드처리+다음탐색 종료여부 검사
//            arrayDeepCopy(); //dfsMap초기화 &map 복사 - map은 훼손되면 안되니깐
//            spread();
//            int result = countSafe();
//            max = Math.max(max, result);
//            return;
//        }
//
//        for(int index=start+1;index<visitList.size();index++){
//            int i = visitList.get(index)[0];
//            int j = visitList.get(index)[1];
//
//            //유효성검사X - map[][]==1,2인 곳을 꺼내지 않는다.
//            map[i][j]=3; //쉬운 디버깅을 위해서 3으로 처리
//            //방문검사X
//            //방문처리X
//
//            BT(index, depth+1);
//            map[i][j]=0;
//
//        }
//    }
//
//    private static void arrayDeepCopy() {
//        dfsMap = new int[N][M];
//        for(int i=0;i<N;i++){
//            for(int j=0;j<M;j++){
//                dfsMap[i][j] = map[i][j];
//            }
//        }
//    }
//
//    //
//    static void spread(){
//        for(int[] virus : virusList) { //처음 입력됐던 바이러스 대상으로 DFS실시
//            DFS(virus[0],virus[1]);
//        }
//    }
//
//    static int countSafe(){
//        int safe=0;
//        for(int i=0;i<N;i++){
//            for(int j=0;j<M;j++){
//                if(dfsMap[i][j]==0) safe++;
//            }
//        }
//        return safe;
//    }
//    static void DFS(int r, int c){ //
//        dfsMap[r][c] = 2; //바이러스 확산 - 노드처리
//
//        for(int d=0;d<4;d++){
//            int nr=r+dir[d][0];
//            int nc=c+dir[d][1];
//
//            if(nr<0||nc<0||nr>=N||nc>=M) continue; //범위검사
//            if(dfsMap[nr][nc]==1 ||dfsMap[nr][nc]==2|| dfsMap[nr][nc]==3) continue; //방문검사+유효성검사 - 벽, 새벽 있는 곳은 바이러스 침투 불가, 바이러스 있는 곳은 침투필요X
//
//            DFS(nr, nc);
//        }
//    }
//}

public class B14502_연구소 {
    static int N;
    static int M;
    static int max=0;
    static int[][] originalMap;
    static int[][] tempMap;
    static List<int[]> virusList= new ArrayList<>();
    static List<int[]> visitList= new ArrayList<>();
    static int[][] dir = {{0,-1},{1,0},{0,1},{-1,0}};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();//N입력받기
        M = sc.nextInt();//M입력받기

        //map초기화
        originalMap = new int[N][M];
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                int input = sc.nextInt();
                originalMap[i][j]=input;
                if(input==0) visitList.add(new int[]{i,j}); //빈벽이었던 곳만 새벽 놓는 후보가 될 수 있도록
                if(input==2) virusList.add(new int[]{i,j});
            }
        }

        BT(0,0);
        System.out.println(max);
    }

    static void BT(int start, int depth){
        if(depth==3){ //벽의 개수가 3개일때 바이러스 DFS시작 - 노드처리+다음탐색 종료여부 검사
            arrayDeepCopy(); //tempMap초기화 &map 복사 - map은 훼손되면 안되니깐
            spread();
            max = Math.max(max, countSafe());
            return;
        }

        for(int index=start;index<visitList.size();index++){
            int i = visitList.get(index)[0];
            int j = visitList.get(index)[1];

            //유효성검사X - map[][]==1,2인 곳을 꺼내지 않는다.
            originalMap[i][j]=3; //쉬운 디버깅을 위해서 새벽 놓는 곳은 3으로 처리
            //방문검사X
            //방문처리X

            BT(index+1, depth+1);
            originalMap[i][j]=0;

        }
    }

    private static void arrayDeepCopy() {
        tempMap = new int[N][M];
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                tempMap[i][j] = originalMap[i][j];
            }
        }
    }

    //
    static void spread(){
        for(int[] virus : virusList) { //처음 입력됐던 바이러스 대상으로 DFS실시
            DFS(virus[0],virus[1]);
        }
    }

    static int countSafe(){
        int safe=0;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(tempMap[i][j]==0) safe++;
            }
        }
        return safe;
    }
    static void DFS(int r, int c){ //
        tempMap[r][c] = 2; //바이러스 확산 - 노드처리

        for(int d=0;d<4;d++){
            int nr=r+dir[d][0];
            int nc=c+dir[d][1];

            if(nr<0||nc<0||nr>=N||nc>=M) continue; //범위검사
            if(tempMap[nr][nc]==1 || tempMap[nr][nc]==2|| tempMap[nr][nc]==3) continue; //방문검사+유효성검사 - 벽, 새벽 있는 곳은 바이러스 침투 불가, 바이러스 있는 곳은 침투필요X

            DFS(nr, nc);
        }
    }
}
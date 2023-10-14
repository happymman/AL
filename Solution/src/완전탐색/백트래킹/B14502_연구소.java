package 완전탐색.백트래킹;


/*
1차풀이 - 2h

- 벽이 3개일땐 3중for문 완전탐색 가능, 벽개수가 더 많아질때는 백트래킹 풀이 필요
 */
//public class B14502_연구소 {
//    static int N;
//    static int M;
//    static int max=0;
//    static int[][] original;
//    static int[][] tempMap;
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
//        original = new int[N][M];
//        for(int i=0;i<N;i++){
//            for(int j=0;j<M;j++){
//                int input = sc.nextInt();
//                original[i][j]=input;
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
//        if(depth==3){ //다음탐색 종료여부 검사 - 벽의 개수가 3개일때 바이러스 DFS시작
//            arrayDeepCopy(); //tempMap초기화 &map 복사 - map은 훼손되면 안되니깐
//            spread();
//            max = Math.max(max, countSafe());
//            return;
//        }
//
//        for(int index=start;index<visitList.size();index++){ //조합 - 순차적 방문상황 최적화를 위해서 방문리스트 탐색을 해당 인덱스부터 시작한다.
//            int i = visitList.get(index)[0];
//            int j = visitList.get(index)[1];
//
//            //유효성검사X - 초기 빈공간이었던(0) 부분의 좌표를 대상으로 하므로 유효성검사가 필요없다(map[][]==1,2인 곳을 꺼내지 않는다.)
//            original[i][j]=1; //벽위치 선택 - 상태변경
//            //방문검사, 방문처리X - 노드방문이 순차적으로 이루어지므로, 중복방문의 가능성이 없다.
//
//            BT(index+1, depth+1); //다음 대상 선택
//            original[i][j]=0; // 상태복원 - 이유 : 모든 후보가 선택되지는 않는 상황
//        }
//    }
//
//    private static void arrayDeepCopy() {
//        tempMap = new int[N][M];
//        for(int i=0;i<N;i++){
//            tempMap[i] = original[i].clone();
//        }
//    }
//
//    //
//    static void spread(){
//        for(int[] virus : virusList) { //처음 입력됐던 바이러스 대상으로 DFS실시
//            //방문처리X
//            DFS(virus[0],virus[1]);
//        }
//    }
//
//    static int countSafe(){
//        int safe=0;
//        for(int i=0;i<N;i++){
//            for(int j=0;j<M;j++){
//                if(tempMap[i][j]==0) safe++;
//            }
//        }
//        return safe;
//    }
//    static void DFS(int r, int c){
//
//        for(int d=0;d<4;d++){
//            int nr=r+dir[d][0];
//            int nc=c+dir[d][1];
//
//            if(nr<0||nc<0||nr>=N||nc>=M) continue; //범위검사
//            if(tempMap[nr][nc]==1 || tempMap[nr][nc]==2|| tempMap[nr][nc]==3) continue; //방문검사+유효성검사 - 벽, 새벽 있는 곳은 바이러스 침투 불가, 바이러스 있는 곳은 침투필요X
//            tempMap[nr][nc] = 2; //바이러스 확산 - 방문처리
//
//            DFS(nr, nc);
//        }
//    }
//}


/*
2차풀이 - 50m
- 실수 : deepcopy : .clone() 얕은복사
- isVisited - 일회용배열
              재사용배열 -> 초기화 계속하기
-> (+25m)
 */
import java.util.Arrays;
import java.util.Scanner;

public class B14502_연구소 {
    static int N;
    static int M;
    static int max = Integer.MIN_VALUE;
    static int[][] a;
    static int[][] b;
    static boolean[][] isVisited;
    static boolean[][] isSpace;

    static int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};

    public static void main(String[] args) {
        input();
        pro();
        System.out.println(max);
    }

    static void input(){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        a = new int[N][M];
        b = new int[N][M];
        isSpace = new boolean[N][M];

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                a[i][j] = sc.nextInt();
                if(a[i][j] ==0 ) isSpace[i][j] = true;
            }
        }
    }

    static void pro(){
        BT(0,0);
    }

    static void BT(int depth, int start){
        if(depth==3){
            //dfs에서 사용할 1.map배열 딥카피 2.방문배열 초기화
            deepCopy();
            isVisited = new boolean[N][M]; //초기화 - 이유 : 재사용배열

            spread();
            int result = 개수세기();
            max = Math.max(max, result);
            return;
        }

        for(int si=start;si<N*M;si++){ //범위검사(자동)
            int i=si/M;
            int j=si%M;

            if(!isSpace[i][j]) continue; //유효성검사
            //방문검사, 방문처리X

            a[i][j] = 1; //상태변경

            int ci = i*M+j;
            BT(depth+1, ci+1);

            a[i][j] = 0; //상태복원

        }
    }

    static void deepCopy(){
        for(int i=0;i<N;i++){
            b[i] = Arrays.copyOf(a[i], a[i].length);
        }
    }

    static void spread(){
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(b[i][j] == 2){
                    isVisited[i][j] = true;
                    dfs(i,j);
                }
            }
        }
    }

    static void dfs(int i, int j){

        for(int d=0;d<4;d++){
            int ni = i+dir[d][0];
            int nj = j+dir[d][1];

            if(ni<0||nj<0||ni>=N||nj>=M) continue; //범위검사
            if(b[ni][nj] ==1) continue; //유효성검사
            if(isVisited[ni][nj]) continue; //방문검사
            isVisited[ni][nj] = true; //방문처리

            b[ni][nj] = 2; //상태변경 - 바이러스 전파

            dfs(ni,nj);
        }
    }
    static int 개수세기(){
        int count=0;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(b[i][j] == 0) count++;
            }
        }
        return count;
    }


}
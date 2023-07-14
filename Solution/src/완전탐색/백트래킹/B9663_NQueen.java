package 완전탐색.백트래킹;
//
//import java.util.Scanner;
//
//public class B9663_NQueen {
//
//    static int[][] isAttacked;
//    static int answerCount=0;
//    static int N;
//    public static void main(String[] args) {
//        //N받기
//        Scanner sc = new Scanner(System.in);
//        N = sc.nextInt();
//
//        //Attacked배열 NN할당하기
//        isAttacked = new int[N][N];
//        //BT(0,0,0)
//        BT(0,0,0);
//        System.out.println(answerCount);
//    }
//
//    private static void BT(int count, int row, int col){
//        //count == N -> answerCount++ &return
//        if(count == N){
//            answerCount++;
//            return;
//        }
//
//        for(int i=0;i<N;i++){
//            for(int j=0;j<N;j++){
//                if(isAttacked[i][j] != 0) continue;
//                //Attacked배열 처리
//                attack(i,j);
//
//                //BT(count+1, i,j)
//                BT(count+1, i,j);
//                //Attacked배열 해제
//                withdraw(i, j);
//            }
//        }
//    }
//
//    private static void attack(int row, int col){
//        //범위 안벗어나면 -> 가로, 세로체크
//        for(int i=0;i<N;i++){
//            for(int j=0;j<N;j++){
//                if(i==row) isAttacked[i][j]++;
//                if(j==col) isAttacked[i][j]++;
//            }
//        }
//        isAttacked[row][col]--;
//
//        //왼대각, 오른대각 체크
//        for(int k=1;k<N;k++){
//            if(row-k >= 0 && col-k >=0) isAttacked[row-k][col-k]++;
//            if(row-k >= 0 && col+k <N) isAttacked[row-k][col+k]++;
//            if(row+k < N && col-k >=0) isAttacked[row+k][col-k]++;
//            if(row+k < N && col+k <N) isAttacked[row+k][col+k]++;
//        }
//    }
//
//    private static void withdraw(int row, int col){
//        //범위 안벗어나면 -> 가로, 세로체크
//        for(int i=0;i<N;i++){
//            for(int j=0;j<N;j++){
//                if(i==row) isAttacked[i][j]--;
//                if(j==col) isAttacked[i][j]--;
//            }
//        }
//        isAttacked[row][col] ++;
//
//        //왼대각, 오른대각 체크
//        for(int k=1;k<N;k++){
//            if(row-k >= 0 && col-k >=0) isAttacked[row-k][col-k]--;
//            if(row-k >= 0 && col+k <N) isAttacked[row-k][col+k]--;
//            if(row+k < N && col-k >=0) isAttacked[row+k][col-k]--;
//            if(row+k < N && col+k <N) isAttacked[row+k][col+k]--;
//        }
//    }
//}

//import java.util.Scanner;
//
//public class B9663_NQueen {
//
//    public static int[] arr;
//    public static int N;
//    public static int count = 0;
//
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        N = in.nextInt();
//        arr = new int[N];
//
//        nQueen(0);
//        System.out.println(count);
//    }
//
//    public static void nQueen(int depth) {
//        // depth 가 곧 놓은 퀸의 갯수.
//        if (depth == N) { // 모든 원소를 다 채운 상태면 count 증가 및 return
//            count++;
//            return;
//        }
//        for (int i = 0; i < N; i++) {
//            arr[depth] = i; // arr[depth]=0일때 Possibility검사, arr[depth]=1일때 Possibility검사, arr[depth]=2일때 Possibility검사, arr[depth]=3일때 Possibility검사
//
//            if (Possibility(depth)) {
//                nQueen(depth + 1);
//            }
//        }
//    }
//    public static boolean Possibility(int depth) { //depth == 현재 놓여져 있는 퀸의 개수
//        //depth열과
//        for (int i = 0; i < depth; i++) { //현재 놓여져 있는 퀸의 개수만큼 for문을 돌면서 같은행 혹은 대각위치에 있는지 확인
//            //i열 검사
//
//            // if depth == 0 -> for문이 안돌기 때문에 무조건 true -> 퀸을 놓을 수 있다고 판단
//
//            //arr[i] -> i열의 어떤행에 퀸이 놓여져 있는지
//            //arr[depth] -> depth열의 어떤행에 퀸이 놓여져 있는지
//            if (arr[i] == arr[depth]) {
//                return false;
//            }
//
//            //depth열과 i열의 차 == depth열의 행위치와 i열의 행위치의 차 -> 대각선에 놓임
//            else if (Math.abs(depth - i) == Math.abs(arr[depth] - arr[i])) {
//                return false;
//            }
//        }
//        //앞의 두조건 모두에 해당하지 않는다면, 같은행에 위치하지도 않고, 대각에 위치하고 있지도 않다.
//        return true;
//    }
//}

//import java.util.Scanner;
//
//public class B9663_NQueen {
//
//    public static int[] arr;
//    public static int N;
//    public static int count = 0;
//
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        N = in.nextInt();
//        arr = new int[N];
//
//        nQueen(0);
//        System.out.println(count);
//    }
//
//    public static void nQueen(int depth) {
//        //depth가 N이면 count++, return
//        if(depth == N){
//            count++;
//            return;
//        }
//        //for문 arr[depth] = 0,1,2,3 하나씩 공급
//        for(int i=0;i<N;i++){
//            arr[depth] = i;
//            if(Possibility(depth)){
//                nQueen(depth+1);//OK이면 nQueen(depth+1);
//            }
//        }
//    }
//    public static boolean Possibility(int depth) { //depth열과 depth이전열의 퀸위치를 확인하면서 가능여부 확인
//        //for문으로 depth이전열 i열과 확인
//        for(int i=0;i<depth;i++){
//            //같은행에 위치 했는지
//            if(arr[i]==arr[depth]) return false;
//
//            //대각에 위치 했는지
//            if(Math.abs(i-depth) == Math.abs(arr[i]-arr[depth])) return false;
//        }
//        //아니라면 return true;
//        return true;
//    }
//}

//2차 풀이
import java.util.Scanner;

public class B9663_NQueen {

    private static int[] queens;
    private static int N;
    private static int count=0;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        queens = new int[N];

        bt(0);

        System.out.println(count);
    }

    public static void bt(int col){

        if (col == N){
            count++;
            return;
        }

        for(int i=0;i<N;i++){
            queens[col] = i;
            if(!canLocated(col)) continue;

            bt(col+1);
        }

    }

    public static boolean canLocated(int col){

        for(int i=0;i<col;i++){
           //대각
           if(Math.abs(queens[i] - queens[col]) == Math.abs(i-col)) return false;
           //행같음
           if(queens[i] == queens[col]) return false;
        }
        return true;
    }
}

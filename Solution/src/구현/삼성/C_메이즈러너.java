package 구현.삼성;

import java.util.Scanner;
/*
시간초과 - 무한루프 - 상황 : 시간복잡도 계산시 충분할때
                 ex : 찾을때까지 반복되는데, 없는 경우의수도 있는 경우
        최적화필요

        https://colorscripter.com/s/OOPBE7p
 */
public class C_메이즈러너 {
    static int N;
    static int M;
    static int K;
    static int sum;
    static int[] exit = new int[2];
    static int[][] map;

    public static void main(String[] args) {
        input();
        pro();
        System.out.println(sum);
        exit[0] +=1;
        exit[1] +=1;
        System.out.println(exit[0]+" "+exit[1]);
    }

    static void input(){
        Scanner sc = new Scanner(System.in);

        N=sc.nextInt();
        M=sc.nextInt();
        K=sc.nextInt();

        map = new int[N][N];

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                map[i][j] = -(sc.nextInt()); //벽 내구도 -로 입력받기
            }
        }

        while(M-->0){ // M명의 참가자
            int i=sc.nextInt()-1;
            int j=sc.nextInt()-1;
            map[i][j]++;
        }

        int i=sc.nextInt()-1;
        int j=sc.nextInt()-1;
        exit[0] = i;
        exit[1] = j;
        map[i][j] = -10;
    }

    static void pro() {
        //최대 10의 6승
        while(K-->0){ //최대 10의 2승
            참가자_이동();
            if(!personExists()) break; //참가자가 모두 출구로 이동해서 무한회전하는 경우를 방지하기 위함.
            회전(); //최대 10의 4승
        }
    }

    static void 참가자_이동(){

        int[][] temp = new int[N][N];

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(map[i][j] >= 1){ //참가자 발견
                    이동(i,j, temp);
                }
            }
        }

        //temp -> map이동
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(temp[i][j] >= 1) map[i][j] = temp[i][j]; //temp는 사람 or 0이므로, 이동한 사람위치만 복사한다.(벽들이 없어지는 것 방지)
            }
        }
    }

    //temp에 이동하는 이유 : 여러번 연속적으로 이동하는 경우 방지
    static void 이동(int i, int j, int[][] temp){
        int 원래_최단거리 = Math.abs(exit[0] - i) + Math.abs(exit[1] - j);

        int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}}; //상, 하, 좌, 우

        for(int d=0; d<4; d++) { // 방향 선택
            int nx = i + dir[d][0];
            int ny = j + dir[d][1];

            if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue; // 범위 검사
            if (-9 <= map[nx][ny] && map[nx][ny] <= -1) continue; // 벽이 있으면 이동 불가 - 유효성 검사
            int 새로운_최단거리 = Math.abs(exit[0] - nx) + Math.abs(exit[1] - ny);
            if (새로운_최단거리 >= 원래_최단거리) continue; // 최단거리가 짧아지는지 검사 - 유효성 검사

            sum+=map[i][j]; //사람수만큼 총 이동거리 누적
            if (map[nx][ny] >= 0) { //츨구가 아닌 경우(문제원인 : 빈공간 or 출구라고 생각했다.)
                temp[nx][ny] +=map[i][j]; //새위치 이동 - +=하는 이유 : 겹쳐질 수 있으니, =가 아니라 +=를 해줘야함.
            }
            map[i][j] = 0; //기존위치 없애기

            return;
        }
    }

//    static void 회전(){
//        int[][] 가장작은_정사각형 = 가장작은_정사각형_찾기(); //최대 10의 4승
//        정사각형_회전(가장작은_정사각형);
//    }

    static void subRotate(int x, int y, int d) {
        int[][] a = new int[N][N];
        int[][] b = new int[N][N];

        // 회전하는 부분을 a[0][0]부터 시작하도록 옮기기
        for (int i = x; i <= x + d; i++) {
            for (int j = y; j <= y + d; j++) {
                a[i - x][j - y] = map[i][j];
            }
        }

        // a 배열을 90도 회전해서 b 배열에 저장하기
        int n = d + 1; // 현재 옮겨하는 정사각형의 한 변의 격자 개수
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (-9 <= a[i][j] && a[i][j] <= -1) { //벽이 회전한다면, 내구도 감소
                    a[i][j]++;
                }
                // a[i][j] => a[j][n + 1 - i]
                b[j][n + 1 - i] = a[i][j];
                if(i==exit[0] && j==exit[1]){
                    exit[0] = i;
                    exit[1] = n+1-i;
                }
            }
        }

        //미로 복원
        for (int i = x; i <= x + d; i++) {
            for (int j = y; j <= y + d; j++) {
                map[i][j] = b[i - x + 1][j - y + 1];
            }
        }
    }

    static void 회전() {
        int minDist = 1000000;
        int[] ex = findExit();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] <= 0) continue;
                int dist = Math.max(Math.abs(i - ex[0]), Math.abs(j - ex[1]));
                minDist = Math.min(minDist, dist);
            }
        }

        int bestRow = 0, bestCol = 0;
        search: for (int i = 0; i < N - minDist; i++) {
            for (int j = 0; j < N - minDist; j++) {
                boolean flagExit = false, flagPerson = false;
                for (int r = i; r <= i + minDist; r++) {
                    for (int c = j; c <= j + minDist; c++) {
                        if (map[r][c] == -10) flagExit = true;
                        if (map[r][c] > 0) flagPerson = true;
                    }
                }
                if (flagExit && flagPerson) {
                    bestRow = i;
                    bestCol = j;
                    break search;
                }
            }
        }
        subRotate(bestRow, bestCol, minDist);
    }

    static int[] findExit() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == -10) {  // 만약 특정 격자에 -10 이 적혀있다면, 출구를 의미한다.
                    return new int[] {i, j};
                }
            }
        }
        return new int[]{-1,-1}; //못 찾은 경우
    }


    //규칙 질문하기(어떤 규칙으로 만들고 있는거지?) -> '다양한 경우의수 그림 관찰하기'
    static int[][] 가장작은_정사각형_찾기(){ //

        int min = 정사각형_시작길이_찾기(); //최대 100

        int length=min;
        int[] 좌상 = new int[2];
        int[] 우하= new int[2];
        int[] n좌상= new int[2];
        int[] n우하= new int[2];

        while(true){ //최대 100
            좌상[0] = exit[0]-(length-1);
            좌상[1] = exit[1]-(length-1);
            우하[0] = exit[0];
            우하[1] = exit[1];
            for(int i=0;i<length;i++){
                for(int j=0;j<length;j++){
                    //좌상, 우하 업데이트
                    n좌상[0] = 좌상[0]+i;
                    n좌상[1] = 좌상[1]+j;
                    n우하[0] = 우하[0]+i;
                    n우하[1] = 우하[1]+j;

                    //유효한 범위인지 검사
                    if(n좌상[0]<0 || n좌상[1]<0 || n우하[0]>=N || n우하[1] >= N) continue;
                    if(범위안에_참가자있는지_확인(n좌상, n우하)){ //최대 100
                        //상황 : 유효범위 찾음
                        int[][] 좌상우하 = new int[][]{n좌상,n우하};
                        return 좌상우하;
                    }
                }
            }
            length++;
        }
    }

    static int 정사각형_시작길이_찾기(){
        int min=Integer.MAX_VALUE;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(map[i][j] >= 1){ //참가자 발견시,
                    int dist = Math.max(Math.abs(exit[0] -i), Math.abs(exit[1]-j));
                    min = Math.min(min, dist);
                }
            }
        }
        return min;
    }

    static boolean 범위안에_참가자있는지_확인(int[] 좌상, int[] 우하){

        for(int i=좌상[0]; i<=우하[0] ; i++){
            for(int j=좌상[1];j<=우하[1];j++){
                if(map[i][j] >= 1) return true; //참가자 찾으면
            }
        }
        return false;

    }

    static void 정사각형_회전(int[][] 가장작은_정사각형) { // {{0,0},{2,2}};
        int[] 좌상 = 가장작은_정사각형[0];
        int[] 우하 = 가장작은_정사각형[1];
        int size = 우하[1]-좌상[1]+1;

        int[][] temp = new int[N][N];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (-9 <= map[i+좌상[0]][j+좌상[1]] && map[i+좌상[0]][j+좌상[1]] <= -1){ //벽인 경우
                    temp[j+좌상[0]][size-1-i+좌상[1]] = map[i+좌상[0]][j+좌상[1]] + 1; // 내구도 1 감소하면서 회전
                }else if(map[i+좌상[0]][j+좌상[1]] >= 1){ //참가자인 경우
                    temp[j+좌상[0]][size-1-i+좌상[1]] = map[i+좌상[0]][j+좌상[1]]; // 그냥 회전
                }else if(map[i+좌상[0]][j+좌상[1]] == -10){ //출구일 경우
                    temp[j+좌상[0]][size-1-i+좌상[1]] = map[i+좌상[0]][j+좌상[1]]; // 그냥 회전
                    //글로벌 출구위치 업데이트
                    exit[0] = j+좌상[0];
                    exit[1] = size-1-i+좌상[1];
                }
            }
        }

        // temp -> map 복사
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                map[i+좌상[0]][j+좌상[1]] = temp[i+좌상[0]][j+좌상[1]];
            }
        }

    }

    static boolean personExists(){
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(map[i][j] >= 1) return true;
            }
        }
        return false;
    }

}

/*
2% 시간초과 풀이
public class C_메이즈러너 {
    static int N;
    static int M;
    static int K;
    static int sum;
    static int[] exit = new int[2];
    static int[][] map;

    public static void main(String[] args) {
        input();
        pro();
        System.out.println(sum);
        exit[0] +=1;
        exit[1] +=1;
        System.out.println(exit[0]+" "+exit[1]);
    }

    static void input(){
        Scanner sc = new Scanner(System.in);

        N=sc.nextInt();
        M=sc.nextInt();
        K=sc.nextInt();

        map = new int[N][N];

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                map[i][j] = -(sc.nextInt()); //벽 내구도 -로 입력받기
            }
        }

        while(M-->0){ // M명의 참가자
            int i=sc.nextInt()-1;
            int j=sc.nextInt()-1;
            map[i][j]++;
        }

        int i=sc.nextInt()-1;
        int j=sc.nextInt()-1;
        exit[0] = i;
        exit[1] = j;
        map[i][j] = -10;
    }

    static void pro() {
        while(K-->0){ //최대 100
            참가자_이동();
            회전();
            if(!personExists()) break;
        }
    }

    static void 참가자_이동(){

        int[][] temp = new int[N][N];

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(map[i][j] >= 1){ //참가자 발견
                    이동(i,j, temp);
                }
            }
        }

        //temp -> map이동
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(temp[i][j] >= 1) map[i][j] = temp[i][j]; //temp는 사람 or 0이므로, 이동한 사람위치만 복사한다.(벽들이 없어지는 것 방지)
            }
        }
    }

    //temp에 이동하는 이유 : 여러번 연속적으로 이동하는 경우 방지
    static void 이동(int i, int j, int[][] temp){
        int 원래_최단거리 = Math.abs(exit[0] - i) + Math.abs(exit[1] - j);

        int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}}; //상, 하, 좌, 우

        for(int d=0; d<4; d++) { // 방향 선택
            int nx = i + dir[d][0];
            int ny = j + dir[d][1];

            if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue; // 범위 검사
            if (-9 <= map[nx][ny] && map[nx][ny] <= -1) continue; // 벽이 있으면 이동 불가 - 유효성 검사
            int 새로운_최단거리 = Math.abs(exit[0] - nx) + Math.abs(exit[1] - ny);
            if (새로운_최단거리 >= 원래_최단거리) continue; // 최단거리가 짧아지는지 검사 - 유효성 검사

            sum+=map[i][j]; //사람수만큼 총 이동거리 누적
            if (map[nx][ny] == 0) { //빈공간인 경우
                temp[nx][ny] +=map[i][j]; //새위치 이동
            }
            map[i][j] = 0; //기존위치 없애기

            return;
        }
    }

    static void 회전(){
        int[][] 가장작은_정사각형 = 가장작은_정사각형_찾기();
        정사각형_회전(가장작은_정사각형);
    }

    //규칙 질문하기(어떤 규칙으로 만들고 있는거지?) -> '다양한 경우의수 그림 관찰하기'
    static int[][] 가장작은_정사각형_찾기(){ //

        int min = 정사각형_시작길이_찾기();

        int length=min;
        int[] 좌상 = new int[2];
        int[] 우하= new int[2];
        int[] n좌상= new int[2];
        int[] n우하= new int[2];

        while(true){
            좌상[0] = exit[0]-(length-1);
            좌상[1] = exit[1]-(length-1);
            우하[0] = exit[0];
            우하[1] = exit[1];
            for(int i=0;i<length;i++){
                for(int j=0;j<length;j++){
                    //좌상, 우하 업데이트
                    n좌상[0] = 좌상[0]+i;
                    n좌상[1] = 좌상[1]+j;
                    n우하[0] = 우하[0]+i;
                    n우하[1] = 우하[1]+j;

                    //유효한 범위인지 검사
                    if(n좌상[0]<0 || n좌상[1]<0 || n우하[0]>=N || n우하[1] >= N) continue;
                    if(범위안에_참가자있는지_확인(n좌상, n우하)){
                        //상황 : 유효범위 찾음
                        int[][] 좌상우하 = new int[][]{n좌상,n우하};
                        return 좌상우하;
                    }
                }
            }
            length++;
        }
    }

    static int 정사각형_시작길이_찾기(){
        int min=Integer.MAX_VALUE;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(map[i][j] >= 1){ //참가자 발견시,
                    min = Math.min(min, Math.max(Math.abs(exit[0] -i), Math.abs(exit[1]-j)));
                }
            }
        }
        return min;
    }

    static boolean 범위안에_참가자있는지_확인(int[] 좌상, int[] 우하){

        for(int i=좌상[0]; i<=우하[0] ; i++){
            for(int j=좌상[1];j<=우하[1];j++){
                if(map[i][j] >= 1) return true; //참가자 찾으면
            }
        }
        return false;

    }

    static void 정사각형_회전(int[][] 가장작은_정사각형) { // {{0,0},{2,2}};
        int[] 좌상 = 가장작은_정사각형[0];
        int[] 우하 = 가장작은_정사각형[1];
        int size = 우하[1]-좌상[1]+1;

        int[][] temp = new int[N][N];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (-9 <= map[i+좌상[0]][j+좌상[1]] && map[i+좌상[0]][j+좌상[1]] <= -1){ //벽인 경우
                    temp[j+좌상[0]][size-1-i+좌상[1]] = map[i+좌상[0]][j+좌상[1]] + 1; // 내구도 1 감소하면서 회전
                }else if(map[i+좌상[0]][j+좌상[1]] >= 1){ //참가자인 경우
                    temp[j+좌상[0]][size-1-i+좌상[1]] = map[i+좌상[0]][j+좌상[1]]; // 그냥 회전
                }else if(map[i+좌상[0]][j+좌상[1]] == -10){ //출구일 경우
                    temp[j+좌상[0]][size-1-i+좌상[1]] = map[i+좌상[0]][j+좌상[1]]; // 그냥 회전
                    //글로벌 출구위치 업데이트
                    exit[0] = j+좌상[0];
                    exit[1] = size-1-i+좌상[1];
                }
            }
        }

        // temp -> map 복사
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                map[i+좌상[0]][j+좌상[1]] = temp[i+좌상[0]][j+좌상[1]];
            }
        }

    }

    static boolean personExists(){
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(map[i][j] >= 1) return true;
            }
        }
        return false;
    }

}
*/

/*
#include <iostream>
#include <algorithm>
#define NM 15
using namespace std;
int maze[NM][NM];
// maze[i][j]
// 0 -> 빈 칸
// -1 ~ -9 -> 벽을 의미하고, 내구도는 양수 버전입니다.  (예, -5 => 내구도가 5인 벽)
// -10 -> 출구
// 1 이상의 자연수 -> 해당 칸에 존재하는 참가자의 수  (예, 8 => 8명의 참가자가 해당 칸에 존재)
int N, K;       // 미로의 크기, 게임 시간
int moveCnt;    // 모든 참가자들의 이동 거리 합

void input() {  // 입력 받기
    int M;
    cin >> N >> M >> K;
    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= N; j++) {
            int x;
            cin >> x;
            maze[i][j] = -x;  // 해당 위치에 벽 생성
        }
    }
    for (int i = 1; i <= M; i++) {
        int x, y;
        cin >> x >> y;
        maze[x][y]++;  // 해당 위치의 참가자 수를 1 증가
    }

    int x, y;
    cin >> x >> y;
    maze[x][y] = -10;
}
pair<int, int> findExit() {  // 출구의 좌표를 돌려주는 함수
    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= N; j++) {
            if (maze[i][j] == -10) {  // 만약 특정 격자에 -10 이 적혀있다면, 출구를 의미한다.
                return { i,j };
            }
        }
    }
}
int dirs[4][2] = { {-1,0},{1,0},{0,-1},{0,1} };  // 상, 하, 좌, 우 순서로 인접한 네 방향

void moveAll() { // 1. 모든 참가자를 한 칸씩 이동시키기
    int newMaze[15][15] = { 0, };  // 이동 결과를 저장할 배열
    auto ex = findExit();

    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= N; j++) {
            if (maze[i][j] < 0) {  // 해당 격자에 벽이나 출구가 있다면, 그대로 복사하기
                newMaze[i][j] = maze[i][j];
                continue;
            }
            if (maze[i][j] == 0) {
                continue;
            }
            int curDist = abs(i - ex.first) + abs(j - ex.second);
            int minDist = curDist, minI, minJ;
            for (int k = 0; k < 4; k++) {
                int ni = i + dirs[k][0];
                int nj = j + dirs[k][1];
                // (i, j) => (ni, nj)
                if (ni < 1 || nj < 1 || ni > N || nj > N) continue; // 만약 격자를 벗어나면 무효
                if (-9 <= maze[ni][nj] && maze[ni][nj] <= -1) continue; // 만약 벽을 향한 이동이면 무효
                int dist = abs(ni - ex.first) + abs(nj - ex.second); // 출구와 (ni, nj) 사이의 거리
                if (minDist > dist) { // 출구까지의 거리가 "현재보다" 더 가까워졌다면, 갱신
                    minDist = dist;
                    minI = ni;
                    minJ = nj;
                }
            }
            // 만약 아무 곳도 갈 수가 없는 경우
            if (minDist == curDist) {
                newMaze[i][j] += maze[i][j];  // 그대로 위치가 유지
                continue;
            }
            moveCnt += maze[i][j]; // 참가자들이 1만큼 움직인다.

            // 탈출에 성공하는 경우
            if (maze[minI][minJ] == -10) {
                continue;
            }
            newMaze[minI][minJ] += maze[i][j];  // (i, j) -> (minI, minJ)
        }
    }
    // 미로 덮어쓰기
    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= N; j++) {
            maze[i][j] = newMaze[i][j];
        }
    }
}

void subRotate(int x, int y, int d) {  // (x, y)에서 시작해서 한 변의 길이가 d 인 정사각형을 회전
    int a[NM][NM] = { 0, };  // 더미 배열
    int b[NM][NM] = { 0, };  // 더미 배열
    // 회전해야 하는 부분을 a[1][1] 부터 시작하도록 옮기기
    for (int i = x; i <= x + d; i++) {
        for (int j = y; j <= y + d; j++) {
            a[i - x + 1][j - y + 1] = maze[i][j];
        }
    }
    // a 배열을 90도 회전해서 b 배열에 저장하기
    int n = d + 1;  // 현재 옮겨하는 정사각형의 한 변의 격자 개수
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= n; j++) {
            if (-9 <= a[i][j] && a[i][j] <= -1) {  // 벽이 회전한다면, 내구도 변화
                a[i][j]++;
            }
            // a[i][j] => a[j][n + 1 - i]
            b[j][n + 1 - i] = a[i][j];
        }
    }

    // 다시 미로에 복원하기
    for (int i = x; i <= x + d; i++) {
        for (int j = y; j <= y + d; j++) {
            maze[i][j] = b[i - x + 1][j - y + 1];
        }
    }
}
void rotate() { // 출구와 참가자를 포함한 회전
    // 1. 정사각형의 크기를 먼저 결정하자.
    // 가장 작은 정사각형의 크기 = 출구 & (출구랑 가장 가까운 참가자)
    int minDist = 1000000;
    auto ex = findExit();
    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= N; j++) {
            if (maze[i][j] <= 0) continue;  // 해당 위치에 참가자가 없다면 무시하기
            int dist = max(abs(i - ex.first), abs(j - ex.second));  // (i, j) 와 출구 사이의 거리
            minDist = min(minDist, dist);
        }
    }
    // 2. 정사각형의 위치를 그 다음으로 결정하자.
    int bestRow = 0, bestCol = 0; // 결정된 정사각형의 좌상단 좌표
    for (int i = 1; i <= N - minDist; i++) {
        for (int j = 1; j <= N - minDist; j++) {
            // (i, j) := 이번에 결정한 정사각형의 좌상단 좌표
            // 행 := i ~ i + minDist
            // 열 := j ~ j + minDist
            bool flagExit = false, flagPerson = false;
            for (int r = i; r <= i + minDist; r++) {
                for (int c = j; c <= j + minDist; c++) {
                    if (maze[r][c] == -10) flagExit = true;
                    if (maze[r][c] > 0) flagPerson = true;
                }
            }
            if (flagExit && flagPerson) {  // 이번에 결정한 정사각형이 사람과 출구를 모두 포함!
                bestRow = i;
                bestCol = j;
                break;
            }
        }
        if (bestRow != 0) break;
    }
    // 3. 회전하기
    subRotate(bestRow, bestCol, minDist);
}
void output() {  // 정답 출력
    cout << moveCnt << "\n";
    auto ex = findExit();
    cout << ex.first << " " << ex.second;
}
bool isFinish() {
    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= N; j++) {
            if (maze[i][j] > 0) {  // 한 번이라도 참가자가 발견되면
                return false;
            }
        }
    }
    return true;
}
int main() {
    // 입력 받기
    input();
    while (K--) {
        // 시뮬레이션 진행
        // 1. 모든 참가자를 한 칸씩 이동시키기
        moveAll();
        if (isFinish()) {  // 게임 종료 조건 확인
            break;
        }
        // 2. 출구와 참가자를 포함한 회전
        rotate();
    }
    // 정답 출력
    output();
    return 0;
}
 */
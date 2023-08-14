package 구현;

//실패 풀이
//import java.util.Scanner;

//public class B20057_마법사상어와토네이도 {
//
//    static int N;
//    static int[][] A;
//
//    static int outSand =0;
//
//    static int[] leftdy = {1,-1,1,-1,2,-2,1,-1,0,0};
//    static int[] leftdx = {0,0,-1,-1,-1,-1,-2,-2,-3,-2};
//
//    static int[] rightdy = {1,-1,1,-1,2,-2,1,-1,0,0};
//    static int[] rightdx = {0,0,1,1,1,1,2,2,3,2};
//
//    static int[] downdy = {0,0,1,1,1,1,2,2,3,2};
//    static int[] downdx = {-1,1,-1,1,-2,2,-1,1,0,0};
//
//    static int[] updy = {0,0,-1,-1,-1,-1,-2,-2,-3,-2};
//    static int[] updx = {-1,1,-1,1,-2,2,-1,1,0,0};
//
//    static double[] sand = {0.01, 0.01, 0.07, 0.07, 0.02, 0.02, 0.10, 0.10, 0.05};
//
//    public static void main(String[] args) {
//
//        Scanner sc = new Scanner(System.in);
//        N = sc.nextInt();
//        A = new int[N+1][N+1];
//
//        for(int i=1;i<N+1;i++){
//            for(int j=1;j<N+1;j++){
//                A[i][j] = sc.nextInt();
//            }
//        }
//
//        tonado(N/2+1, N/2+1);
//        System.out.println(outSand);
//    }
//
//    static void tonado(int y,int x){
//
//        if(y==1 && x==1) return;
//
//        //영역 판단
//        String direction = null;
//
//        if(y<=x && y<=-x+N+1){
//            direction = "left";
//        }else if(y>=x+1&&y<=-x+N){
//            direction = "down";
//        }else if(y>=x+1 && y>=-x+N+1){
//            direction = "right";
//        }else if(y<=x&&y>=-x+(N+2)){
//            direction = "up";
//        }
//
//        System.out.println(direction);
//
//        switch(direction){
//            case "up" -> {
//                int spreadTotal = 0;
//
//                for(int i=0;i<10;i++){
//                    int ny = y+updy[i];
//                    int nx = x+updx[i];
//
//                    if(ny<=N && ny>=1 && nx<=N && nx>=1){
//                        if(i!=9){
//                            A[ny][nx] += (int)(A[y+1][x] * sand[i]);
//                            spreadTotal += (int)(A[y+1][x] * sand[i]);
//                        }else{
//                            A[ny][nx] += spreadTotal;
//                            A[y+1][x] = 0;
//                        }
//                    }else{ //범위 벗어나면
//                        if(i!=9){
//                            outSand += (int)(A[y+1][x] * sand[i]);
//                            spreadTotal += (int)(A[y+1][x] * sand[i]);
//                        }else{
//                            outSand += spreadTotal;
//                            A[y+1][x] = 0;
//                        }
//                    }
//                }
//
//                tonado(y-1,x);
//            }
//            case "down" -> {
//                int spreadTotal = 0;
//
//                for(int i=0;i<10;i++){
//                    int ny = y+downdy[i];
//                    int nx = x+downdx[i];
//
//
//                    if(ny<=N && ny>=1 && nx<=N && nx>=1){
//                        if(i!=9){
//                            A[ny][nx] += (int) (A[y-1][x] * sand[i]);
//                            spreadTotal += (int) (A[y-1][x] * sand[i]);
//                        }else{
//                            A[ny][nx] += spreadTotal;
//                            A[y-1][x] = 0;
//                        }
//                    }else{ //범위 벗어나면
//                        if(i!=9){
//                            outSand += (int)(A[y-1][x] * sand[i]);
//                            spreadTotal += (int) (A[y-1][x] * sand[i]);
//                        }else{
//                            outSand += spreadTotal;
//                            A[y-1][x] = 0;
//                        }
//                    }
//                }
//                tonado(y+1,x);
//            }
//            case "left" -> {
//                int spreadTotal = 0;
//
//                for(int i=0;i<10;i++){
//                    int ny = y+leftdy[i];
//                    int nx = x+leftdx[i];
//
//                    if(ny<=N && ny>=1 && nx<=N && nx>=1){
//                        if(i!=9){
//                            A[ny][nx] += (int) (A[y][x-1] * sand[i]);
//                            spreadTotal += (int) (A[y][x-1] * sand[i]);
//                        }else{
//                            A[ny][nx] += spreadTotal;
//                            A[y][x-1] = 0;
//                        }
//                    }else{ //범위 벗어나면
//                        if(i!=9){
//                            outSand += (int)(A[y][x-1] * sand[i]);
//                            spreadTotal += (int) (A[y][x-1] * sand[i]);
//                        }else{
//                            outSand += spreadTotal;
//                            A[y][x-1] = 0;
//                        }
//                    }
//                }
//                tonado(y,x-1);
//
//            }
//            case "right" -> {
//                int spreadTotal = 0;
//
//                for(int i=0;i<10;i++){
//                    int ny = y+rightdy[i];
//                    int nx = x+rightdx[i];
//
//
//                    if(ny<=N && ny>=1 && nx<=N && nx>=1){
//                        if(i!=9){
//                            A[ny][nx] += (int)(A[y][x+1] * sand[i]);
//                            spreadTotal += (int) (A[y][x+1] * sand[i]);
//                        }else{
//                            A[ny][nx] += spreadTotal;
//                            A[y][x+1] = 0;
//                        }
//                    }else{ //범위 벗어나면
//                        if(i!=9){
//                            outSand += (int)(A[y][x+1] * sand[i]);
//                            spreadTotal += (int)(A[y][x+1] * sand[i]);
//                        }else{
//                            outSand += spreadTotal;
//                            A[y][x+1] = 0;
//                        }
//                    }
//                }
//
//                tonado(y,x+1);
//            }
//        }
//    }
//}

//정답 풀이
//import java.io.BufferedReader;
//        import java.io.BufferedWriter;
//        import java.io.InputStreamReader;
//        import java.io.OutputStreamWriter;
//        import java.util.StringTokenizer;
//
//public class B20057_마법사상어와토네이도 {
//    static int N;
//    static int[][] map;
//    static int[] dx = {0,1,0,-1};   //토네이토의 x 이동 방향
//    static int[] dy = {-1,0,1,0};   //토네이토의 y 이동 방향
//    static int[] dc = {1,1,2,2};   // 토네이도의 각 방향으로 이동하는 횟수
//    static int[][] dsx = {{-1,1,-2,-1,1,2,-1,1,0}, {-1,-1,0,0,0,0,1,1,2},    //모래가 퍼지는 x방향
//            {1,-1,2,1,-1,-2,1,-1,0}, {1,1,0,0,0,0,-1,-1,-2}};
//    static int[][] dsy = {{1,1,0,0,0,0,-1,-1,-2},{-1,1,-2,-1,1,2,-1,1,0},    //모래가 퍼지는 y방향
//            {-1,-1,0,0,0,0,1,1,2},{1,-1,2,1,-1,-2,1,-1,0}};
//    static int[] sandRatio ={1,1,2,7,7,2,10,10,5};
//    public static void main(String[] args) throws Exception{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//        StringTokenizer st;
//
//        N = Integer.parseInt(br.readLine().trim());
//        map = new int[N][N];
//
//        for(int r=0; r<N; r++){
//            st = new StringTokenizer(br.readLine()," ");
//            for(int c=0; c<N; c++){
//                map[r][c] = Integer.parseInt(st.nextToken());
//            }
//        }
//
//        int result = calculateOutSand(N/2, N/2);
//        bw.write(String.valueOf(result));
//        bw.flush();
//
//    }
//    //현재위치에서 이동 -> 이동한 위치의 모래 뿌리기 -> 이동한위치를 현재위치로 업데이트
//    static int calculateOutSand(int x, int y){
//        int totalOutSand = 0;
//
//        int currentX = x;
//        int currentY = y;
//
//        while (true) {
//            for(int d = 0; d<4; d++){
//                for(int moveCount = 0; moveCount<dc[d]; moveCount++){
//                    //현재위치에서 이동
//                    int nextX = currentX+dx[d];
//                    int nextY = currentY+dy[d];
//
//                    if(nextX<0 || nextY<0 || nextX>=N ||nextY>=N){
//                        return totalOutSand;
//                    }
//
//                    //이동한 위치의 모래 뿌리기
//                    int sand = map[nextX][nextY];
//                    map[nextX][nextY] = 0;
//                    int spreadTotal = 0;
//
//
//                    for(int spread = 0; spread<9; spread++){
//                        int sandX = nextX + dsx[d][spread];
//                        int sandY = nextY + dsy[d][spread];
//                        int spreadAmount = (sand*sandRatio[spread])/100;
//
//                        if(sandX<0 || sandX>=N || sandY<0 || sandY>=N){
//                            totalOutSand += spreadAmount;
//                        }
//                        else{
//                            map[sandX][sandY]+=spreadAmount;
//                        }
//                        spreadTotal+= spreadAmount;
//                    }
//
//                    //알파
//                    int alphaX = nextX+dx[d];
//                    int alphaY = nextY+dy[d];
//                    int alphaAmount = sand -spreadTotal;
//                    if(alphaX<0 || alphaX>=N || alphaY<0|| alphaY>=N){
//                        totalOutSand +=alphaAmount;
//                    }
//                    else{
//                        map[alphaX][alphaY] +=alphaAmount;
//                    }
//
//
//                    //이동한 위치를 현재위치로 업데이트
//                    currentX = nextX;
//                    currentY = nextY;
//                }
//            }
//
//            //횟수 업데이트
//            for(int index = 0; index<4; index++){
//                dc[index] +=2;
//            }
//        }
//    }
//
//}

//import java.util.Scanner;
//
//public class B20057_마법사상어와토네이도 {
//
//    static int N;
//    static int[][] map;
//    static int outSand=0;
//
//    private static int[] dx = {0,1,0,-1};
//    private static int[] dy = {-1,0,1,0};
//
//    private static int[][] dsx = {{-1,1,-2,2,-1,1,-1,1,0},{-1,-1,0,0,0,0,1,1,2},{-1,1,-2,2,-1,1,-1,1,0},{1,1,0,0,0,0,-1,-1,-2}};
//    private static int[][] dsy = {{1,1,0,0,0,0,-1,-1,-2},{-1,1,-2,2,-1,1,-1,1,0},{-1,-1,0,0,0,0,1,1,2},{-1,1,-2,2,-1,1,-1,1,0}};
//    private static int[] dc = {1,1,2,2};
//    private static double[] ratio = {0.01, 0.01, 0.02, 0.02, 0.07, 0.07, 0.1, 0.1, 0.05 };
//
//
//    public static void main(String[] args) {
//
//        //N입력 받기
//        Scanner sc = new Scanner(System.in);
//        N = sc.nextInt();
//        map = new int[N][N];
//
//        //map입력 받기
//        for(int r=0;r<N;r++){
//            for(int c=0;c<N;c++){
//                map[r][c] = sc.nextInt();
//            }
//        }
//
//        tonado(N/2, N/2);
//        //outSand 출력
//        System.out.println(outSand);
//
//    }
//
//
//    static void tonado(int row, int col) {
//
//        //현재좌표 설정
//        int currentX = row;
//        int currentY = col;
//
//        int nextX;
//        int nextY;
//
//        while (true) {
//            for (int direction = 0; direction < 4; direction++) {
//                for (int j = 0; j < dc[direction]; j++) { //j번 반복
//
//                    if (currentX == 0 && currentY == 0) return; // 이유 : - <- 작동까지했을때가 다음좌표 1,1로 설정, 그 이후에
//
//                    //nextX, nextY 설정 by dy, dx
//                    nextX = currentX + dx[direction];
//                    nextY = currentY + dy[direction];
//
//                    int sand = map[nextX][nextY]; //다음칸 모래이식
//                    map[nextX][nextY] = 0;
//                    int spreadTotal = 0;
//
//                    for (int spread = 0; spread < 9; spread++) {
//                        int spreadX = nextX + dsx[direction][spread];
//                        int spreadY = nextY + dsy[direction][spread];
//
//                        if (spreadX >= 0 && spreadX < N && spreadY >= 0 && spreadY < N) {
//                            map[spreadX][spreadY] += (int) (sand * ratio[spread]);
//                        } else { //범위밖
//                            outSand += (int) (sand * ratio[spread]);
//                        }
//
//                        //spreadTotal 적립
//                        spreadTotal += (int) (sand * ratio[spread]);
//                    }
//
//                    //알파 좌표 설정
//                    int alphaX = nextX + dx[direction];
//                    int alphaY = nextY + dy[direction];
//
//                    //알파 좌표 범위안
//                    if (alphaX >= 0 && alphaX < N && alphaY >= 0 && alphaY < N) {
//                        map[alphaX][alphaY] += sand - spreadTotal;
//                    } else { //범위밖
//                        outSand += sand - spreadTotal;
//                    }
//
//                    currentX = nextX;
//                    currentY = nextY;
//                }
//            }
//
//            //dc +2씩 더해주기
//            for (int i = 0; i < 4; i++) {
//                dc[i] += 2;
//            }
//        }
//    }
//}

import java.util.Scanner;

public class B20057_마법사상어와토네이도 {

    static int[] dc = {1,1,2,2};
    static int[] dx = {0,1,0,-1};
    static int[] dy = {-1,0,1,0};
    static int[][] dsx = {{-1,1,-2,2,-1,1,-1,1,0},
            {0,0,1,1,1,1,2,2,3},
            {-1,1,-2,2,-1,1,-1,1,0},
            {0,0,-1,-1,-1,-1,-2,-2,-3}};
    static int[][] dsy = {{0,0,-1,-1,-1,-1,-2,-2,-3},
            {-1,1,-2,2,-1,1,-1,1,0},
            {0,0,1,1,1,1,2,2,3},
            {-1,1,-2,2,-1,1,-1,1,0}};
    static int[] ratio = {1,1,2,2,7,7,10,10,5};

    static int[][] sand;
    static int N;
    static int outSand=0;

    public static void main(String[] args) {

        //N입력받기
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        sand = new int[N][N];
        //모래판 입력받기
        for(int i=0;i<N;i++) {
            for (int j = 0; j < N; j++) {
                sand[i][j] = sc.nextInt();
            }
        }

        tonado(N/2, N/2);

        //outSand 출력
        System.out.println(outSand);

    }

    static void tonado(int startR, int startC){

        int currentR = startR;
        int currentC = startC;

        while(true) {
            for (int i = 0; i < 4; i++) {
                for (int rCount = 0; rCount < dc[i]; rCount++) {
                    //종료조건
                    if (currentR == 0 && currentC == 0) return; //종료

                    //이동칸 좌표구하기
                    int nextR = currentR + dx[i];
                    int nextC = currentC + dy[i];

                    //이동칸 모래 복사
                    int targetSand = sand[nextR][nextC];
                    int moveSandSum = 0;
                    for (int j = 0; j < 9; j++) {
                        //모래비율 배열 이용해서 이동시킬 모래양 구하기
                        int moveSand = (targetSand * ratio[j]) / 100;
                        moveSandSum += moveSand;

                        //방향배열 이용해서 목표모래지점 좌표구하기
                        int nr = currentR + dsx[i][j];
                        int nc = currentC + dsy[i][j];
                        if (nr >= 0 && nr < N && nc >= 0 && nc < N) { //범위 안벗어나면
                            sand[nr][nc] += moveSand; //모래이동시키기
                        } else {
                            outSand += moveSand; //벗어난모래양+
                        }
                    }
                    int restSand = targetSand - moveSandSum; //남은모래양 구하기

                    int alphaR = nextR + dx[i];
                    int alphaC = nextC + dy[i];

                    //범위 안벗어나면
                    if (alphaR >= 0 && alphaR < N && alphaC >= 0 && alphaC < N) {
                        //방향배열 이용해서 모래이동시키기(알파)
                        sand[alphaR][alphaC] += restSand;
                    } else {
                        outSand += restSand;
                    }

                    //이동
                    currentR = nextR;
                    currentC = nextC;
                }
            }

            for (int i = 0; i < 4; i++) {
                dc[i] += 2;//이동횟수 배열 업데이트
            }
        }
    }
}
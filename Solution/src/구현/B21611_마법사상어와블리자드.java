package 구현;

//틀린 풀이
//public class B21611_마법사상어와블리자드 {
//    static int[][] map;
//    static int N;
//    static int[] dx={0,1,0,-1}; //상하좌우 -> 좌하우상 : 1->3, 2->1, 3->0, 4->2
//    static int[] dy={-1,0,1,0};
//
//    static int[] dc = {1,1,2,2};
//
//    static List<int[]> magicList = new ArrayList<>();
//    static Queue<Marble> q = new LinkedList<>(); //q : 기본큐
//    static List<Marble> orderList = new ArrayList<>();
//    static int[] destroyCounts = new int[4];
//
//    static class Marble{
//        int x;
//        int y;
//        int num;
//
//        Marble(int x, int y, int num){
//            this.x=x;
//            this.y=y;
//            this.num=num;
//        }
//
//        @Override
//        public boolean equals(Object o){
//            return this.x==((Marble)o).x && this.y==((Marble)o).y;
//        }
//
//        @Override
//        public int hashCode() {
//            int result = Integer.hashCode(x);
//            result = 31 * result + Integer.hashCode(y);
//            return result;
//        }
//
//    }
//
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//
//        //N, M입력받기
//        N =sc.nextInt();
//        int M = sc.nextInt();
//
//        map = new int[N][N]; //map 초기화
//        //구슬 입력받기
//        for(int i=0;i<N;i++){
//            for(int j=0;j<N;j++){
//                map[i][j] = sc.nextInt();
//            }
//        }
//
//        //마법 list 입력받기
//        for(int i=0;i<M;i++){
//            int direct = sc.nextInt();
//            int dist = sc.nextInt();
//            magicList.add(new int[]{direct, dist});
//        }
//
//        구슬입력받기_순서리스트만들기();
//
//        for(int[] magic : magicList){ //M번
//
//            int direction = magic[0];
//            int dist = magic[1];
//
//            구슬파괴(direction, dist);
//            구슬위치조정();
//
//            while(구슬폭발());
//
//            구슬위치조정();
//            구슬변화();
//            구슬위치조정();
//        }
//
//        int sum=0;
//        for(int i=1;i<destroyCounts.length;i++){
//            sum += destroyCounts[i]*i;
//        }
//        System.out.println(sum);
//    }
//
//    static void 구슬입력받기_순서리스트만들기(){
//
//        int cx=N/2;
//        int cy=N/2;
//        //맵 순회하면서 입력받은 구슬 큐에 넣기
//        while(true){
//            for(int i=0;i<4;i++){ //방향선택
//                for(int count=0;count<dc[i];count++){ //~만큼 반복
//                    cx = cx+dx[i];
//                    cy = cy+dy[i];
//                    if(cx<0||cx>=N||cy<0||cy>=N) return; // 0,0 넘어가면 종료
//
//                    orderList.add(new Marble(cx, cy, 0)); //순서리스트에 좌표 add
//                    if(map[cx][cy]==0) continue; //0이면 큐에 안넣는다.
//                    q.add(new Marble(cx, cy, map[cx][cy])); //큐에 넣기
//                }
//            }
//
//            for(int i=0;i<4;i++){
//                dc[i]+=2;
//            }
//        }
//
//    }
//
//    static void 구슬파괴(int direction, int dist){
//
//        Set<Marble> destroy = new HashSet<>();//destory : 파괴예약 Set
//        //방향선택
//
//        switch (direction){
//            case 1:
//                direction=3;
//                break;
//            case 2:
//                direction=1;
//                break;
//            case 3 :
//                direction=0;
//                break;
//            case 4:
//                direction=2;
//                break;
//        }
//
//        int cx = N/2;
//        int cy = N/2;
//
//        for(int i=0;i<dist;i++){ //거리만큼
//            cx = cx+dx[direction];
//            cy = cy+dy[direction];
//
//            destroy.add(new Marble(cx, cy, 0));//파괴리스트에 추가
//        }
//
//        구슬파괴(destroy);//구슬 파괴
//    }
//
//    static void 구슬파괴(Set<Marble> destroy){
//        Queue<Marble> tempQ = new LinkedList<>(); //temp큐 생성
//        while(!q.isEmpty()){
//            Marble now = q.poll();//큐 원소 꺼내기
//
//            if(destroy.contains(now)){ //파괴할 구슬 좌표 일치하면
//                //(그냥 버리기 - temp큐에 안넣기)
//            }else{
//                tempQ.add(now);//temp큐에 넣기
//            }
//        }
//        q = tempQ; //tempQ를 기존큐에 값복사?
//    }
//
//    static void 구슬위치조정(){ //구슬위치 업데이트
//
//        Queue<Marble> tempQ = new LinkedList<>(); //temp큐 생성
//        int index=0;
//        while(!q.isEmpty()){
//            Marble now = q.poll();//큐 원소 꺼내기
//
//            //꺼낸원소좌표랑 순서리스트의 좌표랑 다름
//            if(now.x != orderList.get(index).x || now.y != orderList.get(index).y){
//                //위치 업데이트
//                now.x = orderList.get(index).x;
//                now.y = orderList.get(index).y;
//            }
//
//            tempQ.add(now);//꺼낸원소 temp큐에 다시 넣기;
//            index++;
//        }
//        q = tempQ;
//    }
//
//
//    static boolean 구슬폭발(){
//        boolean bomb=false;
//
//        Queue<Marble> succQ = new LinkedList<>(); //연속큐 생성
//        Queue<Marble> tempQ = new LinkedList<>(); //temp큐 생성
//
//        q.add(new Marble(0,0,0)); //기존큐에 0원소넣기 - 어떤구슬과도 다른 숫자이므로, 마지막 원소까지 남김없이 처리됨
//
//        while(!q.isEmpty()){
//            Marble now = q.poll();//큐 원소 꺼내기
//            int succNum = !succQ.isEmpty() ? succQ.peek().num : 0; //연속 숫자
//            int succCount = succQ.size(); //연속Count
//
//            if(now.num==succNum){ //연속숫자랑 같음 -> 숫자 연속
//                succQ.add(new Marble(now.x, now.y, now.num));//연속큐에 넣기
//            }else{ //꺼낸원소의 숫자 불연속
//                if(succCount<4){ //연속count가 4미만
//                    while(!succQ.isEmpty()){ //기존에 연속큐에 있던 원소들 전부 temp큐에 넣기
//                        tempQ.add(succQ.poll());
//                    }
//                }else{ //4이상
//                    bomb = true;
//                    while(!succQ.isEmpty()){ //연속큐 전부 버리기
//                        Marble succ = succQ.poll();
//                        destroyCounts[succ.num]++; //버릴 원소 종류, 개수 Count
//                    }
//                }
//                succQ.add(now); //꺼낸원소 연속큐에 넣기 - (기존 연속큐 원소를 모두 큐에넣은 뒤에) 꺼낸원소를 연속큐에 넣어야함.
//            }
//        }
//        q = tempQ;
//        return bomb;
//    }
//
//    static void 구슬변화(){
//
//        Queue<Marble> succQ = new LinkedList<>(); //연속큐 생성
//        Queue<Marble> tempQ = new LinkedList<>(); //temp큐 생성
//
//        q.add(new Marble(0,0,0)); //기존큐에 0원소넣기 - 어떤구슬과도 다른 숫자이므로, 마지막 원소까지 남김없이 처리됨
//
//        while(!q.isEmpty()){
//            Marble now = q.poll();//큐 원소 꺼내기
//            int succNum = !succQ.isEmpty() ? succQ.peek().num : 0; //연속 숫자
//            int succCount = succQ.size(); //연속Count
//
//            if(now.num==succNum){ //연속숫자랑 같음 -> 숫자 연속
//                succQ.add(new Marble(now.x, now.y, now.num));//연속큐에 넣기
//            }else{ //숫자 불연속
//
//                if(tempQ.size()<N*N-1) tempQ.add(new Marble(0,0,succCount)); //if(temp큐사이즈 < 기준사이즈) 연속개수 원소 temp큐에 넣기
//                if(tempQ.size()<N*N-1) tempQ.add(new Marble(0,0,succNum));  //if(temp큐사이즈 < 기준사이즈) 연속숫자 원소 temp큐에 넣기
//
//                succQ.add(now); //꺼낸원소 연속큐에 넣기 - (기존 연속큐 원소를 모두 큐에넣은 뒤에) 꺼낸원소를 연속큐에 넣어야함.
//            }
//        }
//
//        q = tempQ;
//    }
//}

//import java.util.Scanner;
//
//public class B21611_마법사상어와블리자드 {
//    static final int NM = 51; //NM : 최대 변길이
//    static int M;
//    static int N;
//    static int[][] original = new int[NM][NM]; //original : 기존 달팽이 배열
//    static int[][] num = new int[NM][NM]; // num :
//    static int[] a = new int[NM * NM], b = new int[NM * NM]; //a : 달팽이를 1차원으로 핀 배열(주사용 배열), b : 임시배열
//    static int ans;
//    static int[] cnt = new int[5];
//
//    public static void calculate_snail_num() {
//        int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
//        int x = 1, y = 1, d = 0, v = N * N - 1; //d : 방향인덱스, v : 순번
//
//        while (v > 0) { // 총개수만큼(v가 N*N-1 -> 1때까지)
//            a[v] = original[x][y]; //달팽이배열 1차원으로 펴기
//            num[x][y] = v--;
//            //상황 : 같은것 반복하다가 특정조건시 변환 - 같은방향 반복하다가 특정조건 성립시 방향전환
//            while (true) { //다음좌표 유효할때까지 유효좌표 찾기
//                int nx = x + dir[d][0]; //nx : 다음x좌표
//                int ny = y + dir[d][1]; //ny : 다음y좌표
//                if ((nx < 1 || ny < 1 || nx > N || ny > N) || num[nx][ny] != 0) { //다음좌표가 벗어나거나 순번배열이 이미 등록이 되있다면
//                    d = (d + 1) % 4; //방향을 바꿔서
//                    continue; //다음좌표 할당
//                }
//                x = nx;
//                y = ny;
//                break; //유효한 좌표를 찾음
//            }
//        }
//    }
//
//    // 중간에 빈곳(0)없도록 압축
//    public static void compress() {
//        int last = 0; //last : 마지막 구슬위치
//        for (int i = 1; i <= N * N - 1; i++) {
//            if (a[i] == 0) continue;
//            a[++last] = a[i];
//        }
//        for (int i = last + 1; i <= N * N - 1; i++) a[i] = 0;
//    }
//
//    public static void blizzard(int d, int s) {
//        int[][] dir = {{0, 0}, {-1, 0}, {1, 0}, {0, -1}, {0, 1}};
//        int x = N / 2 + 1, y = N / 2 + 1; //상어위치
//        for (int i = 1; i <= s; i++) { //세기만큼
//            x += dir[d][0];
//            y += dir[d][1];
//            a[num[x][y]] = 0; //구슬 없애기
//        }
//        compress(); //압축
//    }
//
//    public static boolean bomb() {
//        boolean flag = false; //flag : 폭발 여부
//        for (int i = 1; i <= N * N - 1 && a[i] != 0; i++) { // 구슬선택, compress()이후이므로, 0을 만나면 더이상 구슬이 없으므로 for문 종료
//            int j = i; //i : 기준 구슬 인덱스, j : 최대 연속 구슬 인덱스
//
//            while (j + 1 <= N * N - 1 && a[i] == a[j + 1]) { //연속 구슬 찾기
//                j++;
//            }
//            //최대 연속 지점 찾음
//
//            if (j - i + 1 >= 4) { //연속구슬 4개이상이면, 폭발
//                cnt[a[i]] += j - i + 1;
//                for (int k = i; k <= j; k++) a[k] = 0; //k : 폭발 구슬 선택
//                flag = true;
//            }
//            i = j; //다음 검사 구슬 이전으로 이동(for문 종료시+1되니깐)
//        }
//        compress();
//        return flag;
//    }
//
//    public static void convert() {
//        //임시배열 초기화
//        for (int i = 1; i <= N * N - 1; i++) b[i] = 0;
//
//        int last = 0; //last : 임시배열에 놓인 마지막 구슬 위치
//        for (int i = 1; i <= N * N - 1 && a[i] != 0; i++) {
//            int j = i;
//            while (j + 1 <= N * N - 1 && a[i] == a[j + 1]) { //연속 구슬 찾기
//                j++;
//            }
//            int A = j - i + 1, B = a[i]; //A : 구슬종류개수, B : 구슬 숫자
//            if (last < N * N - 1) b[++last] = A;
//            if (last < N * N - 1) b[++last] = B;
//
//            i = j; //다음 구슬 검사 이전으로 이동
//        }
//
//        for (int i = 1; i <= N * N - 1; i++) a[i] = b[i]; //배열 복사
//    }
//
//    public static void input(Scanner scanner) {
//        N = scanner.nextInt();
//        M = scanner.nextInt();
//        for (int i = 1; i <= N; i++) {
//            for (int j = 1; j <= N; j++) {
//                original[i][j] = scanner.nextInt();
//            }
//        }
//    }
//
//    public static void pro(Scanner scanner) {
//        calculate_snail_num();
//        while (M-- > 0) { //M개만큼 반복
//            int d = scanner.nextInt();
//            int s = scanner.nextInt();
//            blizzard(d, s);
//            while (bomb());
//            convert();
//        }
//
//        int ans = 0;
//        for (int i = 1; i <= 3; i++) ans += i * cnt[i];
//        System.out.println(ans);
//    }
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        input(scanner);
//        pro(scanner);
//    }
//}

//import java.util.Scanner;
//
//public class B21611_마법사상어와블리자드 {
//    static final int NM = 51; //NM : 최대 변길이
//    static int M;
//    static int N;
//    static int[][] original = new int[NM][NM]; //original : 기존 달팽이 배열
//    static int[][] num = new int[NM][NM]; // num :
//    static int[] a = new int[NM * NM], b = new int[NM * NM]; //a : 달팽이를 1차원으로 핀 배열(주사용 배열), b : 임시배열
//    static int[] cnt = new int[4];
//
//    public static void calculate_snail_num() {
//        int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
//        int x = 1, y = 1, d = 0, v = N * N - 1; //d : 방향인덱스, v : 순번
//
//        while (v > 0) { // 총개수만큼(v가 N*N-1 -> 1때까지)
//            a[v] = original[x][y]; //달팽이배열 1차원으로 펴기
//            num[x][y] = v--;
//            //상황 : 같은것 반복하다가 특정조건시 변환 - 같은방향 반복하다가 특정조건 성립시 방향전환
//            while (true) { //다음좌표 유효할때까지 유효좌표 찾기
//                int nx = x + dir[d][0]; //nx : 다음x좌표
//                int ny = y + dir[d][1]; //ny : 다음y좌표
//                if ((nx < 1 || ny < 1 || nx > N || ny > N) || num[nx][ny] != 0) { //다음좌표가 벗어나거나 순번배열이 이미 등록이 되있다면
//                    d = (d + 1) % 4; //방향을 바꿔서
//                    continue; //다음좌표 할당
//                }
//                x = nx;
//                y = ny;
//                break; //유효한 좌표를 찾음
//            }
//        }
//    }
//
//    // 중간에 빈곳(0)없도록 압축
//    public static void compress() {
//        int last = 0; //last : 마지막 구슬위치
//        for (int i = 1; i <= N * N - 1; i++) {
//            if (a[i] == 0) continue;
//            a[++last] = a[i];
//        }
//        for (int i = last + 1; i <= N * N - 1; i++) a[i] = 0;
//    }
//
//    public static void blizzard(int d, int s) {
//        int[][] dir = {{0, 0}, {-1, 0}, {1, 0}, {0, -1}, {0, 1}};
//        int x = N / 2 + 1, y = N / 2 + 1; //상어위치
//        for (int i = 1; i <= s; i++) { //세기만큼
//            x += dir[d][0];
//            y += dir[d][1];
//            a[num[x][y]] = 0; //구슬 없애기
//        }
//        compress(); //압축
//    }
//
//    public static boolean bomb() {
//        boolean flag = false; //flag : 폭발 여부
//        for (int i = 1; i <= N * N - 1 && a[i] != 0; i++) { // 구슬선택, compress()이후이므로, 0을 만나면 더이상 구슬이 없으므로 for문 종료
//            int j = i; //i : 기준 구슬 인덱스, j : 최대 연속 구슬 인덱스
//
//            while (j + 1 <= N * N - 1 && a[i] == a[j + 1]) { //연속 구슬 찾기
//                j++;
//            }
//            //최대 연속 지점 찾음
//
//            if (j - i + 1 >= 4) { //연속구슬 4개이상이면, 폭발
//                cnt[a[i]] += j - i + 1;
//                for (int k = i; k <= j; k++) a[k] = 0; //k : 폭발 구슬 선택
//                flag = true;
//            }
//            i = j; //다음 검사 구슬 이전으로 이동(for문 종료시+1되니깐)
//        }
//        compress();
//        return flag;
//    }
//
//    public static void convert() {
//        //임시배열 초기화
//        for (int i = 1; i <= N * N - 1; i++) b[i] = 0;
//
//        int last = 0; //last : 임시배열에 놓인 마지막 구슬 위치
//        for (int i = 1; i <= N * N - 1 && a[i] != 0; i++) {
//            int j = i;
//            while (j + 1 <= N * N - 1 && a[i] == a[j + 1]) { //연속 구슬 찾기
//                j++;
//            }
//            int A = j - i + 1, B = a[i]; //A : 구슬종류개수, B : 구슬 숫자
//            if (last < N * N - 1) b[++last] = A;
//            if (last < N * N - 1) b[++last] = B;
//
//            i = j; //다음 구슬 검사 이전으로 이동
//        }
//
//        for (int i = 1; i <= N * N - 1; i++) a[i] = b[i]; //배열 복사
//    }
//
//    public static void input(Scanner scanner) {
//        N = scanner.nextInt();
//        M = scanner.nextInt();
//        for (int i = 1; i <= N; i++) {
//            for (int j = 1; j <= N; j++) {
//                original[i][j] = scanner.nextInt();
//            }
//        }
//    }
//
//    public static void pro(Scanner scanner) {
//        calculate_snail_num();
//        while (M-- > 0) { //M개만큼 반복
//            int d = scanner.nextInt();
//            int s = scanner.nextInt();
//            blizzard(d, s);
//            while (bomb());
//            convert();
//        }
//
//        int ans = 0;
//        for (int i = 1; i <= 3; i++) ans += i * cnt[i];
//        System.out.println(ans);
//    }
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        input(scanner);
//        pro(scanner);
//    }
//}

import java.util.Arrays;
import java.util.Scanner;

public class B21611_마법사상어와블리자드 {
    static final int NM = 51; //MN : 최대 변길이
    static int N; //N : 변길이
    static int M; //M : 마법횟수

    static int[][] original = new int[NM][NM]; //original : 기존 달팽이 배열
    static int[][] num = new int[NM][NM]; //mapping : original2일차원배열 매핑정보배열
    static int[] a = new int[NM * NM], temp = new int[NM * NM]; // A : 달팽이to1차원배열, temp : convert때 사용할 임시배열
    static int[] cnt = new int[4];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        input(sc);
        pro(sc);
    }

    public static void input(Scanner scanner) {
        N = scanner.nextInt();
        M = scanner.nextInt();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                original[i][j] = scanner.nextInt();
            }
        }
    }

    static void pro(Scanner scanner){
        snailToArr();
        while (M-- > 0) { //M개만큼 반복
            int d = scanner.nextInt();
            int s = scanner.nextInt();
            blizzard(d, s);
            while (bomb());
            convert();
        }

        int ans = 0;
        for (int i = 1; i <= 3; i++) ans += i * cnt[i];
        System.out.println(ans);
    }

    static void snailToArr(){
        int[][] dir ={{0,1}, {1,0}, {0,-1}, {-1,0}};

//        int d=0, v=N*N-1, x=1, y=1;
        int x = 1, y = 1, d = 0, v = N * N - 1; //d : 방향인덱스, v : 순번
        while(v>0){ //v만큼
            a[v] = original[x][y];
            num[x][y] = v--;
            while(true){
                int nx=x+dir[d][0];
                int ny=y+dir[d][1];

                //바꾼부분
                if((nx<1||nx>N||ny<1||ny>N) || num[nx][ny]!=0){ //범위를 넘어가거나 이미 순번이 할당된 배열을 만나면
                    d = (d+1)%4; //방향 전환
                    continue;
                }
                x= nx;
                y=ny;
                break;
            }
        }

    }

    static void blizzard(int d, int s){
        int[][] dir = {{0,0}, {-1, 0}, {1,0}, {0,-1}, {0,1}}; //실수

        int x=N/2+1; //상어위치
        int y=N/2+1;

        for(int i=1;i<=s;i++){ //세기만큼
            x+=dir[d][0]; //실수
            y+=dir[d][1];

            a[num[x][y]]=0; //구슬 파괴
        }
        compress();
    };


    static void compress(){
        int last=0;
        for(int i = 1; i<=N*N-1; i++){
            if(a[i]==0) continue;
            a[++last]= a[i]; //덮어쓰기
        }

//        for(int i=last+1;i<=N*N-1 && a[i]!=0; i++){ //옮긴 숫자들은 없애기
        for(int i=last+1;i<=N*N-1; i++){ //옮긴 숫자들은 없애기
            a[i]=0;
        }
    }

    static boolean bomb(){
        boolean flag=false;
        for(int i = 1; i<=N*N-1 && a[i]!=0; i++){ //a[i]!=0 실수
            int j=i; //j : 연속 구슬 인덱스
            while(j+1<=N*N-1 && a[i]== a[j+1]){
                j++;
            }
            //최대 연속 구슬 인덱스 찾음
            if(j-i+1>=4){ //4이상이면
                flag = true;
                cnt[a[i]] += j-i+1; //구슬숫자종류별 폭발 개수 세기
                for(int k=i;k<=j;k++){ //구슬 제거
                    a[k] =0;
                }
            }

            i=j; //다음 검사 인덱스 이전으로 이동
        }
        compress();
        return flag;
    };

    static void convert(){
        //임시배열 초기화
        for (int i = 1; i <= N * N - 1; i++) temp[i] = 0;

        int last=0;
        for(int i = 1; i<=N*N-1 && a[i]!=0; i++){
            int j=i; //j : 연속 구슬 인덱스
            while(j+1<=N*N-1 && a[i]== a[j+1]){
                j++;
            }
            //최대 연속 구슬 인덱스 찾음

            int count = j-i+1, type=a[i]; //count : 연속 횟수, type : 구슬 종류

            if(last < N*N-1) temp[++last] = count;
            if(last < N*N-1) temp[++last] = type;

            i=j; //다음 검사 인덱스 이전으로 이동
        }

        a=Arrays.copyOf(temp, N*N);
    }

}
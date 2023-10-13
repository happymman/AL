package 구현.시뮬.삼성;

import java.util.*;

/*
1차풀이 - 3시간 초과
- 단계메써드간 영향고려 ex)weakestSelector()이후 공격력 업데이트 -> StrongestSelector()의 결과에 영향
- 평면(NM)인데 input을 NN으로 받음
- 우선순위 고려(행+열, 행, 열)
 */

//public class C_포탑부수기 {
//    static int time=0;
//    static int M;
//    static int N;
//    static int K;
//    static int[][] map;
//    static int[][] timeMap;
//
//    static boolean[][] 공격관련; //공격관련 포탑(공격자, 절반공격받은, 전체공격받은)
//
//    static class Node{
//        int x;
//        int y;
//        String route;
//
//        Node(int x, int y, String route){
//            this.x=x;
//            this.y=y;
//            this.route=route;
//        }
//    }
//
//    public static void main(String[] args) {
//        input();
//        while(K-->0){
//            time++; //1초부터 시작
//            공격();
//            if(isFinished()) break;
//            포탑정비();
//        }
//
//        int[] strongest = selectTarget();
//        System.out.println(map[strongest[0]][strongest[1]]);
//
//    }
//
//    static void input(){
//        Scanner sc = new Scanner(System.in);
//        N = sc.nextInt();
//        M = sc.nextInt();
//        K = sc.nextInt();
//
//        map = new int[N][M];
//        timeMap = new int[N][M];
//
//        for(int i=0;i<N;i++){
//            for(int j=0;j<M;j++){
//                map[i][j] = sc.nextInt();
//            }
//        }
//    }
//
//
//
//    static void 공격(){
//
//        //1.공격자 선정
//        int[] weakest = selectAttacker();
//        //2.피공격자 선정
//        int[] strongest = selectTarget();
//
//        //공격력, 최근공격 업데이트
//        map[weakest[0]][weakest[1]] += N+M;
//        timeMap[weakest[0]][weakest[1]] = time;
//
//        Node attackRoute = 레이저공격루트_구하기(weakest, strongest);
//        공격관련 = new boolean[N][M];
//        if(attackRoute != null){
//            레이저공격(weakest, strongest, attackRoute);
//        }else{
//            포탄공격(weakest, strongest);
//        }
//
//        //포탑 부서짐
//        //포탑공격력 음수 -> 0으로 조정
//        for(int i=0;i<N;i++){
//            for(int j=0;j<M;j++){
//                if(map[i][j] < 0) map[i][j] = 0;
//            }
//        }
//    }
//
//    //자동으로 우선순위대로 뽑는 모델로 구현
//    static Node 레이저공격루트_구하기(int[] attack, int[] attacked){
//
//        int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}};
//        boolean[][] isVisited = new boolean[N][M];
//
//        Queue<Node> q = new LinkedList<>();
//        isVisited[attack[0]][attack[1]] = true;
//        q.add(new Node(attack[0], attack[1], ""));
//
//        while(!q.isEmpty()){
//            Node now = q.poll();
//
//            if(now.x == attacked[0] && now.y == attacked[1]){ //목표도달 - 노드처리
//                return now;
//            }
//
//            for(int d=0;d<4;d++){ //방향선택
//                int nx = ((now.x+dir[d][0])+N)%N;
//                int ny = ((now.y+dir[d][1])+M)%M;
//
//                //범위검사X
//                if(map[nx][ny] ==0 ) continue; //부서진포탑 못지나감 - 유효성검사
//                if(isVisited[nx][ny]) continue; //방문검사
//                isVisited[nx][ny] = true; //방문처리
//
//                q.add(new Node(nx, ny, now.route+d));//경로담고 + 다음방문 예약 ex : "10132"(방향) - step안담아도, 우선순위대로 얻을 수 있지않을까?
//            }
//        }
//
//        //상황 : 레이저 공격루트 없을시
//        return null;
//    }
//
//    static void 레이저공격(int[] attack, int[] attacked, Node attackRoute){
//
//        int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}};
//
//        String[] route = attackRoute.route.split("");
//        //공격자 위치
//        int currentX = attack[0];
//        int currentY = attack[1];
//        공격관련[currentX][currentY] = true;
//
//        int power = map[attack[0]][attack[1]];
//        for(String d : route){
//            //이동
//            currentX += dir[Integer.parseInt(d)][0];
//            currentY += dir[Integer.parseInt(d)][1];
//            //위치보정
//            currentX = (currentX+N)%N;
//            currentY = (currentY+M)%M;
//
//            //목표 피공격자
//            if(currentX == attacked[0] && currentY == attacked[1]){
//                map[currentX][currentY] -= power;
//            }else{ //경로 피공격자
//                if(map[currentX][currentY]==0) continue; //이미 부서진 포탑은 공격X
//                map[currentX][currentY] -= power/2;
//            }
//            공격관련[currentX][currentY] = true;
//
//        }
//    }
//
//    static void 포탄공격(int[] attack, int[] attacked){
//
//        int[][] dir = {{0,1},{1,0},{0,-1},{-1,0},{1,1},{1,-1},{-1,1},{-1,-1}};
//        int power = map[attack[0]][attack[1]];
//
//        //공격자 관련
//        공격관련[attack[0]][attack[1]] = true;
//
//        //목표물 공격
//        map[attacked[0]][attacked[1]] -= power;
//        공격관련[attacked[0]][attacked[1]] = true;
//
//        //주변 공격
//        for(int d=0;d<8;d++){ //방향선택
//            //위치 계산
//            int ax = ((attacked[0] + dir[d][0])+N)%N;
//            int ay = ((attacked[1] + dir[d][1])+M)%M;
//
//            if(map[ax][ay] == 0) continue; //부서진 포탑 여부 - 유효성검사
//            if(ax == attack[0] && ay ==attack[1]) continue; //공격자 여부 - 유효성검사
//
//            map[ax][ay] -= power/2;
//            공격관련[ax][ay] = true;
//        }
//
//
//    }
//
//    static void 포탑정비(){
//        for(int i=0;i<N;i++){
//            for(int j=0;j<M;j++){
//                if(!공격관련[i][j] && map[i][j] > 0) map[i][j] ++; //공격과 무관(공격자 or 공격에 피해입은 포탑이 아닌) and 살아있는 포탑이라면 -> 포탑 공격력 1회복
//            }
//        }
//    }
//
//    static boolean isFinished(){
//        int count=0;
//        for(int i=0;i<N;i++){
//            for(int j=0;j<M;j++){
//                if(map[i][j] > 0) count++; //살아있는 포탑 개수 count
//            }
//        }
//        return count<=1;
//    }
//
//    public static int[] selectAttacker() { //O(NM)
//        //구해야하는 대상
//        int minV = Integer.MAX_VALUE, maxT = -1, minI = 0, minJ = 0;
//
//        //상황 : 선택기준값 2개초과 고려(행+열의 합, 열)
//        for (int sum = N + M - 2; sum >= 0; sum--) { //합선택(합 큰것 우선)(우선순위 높은 기준부터 순회) - 하면 -> 우선순위 높은 기준값이 '같을'경우 무시하게된다(
//            for (int j = M - 1; j >= 0; j--) { //열선택(열 큰것 우선)
//                int i = sum - j; //행결정 by열
//                if (i < 0 || i >= N) continue; //행 범위제한(상황 : 열!=행 일때)
//
//                if (map[i][j] == 0) continue; //부서진포탑 제외
//
//                //상황 : 선택기준값 2개고려 -> 우선순위 높은기준부터 고려
//                if (minV > map[i][j]) { //minV == map[i][j]일때 무시 -> 자연스럽게 먼저 순회하는 대상이 우선고려됨
//                    minV = map[i][j];
//                    maxT = timeMap[i][j];
//                    minI = i;
//                    minJ = j;
//                } else if (minV == map[i][j] && maxT < timeMap[i][j]) { //maxT == timeMap[i][j]일때 무시 -> 자연스럽게 먼저 순회하는 대상이 우선고려됨
//                    minV = map[i][j];
//                    maxT = timeMap[i][j];
//                    minI = i;
//                    minJ = j;
//                }
//            }
//        }
//        return new int[] {minI, minJ};
//    }
//
//    public static int[] selectTarget(){ //O(NM)(vs 정렬 O(NM log(NM)))
//        int maxV=-1, minT = Integer.MAX_VALUE, maxI=-1, maxJ=-1;//?
//
//        for(int sum=0;sum<=N+M-2;sum++){ //합선택
//            for(int j=0;j<M;j++){ //열선택
//                int i=sum-j; //행결정 by열
//
//                if(i<0 || i>=N) continue; //행범위 제한
//
//                if(maxV < map[i][j]){
//                    maxV = map[i][j];
//                    minT = timeMap[i][j];
//                    maxI = i;
//                    maxJ = j;
//                }else if(maxV == map[i][j] && minT > timeMap[i][j]){
//                    maxV = map[i][j];
//                    minT = timeMap[i][j];
//                    maxI = i;
//                    maxJ = j;
//                }
//            }
//        }
//
//        return new int[]{maxI, maxJ};
//    }

public class C_포탑부수기 {
    static int time = 0;
    static int M;
    static int N;
    static int K;
    static int[][] map;
    static int[][] recentAttack;
    static boolean[][] attackRelated; //공격관련 포탑(공격자, 절반공격받은, 전체공격받은)

    public static void main(String[] args) {
        input();
        while (K-- > 0) {
            time++; //1초부터 시작
            attack();
            if (isFinished()) break;
            maintenance();
        }

        int[] strongest = selectTarget();
        System.out.println(map[strongest[0]][strongest[1]]);

    }

    static void input() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();

        map = new int[N][M];
        recentAttack = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
            }
        }
    }


    static void attack() {

        //1.공격자 선정
        int[] atk = selectAttacker();
        //2.타겟 선정
        int[] tgt = selectTarget();

        //공격력, 최근공격 업데이트
        map[atk[0]][atk[1]] += N + M;
        recentAttack[atk[0]][atk[1]] = time;
        
        attackRelated = new boolean[N][M];
        if (!tryLasor(atk, tgt)) {
            bomb(atk, tgt);
        }

        //포탑 부서짐(포탑공격력 음수 -> 0으로 조정)
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] < 0) map[i][j] = 0;
            }
        }
    }
    
    static boolean tryLasor(int[] atk, int[] tgt) {

        int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        boolean[][] isVisited = new boolean[N][M];
        int[][][] come = new int[N][M][2];

        Queue<int[]> q = new LinkedList<>();
        isVisited[atk[0]][atk[1]] = true;
        q.add(new int[]{atk[0], atk[1]});

        while (!q.isEmpty()) {
            int[] now = q.poll();

            //탐색종료여부검사X - 상황 : 목표도달 즉시 종료할 필요X & 추가적인 BFS가 문제발생X
            
            for (int d = 0; d < 4; d++) { //방향선택
                int nx = ((now[0] + dir[d][0]) + N) % N;
                int ny = ((now[1] + dir[d][1]) + M) % M;

                //범위검사X - 이유 : 벗어나는 범위에 도달할 수 없음(by 모듈러 처리)
                if (map[nx][ny] == 0) continue; //부서진포탑 못지나감 - 유효성검사
                if (isVisited[nx][ny]) continue; //방문검사
                isVisited[nx][ny] = true; //방문처리
                come[nx][ny][0] = now[0]; //추적저장
                come[nx][ny][1] = now[1];

                q.add(new int[]{nx, ny});//경로담고 + 다음방문 예약 ex : "10132"(방향) - step안담아도, 우선순위대로 얻을 수 있지않을까?
            }
        }

        //상황 : 레이저 공격루트 없을시
        if(!isVisited[tgt[0]][tgt[1]]) return false;

        //공격자 -> 공격관련 등록
        attackRelated[atk[0]][atk[1]] = true;

        int cx = tgt[0], cy = tgt[1];
        while(!(cx==atk[0] && cy==atk[1])){ //공격자 도달전까지
            //공격력 설정
            int power =  map[atk[0]][atk[1]];
            power = cx==tgt[0] && cy==tgt[1] ? power : power/2;
            
            //공격
            map[cx][cy] -= power;
            attackRelated[cx][cy] = true;

            //cx, cy업데이트
            int nx=come[cx][cy][0];
            int ny=come[cx][cy][1];
            cx=nx;
            cy=ny;
        }
        
        return true;
    }

    static void bomb(int[] atk, int[] tgt) {

        int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        int power = map[atk[0]][atk[1]];

        //공격자 관련
        attackRelated[atk[0]][atk[1]] = true;

        //목표물 공격
        map[tgt[0]][tgt[1]] -= power;
        attackRelated[tgt[0]][tgt[1]] = true;

        //주변 공격
        for (int d = 0; d < 8; d++) { //방향선택
            //위치 계산
            int ax = ((tgt[0] + dir[d][0]) + N) % N;
            int ay = ((tgt[1] + dir[d][1]) + M) % M;

            if (map[ax][ay] == 0) continue; //부서진 포탑 여부 - 유효성검사
            if (ax == atk[0] && ay == atk[1]) continue; //공격자 여부 - 유효성검사

            map[ax][ay] -= power / 2;
            attackRelated[ax][ay] = true;
        }
    }

    static void maintenance() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!attackRelated[i][j] && map[i][j] > 0)
                    map[i][j]++; //공격과 무관(공격자 or 공격에 피해입은 포탑이 아닌) and 살아있는 포탑이라면 -> 포탑 공격력 1회복
            }
        }
    }

    static boolean isFinished() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] > 0) count++; //살아있는 포탑 개수 count
            }
        }
        return count <= 1;
    }

    public static int[] selectAttacker() { //O(NM)
        //구해야하는 대상
        int minV = Integer.MAX_VALUE, maxT = -1, minI = 0, minJ = 0;

        //상황 : 선택기준값 2개초과 고려(행+열의 합, 열)
        for (int sum = N + M - 2; sum >= 0; sum--) { //합선택(합 큰것 우선)(우선순위 높은 기준부터 순회) - 하면 -> 우선순위 높은 기준값이 '같을'경우 무시하게된다(
            for (int j = M - 1; j >= 0; j--) { //열선택(열 큰것 우선)
                int i = sum - j; //행결정 by열
                if (i < 0 || i >= N) continue; //행 범위제한(상황 : 열!=행 일때)

                if (map[i][j] == 0) continue; //부서진포탑 제외

                //상황 : 선택기준값 2개고려 -> 우선순위 높은기준부터 고려
                if (minV > map[i][j]) { //minV == map[i][j]일때 무시 -> 자연스럽게 먼저 순회하는 대상이 우선고려됨
                    minV = map[i][j];
                    maxT = recentAttack[i][j];
                    minI = i;
                    minJ = j;
                } else if (minV == map[i][j] && maxT < recentAttack[i][j]) { //maxT == timeMap[i][j]일때 무시 -> 자연스럽게 먼저 순회하는 대상이 우선고려됨
                    minV = map[i][j];
                    maxT = recentAttack[i][j];
                    minI = i;
                    minJ = j;
                }
            }
        }
        return new int[]{minI, minJ};
    }

    public static int[] selectTarget() { //O(NM)(vs 정렬 O(NM log(NM)))
        int maxV = -1, minT = Integer.MAX_VALUE, maxI = -1, maxJ = -1;//?

        for (int sum = 0; sum <= N + M - 2; sum++) { //합선택
            for (int j = 0; j < M; j++) { //열선택
                int i = sum - j; //행결정 by열

                if (i < 0 || i >= N) continue; //행범위 제한

                if (maxV < map[i][j]) {
                    maxV = map[i][j];
                    minT = recentAttack[i][j];
                    maxI = i;
                    maxJ = j;
                } else if (maxV == map[i][j] && minT > recentAttack[i][j]) {
                    maxV = map[i][j];
                    minT = recentAttack[i][j];
                    maxI = i;
                    maxJ = j;
                }
            }
        }

        return new int[]{maxI, maxJ};
    }
}


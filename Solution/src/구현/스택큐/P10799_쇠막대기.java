package 구현.스택큐;

import java.util.Scanner;
import java.util.Stack;

public class P10799_쇠막대기 {
    public static void main(String[] args) {
        Stack<Character> s = new Stack<>();
        Scanner sc = new Scanner(System.in);
        String string = sc.next();

        int answer=0;
        char prev = ' ';
        for(char c : string.toCharArray()){
            if(c == '('){
                s.push('(');
            }else{
                s.pop();
                if(prev == '('){
                    answer += s.size();
                }else{
                    answer += 1;
                }
            }
            prev = c;
        }

        System.out.println(answer);
    }
}

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
package 완전탐색.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

//1차풀이 - 24m
public class B1987_알파벳 {
    static int max=0;
    static int R;
    static int C;
    static int[] dx = {0,-1,0,1};
    static int[] dy = {-1,0,1,0};
    static boolean[][] isVisited;
    static String[][] map;
    public static void main(String[] args) throws IOException {

        //R, C 입력받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map=new String[R][C];
        isVisited=new boolean[R][C];

        //map 입력받기
        for(int i=0;i<R;i++){
            String[] input = br.readLine().split("");
            for(int j=0;j<C;j++){
                map[i][j] = input[j];
            }
        }

        Set<String> alphabets = new HashSet<>();
        alphabets.add(map[0][0]);
        BT(0,0, alphabets, 1);

        System.out.println(max); //max 출력

    }
    static void BT(int r, int c, Set<String> visitSet, int count){

        if(count> max){ //최대값 갱신
            max = count;
        }

        for(int i=0;i<4;i++){ //4방향
            int nr = r+dx[i];
            int nc = c+dy[i];

            if(nr<0 || nr>=R || nc<0 || nc>=C) continue;//범위검사
            if(visitSet.contains(map[nr][nc])) continue;//유효성검사
            if(isVisited[nr][nc]) continue;//방문검사
            isVisited[nr][nc] = true;//방문처리
            visitSet.add(map[nr][nc]);//방문목록 추가

            BT(nr, nc, visitSet, count+1);
            isVisited[nr][nc] = false;//방문처리//방문해제
            visitSet.remove(map[nr][nc]);//방문목록에서 알파벳 제거
        }

    }
}

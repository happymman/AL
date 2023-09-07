package 완전탐색.DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

import static java.lang.Math.min;

public class B2583_영역구하기 {
    static ArrayList<Integer> List;
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    static int count =0;

    static int m;
    static int n;
    static int k;
    static int[][] map;

    public static void solution() throws IOException {
        List = new ArrayList<Integer>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        map = new int[m][n];

        for(int i=0;i<k;i++){
            st = new StringTokenizer(br.readLine()," ");

            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            for(int a=y1;a<y2;a++){
                for(int b=x1;b<x2;b++){
                    map[a][b] = 1;
                }
            }
        }

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(map[i][j]==0){
                    count=0;
                    DFS(i,j);
                    List.add(count);
                }
            }
        }

        System.out.println(List.size());
        Collections.sort(List);
        for(Integer c : List) System.out.println(c+" ");
        br.close();
    }

    public static void DFS(int r, int c){
        map[r][c] = 1;
        count++;

        for(int i=0;i<4;i++){
            int nr = r+dr[i];
            int nc = c +dc[i];

            if(nr>=0 && nc >=0 && nr<m && nc< n){
                if(map[nr][nc]==0) DFS(nr, nc);
            }
        }
    }
}


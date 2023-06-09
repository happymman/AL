package 완전탐색.DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.zip.InflaterInputStream;

public class B2667_단지번호붙이기 {

    private static int[][] marking;
    private static int[][] maps;
    private static boolean[][] isVisited;

    private static int[] dx = {0,1,0,-1};
    private static int[] dy = {-1,0,1,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        marking = new int[N][N];
        maps = new int[N][N];
        isVisited = new boolean[N][N];

       for(int i=0;i<N;i++){
           String[] arr = br.readLine().split("");
           for(int j=0;j<N;j++){
               maps[i][j] = Integer.parseInt(arr[j]);
           }
       }

       int mark=1;
       for(int i=0;i<N;i++){
           for(int j=0;j<N;j++){
               if(maps[i][j] == 0 )continue;
               if(isVisited[i][j]) continue;
               isVisited[i][j] = true;

               DFS(i,j,mark);
               mark++;
           }
       }

       //mark세서 반환하기
       int[] markCount = new int[mark];
       for(int i=0;i<N;i++){
           for(int j=0;j<N;j++){
               if(marking[i][j] ==0) continue;
               markCount[marking[i][j]]++;
           }
       }

       Arrays.sort(markCount);
        System.out.println(mark-1);
       for(int i=1;i<markCount.length;i++){
           System.out.println(markCount[i]);
       }
    }

    private static void DFS(int y, int x, int mark){
        marking[y][x] = mark;

        for(int i=0;i<4;i++){
            int ny = y+dy[i];
            int nx = x+dx[i];

            if(ny<0 || ny>=maps.length || nx<0 || nx>=maps[0].length) continue;
            if(maps[ny][nx] == 0) continue;
            if(isVisited[ny][nx]) continue;
            isVisited[ny][nx] = true;

            DFS(ny, nx, mark);
        }
    }
}

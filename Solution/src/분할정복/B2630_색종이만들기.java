package 분할정복;

import java.util.Scanner;

public class B2630_색종이만들기 {

    private static int white =0;
    private static int blue =0;
    private static int[][] board;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int n=sc.nextInt();
        board = new int[n][n];

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                board[i][j] = sc.nextInt();
            }
        }

        partition(0,0,n);

        System.out.println(white);
        System.out.println(blue);
    }

    private static void partition(int row, int col, int size){

        if(isAllSame(row, col, size)){
            if(board[row][col] == 0){
                white++;
            }else{
                blue++;
            }
            return;
        }

        int newSize = size/2;
        partition(row, col,newSize);
        partition(row+newSize,col,newSize);
        partition(row,col+newSize,newSize);
        partition(row+newSize,col+newSize,newSize);
    }

    private static boolean isAllSame(int row, int col, int size){
        int color = board[row][col];
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(board[row+i][col+j] != color) return false;
            }
        }
        return true;
    }
}
//package 재귀;
//
//import java.util.Arrays;
//import java.util.Scanner;
//
//public class B2447_별찍기10 {
//
//    private static int[][] board;
//
//    public static void main(String[] args){
//        Scanner sc = new Scanner(System.in);
//        int n= sc.nextInt();
//
//        //board -> 1초기화
//        board = new int[n][n];
//        for(int i=0;i<n;i++){
//            Arrays.fill(board[i], 1);
//        }
//
//        partition(0,0,n);
//        //별 출력
//        for(int i=0;i<n;i++){
//            for(int j=0;j<n;j++){
//                if(board[i][j] ==1){
//                    System.out.print('*');
//                }else{
//                    System.out.print(' ');
//                }
//            }
//            System.out.println();
//        }
//    }
//    private static void partition(int row, int col, int size){
//        if(size == 3){
//            board[row+size/3][col+size/3] = 0;
//            return;
//        }
//        //가운데 0처리
//        int empty_size = size/3;
//        int start_row = row+size/3;
//        int start_col = col+size/3;
//        for(int i=start_row;i<start_row+empty_size;i++){
//            for(int j=start_col; j<start_col+empty_size;j++){
//                board[i][j] = 0;
//            }
//        }
//
//        //partition(); 반복
//        partition(row,col,size/3); //1
//        partition(row,col+size/3,size/3); //2
//        partition(row,col+size*2/3,size/3); //3
//
//        partition(row+size/3,col,size/3); //4
//        partition(row+size/3,col+size*2/3,size/3); //6
//
//        partition(row+size*2/3,col,size/3); //7
//        partition(row+size*2/3,col+size/3,size/3); //8
//        partition(row+size*2/3,col+size*2/3,size/3); //9
//    }
//}

package 재귀;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;

public class B2447_별찍기10 {

    private static char[][] board;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n= sc.nextInt();

        //board -> 1초기화
        board = new char[n][n];
        for(int i=0;i<n;i++){
            Arrays.fill(board[i], '*');
        }

        partition(0,0,n);
        //별 출력
        for(int i=0;i<n;i++){
            bw.write(board[i]);
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }
    private static void partition(int row, int col, int size){
        if(size == 3){
            board[row+size/3][col+size/3] = ' ';
            return;
        }
//        if(size == 9){
//            for(int i=0;i<3;i++){
//                for(int j=0;j<3;j++){
//                    board[row+1+3*i][col+1+3*j] = ' ';
//                }
//            }
//            for(int i=0;i<3;i++){
//                for(int j=0;j<3;j++){
//                    board[row+3+i][col+3+j] = ' ';
//                }
//            }
//            return;
//        }
        //가운데 0처리
        int empty_size = size/3;
        int start_row = row+size/3;
        int start_col = col+size/3;
        for(int i=start_row;i<start_row+empty_size;i++){
            for(int j=start_col; j<start_col+empty_size;j++){
                board[i][j] = ' ';
            }
        }

        //partition(); 반복
        partition(row,col,size/3); //1
        partition(row,col+size/3,size/3); //2
        partition(row,col+size*2/3,size/3); //3

        partition(row+size/3,col,size/3); //4
        partition(row+size/3,col+size*2/3,size/3); //6

        partition(row+size*2/3,col,size/3); //7
        partition(row+size*2/3,col+size/3,size/3); //8
        partition(row+size*2/3,col+size*2/3,size/3); //9
    }
}


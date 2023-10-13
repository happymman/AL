//package 구현.삼성;
//
//import java.util.Scanner;
//
//public class test {
//    static int[][] temp;
//    static int[][] map;
//    static int N = 2;
//    static int M=3;
//
//    public static void main(String[] args) {
//
//        map = new int[30][30];
//        temp = new int[30][30];
//
//        int num=0;
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < N; j++) {
//                map[i][j] = ++num;
//            }
//        }
//
//        세로복사(1, 0); //
//
//        System.out.println();
//
//    }
//
//    static void 시계_회전(int x1, int y1, int size) {
//
//        //temp에 회전
//        for (int i = 0; i < size; i++) { //1.for문 0~size
//            for (int j = 0; j < size; j++)
//                temp[j+x1][size-1-i+y1] = map[i+x1][j+y1]; //2.(행열전환) 행이 반대열로
//                                                            //3.좌상 보정
//        }
//
//        //회전영역만 복사
//        for (int i = 0; i < size; i++) {
//            for (int j = 0; j < size; j++)
//                map[i+x1][j+y1] = temp[i+x1][j+y1];
//        }
//    }
//
//
//    static void 반시계_회전(int x1, int y1, int size) {
//
//        //temp에 회전
//        for (int i = 0; i < size; i++) { //1.for문 0~size
//            for (int j = 0; j < size; j++)
//                temp[size-1-j+x1][i+y1] = map[i+x1][j+y1]; //2.(행열전환) 열이 반대행으로
//                                                           //3.좌상 보정
//        }
//
//        //회전영역만 복사
//        for (int i = 0; i < size; i++) {
//            for (int j = 0; j < size; j++)
//                map[i+x1][j+y1] = temp[i+x1][j+y1];
//        }
//
//    }
//
//    static void half_회전(int x1, int y1, int size) {
//
//        //temp에 회전
//        for (int i = 0; i < size; i++) { //1.for문 0~size
//            for (int j = 0; j < size; j++)
//                temp[size-1-j+x1][size-1-i+y1] = map[i+x1][j+y1]; //2.(행열전환) 열행모두 반대행렬(열)로
//                                                                  //3.좌상 보정
//        }
//
//        //회전영역만 복사
//        for (int i = 0; i < size; i++) {
//            for (int j = 0; j < size; j++)
//                map[i+x1][j+y1] = temp[i+x1][j+y1];
//        }
//
//    }
//
//    static void 상하반전(){
//        for (int i = 0; i<N; i++) {
//            temp[i] = map[N-i-1];
//        }
//
//        //반전영역 복사
//        for (int i = 0; i < N; i++) {
//            map[i] = temp[i];
//        }
//    }
//
//
//    static void 좌우반전(){
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < M; j++) {
//                temp[i][j] = map[i][M-1-j];
//            }
//        }
//
//        //반전영역 복사
//        for (int i = 0; i < N; i++) {
//            for(int j=0;j<M;j++){
//                map[i][j] = temp[i][j];
//            }
//        }
//    }
//
//    static void 세로복사_원점기준(){
//        for(int i=0;i<N;i++){ //1.for문 0~N, 0~M
//            for(int j=0;j<M;j++){
//                map[2*N-1-i][j] = map[i][j]; //2.(행열그대로) 행 size-1에서 빼기
//            }
//        }
//        N*=2; //세로길이 두배 증가
//    }
//
//    static void 가로복사_원점기준(){
//        for(int i=0;i<N;i++){ //1.for문 0~N, 0~M
//            for(int j=0;j<M;j++){
//                map[i][2*M-1-j] = map[i][j]; //2.(행열그대로) 열 size-1에서 빼기
//            }
//        }
//        M*=2; //가로길이 두배 증가
//    }
//
//
//
//}
package DP;

public class P43105_정수삼각형 {
    public int solution(int[][] triangle) {
        for(int i=triangle.length-2 ; i>=0;i--){
            for(int j=0 ; j<i+1; j++){
                triangle[i][j] += Math.max(triangle[i+1][j], triangle[i+1][j+1]);
            }
        }
        return triangle[0][0];
    }
}

//class Solution{
//    public int solution(int[][] triangle){
//        for(int i=triangle.length-2;i>=0;i--){
//            for(int j=0;j<triangle[i].length;j++){
//                triangle[i][j] += Math.max(triangle[i+1][j], triangle[i+1][j+1]);
//            }
//        }
//        return triangle[0][0];
//    }
//}
package 완전탐색;

public class P86491_최소직사각형 {
}

// class Solution {
//     public int solution(int[][] sizes) {

//         int max_w =0;
//         int max_h = 0;
//         for(int i=0;i<sizes.length;i++){
//             if(max_w < sizes[i][0]){
//                 max_w = sizes[i][0];
//             }
//             if(max_h < sizes[i][1]){
//                 max_h = sizes[i][1];
//             }
//         }
//         return max_w * max_h;
//     }
// }

//class Solution {
//    public int solution(int[][] sizes) {
//        //큰값, 작은값 구별
//        int maxWidth =0, maxHeight =0;
//
//        for(int i=0; i<sizes.length; i++){
//            maxWidth = Math.max(maxWidth, Math.max(sizes[i][0], sizes[i][1]));
//            maxHeight = Math.max(maxHeight, Math.min(sizes[i][0], sizes[i][1]));
//        }
//        return maxWidth*maxHeight;
//    }
//}

//public class Solution{
//    public int solution(int[][] sizes){
//        int max =0;
//        int second_max =0;
//
//        for(int i=0;i<sizes.length;i++){
//            max = Math.max(max, Math.max(sizes[i][0], sizes[i][1]));
//            second_max = Math.max(second_max, Math.min(sizes[i][0], sizes[i][1]));
//        }
//
//        return max*second_max;
//    }
//}
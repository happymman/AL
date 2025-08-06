// class Solution {
//     public int solution(int[] diffs, int[] times, long limit) {
        
//         int left = 1; //level 최솟값
//         // int right = 100000; //탐색범위 최댓값 설정 by 최고난이도
//         int right = 0;
//         for(int i =0;i<diffs.length;i++) {
//             right = Math.max(right, diffs[i]);
//         }
//         while(left<=right){
//             int 숙련도 = (left+right)/2; //level설정(중간값)
            
//             //검사
//             long use =0; //use : 사용시간            
//             for(int i=0;i<diffs.length;i++){ //퍼즐선택<=30만
//                 int 난이도 = diffs[i];
                
//                 if(난이도<=숙련도) use+=times[i]; //현재퍼즐시간
//                 else use+=(times[i]+times[i-1])*(난이도-숙련도)+times[i-1]; //현재퍼즐시간+이전퍼즐시간
//             }
            
//             //탐색범위 이동
//             if(use<=limit) right = 숙련도-1;
//             else left = 숙련도+1;
//         }
//         return left;
//     }
// }

import java.util.*;

class Solution {
    static int n;
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;
        n = diffs.length;
        // 숙련도의 최솟값을 정수로 return
        long s = diffs[0];
        long e = 0;
        for(int i =0;i<diffs.length;i++) {
            e = (int)Math.max(e, diffs[i]);
        }
        answer = (int)e;
        while(s <= e) {
            long mid = (s + e) / 2;
            if(check(mid, diffs, times, limit)) {
                e = mid - 1;
                answer = (int) Math.min(mid, answer);
            } else {
                s = mid + 1;
            }
        }
        return answer;
    }
    static boolean check(long value, int[] d, int[] t, long l) {
        long sum = 0;
        for(int i = 0;i<n;i++) {
            if(d[i] <= value) {
                sum += t[i];
            } else {
                sum += ((t[i-1] + t[i]) * (d[i] - value) + t[i]);
            }
        }
        return sum <= l;
    }
}
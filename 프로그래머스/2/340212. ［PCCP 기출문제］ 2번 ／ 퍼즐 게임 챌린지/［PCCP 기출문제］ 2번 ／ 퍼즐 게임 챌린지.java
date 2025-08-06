class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        
        long left = 1; //level 최솟값
        // int right = 100000; //탐색범위 최댓값 설정 by 최고난이도
        long right = 0;
        for(int i =0;i<diffs.length;i++) {
            right = Math.max(right, diffs[i]);
        }
        while(left<=right){
            long 숙련도 = (left+right)/2; //level설정(중간값)
            
            //검사
            long use =0; //use : 사용시간            
            for(int i=0;i<diffs.length;i++){ //퍼즐선택<=30만
                int 난이도 = diffs[i];
                
                if(난이도<=숙련도) use+=times[i]; //현재퍼즐시간
                else use+=(times[i]+times[i-1])*(난이도-숙련도)+times[i]; //현재퍼즐시간+이전퍼즐시간
            }
            
            //탐색범위 이동
            if(use<=limit) right = 숙련도-1;
            else left = 숙련도+1;
        }
        return (int)left;
    }
}


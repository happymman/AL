package 이진탐색;

import java.util.Arrays;

/*
1차풀이

class P43248_입국심사 {
    public long solution(int n, int[] times) {

        Arrays.sort(times);
        long left = 1;
        long right = (long) n * times[times.length - 1];
        while (left <= right) {
            long mid = (left + right) / 2;
            long count = 0; // 총 심사한 인원

            //빨리 심사하는 심사관 순으로 심사처리
            for (int i = 0; i < times.length; i++) {
                count += mid / times[i];
            }

            if (count < n) { // 해야할 인원보다 심사처리 못함 -> 시간 더 필요
                left = mid + 1;
            } else { // 해야할 인원보다 심사처리 많이함 -> 시간을 줄여서 더 최고 경우의 시간을 만든다.
                right = mid - 1;
            }

        }
        return left;
    }
}
 */

/*
4차풀이 - 틀렸던 부분

코드종류 : long 변수 = 값 할당 -> 계산값이 int범위를 넘을 수 있는지 판단하기
이진탐색 - left, right 값 : 탐색범위 최솟값, 최댓값은 tight할 필요없이, 안전하게 여유롭게 해도됨. 그렇게해도 시간복잡도가 크게 늘지 않음.

 */
class P43248_입국심사 {
    public long solution(int n, int[] times) {

        Arrays.sort(times); // times 정렬

        long left = n * times[0] / times.length; //탐색범위 최솟값 설정
        long right = n*times[times.length-1] / times.length; // 탐색범위 최댓값 설정

        while(left<=right){
            long mid = (left+right)/2; //mid 설정

            //조건충족여부 검사 로직
            long time = mid; //28
            long check=0;
            for(int i=0;i<times.length;i++){
                check += time / times[i];
            }

            if(check >= n){ //조건충족
                right = mid-1;
            }else{ //조건불충족
                left = mid+1;
            }
        }

        return left;

    }
}
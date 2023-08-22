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
4차풀이 - 성공풀이

틀렸던 부분
코드종류 : long 변수 = 값 할당 -> 계산값이 int범위를 넘을 수 있는지 판단하기
이진탐색 - left, right 값 : 탐색범위 최솟값, 최댓값은 tight할 필요없이, 안전하게 여유롭게 해도됨. 그렇게해도 시간복잡도가 크게 늘지 않음.

풀이 :
완전탐색 가정
- 탐색 최소범위 : n*검색소요시간중 최솟값/심사관 숫자 -> 10의 9승*1/10의 5승 = 10의 4승
- 탐색 최대범위 : n*검색소요시간 중 최댓값/심사관이므로 -> 10의 9승*10의9승/10의5승 = 10의 13승
매우 넓으므로 절대 불가

해당 범위를 O(logN)의 시간복잡도를 가지는 이진탐색을 이용하면 되겠다라는 생각을 했음.
times정렬 = O(NlogN) = O(10의 5승*log10의 5승) = O(10의 5승)
이진탐색*for문 = O(log10의 13승)*O(10의 5승) -> 약 10의 5승이므로 통과 가능한 풀이

 */
class P43248_입국심사 {
    public long solution(int n, int[] times) {

        Arrays.sort(times); // times 정렬 -> 탐색범위 최솟값, 최댓값 설정을 위해서

        long left = n * times[0] / times.length; //탐색범위 최솟값 설정
        long right = n*times[times.length-1] / times.length; // 탐색범위 최댓값 설정

        while(left<=right){
            long mid = (left+right)/2; //mid 설정

            //조건충족여부 검사 로직
            long time = mid; //28
            long check=0;
            for(int i=0;i<times.length;i++){ //빠른
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
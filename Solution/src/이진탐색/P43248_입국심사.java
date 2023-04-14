package 이진탐색;

import java.util.Arrays;

class P43248_입국심사 {
    public long solution(int n, int[] times) {
        long answer = 0;
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
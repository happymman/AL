package 스택큐;

import java.util.*;

public class P12906_같은숫자는싫어 {
    public int[] solution(int []arr) {
        int[] answer = {};

        Stack<Integer> s = new Stack<>();

        for(int num : arr){
            if(!s.empty() && s.peek() == num){
                s.pop();

            }
            s.push(num);
        }

        return s.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }
}

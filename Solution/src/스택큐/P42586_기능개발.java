package 스택큐;


import java.util.Queue;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

public class  P42586_기능개발{
    public int[] solution(int[] progresses, int[] speeds){
        Queue<Integer> q = new LinkedList<>();
        for(int i=0; i<progresses.length;i++){
            q.add((int)Math.ceil(((100.0 - progresses[i])/speeds[i])));
        }


        int day =0;
        List<Integer> Answer = new ArrayList<>();
        while(!q.isEmpty()){
            day = q.poll();
            int count = 1;
            while(!q.isEmpty() && q.peek() <= day){
                System.out.println(q.peek()+" ");
                count++;
                q.poll();
            }
            Answer.add(count);
        }
        return Answer.stream().mapToInt(Integer::intValue).toArray();
    }
}
package 스택큐;

import java.util.*;

public class P42587_프로세스 {

    private static class Process{
        int location;
        int priority;

        Process(int location, int priority){
            this.location = location;
            this.priority = priority;
        }
    }

    private static int[] priorityCount = new int[10];

    public int solution(int[] priorities, int location) {

        Queue<Process> q = new LinkedList<>();
        int out =0;

        for(int i=0;i<priorities.length;i++){
            q.add(new Process(i, priorities[i]));
            priorityCount[priorities[i]]++;
        }

        while(!q.isEmpty()){
            Process current = q.poll();
            if(!hasHigherPriority(current.priority)){
                priorityCount[current.priority]--;
                out++;
                if(current.location == location) break;
            }else{
                q.add(new Process(current.location, current.priority));
            }
        }
        return out;
    }

    private static boolean hasHigherPriority(int priority){
        if(priority == 9) return false;
        for(int i=priority+1;i<=9;i++){
            if(priorityCount[i] != 0) return true;
        }
        return false;
    }
}

/*
우선순위 큐 풀이

import java.util.*;

class Solution {

    public int solution(int[] priorities, int location) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for(int i=0;i<priorities.length;i++){
            pq.add(priorities[i]);
        }

        int out=0;
        while(!pq.isEmpty()){
            for(int i=0;i<priorities.length;i++){
                if(pq.peek() == priorities[i]){
                    if(i==location){
                        out++;
                        return out;
                    }
                    pq.poll();
                    out++;
                }
            }
        }
        return -1;
    }

}
 */
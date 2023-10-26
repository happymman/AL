package 구현.스택큐;

import java.util.*;

class P121686_운영체제 {
    static PriorityQueue<Program> wait = new PriorityQueue<>((p1,p2) -> p1.invokeTime - p2.invokeTime);
    static PriorityQueue<Program> invoke = new PriorityQueue<>(
            (p1,p2) -> {
                if(p1.score != p2.score) return p1.score - p2.score; //우선순위를 1순위로
                return p1.invokeTime - p2.invokeTime; //호출시간을 2순위로 비교하여 정렬한다.
            });
    static class Program {
        int score;
        int invokeTime;
        int runTime;

        Program(int score, int invokeTime, int runTime){
            this.score=score;
            this.invokeTime=invokeTime;
            this.runTime=runTime;
        }

    }
    public long[] solution(int[][] programs) {

        long[] answer = new long[11];

        for(int[] program : programs){ //프로그램 선택
            wait.add(new Program(program[0], program[1], program[2])); //pq에 넣기
        }

        long time=0;
        while(!wait.isEmpty()){ //wait 비어있기 전까지
            //시간 업데이트 - 목적 : run
            if(invoke.isEmpty() && time<wait.peek().invokeTime) time = wait.peek().invokeTime; //이유 :

            while(!wait.isEmpty() && time >= wait.peek().invokeTime){ //
                Program toRun = wait.poll();
                invoke.add(new Program(toRun.score, toRun.invokeTime, toRun.runTime));
            }

            Program now = invoke.poll(); //원소꺼내기
            // System.out.println("time : "+time+" "+now.score+" "+now.invokeTime+" "+now.runTime);
            long waitTime = time-now.invokeTime; //대기시간 계산(by 현재시간-호출시간)
            answer[now.score] += waitTime;

            time += now.runTime; //현재시간 업데이트
        }

        while(!invoke.isEmpty()){ //run 비어있기 전까지
            Program now = invoke.poll(); //원소꺼내기

            long waitTime = time-now.invokeTime; //대기시간 계산(by 현재시간-호출시간)
            answer[now.score] += waitTime;
            //현재시간 업데이트
            time += now.runTime;
        }

        answer[0] = time;

        return answer;
    }
}
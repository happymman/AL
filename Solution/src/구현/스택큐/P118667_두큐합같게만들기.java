package 구현.스택큐;

import java.util.*;
/*
1차풀이
- 큐1의 원소합이 더 큰 상태면, 반드시 그 즉시 큐1를 pop하지 않아도 두 큐의 합이 같게 만들 수는 있지만,
    어쨌든 무조건 최소 한번 이상 큐1pop를 진행해야함 -> 원소합이 더 큰 큐에서 pop()
- 어느 한큐에서 모든 state를 검사했다면 -> 더이상 두 큐 원소합을 같게만들 수 없다는 것을 의미한다.
    4n(큐의 크기)번을 pop했다는 것은 최소 1개이상의 큐에서 2n번 이상 pop했다는 것을 의미
    처음에 해당 큐에 들어있었던 모든 원소(n개)+다른 큐에 들어있었다가 해당큐에 옮겨진 모든 원소(n개)를 모두 pop했다는 것이므로
    어느 한큐에서 2n번 이상 pop했다는 것은 모든 state를 검사했다는 것을 의미한다.
    어느 한큐에서의 모든 state를 검사했음에도 불구하고 두 큐의 원소합이 같아진 적이 한번도 없다면 -> 두 큐 원소합을 같게 만들 수 없다는 것을 의미한다.

 */
public class P118667_두큐합같게만들기 {

    public int solution(int[] queue1, int[] queue2) {

        Queue<Integer> q1=new LinkedList<>(); //큐1생성
        long q1Sum=0;
        for(int input : queue1){ //큐1넣기
            q1.add(input);
            q1Sum+=input;
        }

        Queue<Integer> q2=new LinkedList<>(); //큐2생성
        long q2Sum=0;
        for(int input : queue2){//큐2넣기
            q2.add(input);
            q2Sum+=input;
        }

        if((q1Sum+q2Sum)%2==1) return -1; //두큐 원소합 홀수이면 같게 만드는 것이 불가

        int move=0;
        while(true){
            if(q1Sum>q2Sum){ //큐1 합 더큼
                int item1 = q1.poll();
                q1Sum-=item1;
                q2.add(item1);
                q2Sum+=item1;

                move++;
            }else if(q1Sum<q2Sum){ //큐2 합 더큼 - else대신 else if(q1Sum<q2Sum)을 하는 이유 : 맨처음부터 합이 같은 경우조차도 q2에서 원소를 빼게 될 수가 있기때문에, 정확하게 q2합이 더 클때만 q2에서 q1로 원소를 옮겨준다.
                int item2 = q2.poll();
                q2Sum-=item2;
                q1.add(item2);
                q1Sum+=item2;

                move++;
            }

            if(q1Sum == q2Sum) return move;
            if(move >= queue1.length*4) return -1; //
        }
    }
}
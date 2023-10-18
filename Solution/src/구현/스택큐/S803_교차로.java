package 구현.스택큐;

import java.util.*;
/*
1차풀이 - 2h 55m
-
 */

public class S803_교차로 {

    public class Main {
        static Queue<Car> q = new LinkedList<>(); //q : 차량 대기큐
        static Queue<Car>[] roads = new Queue[4]; //roads : A, B, C, D도로큐 담고 있는 배열
                                                  //사용이유 : 여러개의 큐를 숫자(배열 인덱스)와 매핑하지 않으면, 규칙성을 이용하지 못하고 중복되는 코드를 작성해야함
        static boolean[] pass; // pass : i번째 도로 차량 현재 통과 가능여부 배열(0번째 A, 1번째 B, 2번째 C, 3번째 D)
        static int[] result; // result : 도로 통과 시간
        static List<Integer> zero = new ArrayList<>(); //zero : 0초 출차 차량 idx담고 있는 List (for 간편한 교착상태 처리)
        static class Car{
            int time;
            String road;
            int idx;

            Car(int time, String road, int idx){
                this.time = time;
                this.road = road;
                this.idx = idx;
            }
        }

        public static void main(String args[])
        {
            input();
            pro();
            for(int num : result){
                System.out.println(num);
            }
        }

        static void input(){
            Scanner sc = new Scanner(System.in);
            int N = sc.nextInt();
            result = new int[N];

            for(int i=0;i<N;i++){
                int time = sc.nextInt();
                String road = sc.next();
                q.add(new Car(time, road, i));
            }

            //도로배열 초기화
            for(int i=0;i<4;i++){
                roads[i] = new LinkedList<>();
            }
        }

        static void pro(){
            int time=0; //time : 현재시간, 현재시간 0초기화

            //도로에 차량 없고, 대기차량도 없을때 종료
            while(!(q.isEmpty() && 모든도로_비어있는())){

                if(모든도로_비어있는()){ //모든도로 비었을때만 -> 시간 업데이트(2초 이상)
                    if(q.peek().time != time){ //큐.peek()전에 !큐.isEmpty()가 없는 이유 : 1.이전코드에 의해서 !q.isEmpty()보장 2.whlie루프 내부 코드가 아니므로, 한번만 보장되도 됨
                        time = q.peek().time;
                    }
                }

                //현재 시간에 도로에 진입해야될차 -> 전부 도로에 진입(by while반복)
                while(!q.isEmpty() && q.peek().time == time){
                    Car now = q.poll();

                    switch(now.road){
                        case "A" : roads[0].add(new Car(now.time, now.road, now.idx)); break;
                        case "B" : roads[1].add(new Car(now.time, now.road, now.idx)); break;
                        case "C" : roads[2].add(new Car(now.time, now.road, now.idx)); break;
                        case "D" : roads[3].add(new Car(now.time, now.road, now.idx)); break;
                    }
                }

                //상황 : 도로큐에 1개 이상 차량 있는
                if(모든도로_차량있는()){ //-> 전부 통과 불가
                    for(int i=0;i<result.length;i++){
                        //이유 : 0초 통과 차량이 아님에도 불구하고 값이 0인경우는 아직 대기 차량큐에서 나오지 않은 것이므로,
                        // -> 전부 통과 불가(-1)처리를 해준다.
                        if(!zeroOut(i) && result[i]==0) result[i] = -1;
                    }
                    break;

                }else{ //4개미만 도로에 차 있을때
                    pass = new boolean[4]; //재사용 배열이기때문에 -> 항상 해당 위치에서 초기화 필요

                    for(int i=0;i<4;i++){
                        if(!roads[i].isEmpty() && roads[((i-1)+4)%4].isEmpty()) //* 모듈러 연산을 통해서 0번째 도로 출차여부를 판단할때 -1이 아닌 3번째 도로가 비어있는지를 판단
                            pass[i] = true;
                    }

                    for(int i=0;i<4;i++){
                        if(pass[i]){ // -> 오른쪽에 차 없는 도로의 차량만 통과
                            Car now = roads[i].poll();
                            result[now.idx] = time;
                            if(time==0) zero.add(now.idx);
                        }
                    }
                }

                time++; //시간 1초 증가
            }
        }

        static boolean 모든도로_비어있는(){
            for(int i=0;i<4;i++){
                if(!roads[i].isEmpty()) return false; //하나라도 차량있는 도로가 있으면 false
            }
            return true;
        }
        static boolean 모든도로_차량있는(){
            for(int i=0;i<4;i++){
                if(roads[i].isEmpty()) return false; //하나라도 빈 도로가 있으면 false
            }
            return false;
        }

        static boolean zeroOut(int idx){
            if(zero.contains(idx)) return true;
            return false;
        }

    }
}


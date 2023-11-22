package 구현.카카오;
import java.util.*;

public class P150369_택배배달과수거하기 {
    public static void main(String[] args) {
        P150369_택배배달과수거하기_Solution s = new P150369_택배배달과수거하기_Solution();
//        int cap = 4;
//        int n=5;
//        int[] deliveries = {1, 0, 3, 1, 2};
//        int[] pickups = {0, 3, 0, 4, 0};

        int cap2 = 2;
        int n2=7;
        int[] deliveries2 = {1, 0, 2, 0, 1, 0, 2};
        int[] pickups2 = {0, 2, 0, 1, 0, 2, 0};
        System.out.println(s.solution(cap2, n2, deliveries2, pickups2));
        System.out.println();
//
//        System.out.println(s.solution(4, 5, new int[]{1, 0, 3, 1, 2}, new int[]{0, 3, 0, 4, 0}));
//        System.out.println(s.solution(4, 5, new int[]{8, 0, 8, 0, 4}, new int[]{0, 0, 0, 0, 20})); //50
        System.out.println(s.solution(2, 2, new int[]{0, 1}, new int[]{0, 4})); //8
        System.out.println(s.solution(2, 3, new int[]{0, 6,13}, new int[]{19, 0,1})); //54
        System.out.println(s.solution(2, 3, new int[]{4, 2,1}, new int[]{0, 4,1})); //16
        System.out.println(s.solution(4, 5, new int[]{1, 1,1,1,1}, new int[]{1, 1,1,1,1})); //12
        System.out.println(s.solution(4, 4, new int[]{25, 24,51,0}, new int[]{51, 0,0,49})); //(70)*2
    }
}


/*
첫번째 풀이(비효율적)
class P150369_택배배달과수거하기_Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {

        List<Integer> 배달List = new ArrayList<>();
        List<Integer> 픽업List = new ArrayList<>();

        int 배달lastIndex = n-1;
        int 배달Cap = cap;
        while(배달lastIndex>=0){
            //첫번째 배달지 디텍팅
            while(배달lastIndex>=0 &&deliveries[배달lastIndex] ==0){
                배달lastIndex--;
            }
            배달List.add(배달lastIndex+1);

            if(배달lastIndex<0) continue;

            //배달하기
            while(배달Cap>0 && 배달lastIndex >= 0){
                if(배달lastIndex<0) break;

                if(배달lastIndex>=0 && deliveries[배달lastIndex] <= 배달Cap){
                    배달Cap -= deliveries[배달lastIndex];
                    deliveries[배달lastIndex] =0; //.
                    배달lastIndex--; //. else일때는 안움직여야되니깐. 남아있다는거니깐
                }else if(배달lastIndex>=0 && deliveries[배달lastIndex] > 배달Cap){
                    deliveries[배달lastIndex] -= 배달Cap;
                    배달Cap = 0; //.
                }
            }

            배달Cap = cap; //배달Cap 초기화
        }

        int 픽업lastIndex = n-1;
        int 픽업Cap = cap;
        while(픽업lastIndex>=0){
            //첫번째 픽업지 디텍팅
            while(픽업lastIndex>=0 && pickups[픽업lastIndex] ==0){
                픽업lastIndex--;
            }
            픽업List.add(픽업lastIndex+1);

            if(픽업lastIndex<0) continue;

            //픽업하기
            while(픽업Cap>0){
                if(픽업lastIndex<0) break;

                if(픽업lastIndex>=0 && pickups[픽업lastIndex] <= 픽업Cap){
                    픽업Cap -= pickups[픽업lastIndex];
                    pickups[픽업lastIndex] =0; //.
                    픽업lastIndex--; //. else일때는 안움직여야되니깐. 남아있다는거니깐
                }else if(픽업lastIndex>=0 && pickups[픽업lastIndex] > 픽업Cap){
                    pickups[픽업lastIndex] -= 픽업Cap;
                    픽업Cap = 0; //.
                }
            }
            픽업Cap = cap; //픽업Cap 초기화
        }

        long dist=0;
        int 배달ListIndex =0;
        int 픽업ListIndex =0;
        while(배달ListIndex!=배달List.size() || 픽업ListIndex!=픽업List.size()){ //. 변수명고침
            int 배달=0;
            int 픽업=0;

            if(배달ListIndex<배달List.size()){
                배달 = 배달List.get(배달ListIndex);
                배달ListIndex++; //.
            }

            if(픽업ListIndex<픽업List.size()){
                픽업 = 픽업List.get(픽업ListIndex);
                픽업ListIndex++; //.
            }
            dist += Math.max(배달, 픽업)*2;
        }

        return dist;
    }
}
*/

/*
class P150369_택배배달과수거하기_Solution {

    static class Task{
        int dist;
        int box;

        Task(int dist, int box){
            this.dist = dist;
            this.box=box;
        }
    }

    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        Stack<Task> d = new Stack<>();
        Stack<Task> p = new Stack<>();
        long answer = 0;

        for(int i=0; i<n; i++){
            if(deliveries[i]!=0){
                d.push(new Task(i+1,deliveries[i]));
            }
            if(pickups[i]!=0){
                p.push(new Task(i+1, pickups[i]));
            }
        }

        //둘 중 하나라도 참이면 계속 굴러간다.
        while(!d.isEmpty() || !p.isEmpty()){
            int dDist = !d.isEmpty() ? d.peek().dist : 0;
            int pDist = !p.isEmpty() ? p.peek().dist : 0;
            answer += 2*Math.max(dDist, pDist);

            int dRest = cap;
            while(!d.isEmpty()){
                Task Dcurrent = d.pop();

                //배달 가능
                if(dRest >= Dcurrent.box){
                    dRest -= Dcurrent.box;
                }else{ //배달 불가능
                    //남은 배달 스택에 넣어주기
                    d.push(new Task(Dcurrent.dist, Dcurrent.box-dRest));
                    break;
                }
            }

            int pRest = cap;
            while(!p.isEmpty()){
                Task Pcurrent = p.pop();

                //배달 가능
                if(pRest >= Pcurrent.box){
                    pRest -= Pcurrent.box;
                }else{ //배달 불가능
                    //남은 배달 스택에 넣어주기
                    p.push(new Task(Pcurrent.dist, Pcurrent.box-pRest));
                    break;
                }
            }
        }

        return answer;
    }
}
*/

class P150369_택배배달과수거하기_Solution {

    public class Task{
        int dist;
        int box;

        Task(int dist, int box){
            this.dist = dist;
            this.box = box;
        }
    }

    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        Stack<Task> d = new Stack<>();
        Stack<Task> p = new Stack<>();

        for(int i=0;i<deliveries.length;i++){
            int delivery = deliveries[i];
            if(delivery !=0){
                d.push(new Task(i+1, delivery));
            }
        }

        for(int i=0;i<pickups.length;i++){
            int pickup = pickups[i];
            if(pickup !=0){
                p.push(new Task(i+1, pickup));
            }
        }

        long sum=0;
        while(!d.isEmpty() || !p.isEmpty()){
            int dDist = !d.isEmpty() ? d.peek().dist : 0;
            int pDist = !p.isEmpty() ? p.peek().dist : 0;

            System.out.println("dDist : "+dDist);
            System.out.println("pDist : "+pDist);
            sum+=Math.max(dDist, pDist)*2;

            int dCap=cap;
            //cap만큼 배달하기
            while(!d.isEmpty()){
                if(dCap==0) break;

                Task dNow = d.pop();
                if(dNow.box > dCap){
                    d.push(new Task(dNow.dist, dNow.box-dCap));
                    dCap=0;
                }else if(dNow.box <= dCap){
                    dCap -= dNow.box;
                }
            }

            int pCap=cap;
            //cap만큼 수거하기
            while(!p.isEmpty()){
                if(pCap==0) break;

                Task pNow = p.pop();

                if(pNow.box > pCap){
                    pCap=0;
                    p.push(new Task(pNow.dist, pNow.box-pCap));
                }else if(pNow.box <= pCap){
                    pCap -= pNow.box;
                }
            }
        }

        return sum;


    }
}

/*
2차풀이 - 42m

코드쓰기 - 상황 : 계속반복 - 이미 while있는 상태 -> break로 종료조건에 대한 코드를 작성할 것
코드쓰기 - 상황 : 스택, 큐 -> 원소꺼내는 것은 무조건 while(스택큐 안비었을때, if(스택큐 안비었을때)

 */
//import java.util.*;
//
//class Solution {
//    public class Task{
//        int dist;
//        int count;
//        Task(int dist, int count){
//            this.dist=dist;
//            this.count=count;
//        }
//    }
//
//    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
//
//        //배달큐생성
//        Stack<Task> d= new Stack<>();
//        //배달 원소넣기
//        for(int i=0;i<deliveries.length;i++){
//            if(deliveries[i]!=0) d.add(new Task(i+1,deliveries[i]));
//        }
//
//        //픽업큐생성
//        Stack<Task> p= new Stack<>();
//        //픽업 원소넣기
//        for(int i=0;i<pickups.length;i++){
//            if(pickups[i]!=0) p.add(new Task(i+1,pickups[i]));
//        }
//
//        long sum=0;
//        while(true){
//            if(d.isEmpty() && p.isEmpty()) break; //배달큐, 픽업큐 모두 비었을때 종료
//
//            int dDist= !d.isEmpty() ? d.peek().dist : 0;
//            int pDist= !p.isEmpty() ? p.peek().dist : 0;
//
//            sum+=Math.max(dDist, pDist)*2; //더 멀리가야하는 집의 거리를 더함
//
//            //배달용량 복사
//            int dCap=cap;
//            while(!d.isEmpty()){ //배달큐 안비었을때
//                Task now = d.pop();//원소꺼내기
//
//                if(now.count<=dCap){ //배달용량 이하일때
//                    dCap-=now.count;//배달용량 감소
//                }else{ //초과했을때
//                    d.add(new Task(now.dist, now.count-dCap));//원소 다시 넣기
//                    dCap=0;//배달용량 =0;
//                }
//
//                if(dCap==0) break;//배달용량 0일때 종료
//
//            }
//
//            //픽업용량 복사
//            int pCap = cap;
//            while(!p.isEmpty()){ //픽업큐 안비었을때
//                Task now = p.pop();//원소꺼내기
//
//                if(now.count<=pCap){ //배달용량 이하일때
//                    pCap-=now.count;//배달용량 감소
//                }else{ //초과했을때
//                    p.add(new Task(now.dist, now.count-pCap));//원소 다시 넣기
//                    pCap=0;//배달용량 =0;
//                }
//                if(pCap==0) break;//배달용량 0일때 종료
//            }
//        }
//        return sum;
//    }
//}


/*
4차풀이
실수
- 초기화 작업에서 실수 : 0인애들은 안넣었어야함 -> 구체코드 작성시, 왼쪽화면에 input보고, 초기화 작업또한 그림상상하기
- return자료형 long 고려
 */
//import java.util.*;
//
//class Solution {
//    static Stack<Work> dStack = new Stack<>();
//    static Stack<Work> pStack = new Stack<>();
//    static long dist=0;
//    static class Work{
//        int dist;
//        int count;
//        Work(int dist, int count){
//            this.dist = dist;
//            this.count = count;
//        }
//    }
//
//    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
//
//        for(int i=0;i<deliveries.length;i++){
//            int delivery = deliveries[i];
//            if(delivery != 0) dStack.add(new Work(i+1, delivery));
//        }
//
//        for(int i=0;i<pickups.length;i++){
//            int pickup = pickups[i];
//            if(pickup != 0) pStack.add(new Work(i+1, pickup));
//        }
//
//        while(!(dStack.isEmpty() && pStack.isEmpty())){
//            int dDist = !dStack.isEmpty() ? dStack.peek().dist : 0;
//            int pDist = !pStack.isEmpty() ? pStack.peek().dist : 0;
//            dist += Math.max(dDist, pDist)*2;
//
//            int dCap = cap;
//            while(!dStack.isEmpty()){
//                Work now = dStack.pop();
//
//                if(now.count > dCap){
//                    dStack.add(new Work(now.dist, now.count-dCap));
//                    dCap=0;
//                }else if(now.count <= dCap){
//                    dCap -= now.count;
//                }
//
//                if(dCap ==0) break;
//            }
//
//            int pCap=cap;
//            while(!pStack.isEmpty()){
//                Work now = pStack.pop();
//
//                if(now.count > pCap){
//                    pStack.add(new Work(now.dist, now.count-pCap));
//                    pCap=0;
//                }else if(now.count <= pCap){
//                    pCap -= now.count;
//                }
//                if(pCap ==0) break;
//            }
//
//        }
//        return dist;
//    }
//}


package 완전탐색.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class B12100_2048 {
    static int N;
    static int max;
    static String[] types = {"up", "down", "left", "right"};
    //방문배열 만들기
    static class Block{
        int x;
        int y;
        int number;
        boolean update;

        Block(int x, int y, int number){
            this.x=x;
            this.y=y;
            this.number=number;
            this.update = false; //기본값은 false로 들어가도록
        }
    }
    public static void main(String[] args) throws IOException {
        /*
        N입력받기
        map NN 메모리할당

         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        Set<Block> set = new HashSet<>();
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                int input = Integer.parseInt(st.nextToken());
                set.add(new Block(i, j, input));
            }
        }

        BT(0, set);

        System.out.println(max);

    }

    static void BT(int depth, Set<Block> set){
        if(depth==5){ //탐색 종료여부 검사
            int setMax = getMax(set);
            if(max < setMax) max = setMax;
            return;
        }

        for(int i=0;i<4;i++){ // 4의 5승 = 10의 3승
            String type = types[i];
            Set<Block> resultSet = move(set, type);

            BT(depth+1, resultSet);
        }
    }

    static int getMax(Set<Block> set){ // O(N)
        int max=0;
        for(Block b : set){
            if(max<b.number) max=b.number;
        }
        return max;
    };

    static Set<Block> move(Set<Block> set, String type){

        Set<Block> newSet = new HashSet<>();

        PriorityQueue pq = pqAllocation(type);


        for(int i=0;i<N;i++){ //열선택+우선순위큐 넣기+이동시키기 //O(N2)
            insertBlock(pq, i, set, type);

            int rest; //rest : 남은 자리
            if(type.equals("up")||type.equals("left")){
                rest=0;
            }else{
                rest=N-1;
            }

            while(!pq.isEmpty()){ //열마다 N*O(N)
                Block now = (Block) pq.poll(); //맨위 꺼내기

                rest = blockMove(now, rest, type);

                if(!pq.isEmpty()){ //안비었음
                    if(now.number==((Block)pq.peek()).number &&now.update==false){ //합칠 수 있는 경우 : peek랑 숫자같음 &&update여부X
                        now.number *= 2; //꺼낸거 숫자크기 두배 업데이트
                        now.update = true; //꺼낸거 업데이트 여부 true변경
                        pq.poll(); //맨위원소 버리기
                    }
                }
                newSet.add(now); //꺼낸원소 newSet삽입
            }
        }
        return newSet;

    }

    static PriorityQueue<Block> pqAllocation(String type){
        PriorityQueue<Block> pq=null;
        switch(type) {
            case "up":
                pq = new PriorityQueue<Block>((o1,o2) -> o1.x - o2.x);
                break;
            case "down":
                pq = new PriorityQueue<Block>((o1,o2) -> o2.x - o1.x);
                break;
            case "left":
                pq = new PriorityQueue<Block>((o1,o2) -> o1.y - o2.y);
                break;
            case "right":
                pq = new PriorityQueue<Block>((o1,o2) -> o2.y - o1.y);
        }
        return pq;
    };

    static Set<Block> insertBlock(PriorityQueue pq, int i, Set<Block>set, String type){

        for(Block b : set){
            if(type.equals("up") || type.equals("down")){
                if(b.y==i){
                    pq.add(new Block(b.x, b.y, b.number));
                    set.remove(b);
                }
            }else{
                if(b.x==i){
                    pq.add(new Block(b.x, b.y, b.number));
                    set.remove(b);
                }
            }

        }
        return set;
    }

    static int blockMove(Block now, int rest, String type){
        if(type.equals("up")){
            now.x = rest; //꺼낸거 위치 업데이트 by남은 윗자리
            rest++; //남은 자리++
        }else if(type.equals("left")){
            now.y = rest;
            rest++;
        }else if(type.equals("down")){
            now.x=rest;
            rest--;
        }else{//오른쪽으로 이동
            now.y=rest;
            rest--;
        }
        return rest;
    };
}

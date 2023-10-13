package 구현.시뮬.삼성;

import java.util.*;
/*
1차풀이 - 2h53m(Comparable에 대한 솔루션 도움)
- 우선순위 큐 - 추가시 재정렬
             필드값 변경시 따로정렬필요 ex)PriorityQueue<Seat> updatedSeats = new PriorityQueue(entireLocation);
                                    seats.clear();
                                    seats.addAll(updatedSeats)
-
 */

public class C_놀이기구탑승 {
    static int N;
    static int sum=0;
    static int[][] map;
    static boolean[][] isVisited;
    static int[] visitList;
    static int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};
    static Map<Integer, Set<Integer>> likeMap = new HashMap<>();
    static PriorityQueue<Seat> seats = new PriorityQueue();

    static class Seat implements Comparable<Seat>{
        int x;
        int y;
        int like;
        int vacant;

        Seat(int x, int y, int like, int vacant){
            this.x=x;
            this.y=y;
            this.like=like;
            this.vacant=vacant;
        }

        @Override
        public int compareTo(Seat other) {
            if (like != other.like) return -(like - other.like); //1순위로 like많은 자리
            if (vacant != other.vacant) return -(vacant - other.vacant); //2순위로 빈자리많은 자리
            if (x != other.x) return x - other.x; //3순위로 행번호 작은 자리
            return y - other.y; //4순위로 열번호 작은 자리
        }
    }
    public static void main(String[] args) {
        input();
        pro();
        System.out.println(sum);
    }

    static void input(){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        map = new int[N][N];
        isVisited = new boolean[N][N];
        visitList = new int[N*N];

        //likeMap초기화
        for(int i=0;i<N*N;i++){
            int person=0;
            for(int j=0;j<5;j++){
                if(j==0){
                    person = sc.nextInt();
                    visitList[i] = person;
                    likeMap.put(person, new HashSet<>());
                }else{
                    likeMap.get(person).add(sc.nextInt());
                }
            }
        }

        //전체칸리스트 초기화
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                seats.add(new Seat(i,j,0,0));
            }
        }
    }

    static void pro(){

        int i=0;
        while(i<N*N){ //탑승사람 선택
            for(int person : likeMap.keySet()){
                if(person == visitList[i]){ //visitList의 순서대로 탑승
                    Set<Integer> likeSet = likeMap.get(person);
                    좋아하는친구수_세기(likeSet);
                    비어있는칸_개수_세기();
                    탑승(person);
                }
            }
            i++;
        }
        최종_좋아하는친구수_세기();
    }

    static void 좋아하는친구수_세기(Set<Integer> likeSet){
        for(Seat now : seats){ //칸선택, entireLocations : 선택가능한 칸 list

            int like=0;
            for(int i=0;i<4;i++){ //방향선택
                int nx = now.x+dir[i][0];
                int ny = now.y+dir[i][1];

                if(nx<0||ny<0||nx>=N||ny>=N) continue;//범위검사
                if(likeSet.contains(map[nx][ny])) like++;
            }
            now.like = like; //좋아하는친구수 등록
        }
    }
    static void 비어있는칸_개수_세기(){
        //비어있는 칸수 개수 세기
        for(Seat now : seats){ //칸선택

            int vacant=0; //vacant : 비어있는칸수
            for(int i=0;i<4;i++){ //방향선택
                int nx = now.x+dir[i][0];
                int ny = now.y+dir[i][1];

                if(nx<0||ny<0||nx>=N||ny>=N) continue;//범위검사 - 버위안에 드는 칸중에서 방문되지 않은 칸의 개수를 세기 위해서

                if(!isVisited[nx][ny]) vacant++;
            }
            now.vacant = vacant; //비어있는칸수 등록
        }
    }

    static void 탑승(int person){
        // 변경된 like, vacant 기준으로 PriorityQueue를 재정렬(우선순위큐 - add할때 자동정렬, 필드값 바뀔땐 자동정렬X)
        PriorityQueue<Seat> updatedSeats = new PriorityQueue<>(seats);
        seats.clear();
        seats.addAll(updatedSeats);

        Seat first = seats.poll(); //visitList PQ에서 가장 맨 앞에 있는 좌표에 등록하기
        isVisited[first.x][first.y] = true; //isVisited에 등록하기
        map[first.x][first.y] = person; //map에 등록하기

    }

    static void 최종_좋아하는친구수_세기(){
        for(int i=0;i<N;i++){//행선택
            for(int j=0;j<N;j++){ //열선택

                Set<Integer> likeSet = likeMap.get(map[i][j]);//map에서 찾고 likeMap에서 찾아서 좋아하는친구set가져오기

                int like=0;
                for(int d=0;d<4;d++){ //방향선택
                    int nx = i+dir[d][0];
                    int ny = j+dir[d][1];
                    if(nx<0 || ny<0 || nx>=N || ny>=N) continue;//범위검사
                    if(likeSet.contains(map[nx][ny])) like++;
                }

                sum += Math.pow(10, like-1);
            }
        }
    }
}

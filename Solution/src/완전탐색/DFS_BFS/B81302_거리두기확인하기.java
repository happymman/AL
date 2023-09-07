package 완전탐색.DFS_BFS;

import java.util.*;

public class B81302_거리두기확인하기 {
    public static void main(String[] args) {
        B81302_거리두기확인하기_Solution s=new B81302_거리두기확인하기_Solution();
        int[] result = s.solution(new String[][]{{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"}, {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"}, {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"}, {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"}, {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}});
        for (int i : result){
            System.out.print(i+" ");
        }
    }
}
/*
1차풀이
피드백
- 변수 의미 명확하게 하지 않았음.
- 재사용 자료구조에 대한 초기화 진행X

해당 문제는
 */
class B81302_거리두기확인하기_Solution {
    static boolean[][] isVisited; //대기실별 전체방문배열
    static boolean[][] map; //파티션 맵(for 유효성검사)
    static boolean[][] people=new boolean[5][5]; //응시자맵
    static List<int[]> peopleList=new ArrayList<>();
    static int[][] dir={{-1,0},{1,0},{0,-1},{0,1}};
    static class Node{
        int x;
        int y;
        int step;

        Node(int x, int y, int step){
            this.x=x;
            this.y=y;
            this.step=step;
        }
    }

    public int[] solution(String[][] places) {
        List<Integer> result = new ArrayList<>();
        for(String[] place : places){
            input(place);
            result.add(pro());
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    static void input(String[] place){
        peopleList.clear();
        people = new boolean[5][5];
        isVisited = new boolean[5][5];
        map = new boolean[5][5];

        for(int i=0;i<5;i++){
            String[] sit = place[i].split("");

            for(int j=0;j<5;j++){
                if(sit[j].equals("P")){ //응시자 맵, List에 추가
                    people[i][j]=true;
                    peopleList.add(new int[]{i,j});
                }else if(sit[j].equals("X")){ //방문불가
                    map[i][j]=true;
                }
            }
        }

    }

    static int pro(){
        for(int[] person : peopleList){ //응시자 선택
            Queue<Node> q=new LinkedList<>();

            q.add(new Node(person[0], person[1], 0));//큐 삽입 -> 선택한 응시자 노드방문예약
            people[person[0]][person[1]] = false;//응시자맵 방문해제

            boolean[][] personVisited = new boolean[5][5]; //personVisited : 사람별 방문배열
            visitedCopy(personVisited);

            personVisited[person[0]][person[1]]=true;//방문처리

            while(!q.isEmpty()){
                Node now = q.poll();//원소 꺼내기

                if(now.step==3){ //탐색종료여부검사
                    continue;
                }

                if(people[now.x][now.y]) return 0; //다른 응시자와 만나면

                for(int i=0;i<4;i++){ //방향선택
                    int nx=now.x+dir[i][0];
                    int ny=now.y+dir[i][1];

                    if(nx<0 || ny<0 || nx>=5 ||ny>=5) continue;//범위검사
                    if(personVisited[nx][ny]) continue; //방문검사
                    personVisited[nx][ny] = true; //방문처리

                    q.add(new Node(nx, ny, now.step+1));//다음노드방문예약
                }
            }

            //방문해제
            people[person[0]][person[1]] = true;//응시자맵 다시 방문처리

        }
        return 1;
    }

    static void visitedCopy(boolean[][] personVisited){
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                personVisited[i][j] = isVisited[i][j];
            }
        }
    }
}

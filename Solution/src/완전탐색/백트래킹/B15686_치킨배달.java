package 완전탐색.백트래킹;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class B15686_치킨배달 {
    static long answer=Integer.MAX_VALUE; // 최소 치킨거리
    static int N;
    static int M;
    static boolean[] isVisited;
    static List<int[]> chickens = new ArrayList<>();
    static List<int[]> houses = new ArrayList<>();
    public static void main(String[] args) {

        //N, M입력받기
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        //방문배열 메모리 할당
        isVisited = new boolean[14];

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                int input = sc.nextInt();
                if(input ==1){//집 입력받기
                    houses.add(new int[]{i,j});
                }else if(input==2){ //치킨집 입력받기
                    chickens.add(new int[]{i,j});
                }
            }
        }

        BT(new ArrayList<>(), 0);
        System.out.println(answer);
    }
    static void BT(List<int[]> list, int count){ //list : 치킨집리스트, count : 치킨집개수

        if(count==M){
            int minChickenDist = getChickenDist(list);
            answer = Math.min(answer, minChickenDist); //최솟값 갱신
            return;
        }

        for(int i=0;i<chickens.size();i++){ //범위검사
            //유효성검사X
            if(isVisited[i]) continue; //방문검사
            isVisited[i] = true; //방문처리

            list.add(chickens.get(i)); // 치킨집추가
            BT(list, count+1); //BT(치킨집리스트, 치킨집개수);

            isVisited[i] = false; //방문해제
            list.remove(chickens.get(i)); // 치킨집 제거
        }

    }

    static int getChickenDist(List<int[]> list){
        int sum=0;
        for(int[] house : houses){ //집선택
            int minDist=Integer.MAX_VALUE;
            for(int[] chicken : list){ //치킨집 선택
                minDist = Math.min(minDist, Math.abs(house[0]-chicken[0]) + Math.abs(house[1]-chicken[1]));
            }
            sum+=minDist; //집별 최소거리
            if(sum >= answer) return Integer.MAX_VALUE; //해당 치킨조합에서의 최소치킨거리가 기존 구해진 최소치킨거리 이상일경우는 더이상 구할필요가 없다.
        }
        return sum; //해당 치킨집조합에서의 최소치킨거리
    }
}

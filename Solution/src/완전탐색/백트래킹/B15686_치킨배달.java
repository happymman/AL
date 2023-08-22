package 완전탐색.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
1차풀이 - 시간초과
public class B15686_치킨배달 {
    static long answer=Integer.MAX_VALUE; // 최소 치킨거리
    static int N;
    static int M;
    static boolean[] isVisited;
    static List<int[]> chickens = new ArrayList<>();
    static List<int[]> houses = new ArrayList<>();
    public static void main(String[] args) throws IOException {

        //N, M입력받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        //방문배열 메모리 할당
        isVisited = new boolean[14];

        for(int i=0;i<N;i++){//2500
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                int input = Integer.parseInt(st.nextToken());
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

    static int getChickenDist(List<int[]> list){ //O(N)*13
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
 */
/*
1차풀이 - 시간초과 해결

피드백1 :

피드백 : 방문배열을 방문여부검사용으로만 쓸수 있다는 생각에서 벗어나야 했음.
해당 문제에서는 방문배열을 통해서 치킨집선택도 가능했음(나는 선택된 치킨집 리스트를 따로 만들었음)

 */
public class B15686_치킨배달 {
    static long answer=Integer.MAX_VALUE; // 최소 치킨거리
    static int N;
    static int M;
    static boolean[] isOpened;
    static List<int[]> chickens = new ArrayList<>();
    static List<int[]> houses = new ArrayList<>();
    public static void main(String[] args) throws IOException {

        //N, M입력받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for(int i=0;i<N;i++){//2500
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                int input = Integer.parseInt(st.nextToken());
                if(input ==1){//집 입력받기
                    houses.add(new int[]{i,j});
                }else if(input==2){ //치킨집 입력받기
                    chickens.add(new int[]{i,j});
                }
            }
        }

        //방문배열 메모리 할당
        isOpened = new boolean[chickens.size()];

        BT(0,0);
        System.out.println(answer);
    }
    static void BT(int count, int start){ //list : 치킨집리스트, count : 치킨집개수

        if(count==M){ //치킨집 개수가 M개 됐을때만
            int minChickenDist = getChickenDist();
            answer = Math.min(answer, minChickenDist); //최솟값 갱신
            return;
        }

        for(int i=start;i<chickens.size();i++){ //범위검사
            //유효성검사X
            if(isOpened[i]) continue; //방문검사
            isOpened[i] = true; //방문처리 -> 치킨집 선택

            BT(count+1, i+1); //BT(치킨집개수);

            isOpened[i] = false; //방문해제
        }

    }

    static int getChickenDist(){
        int sum=0;
        for(int[] house : houses){ //집선택
            int minDist=Integer.MAX_VALUE; //집별 최소거리
            for(int i=0;i<chickens.size();i++){ //치킨집 선택
                if(!isOpened[i]) continue; //선택되지 않은 치킨집 배제, M개의 선택된 치킨집과의 거리만 구하기

                int[] chicken = chickens.get(i);
                minDist = Math.min(minDist, Math.abs(house[0]-chicken[0]) + Math.abs(house[1]-chicken[1]));
            }
            sum+=minDist;
//            if(sum >= answer) return Integer.MAX_VALUE; //해당 치킨집 조합에서의 최소치킨거리가 기존 구해진 최소치킨거리 이상일경우는 더이상 구할필요가 없다.
        }
        return sum; //해당 치킨집조합에서의 최소치킨거리
    }
}

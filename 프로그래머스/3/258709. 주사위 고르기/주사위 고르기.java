/*
    1132
    승리확률이 더 높은 주사위 가져가기
*/
import java.util.*;

class Solution {
    static List<List<Integer>> A조합목록 = new ArrayList<>();
    static Map<Integer, Integer> A합개수맵;
    static Map<Integer, Integer> B합개수맵;
    static int n;
    static int[][] dice;

    public int[] solution(int[][] diceParam) {
        n = diceParam.length;
        dice = diceParam;

        조합만들기(new ArrayList<>(),0);
        System.out.println(A조합목록.toString());

        List<Integer> max조합 = new ArrayList<>();
        int maxV = -1;
        for(List<Integer> A조합 : A조합목록){ //A주사위조합 선택 ex : 1,2

            //B주사위조합 만들기 ex : 3,4
            List<Integer> B조합 = new ArrayList<>();
            for(int i=0;i<n;i++){
                if(!A조합.contains(i)) B조합.add(i);
            }

            //A합개수맵, B합개수맵 초기화
            A합개수맵 = new TreeMap<>();
            B합개수맵 = new TreeMap<>();

            A합만들기(A조합, 0 , 0,0);
            B합만들기(B조합, 0 , 0,0);

            int V = 0;
            //A합개수맵 순회
            for(int A합 : A합개수맵.keySet()){
                for(int B합 : B합개수맵.keySet()){
                    if(B합 >= A합) break;
                    V += A합개수맵.get(A합) * B합개수맵.get(B합);
                }
            }

            //max조합, maxV 최댓값 갱신
            if(maxV < V){
                max조합 = A조합;
                maxV = V;
            }
        }
        return max조합.stream()
                .mapToInt(Integer::intValue)
                .map(num -> num+1)
                .toArray(); //return하기
    }

    static void 조합만들기(List<Integer> 조합, int start){ //1.조합목록 만들기 -> BT, 시작
        //2.조합만들기 -> BT,
        if (조합.size() == n/2){ // 탐색종료검사 - n/2개 도달
            A조합목록.add(new ArrayList<>(조합)); //조합목록에 추가
            return;
        }

        for(int i=start;i<n;i++){ //
            조합.add(i);
            조합만들기(조합, i+1);
            조합.remove(Integer.valueOf(i));
        }
    }

    static void A합만들기(List<Integer> A조합, int 합, int start, int 사용주사위개수){
        if (사용주사위개수 == A조합.size()){ //목표도달검사 - 주사위 개수 도달
            A합개수맵.put(합, A합개수맵.getOrDefault(합,0)+1); //A합개수맵에 추가
            return;
        }

        for(int idx=start; idx<A조합.size(); idx++){ //주사위 선택
            int i = A조합.get(idx);
            for(int j=0;j<6;j++){ //눈 선택
                A합만들기(A조합, 합+dice[i][j], idx+1, 사용주사위개수+1);
            }
        }
    }

    static void B합만들기(List<Integer> B조합, int 합, int start, int 사용주사위개수){
        if (사용주사위개수 == B조합.size()){ //목표도달검사 - 주사위 개수 도달
            B합개수맵.put(합, B합개수맵.getOrDefault(합,0)+1); //B합개수맵에 추가
            return;
        }

        for(int idx=start; idx<B조합.size(); idx++){ //주사위 선택
            int i = B조합.get(idx);
            for(int j=0;j<6;j++){ //눈 선택
                B합만들기(B조합, 합+dice[i][j], idx+1, 사용주사위개수+1);
            }
        }
    }
}
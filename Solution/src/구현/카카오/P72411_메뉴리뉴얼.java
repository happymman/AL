package 구현.카카오;

/*
order <= 20이하
20명 * 10의 3승(순열) *
*/

import java.util.*;

public class P72411_메뉴리뉴얼 {
    public static void main(String[] args) {
        P72411_메뉴리뉴얼_Solution s = new P72411_메뉴리뉴얼_Solution();
        s.solution(new String[]{"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"}, new int[]{2,3,4});
        //	["AC", "ACDE", "BCFG", "CDE"]
    }
}


/*
20명 * 10의 3승(순열) *

order : 2<= <=20
course : 1<= <=10

*/

class P72411_메뉴리뉴얼_Solution {
    static boolean[] isVisited;
    static Map<String, Integer> 조합map = new HashMap<>();

    public String[] solution(String[] orders, int[] courses) {

        for(String order : orders){ //order선택

            String[] foods = order.split("");
            Arrays.sort(foods); //order 오름차순 정렬
            isVisited = new boolean[foods.length];

            for(int course : courses){ //개수선택 (in courses)
                String[] route = new String[course];
                BT(0, 0,route, foods); //가능조합만들기
            }
        }

        //2회 이상 주문된 조합중에 가장 많이 주문된 조합 횟수 선별, 걔네들만 result에 넣기
        List<String> result = new ArrayList<>();

        for(int course : courses){ //코스요리 종류개수 선택
            int max=0;
            for(String key : 조합map.keySet()){ //조합map 순회(max값 찾기)
                if(key.length() != course) continue;
                max = Math.max(max, 조합map.get(key));
            }

            if(max<=1) continue; //1이하면 등록X

            for(String key : 조합map.keySet()){ //조합map 순회(max값 key만 result에 넣기)
                if(key.length() != course) continue;
                if(조합map.get(key) == max) result.add(key);
            }
        }

        Collections.sort(result);

        return result.toArray(new String[result.size()]);
    }

    static void BT(int depth, int start, String[] route, String[] foods){

        if(depth==route.length){ //탐색종료여부 검사
            //조합map에 등록
            String 조합 = String합치기(route);
            조합map.put(조합, 조합map.getOrDefault(조합,0)+1);
            return;
        }

        for(int i=start;i<foods.length;i++){ //범위검사(자동)
            //유효성검사X - 이유 : 후보배열의 값이 모두 유효
            //방문검사, 방문처리X - 이유 : 노드방문이 순차적으로 이루어지므로, 중복방문의 가능성X

            route[depth] = foods[i];//등록
            BT(depth+1, i+1, route, foods);

            //방문해제X
            //상태복원X - 이유 : 모든 후보가 선택되는 상황
        }
    }

    static String String합치기(String[] route){
        return String.join("", route);
    }
}
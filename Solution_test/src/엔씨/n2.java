package 엔씨;

import java.util.*;

public class n2 {
    public static void main(String[] args) {
        n2_Solution s = new n2_Solution();
        String result = s.solution(new String[]{"CS", "SV"});
        System.out.println();
    }
}
/*
preferecnes 2<= <=10
["CS", "SV] "CCVS"
"SV VS SV VS SV
CM MS SC -> CCMMS
 */
class n2_Solution{
    static List<String> tastes = new ArrayList<>();
    static String[] 선호 = new String[]{"C", "M", "S", "V", "Y"};
    static Set<String>[] preferSet;

    public String solution(String[] prefers){

        preferSet = new HashSet[prefers.length];

        for(int i=0;i<prefers.length;i++){
            preferSet[i] = new HashSet<>();
        }

        List<String> result = new ArrayList<>();
        int level=1;
        while(true){
            recur("", 0, level); //해당 층개수만큼 맛조합 만들기

            boolean find = false;

            for(int i=0;i<tastes.size();i++){
                String taste = tastes.get(i); //맛조합 선택  "BSC"

                preferSet초기화(prefers); //사람별 선호맛set 초기화

                //iceCream초기화 -> BS, SC, C
                String[] iceCream = taste.split("");
                for(int j=0;j<iceCream.length-1;j++){ //아이스크림 맛 섞기
                    iceCream[j] += iceCream[j+1];
                }

                for(int j=0;j<iceCream.length;j++){ //iceCream 층수 선택
                    String[] tastes = iceCream[j].split(""); //해당 층수의 맛선택

                   for(int k=0;k<preferSet.length;k++){ //사람선택
                       Set<String> set = preferSet[k];
                        if(set.contains(tastes[j]))//포함검사 - 먹을 수 있는지 여부 검사
                        preferSet[k].remove(tastes[k]); //포함한것
                   }
                }
                if(모두원하는맛다먹었는지검사()){
                    result.add(taste);
                    find = true;
                }
            }

            if(!find) break; //해당 층개수 조합에서 하나라도 모두 원하는맛 다 먹게한 조합이 있다
            tastes.clear(); //routes초기화
            level++; //층수 올리기
        }

        Collections.sort(result); //result 정렬

        return result.get(0); //최소 한개는 무조건 먹을 수 있다고 가정

    }

    static void recur(String route, int depth, int target){
        if(depth==target){ //다음탐색종료여부 검사
            tastes.add(route);//route추가
            return;
        }

        for(int i=0;i<5;i++){
            recur(route+선호[i], depth+1, target);
        }

    }

    static void preferSet초기화(String[] prefers){
        for(int i=0;i<prefers.length;i++){
            String[] items = prefers[i].split("");
            for(String item : items){
                preferSet[i].add(item);
            }
        }
    }

    static boolean 모두원하는맛다먹었는지검사(){
        for(Set<String> set : preferSet){
            if(set.size()!=0) return false;//preferSet순회하면서 set.size가 모두 0
        }
        return true;
    }
}

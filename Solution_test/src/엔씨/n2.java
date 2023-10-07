package 엔씨;

import java.util.*;

public class n2 {
    public static void main(String[] args) {
        n2_Solution s = new n2_Solution();
        String result = s.solution(new String[]{"CS", "SV"});
        System.out.println(result);
    }
}
/*
preferecnes(사람수) 2<= <=10
["CS", "SV] "CCVS"

"SV VS SV VS SV
CM MS SC -> CCMMS

1.맛조합 만들기(중복조합)

문제조건
1.모든사람이 선호맛을 모두 먹는다 + 가장 작은 층수

 */
class n2_Solution{
    static List<String> tastes = new ArrayList<>();
    static String[] 선호 = new String[]{"C", "M", "S", "V", "Y"};
    static Set<String>[] preferSets;

    public String solution(String[] prefers){

        preferSets = new HashSet[prefers.length];

        for(int i=0;i<prefers.length;i++){
            preferSets[i] = new HashSet<>();
        }

        List<String> result = new ArrayList<>();
        int level=1;
        while(true){
            recur("", 0, level); //해당 층개수만큼 맛조합 만들기

            boolean find = false;

            for(int i=0;i<tastes.size();i++){  //맛조합 선택  "BSC"
                String taste = tastes.get(i);

                preferSet초기화(prefers); //사람별 선호맛set 초기화

                //iceCream초기화 -> BS, SC, C
                String[] iceCreams = taste.split("");

                //아이스크림 맛 섞기
                for(int j=0;j<iceCreams.length-1;j++){
                    iceCreams[j] += iceCreams[j+1];
                }

                for(String iceCream : iceCreams){ //iceCream 층수 선택 -> j번째층의 맛 선택
                    String[] tastes = iceCream.split(""); //해당 층수의 맛선택

                   for(Set<String> preferSet : preferSets){ //사람선택


                       if(canEat(preferSet, tastes))
                        if(preferSet.contains(tastes[j]))//preferSet[k] : k번째사람의 선호set, 포함검사 - 먹을 수 있는지 여부 검사
                            preferSet.remove(tastes[j]); //포함한것
                   }
                }

                if(모두원하는맛다먹었는지검사()){
                    result.add(taste);
                    find = true;
                }
            }

            if(find) break; //이유 : 가장 작은 층수를 찾는 것이기때문에, 찾았으면 끝
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
                preferSets[i].add(item);
            }
        }
    }

    static boolean 모두원하는맛다먹었는지검사(){
        for(Set<String> set : preferSets){
            if(set.size()!=0) return false;//preferSet순회하면서 set.size가 모두 0
        }
        return true;
    }
}

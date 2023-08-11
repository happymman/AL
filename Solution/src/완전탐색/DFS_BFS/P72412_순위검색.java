package 완전탐색.DFS_BFS;
import java.util.*;

public class P72412_순위검색 {
    public static void main(String[] args) {
        P72412_순위검색_Solution s = new P72412_순위검색_Solution();
        String[] info = {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
        String[] query = {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};
        System.out.println(s.solution(info, query));
    }
}


class P72412_순위검색_Solution {
    static Map<String, List<Integer>> infoMap = new HashMap<>();

    public int[] solution(String[] infos, String[] queries) {

        int[] people = new int[queries.length];

        for(String info : infos){
            String[] infoItems = info.split(" ");
            DFS("", 0, infoItems);
        }

        for(List<Integer> personList : infoMap.values()){
            Collections.sort(personList);
        }

        for(int i=0;i<queries.length;i++){
            String query = queries[i];
            String[] queryItmes = query.split(" and "); //결과 : java,backend,junior,pizza 100

            //쿼리 수정
            String searchKey = queryItmes[0]+queryItmes[1]+queryItmes[2]+queryItmes[3].split(" ")[0];
            int stdScore = Integer.parseInt(queryItmes[3].split(" ")[1]);

            if(!infoMap.containsKey(searchKey)){
                people[i] = 0;
            }else{
                //map에서 찾기
                List<Integer> personList = infoMap.get(searchKey);
                int minIndex = binarySearch(personList, stdScore);

                people[i] = (personList.size()-1)-minIndex+1;
            }
        }

        return people;
    }

    static void DFS(String key, int depth, String[] items){
        if(depth==4){
            int score = Integer.parseInt(items[depth]); //150

            //맵에 이미 있으면
            if(infoMap.containsKey(key)){ //javabackendjuniorpizza
                infoMap.get(key).add(score);

            }else{ //없으면
                List<Integer> personList = new ArrayList<>();
                personList.add(score);
                infoMap.put(key, personList);
            }
            return;
        }

        DFS(key+"-", depth+1, items);
        DFS(key+items[depth], depth+1, items);
    }

    static int binarySearch(List<Integer> personList, int score){
        int left=0;
        int right=personList.size()-1;

        while(left<=right){
            int mid = (left+right)/2;

            if(personList.get(mid) >= score){ //조건충족
                right = mid-1;
            }else{
                left = mid+1;
            }
        }
        return left;
    }
}
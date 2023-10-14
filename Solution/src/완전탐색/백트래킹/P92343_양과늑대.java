package 완전탐색.백트래킹;

import java.util.*;

public class P92343_양과늑대 {
    public static void main(String[] args) {
        P92343_양과늑대_Solution s = new P92343_양과늑대_Solution();
        int result = s.solution(new int[]{0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1}, new int[][]{{0, 1}, {1, 2}, {1, 4}, {0, 8}, {8, 7}, {9, 10}, {9, 11}, {4, 3}, {6, 5}, {4, 6}, {8, 9}});
        System.out.println(result);
    }
}

/*
1차풀이(2차풀이 후 수정)
- 방문후보 -> List가 아닌 배열로 무조건 관리할 것 - 이유 : 추가, 제거시 생각한대로 순회일어날 가능성O

 */
 class P92343_양과늑대_Solution {
     static List<Integer>[] tree;
     static int max=0; //max : 최대 양의숫자
     static boolean[] canVisit; //canVisit : 도달가능 노드배열
     static int[] info;

     public int solution(int[] infoParam, int[][] edges) {
         info = infoParam; //info 배열복사

         canVisit = new boolean[info.length];
         tree = new ArrayList[info.length];
         for(int i=0;i<info.length;i++){
             tree[i] = new ArrayList<>();
         }
         //엣지 등록
         for(int[] edge :edges){ //엣지선택
             tree[edge[0]].add(edge[1]);
         }

         canVisit[0]=true;//canVisit에 0추가
         BT(0, 0); //0에서부터 시작

         return max;
     }

     static void BT(int sheep, int wolf){ //sheep : 양의수, wolf : 늑대숫자

         max = Math.max(max, sheep); //최댓값 갱신

         for(int node=0;node<info.length;node++){ //노드선택, 범위검사(자동)

             if(info[node]==1 && sheep<= wolf+1) continue; //양늑대 비교 - 유효성검사
             if(!canVisit[node]) continue; //도달가능검사 - 유효성검사
             방문배열변경(node);//방문노드의 자식노드들을 방문가능 노드리스트에 추가

             if(info[node]==1) BT(sheep, wolf+1); //다음노드 방문
             else BT(sheep+1, wolf);

             방문배열복원(node); //방문노드의 자식노드들을 방문가능 노드리스트에 제거
         }
     }

     static void 방문배열변경(int node){
         canVisit[node] = false;

         for(int child : tree[node]){
             canVisit[child] = true;
         }
     }

     static void 방문배열복원(int node){
         canVisit[node] = true;

         for(int child : tree[node]){
             canVisit[child] = false;
         }
     }

 }

//class P92343_양과늑대_Solution{
//    static int max = 0;
//    static ArrayList<Integer>[] tree;
//    static int[] info;
//    public int solution(int[] infoParam, int[][] edges){
//
//        info = infoParam;
//        tree초기화(info);
//
//        //edges등록
//        for(int[] edge : edges){
//            tree[edge[0]].add(edge[1]);
//        }
//
//        List<Integer> rootVisitList = new ArrayList<>();
//        for(int child : tree[0]){
//            rootVisitList.add(child);
//        }
//
//        BT(1,0, rootVisitList);
//        return max;
//
//    }
//
//    static void tree초기화(int[] info){
//        tree = new ArrayList[info.length];
//
//        for(int i=0;i<tree.length;i++){
//            tree[i] = new ArrayList<>();
//        }
//    }
//
//    static void BT(int sheep, int wolf, List<Integer> visitList){
//
//        max = Math.max(max, sheep);//양개수 최대치 갱신 - 노드처리
//
//        for(int i=0;i<visitList.size();i++){
//            int nn = visitList.get(i);
//
//            //유효성검사 - 양 > 늑대
//            if(info[nn]==1 && sheep <= wolf+1) continue; //늑대인경우 -> ~
//            //방문검사, 방문처리X - 이유 : 방문후보를 관리함으로써, 중복방문의 가능성X
//            //visitList(본인삭제, 본인자식추가) - 상태변경
//            visitList.remove(Integer.valueOf(nn));
//            for(int child : tree[nn]){
//                visitList.add(child);
//            }
//
//            if(info[nn]==0) BT(sheep+1, wolf, visitList); //sheep, wolf - 상태변경, 다음노드 방문
//            else BT(sheep, wolf+1, visitList);
//
//            //방문해제X
//            //visitList(본인추가, 본인자식삭제) - 상태복원
//            visitList.add(nn);
//            for(int child : tree[nn]){
//                visitList.remove(Integer.valueOf(child));
//            }
//        }
//    }
//}


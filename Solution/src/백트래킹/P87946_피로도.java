package 백트래킹;

public class P87946_피로도 {
    public static void main(String[] args) {
        Solution s = new Solution();

        int k=80;
        int[][] dungeons = {{80,20},{50,40},{30,10}};
        System.out.println(s.solution(k, dungeons));
    }
}

class Solution {

    private static boolean[] isVisited;
    private static int max =0;

    public int solution(int k, int[][] dungeons) {

        isVisited = new boolean[dungeons.length];
        visit(k, 0, dungeons);

        return max;
    }

    private static void visit(int k, int count, int[][] dungeons){
        if(count == dungeons.length){
            max = count;
            return;
        }

        for(int i=0;i<dungeons.length;i++){
            if(isVisited[i]) continue;

            if(k<dungeons[i][0]) continue;
            isVisited[i] = true;

            visit(k-dungeons[i][1], count+1, dungeons);
            isVisited[i] = false;
        }
        if(count>max) max =count;
    }

}
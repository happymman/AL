package 완전탐색.백트래킹;


/*
1차풀이 -

- 문제상황 : 배열범위 에러(프로그래머스) - 1.테케성공 개수 확인 - 이유 : 첫번째 테케는 성공했음에도 불구하고, 첫번째 테케로 오류 원인을 찾고 있을 수도 있음
                                 2.디버깅 - 실패 예상 코드 이전, 변수 출력+return
                                 확실한
 */
public class P121684_체육대회 {
    public static void main(String[] args) {
        P121684_체육대회_Solution s = new P121684_체육대회_Solution();
//        int result = s.solution(new int[][]{{40, 10, 10}, {20, 5, 0}, {30, 30, 30}, {70, 0, 70}, {100, 100, 100}});
        int result = s.solution(new int[][]{{20, 30}, {30, 20}, {20, 30}});
        System.out.println(result);
    }
}
class P121684_체육대회_Solution {
    static int[][] ability;
    static boolean[] isVisited;
    static int max=Integer.MIN_VALUE;

    public int solution(int[][] abilityParam) {
        ability = abilityParam;
        isVisited = new boolean[abilityParam.length];

        BT(0,new int[3]);
        return max;
    }

    static void BT(int depth, int[] route){
        // if(depth==2){
        //     for(int num : route){
        //         System.out.print(num+" ");
        //     }
        //     System.out.println();
        //     return;
        // }

        if(depth==3){
            int result = sum(route);
            max = Math.max(max, result);
            return;
        }

        for(int i=0;i<ability.length;i++){
            if(isVisited[i]) continue;
            isVisited[i] = true;

            route[depth] = ability[i][depth];

            BT(depth+1, route);
            isVisited[i] = false;
        }
    }

    static int sum(int[] route){
        int sum=0;
        for(int num : route){
            sum+= num;
        }
        return sum;
    }
}
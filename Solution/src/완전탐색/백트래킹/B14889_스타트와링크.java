package 완전탐색.백트래킹;

import java.util.*;

public class B14889_스타트와링크 {

    static int N;
    static int[][] a;
    static int[] nums;
    static int min = Integer.MAX_VALUE;
    static List<List<Integer>> result = new ArrayList<>();

    public static void main(String[] args) {
        input();
        pro();
    }

    static void input(){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        a= new int[N+1][N+1];
        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                a[i][j] = sc.nextInt();
            }
        }

        //nums초기화
        nums = new int[N];
        for(int i=0;i<nums.length;i++){
            nums[i] = i+1;
        }
    }

    static void pro(){

        BT(0, 0, new ArrayList<>()); //조합 만들기

        for(List<Integer> start : result){ //조합 선택
            List<Integer> link = 나머지팀_만들기(start);

            int startSum=0;
            for(int i=0;i<start.size();i++){
                for(int j=0;j<start.size();j++){ //본인제외하고 a[][]에서 능력치 합산
                    if(i==j) continue;
                    startSum += a[start.get(i)][start.get(j)];
                }
            }

            int linkSum=0;
            for(int i=0;i<link.size();i++){
                for(int j=0;j<link.size();j++){ //본인제외하고 a[][]에서 능력치 합산
                    if(i==j) continue;
                    linkSum += a[link.get(i)][link.get(j)];
                }
            }

            min = Math.min(min, Math.abs(startSum-linkSum));
        }

        System.out.println(min);

    }

    static List<Integer> 나머지팀_만들기(List<Integer> start){
        List<Integer> link = new ArrayList<>();

        for(int num : nums){
            if(!start.contains(num)) link.add(num);
        }
        return link;
    }

    static void BT(int depth, int start, List<Integer> route){
        if(depth==N/2){
            result.add(new ArrayList<>(route));
            return;
        }

        for(int i=start;i<nums.length;i++){ //범위검사(자동)
            //유효성검사X
            //방문검사, 방문처리X - 이유 : 순차방문 for 조합

            route.add(Integer.valueOf(nums[i])); //상태변경
            BT(depth+1, i+1, route);

            route.remove(Integer.valueOf(nums[i]));//상태복원 - 이유 : 상태변수가 배열이었을땐 상태복원 필요X, 리스트일땐 필요O
        }
    }
}

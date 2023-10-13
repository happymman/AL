package 완전탐색.백트래킹;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/*
1차풀이 - 1h25+출력형태

- BT라 잘못 생각했음
 */
public class B7490_0만들기 {
    static int N;
    static int M;
    static String[] ops = {" ", "+", "-"};
    static String[] nums;
    static StringBuilder result= new StringBuilder();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        M = sc.nextInt();
        while(M-->0){
            N = sc.nextInt();
            nums = new String[N];
            for(int i=0;i<N;i++){
                nums[i] = String.valueOf(i+1);
            }
            BT(0, new String[N-1]);

            if(M!=0) result.append("\n");
        }
        System.out.println(result.toString());
    }


    static void BT(int depth, String[] route){
        if(depth==N-1){
            수식계산_출력(route);
            return;
        }

        for(int i=0;i<3;i++){
            //유효성검사X
            //방문검사, 방문처리X

            route[depth] = ops[i];
            BT(depth+1, route);
        }
    }

    static void 수식계산_출력(String[] route){
        List<String> convert = new ArrayList<>();

        //공백 고려해서 기존식 변환식으로 전환하기
        int vacant=0;
        for(int i=0;i<route.length;i++){ //route순회
            if(vacant ==0){
                if(route[i].equals(" ")){ //공백 발견
                    vacant++;
                }else{
                    convert.add(nums[i]);
                }
            }else{ //vacant 찾는거 진행중
                if(route[i].equals(" ")){ //공백이면
                    vacant++;
                }else{ //끈겼으면 ->
                    String result = String.join("", Arrays.copyOfRange(nums, i-vacant, i+1)); //3에서 끊겼으면 -> 1~3 join = i~ i-vacant join
                    convert.add(result);
                    vacant=0;
                }
            }
        }

        //순회 이후
        if(vacant==0){
            convert.add(nums[nums.length-1]);
        }else{
            String result = String.join("", Arrays.copyOfRange(nums, (nums.length-1)-vacant, nums.length)); //3에서 끊겼으면 -> 1~3 join = i~ i-vacant join
            convert.add(result);
        }

        //변환된 숫자가지고 계산하기
        int sum=Integer.parseInt(convert.get(0));
        int convertIndex=1;

        for(String ops : route){ //route순회
            if(ops.equals("+")){
                sum += Integer.parseInt(convert.get(convertIndex));
                convertIndex++;
            }else if(ops.equals("-")){
                sum -= Integer.parseInt(convert.get(convertIndex));
                convertIndex++; //route ops가 "+", "-"일때만, convertIndex이동
            }
        }

        //합이 0일경우만 수식 출력
        if(sum!=0) return;


        for(int i=0;i<nums.length-1;i++){
            result.append(nums[i]);
            result.append(route[i]);
        }
        result.append(nums[nums.length-1]+"\n");

    }

}

/*
레퍼런스 풀이
- 아이디어 : "공백"일때 계산하지않고
 */
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//
//public class B7490_0만들기 {
//
//    static StringBuilder sb = new StringBuilder();
//
//    public static void main(String[] args) throws Exception {
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        int n = Integer.parseInt(br.readLine());
//        int number;
//
//        for (int i=0; i<n; i++) {
//            number = Integer.parseInt(br.readLine());
//            dfs(number, 1, 1, 1, 0, "1");
//            sb.append("\n");
//        }
//        System.out.println(sb.toString());
//    }
//
//    // DFS로 내려갈 때마다 각각의 String을 가져야해서 StringBuilder가 아닌 String사용
//    //depth : dfs깊이이자 '현재 숫자', num : 다음dfs 연산대상숫자, sign : 다음dfs 연산대상부호,
//    static void dfs(int max, int depth, int num, int sign, int sum, String str) {
//        if(max == depth) {
//            sum = sum + (num * sign);
//            if (sum == 0) {
//                sb.append(str +"\n");
//            }
//            return;
//        }
//        dfs(max, depth+1, num*10+(depth+1), sign, sum, str+ " " +String.valueOf(depth+1));
//        // 빈칸인 경우 뒤의 수와 이어야 하기에 sign은 그대로 가져간다.
//        dfs(max, depth+1, depth+1, 1, sum + (num*sign), str+ "+" +String.valueOf(depth+1));
//        dfs(max, depth+1, depth+1, -1, sum + (num*sign), str+ "-" +String.valueOf(depth+1));
//
//
//    }
//}

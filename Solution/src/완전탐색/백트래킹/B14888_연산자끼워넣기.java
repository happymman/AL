package 완전탐색.백트래킹;

import java.util.Scanner;

/*
1차풀이 - 40m

 */
//public class B14888_연산자끼워넣기 {
//    static int N;
//    static int max=Integer.MIN_VALUE;
//    static int min=Integer.MAX_VALUE;
//    static int[] opCount = new int[4];
//    static String[] ops;
//    static int[] nums;
//    static boolean[] isVisited;
//    static Set<String> resultSet = new HashSet<>();
//
//    public static void main(String[] args) {
//        input();
//        opsSetting();
//        BT(0, "");
//
//        for(String ops : resultSet){ //op조합 선택
//            String[] opsStr = ops.split("");
//
//            int sum=nums[0];
//            for(int i=0;i< opsStr.length;i++){ //숫자 선택
//                if(opsStr[i].equals("+")){
//                    sum += nums[i+1];
//                }else if(opsStr[i].equals("-")){
//                    sum -= nums[i+1];
//                }else if(opsStr[i].equals("*")){
//                    sum *= nums[i+1];
//                }else if(opsStr[i].equals("/")){
//                    sum /= nums[i+1];
//                }
//            }
//
//            max = Math.max(max, sum);//최대값 갱신
//            min = Math.min(min, sum);//최솟값 갱신
//        }
//
//        System.out.println(max);
//        System.out.println(min);
//    }
//
//    static void input(){
//
//        Scanner sc = new Scanner(System.in);
//        N = sc.nextInt(); //N입력받기
//
//        nums = new int[N];
//        ops = new String[N-1];//opCount 메모리할당
//        isVisited = new boolean[N-1];//isVisited 메모리할당
//
//        for(int i=0;i<N;i++){ //N만큼 nums배열 입력 받기
//            nums[i] = sc.nextInt();
//        }
//
//        for(int i=0;i<4;i++){ //opCount배열 입력받기
//            opCount[i] = sc.nextInt();
//        }
//
//    }
//
//    static void opsSetting(){
//
//        int index=0;
//        for(int i=0;i<4;i++){ //opCount 순회
//            //ops 배열 세팅
//            String op;
//            if(i==0){
//                op="+";
//            }else if(i==1){
//                op="-";
//            }else if(i==2){
//                op="*";
//            }else{
//                op="/";
//            }
//            while(opCount[i]-->0){
//                ops[index++] = op;
//            }
//        }
//    }
//
//    static void BT(int depth, String str){
//        if(depth==ops.length){
//            resultSet.add(str);
//            return;
//        }
//
//        for(int i=0;i<ops.length;i++){ //ops배열 순회 - 범위검사(자동)
//            //유효성검사X
//            if(isVisited[i]) continue; //방문검사
//            isVisited[i] = true;//방문처리
//
//            BT(depth+1, str+ops[i]);
//
//            isVisited[i] = false; //방문해제
//
//        }
//    }
//}

/*
레퍼런스 풀이
-
 */

//public class Main {
//
//    public static int MAX = Integer.MIN_VALUE;	// 최댓값
//    public static int MIN = Integer.MAX_VALUE;	// 최솟값
//    public static int[] opCount = new int[4];	// 연산자 개수
//    public static int[] nums;					// 숫자
//    public static int N;						// 숫자 개수
//
//    public static void main(String[] args) {
//
//        Scanner in = new Scanner(System.in);
//
//        N = in.nextInt();
//        nums = new int[N];
//
//        for (int i = 0; i < N; i++) { // 숫자 입력
//            nums[i] = in.nextInt();
//        }
//
//        for (int i = 0; i < 4; i++) { // 연산자 입력
//            opCount[i] = in.nextInt();
//        }
//
//        BT(1, nums[0]);
//
//        System.out.println(MAX);
//        System.out.println(MIN);
//
//    }
//
//    public static void BT(int depth, int sum) {
//        if (depth == N) {
//            MAX = Math.max(MAX, sum);
//            MIN = Math.min(MIN, sum);
//            return;
//        }
//
//        for (int i = 0; i < 4; i++) {
//
//            if (opCount[i] > 0) { // 연산자 개수가 1개 이상인 경우
//                opCount[i]--; //연산자 선택(해당 연산자를 1 감소시킨다) - 상태변경
//
//                switch (i) {
//                    case 0:	BT(depth+1, sum + nums[depth]);	break;
//                    case 1:	BT(depth+1,sum - nums[depth]);	break;
//                    case 2:	BT(depth+1,sum * nums[depth]);	break;
//                    case 3:	BT(depth+1,sum / nums[depth]);	break;
//                }
//
//                opCount[i]++; //연산자 선택 해제(재귀호출이 종료되면 다시 해당 연산자 개수를 복구한다) - 상태복원
//            }
//        }
//    }
//
//}

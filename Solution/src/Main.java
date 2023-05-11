
//백준 - 11399번 : ~
/*
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0;i<n; i++){
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);
        int sum =0;
        for(int i=0; i<arr.length; i++){
            sum += arr[i]*(arr.length-(i));
        }
        System.out.println(sum);
    }
}
*/


//백준 - 11047번 동전 0
//
//import java.util.*;
//
//public class Main {
//    public static void main(String[] args) {
//        int answer =0;
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt(), k = sc.nextInt();
//        int[] arr = new int[n];
//        for(int i=0;i<n; i++){
//            arr[i] = sc.nextInt();
//        }
//
//        for(int i=n-1;i>=0;i--){
//            if(arr[i] <= k){
//                answer+=k/arr[i];
//                k=k%arr[i];
//                if(k==0) break;
//            }
//        }
//
//        System.out.println(answer);
//        sc.close();
//    }
//}
//
//import java.util.*;
//
//public class Main {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt(), k = sc.nextInt();
//        int[] arr = new int[n];
//        for(int i=0;i<n; i++){
//            arr[i] = sc.nextInt();
//        }
//        int answer =0;
//
//        for(int i=n-1;i>=0;i--){
//            if(arr[i]<=k){
//                answer += k/arr[i];
//                k = k%arr[i];
//            }
//            if(k==0) break;
//        }
//
//        System.out.println(answer);
//        sc.close();
//    }
//}

//백준 - 1026 보물
//import java.util.*;
//
//import static java.util.Collections.reverseOrder;
//
//public class Main {
//    public static void main(String[] args) {
//        int answer =0;
//        Scanner sc = new Scanner(System.in);
//        int n=0;
//        n = sc.nextInt();
//
//        int[] a = new int[n];
//        Integer[] b= new Integer[n];
//        for(int i=0;i<n; i++) a[i] = sc.nextInt();
//        for(int i=0;i<n; i++) b[i] = sc.nextInt();
//
//        Arrays.sort(a);
//        Arrays.sort(b, reverseOrder());
//
//        for(int i=0;i<n;i++) answer += a[i]*b[i];
//
//        System.out.println(answer);
//        sc.close();
//    }
//
//}


//import java.util.*;
//
//public class Main {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int n=0;
//        n = sc.nextInt();
//        int[] a = new int[n];
//        Integer[] b = new Integer[n];
//        for(int i=0;i<n;i++) a[i] = sc.nextInt();
//        for(int i=0;i<n;i++) b[i] = sc.nextInt();
//        int sum = 0;
//
//        Arrays.sort(a);
//        Arrays.sort(b, Comparator.reverseOrder());
//
//        for(int i=0; i<n;i++){
//            sum += a[i]*b[i];
//        }
//
//        System.out.println(sum);
//        sc.close();
//    }
//
//}


import 구현.P83201_상호평가;

import java.io.IOException;

public class Main {
//    public static void main(String[] args) throws IOException {
//        P86048_입실퇴실 p = new P86048_입실퇴실();
//
//        int[] enter = {1,3,2};
//        int[] leave = {1,2,3};
//
//        int[] result = p.solution(enter, leave);
//        for(int i=0;i<result.length;i++){
//            System.out.print(result[i]+" ");
//        }
//    }
    public static void main(String[] args) throws IOException {
        P83201_상호평가 p = new P83201_상호평가();

        int[][] arr = {
//                {50,90},
//                {50,87},
                {100,90,98,88,65},
                {50,45,99,85,77},
                {47,88,95,80,67},
                {61,57,100,80,65},
                {24,90,94,75,65},
        };

        String result = p.solution(arr);
        System.out.print(result);
    }
}
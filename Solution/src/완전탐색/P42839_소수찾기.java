package 완전탐색;

import java.io.*;
import java.util.HashSet;
import java.util.StringTokenizer;

public class P42839_소수찾기{
    private static char[] arr;
    private static boolean[] isVisited;
    private static HashSet<Integer> set = new HashSet<>();

    public int solution(String numbers){
        arr = numbers.toCharArray();
        isVisited = new boolean[numbers.length()];

        recursion("", 0);

        return set.size();

    }

    private void recursion(String str, int size){ //"", 0
        if(str!=""){
            int num = Integer.parseInt(str);
            if(isPrime(num)) set.add(num);
        }

        if(size == arr.length) return;

        for(int i=0;i<arr.length;i++){
            if(isVisited[i]) continue;
            isVisited[i] = true;

            recursion(str+arr[i], size+1);
            isVisited[i] = false;
        }
    }

    private boolean isPrime(int num){
        if(num<2) return false;
        for(int i=2;i<=Math.sqrt(num);i++){
            if(num%i==0) return false;
        }
        return true;
    }
}

//import java.util.Set;
//        import java.util.HashSet;
//
//public class Solution{
//
//    private static Set<Integer> set = new HashSet<>();
//    private static char[] arr;
//    boolean[] isVisited;
//
//    private void recursion(String str, int size){
//        if(str != ""){
//            int num = Integer.parseInt(str);
//            if(isPrime(num)) set.add(num);
//        }
//
//        if(size == arr.length){
//            return;
//        }
//
//        for(int i=0;i<arr.length;i++){
//            if(isVisited[i]) continue;
//            isVisited[i] = true;
//
//            recursion(str+arr[i], size+1);
//            isVisited[i] = false;
//        }
//    }
//
//    private boolean isPrime(int num){
//        if(num<2) return false;
//        for(int i=2;i<= Math.sqrt(num);i++){
//            if(num%i==0) return false;
//        }
//        return true;
//    }
//
//    public int solution(String numbers){
//        arr = numbers.toCharArray();
//        isVisited = new boolean[numbers.length()];
//
//        recursion("",0);
//
//        return set.size();
//    }
//}
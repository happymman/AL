package 완전탐색;

public class P84512_모음사전 {

    public int solution(String word) {

        int[] mul = {781, 156, 31, 6, 1};

        int order=word.length();
        for(int i=0;i<word.length();i++){
            order+="AEIOU".indexOf(word.charAt(i))*mul[i];
        }

        return order;
    }
}

// By DFS
//import java.util.*;
//
//class Solution {
//
//    static char[] arr;
//    static List<String> wordList = new ArrayList<>();
//
//    public int solution(String word) {
//
//        arr = new char[]{'A', 'E', 'I', 'O', 'U'};
//
//        dfs("",0);
//
//        for(int i=0;i<wordList.size();i++){
//            if(wordList.get(i).equals(word)) return i;
//        }
//        return -1;
//    }
//
//    static void dfs(String str, int count){
//        wordList.add(str);
//
//        if(count==5){
//            return;
//        }
//
//        for(int i=0;i<5;i++){
//            dfs(str+arr[i], count+1);
//        }
//    }
//}
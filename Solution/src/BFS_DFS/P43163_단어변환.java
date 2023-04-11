package BFS_DFS;

import java.util.LinkedList;
import java.util.Queue;

class P43163_단어변환 {
    private static class State{
        String word;
        Integer step;

        public State(String word, Integer step){
            this.word = word;
            this.step = step;
        }
    }

    private boolean isConvertable(String src, String dst){
        char[] srcArr = src.toCharArray();
        char[] dstArr = dst.toCharArray();

        int diff=0;
        for(int i=0 ; i<srcArr.length; i++){
            if(srcArr[i] != dstArr[i]) diff++;
        }
        return diff == 1;
    }


    public int solution(String begin, String target, String[] words) {
        boolean[] isVisited = new boolean[words.length];

        Queue<State> queue = new LinkedList<>();
        queue.add(new State(begin, 0));

        while(!queue.isEmpty()){
            State state = queue.poll();

            if(state.word.equals(target))
                return state.step;
            for(int i=0;i<words.length;i++){
                String next = words[i];

                if(isVisited[i]) continue;
                if(!isConvertable(state.word, next)) continue;
                isVisited[i] = true;
                queue.add(new State(next, state.step+1));
            }
        }
        return 0;

    }
}

//import java.util.Queue;
//        import java.util.LinkedList;
//
//public class Solution{
//    private static boolean isConvertible(String src, String dst){
//        int diff=0;
//        for(int i=0;i<src.length();i++){
//            if(src.charAt(i) != dst.charAt(i)) diff++;
//        }
//        return diff ==1;
//    };
//
//    private class State{
//        String word;
//        int step;
//        public State(String word, int step){
//            this.word = word;
//            this.step = step;
//        }
//    }
//
//    public int solution(String begin, String target, String[] words){
//        boolean[] isVisited = new boolean[words.length];
//        Queue<State> q = new LinkedList<>();
//        q.add(new State(begin, 0));
//
//        while(!q.isEmpty()){
//            State state = q.poll();
//
//            if(state.word.equals(target)) return state.step;
//
//            for(int i=0;i<words.length;i++){
//                if(isVisited[i]) continue;
//                if(!isConvertible(state.word, words[i])) continue;
//                isVisited[i] = true;
//
//                q.add(new State(words[i], state.step+1));
//            }
//        }
//        return 0;
//    }
//}
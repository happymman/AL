package 완전탐색.DFS_BFS;

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

/*
4차풀이

피드백 :
1.words -> word오타찾는데 시간 오래걸림
2.방문배열 필요여부 판단 - 방문배열없을때, 중복방문하는가?(ex : 안하는경우 : 1,2차원 배열 순차적 진행)
                     중복방문 해야하는가? - 중복방문하지않고 답을 찾을 수 있는가? 당연히 찾을 수 있다.
                     중복방문해도 종료될 수 있는가? - 답존재+BFS이기 때문에 종료가능

행위 must이유 질문하기 -> ifX 목적달성여부 질문하기

import java.util.*;

public class Solution{

    public class State{
        String word;
        int step;

        State(String word, int step){
            this.word = word;
            this.step = step;
        }
    }

    public int solution(String begin, String target, String[] words){

        boolean find = false;
        for(String word : words){
            if(word.equals(target)){
                find= true;
                break;
            }
        }
        if(!find) return 0; //target이 words에 포함되어있지 않으면, 0을 반환하고 종료


        Queue<State> q = new LinkedList<>(); //큐 생성
        q.add(new State(begin,0)); // 큐.add(begin)

        while(!q.isEmpty()){
            State now = q.poll();

            if(now.word.equals(target)){ //target단어로 변환이 되었다면
                return now.step;
            }

            for(String next : words){ //단어 선택, next : 다음단어 후보, 범위검사
                if(!isConvertable(now.word, next)) continue; //현재단어로부터 변환가능하나, 유효성검사
                //방문검사, 방문처리X

                q.add(new State(next, now.step+1));
            }
        }
        return 100;
    }

    static boolean isConvertable(String std, String test){
        char[] stdChar = std.toCharArray();
        char[] testChar = test.toCharArray();

        int diff=0; // diff : 다른문자개수
        for(int i=0;i<stdChar.length;i++){
            if(stdChar[i] != testChar[i]) diff++;
        }
        return diff==1 ? true : false; //다른문자개수가 하나여야만 변환가능
    }
}

 */
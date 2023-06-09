package 완전탐색.DFS_BFS;

import java.util.Stack;

public class P43165_타겟넘버 {
    private static class State{
        private final int index;
        private final int acc;

        public State(int index, int acc){
            this.index = index;
            this.acc = acc;
        }
    }

    public int solution(int[] numbers, int target){
        Stack<State> s = new Stack<>();
        s.push(new State(0,0));

        int count = 0;
        while(!s.empty()){
            State state = s.pop();

            if(state.index == numbers.length){
                if(state.acc == target) count++;
                continue;
            }

            s.push(new State(state.index+1, state.acc + numbers[state.index]));
            s.push(new State(state.index+1, state.acc - numbers[state.index]));
        }

        return count;
    }
}

//import java.util.Stack;
//
//public class Solution{
//    private static class State{
//        int sum;
//        int idx;
//        public State(int sum, int idx){
//            this.sum = sum;
//            this.idx = idx;
//        }
//    }
//
//    public int solution(int[] numbers, int target){
//        Stack<State> s = new Stack<>();
//        int count=0;
//
//        s.push(new State(0,0));
//
//        while(!s.isEmpty()){
//            State state = s.pop();
//
//            if(state.idx == numbers.length){
//                if (state.sum == target) count++;
//                continue;
//            }
//
//            s.push(new State(state.sum+numbers[state.idx], state.idx+1));
//            s.push(new State(state.sum-numbers[state.idx], state.idx+1));
//        }
//        return count;
//    }
//}
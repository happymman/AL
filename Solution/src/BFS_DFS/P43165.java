package BFS_DFS;

import java.util.Stack;

public class P43165{
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

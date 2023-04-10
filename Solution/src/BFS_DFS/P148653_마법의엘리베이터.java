package BFS_DFS;

import java.util.Stack;

class P148653_마법의엘리베이터 {
    private class State{
        int value;
        int stone;
        public State(int value, int stone){
            this.value = value;
            this.stone = stone;
        }
    }

    public int solution(int storey) {
        int answer = 0;
        Stack<State> s = new Stack<>();
        s.push(new State(storey, 0));

        int minStone = Integer.MAX_VALUE;
        while(!s.isEmpty()){ //value : 16, stone : 0
            State state = s.pop();

            //타겟 검사
            if(state.value == 0){
                minStone = Math.min(minStone, state.stone);
                continue;
            }

            int smallest = state.value%10; //6
            if(smallest == 0){
                s.push(new State(state.value/10, state.stone+smallest));
            }else if(smallest > 5){
                s.push(new State(state.value/10+1, state.stone+(10-smallest)));
            }else if(smallest == 5){
                s.push(new State(state.value/10, state.stone+5));
                if(state.value/10 != 0)
                    s.push(new State(state.value/10+1, state.stone+5));
            }else if(smallest <5){
                s.push(new State(state.value/10, state.stone+smallest));
            }
        }
        return minStone;
    }
}
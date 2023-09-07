package 구현.스택큐;

import java.util.*;

public class P12909_올바른괄호{
    public boolean solution(String s){
        /*

        스택 초기화
        "("면 스택 넣고
        ')'면 스택 pop()하고, 만약에 비었으면 return false;
        다 끝났을때 비어있어야 return false;

        */
        Stack stack = new Stack<>();
        for(char c : s.toCharArray()){
            if(c=='('){
                stack.push('(');
            }else if(c==')'){
                if(!stack.isEmpty()){
                    stack.pop();
                }else{
                    return false;
                }
            }
        }

        if(stack.isEmpty()){
            return true;
        }else{
            return false;
        }
    }
}
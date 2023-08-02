package 스택큐;

import java.util.Scanner;
import java.util.Stack;

public class P10799_쇠막대기 {
    public static void main(String[] args) {
        Stack<Character> s = new Stack<>();
        Scanner sc = new Scanner(System.in);
        String string = sc.next();

        int answer=0;
        char prev = ' ';
        for(char c : string.toCharArray()){
            if(c == '('){
                s.push('(');
            }else{
                s.pop();
                if(prev == '('){
                    answer += s.size();
                }else{
                    answer += 1;
                }
            }
            prev = c;
        }

        System.out.println(answer);
    }
}

//1:47~? : 문제 읽기
//?~2:57 : 수도코드 &실제코드 1차 작성
//2:57~3:24 : 파일 관련 에러 해결, 스택 에러 해결(삼항연산자 시도, try cath문 사용, 라이브러리 전부 import) 
//3:24~
//자바 스택 자료구조 공부

//스택을 써야할 듯
//스택을 안쓰고 논리적으로 해결할수도 있을 것 같다.
import java.util.*;

public class Solution {
    public int solution(int[] order){ //order = 실어야하는 순서
        Stack<Integer> stack = new Stack<>();
        int answer = 0;

        //for문 -> 반복횟수 고정, iterate방향 고정?, 블록코드 방향 고정(+break, continue)
        //인덱스 while문 -> 반복횟수 가변, 블록코드 방향 가변 
        int i=0;
        int conItem = 1;
        boolean find = false;

        while(i<order.length){
            find = false; //
            int top;
            try{
                top = stack.peek();    
            }catch(EmptyStackException e){
                top = -1;
            }

            //if 원래벨트? -> 택배기사 주기
            //  answer +=1
            //  conItem +=1
            //  find true;
            if (conItem == order[i]){
                answer +=1;
                conItem +=1;
                find =true;

            //elif 보조벨트? -> O -> 택배기사에게 주기
            //                     answer+=1
            //                     stack.pop()
            //                     find true;       
            //                X - 남은거O -> 보조벨트 보내기(stack.push(conItem))
            //                             (다시 원래벨트 확인)
            //                    남은거X(conItem.equals(order.length)) -> 종료(break)
            }else if(conItem == stack.peek()){
                answer +=1;
                stack.pop();
                find =true;
            }else if(conItem != stack.peek()){
                if(conItem!=order.length){
                    stack.push(conItem);
                }else if(conItem==order.length){
                    break;
                }
            }
         
            if(find){ //찾았을때만 order인덱스 올리고, 못 찾았을때는 i고정시켜 원래벨트 다시 확인
                i++;
            }   
        }
        return answer;
    }
}
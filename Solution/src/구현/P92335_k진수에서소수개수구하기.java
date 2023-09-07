package 구현;
import java.util.*;

/*
1차풀이 - 45m이후 반례게시판

문제상황 : 테케오류 -> 자료형long의심 - return값 확인
                                변수값 메써드 결과, 연산결과값 확인 ex : Long.toString(n,k);

 */
public class P92335_k진수에서소수개수구하기 {
    public int solution(int n, int k) {
        String num = Long.toString(n,k); //n진수 변환 437674 -> 211020101011
        System.out.println(num);

        Stack<Integer> s = new Stack<>(); //스택 생성

        int answer=0; //answer : 소수개수
        for(int i=0;i<num.length();i++){ //하나씩 스택에 넣기
            int input = Character.getNumericValue(num.charAt(i)); //value : i번째 숫자

            if(input!=0){ //0이 아니면
                s.push(input); //스택 푸시
            }else{ //0이면
                long sum=0;
                int exp=0; //exp : 지수
                while(!s.isEmpty()){ //빌때까지
                    int value = s.pop(); //pop해서 가져오기
                    sum+= value * Math.pow(10, exp); //ex : 10의 0승, 10의 1승, 10의 2승
                    exp++; //지수 한개씩 증가
                }
                System.out.println(sum);
                if(isPrimary(sum)) answer++; //소수이면 개수++;
            }
        }

        long sum=0;
        int exp=0;
        while(!s.isEmpty()){ //stack에 남아있다면
            int value = s.pop(); //pop해서 가져오기
            sum+= value * Math.pow(10, exp); //ex : 10의 0승, 10의 1승, 10의 2승
            exp++; //지수 한개씩 증가
        }
        System.out.println(sum);
        if(isPrimary(sum)) answer++; //소수이면 개수++;



        return answer;
    }

    static boolean isPrimary(long num){
        if(num==0 ||num==1) return false;

        for(int i=2;i<=Math.sqrt(num);i++){
            if(num%i==0) return false; //i로 나누어지면
        }
        return true;
    }
}

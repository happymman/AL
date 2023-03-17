package solution;

//문자열 압축
//1112~1133 : 문제읽기 도중 필요한 문법에 대해서 매우 몰라서 정답지 보면서 문법 보는게 좋을 것 같다고 판단
//1133~1148 : 솔루션 코드 공부 + 자바 메써드(substring, StringBuilder)
//111~358 : 분석 완료
//358 : 코드쓰기

public class no_60057 {
    public int solution(String s) { //aab bac cc
        int answer = s.length();

        for(int i=1 ; i<=s.length()/2 ; i++){ //3
            int rCount = 1;
            String stdStr = s.substring(0,i);
            StringBuilder result = new StringBuilder();

            for(int j=i ; j<=s.length() ; j+=i){
                String next = s.substring(j,j+i>s.length() ? s.length() : j+i);
                if(stdStr.equals(next)){
                    rCount++;
                }else{
                    result.append((rCount==1?"":rCount)+stdStr);
                    rCount = 1;
                    stdStr = next;
                }
            }
            result.append(stdStr);
            answer = Math.min(answer, result.length());
        }

        return answer;
    }
}

//aabbaccc -> 2a2ba3c
//ababcdcdababcdcd - 8 - 2ababcdcd
//                   2 - 2ab2cd2ab2cd
//abcabcdede -> 2abcdede
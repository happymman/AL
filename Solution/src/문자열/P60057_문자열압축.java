package 문자열;

public class P60057_문자열압축 {
    public static void main(String[] args) {
        P60057_문자열압축Solution sl = new P60057_문자열압축Solution();

        String s1 = "aabbaccc"; //7
        String s2 = "ababcdcdababcdcd"; //9
        String s3 = "abcabcdede"; //8
        String s4 = "abcabcabcabcdededededede"; //14
        String s5 = "xababcdcdababcdcd"; //17
        System.out.println(sl.solution(s5));
    }
}

/*
class Solution {
    public int solution(String s) { //aa aa bb aa aa
        /*

        for(반복단위Count : 1-><= s.length()){

            stdStartIndext=0;
            stdEndIndex=반복단위Count;
            현재문자 = s.substring에서(stdStartIndext, stdEndIndex);
            반복Count=1

            nextStartIndex=stdEndIndex;
            nextEndIndex=nextStartIndex+반복단위Count
            String nextString =

            while(next문자 == 기준문자){
                nextStartIndex=nextEndIndex;
                nextEndIndex=nextStartIndex+반복단위Count

                nextString = s.substring(nextStartIndex, nextEndIndex);
            }

            for(int i=0;){
                다음문자 =

                while(다음문자 현재문자랑 동일한지 검사){
                    반복Count++
                }

                //종료
                if(반복count>1){
                    반복Count만큼 List.remove();
                    반복Count+알파벳을 더하기
                }

            }
        }
    }
}
*/

//class P60057_문자열압축Solution {
//    public int solution(String s) {
//        int answer = s.length();
//
//        for(int i=1 ; i<=s.length()/2 ; i++){ //3
//            int rCount = 1;
//            String stdStr = s.substring(0,i);
//            StringBuilder result = new StringBuilder();
//
//            for(int j=i ; j<=s.length() ; j+=i){
//                String next = s.substring(j,j+i<s.length() ? j+1 : s.length()); //
//                if(stdStr.equals(next)){
//                    rCount++;
//                }else{
//                    result.append((rCount==1?"":rCount)+stdStr); //2a2b4a
//                    rCount = 1;
//                    stdStr = next;
//                }
//            }
//            /*
//                i)이전for문의 j+i가 s.length()인경우,
//                다음for문의 j는 s.length()가 되어 for문을 벗어나지않게되고
//                -> s.substring(s.length(), s.length())
//                -> ""공백을 잘라내어 else문을 실행(&stdStr = ""가되어 for문 벗어난뒤 result에 추가해도 영향없음)
//
//                ii)이전for문의 j+i가 s.length()보다 더큰경우,
//                다음for문의 j는 s.length()를 초과하게되어 for문을 벗어나게되고
//                -> stdStr은 append되지 않은채 남아있으므로
//                여기서 append해준다.
//             */
//            result.append(stdStr);
//            answer = Math.min(answer, result.length());
//        }
//
//        return answer;
//    }
//}

class P60057_문자열압축Solution {
    public int solution(String s) {
        int answer = s.length();

        for(int i=1 ; i<=s.length()/2 ; i++){ //3
            int rCount = 1;
            String stdStr = s.substring(0,i);
            StringBuilder result = new StringBuilder();

            for(int j=i ; j<s.length() ; j+=i){
                String next = s.substring(j,j+i<s.length() ? j+i : s.length()); //
                if(stdStr.equals(next)){
                    rCount++;
                }else{
                    result.append((rCount==1?"":rCount)+stdStr); //2a2b4a
                    rCount = 1;
                    stdStr = next;
                }
            }
            result.append((rCount==1?"":rCount)+stdStr);
            answer = Math.min(answer, result.length());
        }
        return answer;
    }
}

/*
2차풀이 - 틀린풀이

import java.util.*;

class Solution {
    public int solution(String s) {
        int minLength=Integer.MAX_VALUE; //최소길이

        for(int i=1;i<=s.length()/2;i++){ //길이1, 2, 3, ,,, 문자열 반
            String stdStr = s.substring(0,i); //기준문자열 i길이만큼 자르기
            StringBuilder sb = new StringBuilder();
            int rCount=1; //초기화

            for(int j=i;j<s.length();j+=i){ //
                int end = j+i < s.length() ? j+i : s.length(); //end : substring경계
                String testStr = s.substring(j,end); //테스트문자열자르기
                if(stdStr.equals(testStr)){ //기준문자열, 테스트문자열 같으면
                    rCount++;
                }else{
                    sb.append(rCount!=1 ? rCount+stdStr : stdStr);
                    stdStr = testStr; //기준문자열 = 테스트문자열
                    rCount=1;
                }
            }
            //후처리
            sb.append(rCount!=1 ? rCount+stdStr : stdStr);

            minLength = Math.min(minLength, sb.toString().length()); //최솟값 담기
        }

        return minLength;
    }
}

2차풀이 - 옳은풀이

import java.util.*;

class Solution {
    public int solution(String s) {
        int minLength=s.length(); //최소길이 = 가능최대길이로 설정

        for(int i=1;i<=s.length()/2;i++){ //길이1, 2, 3, ,,, 문자열 반
            String stdStr = s.substring(0,i); //기준문자열 i길이만큼 자르기
            StringBuilder sb = new StringBuilder();
            int rCount=1; //초기화

            for(int j=i;j<s.length();j+=i){ //
                int end = j+i < s.length() ? j+i : s.length(); //end : substring경계
                String testStr = s.substring(j,end); //테스트문자열자르기
                if(stdStr.equals(testStr)){ //기준문자열, 테스트문자열 같으면
                    rCount++;
                }else{
                    sb.append(rCount!=1 ? rCount+stdStr : stdStr);
                    stdStr = testStr; //기준문자열 = 테스트문자열
                    rCount=1;
                }
            }
            //후처리
            sb.append(rCount!=1 ? rCount+stdStr : stdStr);

            minLength = Math.min(minLength, sb.toString().length()); //최솟값 담기
        }

        return minLength;
    }
}

피드백
상황 : 코드종류 - 최솟값 갱신 -> 초기기준값 무한대값으로 설정 - 상황 : 무조건 최소1번이상의 갱신이 이루어지는 상황
                          초기기준값 가능최댓값으로 설정 - 상황 : 최소1번이상의 갱신이 안이루어질수도 있는 상황 - ex : for문이 안돌아가서 in 최소길이
                                                  X상황 : 가능최댓값을 결정할 수 없을때
상황 : 테케돌리기 - 예시테케
                최소데이터, 최대데이터

상황 : 테케오류 -> 1.return자료형
               2.테케 최소,최대 고려여부 확인
            -> 의심코드 이유 설명하기(천천히, 완벽하게(거듭))
               모든코드 이유 설명하기(천천히, 완벽하게(거듭))
 */
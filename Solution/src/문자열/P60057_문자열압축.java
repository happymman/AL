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
package 문자열;

import java.io.*;
import java.util.stream.*;
class H_separateTheNumber_Result {

    /*
     * Complete the 'separateNumbers' function below.
     *
     * The function accepts STRING s as parameter.
     */

    public static void separateNumbers(String s) {
        // Write your code here
        for(int count=1;count<=s.length()/2;count++){
            boolean possible = true;
            String currentNumber = "";
            int stringIndex = 0;
            int restCount = count;

            while(restCount!=0){
                currentNumber += s.charAt(stringIndex);
                stringIndex++;
                restCount--;
            }

            String answerNumber = currentNumber;
            boolean changeFlag = false;
            while(possible && stringIndex != s.length()){
                double test = Math.log10(Long.parseLong(currentNumber)+1);
                if(changeFlag || test == Math.floor(test)){
                    restCount = count+1;
                    changeFlag = true;
                }else if(test != Math.floor(test)){
                    restCount = count;
                }

                String nextNumber = "";
                while(restCount!=0){
                    if(stringIndex != s.length()){
                        nextNumber += s.charAt(stringIndex);
                        stringIndex++;
                        restCount--;
                    }else{
                        possible = false;
                        break;
                    }
                }

                if(nextNumber.length() >0 && nextNumber.charAt(0) == '0') possible = false;
                if(nextNumber.length() >0 && Long.parseLong(nextNumber) - Long.parseLong(currentNumber) == 1){
                    currentNumber = nextNumber;
                }else{
                    possible = false;
                }
            }
            if(possible){
                System.out.println("YES "+answerNumber);
                return;
            }
        }
        System.out.println("NO");
    }

}

public class H_separateTheNumber {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, q).forEach(qItr -> {
            try {
                String s = bufferedReader.readLine();

                H_separateTheNumber_Result.separateNumbers(s);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
    }
}

// 다른 사람 풀이

//public class SeparatetheNumbers {
//
//    static void separateNumbers(String s) {
//        String subStr = "";
//        boolean isVaild = false;
//
//        //subString을 0부터 시작하니까 i는 1부터 시작하고 s 길이의 반을 넘을 수 없다.
//        for (int i = 1; i <= s.length() / 2; i++) {
//            subStr = s.substring(0, i);
//            Long num = Long.parseLong(subStr);
//
//            //0부터 i까지 자른 String을 vaildStr변수에 담고 vaildStr를 1씩증가시킨값을 누적합한다.
//            String vaildStr = subStr;
//            while (vaildStr.length() < s.length()) {
//                vaildStr += Long.toString(++num);
//            }
//
//            //누적합한 vaildStr과 s를 비교하여 일치하면 isVaild= true바꿔주고
//            // 가장 작은 숫자를 리턴해야하기때문에 for문을 바로 종료한다.
//            if (s.equals(vaildStr)) {
//                isVaild = true;
//                break;
//            }
//        }
//        System.out.println(isVaild ? "YES " + subStr : "NO");
//    }
//
//    public static void main(String[] args) {
//        separateNumbers("1234");
//        System.out.print("ans: YES 1\n");
//        separateNumbers("91011");
//        System.out.print("ans: YES 9\n");
//        separateNumbers("99100");
//        System.out.print("ans: YES 99\n");
//        separateNumbers("101103");
//        System.out.print("ans: NO \n");
//        separateNumbers("010203");
//        System.out.print("ans: NO \n");
//        separateNumbers("13");
//        System.out.print("ans: NO \n");
//        separateNumbers("1");
//        System.out.print("ans: NO \n");
//    }
//}
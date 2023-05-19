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

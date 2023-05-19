package 문자열;


import java.io.*;
        import java.math.*;
        import java.security.*;
        import java.text.*;
        import java.util.*;
        import java.util.concurrent.*;
        import java.util.function.*;
        import java.util.regex.*;
        import java.util.stream.*;
        import static java.util.stream.Collectors.joining;
        import static java.util.stream.Collectors.toList;

class H_superReducedString_Result {

    /*
     * Complete the 'superReducedString' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING s as parameter.
     */

    public static String superReducedString(String s) {
        // Write your code here
        StringBuilder sb = new StringBuilder(s);
        boolean flag = true;
        while(flag){
            flag = false;
            for(int i=0;i<sb.length()-1;i++){
                char currentChar = sb.charAt(i);
                char nextChar = sb.charAt(i+1);
                if(currentChar == nextChar){
                    sb.deleteCharAt(i);
                    sb.deleteCharAt(i);
                    flag = true;
                    i--;
                }
            }
        }
        return !sb.toString().equals("") ? sb.toString() : "Empty String";
    }

}

public class H_superReducedString {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = bufferedReader.readLine();

        String result = H_superReducedString_Result.superReducedString(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

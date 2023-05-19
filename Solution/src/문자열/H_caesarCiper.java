package 문자열;


import java.io.*;

import static java.util.stream.Collectors.joining;

class H_caesarCiper_Result {

    /*
     * Complete the 'caesarCipher' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. STRING s
     *  2. INTEGER k
     */

    public static String caesarCipher(String s, int k) {
        // Write your code here
        char[] arr = s.toCharArray();
        for(int i=0;i<arr.length;i++){
            char c = arr[i];
            if(Character.isUpperCase(c)){
                arr[i] = (char)((c-'A'+k)%26+'A');
            }else if(Character.isLowerCase(c)){
                arr[i] = (char)((c-'a'+k)%26+'a');
            }else{
                arr[i] = c;
            }
        }

        String answer = "";
        for(char c : arr){
            answer+=c;
        }
        return answer;



    }

}

public class H_caesarCiper {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        String s = bufferedReader.readLine();

        int k = Integer.parseInt(bufferedReader.readLine().trim());

        String result = H_caesarCiper_Result.caesarCipher(s, k);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

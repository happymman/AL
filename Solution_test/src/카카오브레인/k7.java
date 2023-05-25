package 카카오브레인;

import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;


class k7_Result {

    /*
     * Complete the 'bioHazard' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER_ARRAY allergic
     *  3. INTEGER_ARRAY poisonous
     */

    static int[] arr;
    static boolean[] isVisited;
    static int[] poisonous_r;
    static long answerCount;

    public static long bioHazard(int n, List<Integer> allergic, List<Integer> poisonous) {
        // Write your code here
        answerCount=0;

        arr = new int[n+1];
        isVisited = new boolean[n+1];
        poisonous_r = new int[n+1];

        for(int i=1;i<n+1;i++){
            arr[i] = i;
        }

        for(int i=0;i<allergic.size();i++){
            poisonous_r[allergic.get(i)] = poisonous.get(i);
        }
        for(int i=1;i<n+1;i++){
            if(poisonous_r[i] ==0 ) poisonous_r[i] = -1;
        }

        BT("", 0, new HashSet<>());

        return answerCount;
    }

    static void BT(String str, int lastNum, HashSet<Integer> bacteria){
        if(str != "") answerCount++;

        for(int i=1;i<arr.length;i++){
            //포함검사
            boolean flag = false;
            for(int num : bacteria){
                if(poisonous_r[i] == num) flag = true;
                if(poisonous_r[num] == i) flag = true;
            }
            if(flag) continue;

            if(lastNum !=0){
                if(i - lastNum != 1 ) continue;
            }

            if(isVisited[i]) continue;
            isVisited[i] = true;
            bacteria.add(i);

            BT(str+i, i, bacteria);
            isVisited[i] = false;
            bacteria.remove(i);
        }
    }

}

public class k7 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        int allergicCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> allergic = IntStream.range(0, allergicCount).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine().replaceAll("\\s+$", "");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        int poisonousCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> poisonous = IntStream.range(0, poisonousCount).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine().replaceAll("\\s+$", "");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        long result = k7_Result.bioHazard(n, allergic, poisonous);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

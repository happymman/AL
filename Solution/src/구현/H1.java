package 구현;


import java.io.*;
import java.util.*;
import java.util.stream.*;
        import static java.util.stream.Collectors.joining;
        import static java.util.stream.Collectors.toList;



class H1_S {

    /*
     * Complete the 'maxCost' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY cost
     *  2. STRING_ARRAY labels
     *  3. INTEGER dailyCount
     */

    public static int maxCost(List<Integer> cost, List<String> labels, int dailyCount) {
        // Write your code here
        //int index =0 선언
        int index =0;
        //while(index < cost.size()) 다 돌면 종료
        int sum=0;
        int legalCount=0;
        int max=0;
        while(index<cost.size()){
            sum+=cost.get(index);
            if(labels.get(index).equals("legal")) legalCount++;
            if(legalCount == dailyCount){
                max = Math.max(max, sum);
                legalCount =0;
                sum = 0;
            }
        }
        return max;
    }

}

public class H1 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int costCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> cost = IntStream.range(0, costCount).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine().replaceAll("\\s+$", "");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        int labelsCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> labels = IntStream.range(0, labelsCount).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .collect(toList());

        int dailyCount = Integer.parseInt(bufferedReader.readLine().trim());

        int result = H1_S.maxCost(cost, labels, dailyCount);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}


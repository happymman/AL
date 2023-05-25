package 카카오브레인;
//
//public class k5 {
//}
//
//
//import java.io.*;
//        import java.math.*;
//        import java.security.*;
//        import java.text.*;
//        import java.util.*;
//        import java.util.concurrent.*;
//        import java.util.function.*;
//        import java.util.regex.*;
//        import java.util.stream.*;
//        import static java.util.stream.Collectors.joining;
//        import static java.util.stream.Collectors.toList;
//
//
//
//class Result {
//
//    /*
//     * Complete the 'predictAnswer' function below.
//     *
//     * The function is expected to return an INTEGER_ARRAY.
//     * The function accepts following parameters:
//     *  1. INTEGER_ARRAY stockData
//     *  2. INTEGER_ARRAY queries
//     */
//
//    public static List<Integer> predictAnswer(List<Integer> stockData, List<Integer> queries) {
//        // Write your code here
//        //비교
//        List<Integer> answer = new ArrayList<>();
//
//        for(int query : queries){
//            int stdDay = query;
//            int stdPrice = stockData.get(stdDay-1);
//
//            int diff=1;
//            boolean find = false;
//            boolean leftFlag = true;
//            boolean rightFlag = true;
//
//            while(true){
//                if(stdDay-1-diff < 0) leftFlag = false;
//                if(stdDay-1+diff >= stockData.size()) rightFlag = false;
//                if(leftFlag && stockData.get(stdDay-1-diff) < stdPrice){
//                    answer.add(stdDay-diff);
//                    find = true;
//                    break;
//                }else if(rightFlag && stockData.get(stdDay-1+diff) < stdPrice){
//                    answer.add(stdDay+diff);
//                    find = true;
//                    break;
//                }
//
//                if(!leftFlag && !rightFlag) break;
//
//                diff++;
//            }
//            if(!find) answer.add(-1);
//        }
//
//        return answer;
//    }
//
//}
//
//public class Solution {
//    public static void main(String[] args) throws IOException {
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
//
//        int stockDataCount = Integer.parseInt(bufferedReader.readLine().trim());
//
//        List<Integer> stockData = IntStream.range(0, stockDataCount).mapToObj(i -> {
//                    try {
//                        return bufferedReader.readLine().replaceAll("\\s+$", "");
//                    } catch (IOException ex) {
//                        throw new RuntimeException(ex);
//                    }
//                })
//                .map(String::trim)
//                .map(Integer::parseInt)
//                .collect(toList());
//
//        int queriesCount = Integer.parseInt(bufferedReader.readLine().trim());
//
//        List<Integer> queries = IntStream.range(0, queriesCount).mapToObj(i -> {
//                    try {
//                        return bufferedReader.readLine().replaceAll("\\s+$", "");
//                    } catch (IOException ex) {
//                        throw new RuntimeException(ex);
//                    }
//                })
//                .map(String::trim)
//                .map(Integer::parseInt)
//                .collect(toList());
//
//        List<Integer> result = Result.predictAnswer(stockData, queries);
//
//        bufferedWriter.write(
//                result.stream()
//                        .map(Object::toString)
//                        .collect(joining("\n"))
//                        + "\n"
//        );
//
//        bufferedReader.close();
//        bufferedWriter.close();
//    }
//}

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;



class k5_Result {

    /*
     * Complete the 'predictAnswer' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY stockData
     *  2. INTEGER_ARRAY queries
     */

    public static List<Integer> predictAnswer(List<Integer> stockData, List<Integer> queries) {
        // Write your code here
        //비교
        List<Integer> answer = new ArrayList<>();

        for(int query : queries){
            int stdDay = query;
            int stdPrice = stockData.get(stdDay-1);

            int leftDiff = 1;
            int rightDiff = 1;

            boolean leftFind = false;
            boolean rightFind = false;

            while(stdDay-1 -leftDiff != -1  && !leftFind){
                if(stockData.get(stdDay-1-leftDiff) < stdPrice){
                    leftFind = true;
                    break;
                }
                leftDiff++;
            }

            while(stdDay-1 +rightDiff != stockData.size() && !rightFind){
                if(stockData.get(stdDay-1+rightDiff) < stdPrice){
                    rightFind = true;
                    break;
                }
                rightDiff++;
            }

            if(leftFind == true && rightFind ==true){
                if(leftDiff<= rightDiff){
                    answer.add(stdDay - leftDiff);
                }else{
                    answer.add(stdDay + rightDiff);
                }
            }else if(leftFind == false && rightFind ==true){
                answer.add(stdDay + rightDiff);
            }else if(leftFind == true && rightFind ==false){
                answer.add(stdDay - leftDiff);
            }


            if(!leftFind && !rightFind) answer.add(-1);
        }

        return answer;
    }

}

public class k5 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int stockDataCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> stockData = IntStream.range(0, stockDataCount).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine().replaceAll("\\s+$", "");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        int queriesCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> queries = IntStream.range(0, queriesCount).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine().replaceAll("\\s+$", "");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        List<Integer> result = k5_Result.predictAnswer(stockData, queries);

        bufferedWriter.write(
                result.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}

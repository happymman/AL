package 구현;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class H_queensAttack2_Result {

    /*
     * Complete the 'queensAttack' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER k
     *  3. INTEGER r_q
     *  4. INTEGER c_q
     *  5. 2D_INTEGER_ARRAY obstacles
     */

    private static int[] dx = {-1,0,1,1,1,0,-1,-1};
    private static int[] dy = {1,1,1,0,-1,-1,-1,0};

    public static int queensAttack(int n, int k, int r_q, int c_q, List<List<Integer>> obstacles) {
        // Write your code here

        Map<String, String> obstaclesMap = new HashMap<>();
        for(int i=0;i<k;i++){
            String y = Integer.toString(obstacles.get(i).get(0)-1);
            String x = Integer.toString(obstacles.get(i).get(1)-1);
            obstaclesMap.put(x+"-"+y, "obstacle"); //"5-3"
        }

        int count=0;
        for(int i=0;i<8;i++){
            int moveX = dx[i];
            int moveY = dy[i];

            int X = c_q-1;
            int Y = r_q-1;
            while(true){
                X += moveX;
                Y += moveY;

                if(X<0 || X >= n || Y<0 || Y >=n ) break;

                String key = Integer.toString(X)+"-"+Integer.toString(Y);
                if(obstaclesMap.containsKey(key)) break;
                count++;
            }
        }
        return count;
    }

}

public class H_queensAttack2 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int k = Integer.parseInt(firstMultipleInput[1]);

        String[] secondMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int r_q = Integer.parseInt(secondMultipleInput[0]);

        int c_q = Integer.parseInt(secondMultipleInput[1]);

        List<List<Integer>> obstacles = new ArrayList<>();

        IntStream.range(0, k).forEach(i -> {
            try {
                obstacles.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int result = H_queensAttack2_Result.queensAttack(n, k, r_q, c_q, obstacles);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

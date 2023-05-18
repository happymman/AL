package 구현;



import java.io.*;
import java.util.*;
import java.util.stream.*;
        import static java.util.stream.Collectors.joining;
        import static java.util.stream.Collectors.toList;

class H_OrganizingContainersOfBalls_Result {

    /*
     * Complete the 'organizingContainers' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts 2D_INTEGER_ARRAY container as parameter.
     */

    public static String organizingContainers(List<List<Integer>> container) {


        List<Integer> containerTotal = new ArrayList<>();

        List<Integer> typeTotal = new ArrayList<>();

        for(int i=0;i<container.size();i++){
            int containerSum = 0;
            for(int j=0;j<container.get(0).size();j++){
                containerSum += container.get(i).get(j);
            }
            containerTotal.add(containerSum);
        }

        for(int i=0;i<container.size();i++){
            int typeSum = 0;
            for(int j=0;j<container.get(0).size();j++){
                typeSum += container.get(j).get(i);
            }
            typeTotal.add(typeSum);
        }

        Collections.sort(containerTotal);
        Collections.sort(typeTotal);

        System.out.println(containerTotal);
        System.out.println(typeTotal);

        if(containerTotal.equals(typeTotal)){
            return "Possible";
        }else{
            return "Impossible";
        }

    }

}

public class H_OrganizingContainersOfBalls {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, q).forEach(qItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                List<List<Integer>> container = new ArrayList<>();

                IntStream.range(0, n).forEach(i -> {
                    try {
                        container.add(
                                Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                        .map(Integer::parseInt)
                                        .collect(toList())
                        );
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });

                String result = H_OrganizingContainersOfBalls_Result.organizingContainers(container);

                bufferedWriter.write(result);
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}

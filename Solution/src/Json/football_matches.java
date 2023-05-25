
package Json;

        import javax.script.ScriptEngine;
        import javax.script.ScriptEngineManager;
        import javax.script.ScriptException;
        import java.io.IOException;
        import java.io.InputStream;
        import java.net.URL;
        import java.util.Scanner;

public class football_matches {
    public static void main(String[] args) {
        int year = 2022; // Set the year to search for
        int numDraws = getNumDraws(year);
        System.out.println("Number of draws: " + numDraws);
    }

    public static int getNumDraws(int year) {

        int totalDraws = 0;
        int goal = 0;

        try {
            while (true) {
                //url로부터 json읽어들이기
                String apiUrl = "https://jsonmock.hackerrank.com/api/football_matches" +
                        "?year=" + year +
                        "&team1goal="+goal+
                        "&team2goal="+goal;
                String json = readUrl(apiUrl);

                //script Engine setting
                ScriptEngineManager manager = new ScriptEngineManager();
                ScriptEngine engine = manager.getEngineByName("JavaScript");

                //json으로부터 데이터 추출
                engine.eval("var obj = " + json);
                int currentPage = (int) engine.eval("obj.page");
                int totalPages = (int) engine.eval("obj.total_pages");
                int dataLength = (int) engine.eval("obj.data.length");

                int total = (int) engine.eval("obj.total");
                totalDraws += total;

                goal++;
                if(goal == 11) break;
            }
        } catch (IOException | ScriptException e) {
            e.printStackTrace();
        }

        return totalDraws;
    }

    public static String readUrl(String urlString) throws IOException {
        try (InputStream inputStream = new URL(urlString).openStream();
             Scanner scanner = new Scanner(inputStream)) {
            scanner.useDelimiter("\\A"); //입력스트림의 처음부터 끝까지 한번에
            return scanner.hasNext() ? scanner.next() : "";
        }
    }
}



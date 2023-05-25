package Json;

//레퍼런스
//public class Main {
//    public static void main(String[] args) {
//        int year = 2022; // 검색할 년도 설정
//        int numDraws = getNumDraws(year);
//        System.out.println("비긴 경기 수: " + numDraws);
//    }
//
//    public static int getNumDraws(int year) {
//        int totalDraws = 0; //answer관련
//        현재페이지 //순회관련
//        총페이지 //순회관련

//        try {
//            while (true) {
//                String apiUrl = "https://jsonmock.hackerrank.com/api/football_matches?year=" + year + "&page=" + page;
//                String json = readUrl(apiUrl);
//
//                ScriptEngineManager manager = new ScriptEngineManager();
//                ScriptEngine engine = manager.getEngineByName("javascript");
//
//                engine.eval("var obj = " + json);
//                int currentPage = (int) engine.eval("obj.page");
//                int totalPages = (int) engine.eval("obj.total_pages");
//
//                int dataLength = (int) engine.eval("obj.data.length");
//                for (int i = 0; i < dataLength; i++) {
//                    String team1Goals = (String) engine.eval("obj.data[" + i + "].team1goals");
//                    String team2Goals = (String) engine.eval("obj.data[" + i + "].team2goals");
//
//                    if (team1Goals.equals(team2Goals)) {
//                        totalDraws++;
//                    }
//                }
//
//                if (currentPage == totalPages) {
//                    break;
//                }
//
//                page++;
//            }
//        } catch (IOException | ScriptException e) {
//            e.printStackTrace();
//        }
//
//        return totalDraws;
//    }
//
//    public static String readUrl(String urlString) throws IOException {
//        try (InputStream inputStream = new URL(urlString).openStream();
//             Scanner scanner = new Scanner(inputStream)) {
//            scanner.useDelimiter("\\A");
//            return scanner.hasNext() ? scanner.next() : "";
//        }
//    }
//}

public class winner_goal {


}


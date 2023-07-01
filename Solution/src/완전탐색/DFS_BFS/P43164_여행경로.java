package 완전탐색.DFS_BFS;

import java.util.*;

public class P43164_여행경로{
    static boolean[] isVisited;
    static ArrayList<String> allRoutes = new ArrayList<>();

    public String[] solution(String[][] tickets){
        isVisited = new boolean[tickets.length];

        dfs("ICN", "ICN", tickets, 0);
        Collections.sort(allRoutes);
        return allRoutes.get(0).split(" ");
    }

    private static void dfs(String start, String route, String[][] tickets, int count){
        if(count==tickets.length){
            allRoutes.add(route);
            return;
        }

        for(int i=0;i<tickets.length;i++){
            if(!tickets[i][0].equals(start)) continue;
            if(isVisited[i]) continue;

            isVisited[i] = true;
            dfs(tickets[i][1], route+" "+tickets[i][1], tickets, count+1);
            isVisited[i] = false;
        }
    }
}

// 2차 풀이
//import java.util.*;
//
//public class Solution{
//
//    private static List<String> routes = new ArrayList<>();
//    private static boolean[] isVisited;
//
//    public String[] solution(String[][] tickets){
//
//        isVisited = new boolean[tickets.length];
//
//        dfs("ICN", "ICN", 0, tickets);
//
//        Collections.sort(routes);
//        return routes.get(0).split(" ");
//    }
//
//    private static void dfs(String start, String route, int count, String[][] tickets){
//        if (count == tickets.length){
//            routes.add(route);
//            return;
//        }
//
//        for(int i=0;i<tickets.length;i++){
//            String[] ticket = tickets[i];
//
//            if(isVisited[i]) continue;
//            if(!ticket[0].equals(start)) continue;
//            isVisited[i] = true;
//
//            dfs(ticket[1], route+" "+ticket[1], count+1, tickets);
//            isVisited[i] = false;
//        }
//    }
//}
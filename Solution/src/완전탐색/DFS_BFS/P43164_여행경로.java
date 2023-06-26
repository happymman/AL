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
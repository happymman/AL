package 그리디;

import java.util.Arrays;
        import java.util.Comparator;

class P42884_단속카메라 {
    public int solution(int[][] routes) {
        Arrays.sort(routes, Comparator.comparingInt(route->route[1]));
        boolean[] isVisited = new boolean[routes.length];

        int camera =0;
        for(int i=0;i<routes.length;i++){
            if(isVisited[i]) continue;
            isVisited[i] = true;

            for(int j=0 ; j<routes.length;j++){
                if(routes[j][0]<=routes[i][1]) isVisited[j] = true;
            }
            camera++;
        }
        return camera;
    }
}
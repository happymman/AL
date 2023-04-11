package 구현;

public class P120875_평행 {
    public double getSlope(int[] dot1, int[] dot2){
        return (double) (dot2[1]-dot1[1]) / (dot2[0]-dot1[0]);
    }

    public int solution(int[][] dots) {
        if(getSlope(dots[0], dots[1]) == getSlope(dots[2], dots[3])) return 1;
        if(getSlope(dots[0], dots[2])==getSlope(dots[1], dots[3])) return 1;
        if(getSlope(dots[0], dots[3])==getSlope(dots[1], dots[2])) return 1;

        return 0;
    }
}
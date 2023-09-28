package 엔씨;

public class n1 {
    public static void main(String[] args) {
        n1_Solution s = new n1_Solution();
        int[][] result = s.solution(8,6,new int[]{1,-1,-1}, new int[][]{{1,1},{2,2},{4,4}});
        System.out.println();
    }
}

class n1_Solution {
    static int[][] map;
    static int n;
    static int m;
    public int[][] solution(int nParam, int mParam, int[] folds, int[][] cuts){
        n=nParam;
        m=mParam;

        map = new int[n][m]; //map을 n,m으로 초기화

        for(int fold : folds){ //접는 방법 선택
            if(fold==1){ //가로접기
                m = m/2;
            }else{ //세로접기
                n = n/2;
            }
        }
        // 접어서 만들어진 최종map true초기화
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                map[i][j]=1;
            }
        }

        makeHole(cuts);

        //다시 펴기
        for(int i=folds.length-1;i>=0;i--){ //펴는 방법 선택
            int fold = folds[i];
            if(fold==1){ //가로펴기
                가로펴기();
            }else{ //세로펴기
                세로펴기();
            }
        }

        return map;
    }

    static void makeHole(int[][] cuts){

        for(int[] cut : cuts){ //구멍선택
            int x = cut[0]-1;
            int y = cut[1]-1;

            if(x<0||y<0||x>=n||y>=m) continue; //범위검사
            map[x][y] = 0;
        }

    }

    static void 세로펴기(){
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                map[2*n-1-i][j] = map[i][j];
            }
        }

        n*=2; //세로길이 두배 증가
    }

    static void 가로펴기(){
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                map[i][2*m-1-j] = map[i][j];
            }
        }

        m*=2; //가로길이 두배 증가
    }
}

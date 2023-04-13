package 분할정복;

class P68936_쿼드압축후개수세기 {
    private static int zero =0;
    private static int one =0;

    private static void partition(int row, int col, int size, int[][] arr){
        if(isAllSame(row, col, size, arr)){
            if(arr[row][col]==0){
                zero++;
            }else{
                one++;
            }
            return;
        }

        int newSize=size/2;
        partition(row, col, newSize, arr);
        partition(row+newSize, col, newSize, arr);
        partition(row, col+newSize, newSize, arr);
        partition(row+newSize, col+newSize, newSize, arr);
    }

    private static boolean isAllSame(int row, int col, int size, int[][] arr){
        int std = arr[row][col];
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(arr[row+i][col+j] != std) return false;
            }
        }
        return true;
    }

    public int[] solution(int[][] arr) {
        partition(0,0,arr.length, arr);
        int[] answer = {zero, one};
        return answer;
    }
}

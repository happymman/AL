package 구현;

public class P83201_상호평가 {

    public String solution(int[][] scores) {
        String grade = "";
        for(int j=0;j<scores.length;j++){
            int max = 0;
            int min = Integer.MAX_VALUE;
            int max_count=0;
            int min_count=0;

            int total=0;
            int avg = 0;
            boolean flag = false;
            for(int i=0;i<scores.length;i++){
                if(scores[i][j] > max){
                    max=scores[i][j];
                    max_count =1;
                }else if(scores[i][j] == max){
                    max_count++;
                }

                if(scores[i][j] < min){
                    min=scores[i][j];
                    min_count =1;
                }else if(scores[i][j] == min){
                    min_count++;
                }

                total+=scores[i][j];
            }

            if(scores[j][j] == max && max_count==1 || scores[j][j] == min && min_count==1){
                flag = true;
            }

            if(flag){
                avg = (total-scores[j][j])/(scores.length-1);
            }else{
                avg = total/scores.length;
            }

            if(avg >=90){
                grade +='A';
            }else if(avg >=80){
                grade +='B';
            }else if(avg >=70){
                grade +='C';
            }else if(avg >=50){
                grade +='D';
            }else{
                grade +='F';
            }
        }
        return grade;
    }
}

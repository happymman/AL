package 완전탐색;

public class P42842_카펫{
    public int[] solution(int brown, int yellow){
        int whole = brown+yellow;
        int[] answer = new int[2];

        for(int width = whole; width>=1 ; width--){
            if(whole%width ==0){
                int y_width = width-2;
                int y_height = (whole/width)-2;
                if(y_width * y_height == yellow){
                    answer[0] = width;
                    answer[1] = whole/width;
                    return answer;
                }
            }
        }
        return answer;
    }
}

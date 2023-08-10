package 그리디;

import java.text.SimpleDateFormat;
        import java.util.*;

public class P17676_추석트래픽  {
    public static int solution(String[] lines) throws Exception {

        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss.SSS");

        int max=0;
        for(int i=0;i<lines.length;i++){
            String[] stdLog = lines[i].split(" ");

            Date stdEnd = format.parse(stdLog[1]);
            long stdEndMily = stdEnd.getTime();

            int count=0;
            for(int j=i; j<lines.length;j++){
                String[] testLog = lines[j].split(" ");

                Date testEnd = format.parse(testLog[1]);
                double testProcessing = Double.parseDouble(testLog[2].substring(0, testLog[2].length()-1)); //substring으로 맨끝자르기
                long nextStartMily = (long)(testEnd.getTime() - testProcessing*1000+1);


                //시작시간이 기준종료시간+1000미만
                if(nextStartMily < stdEndMily+1000){ //1초동안 = next시작시간이 기준종료시간+0.999초이하에 있다.
                    count++;
                    max = Math.max(max, count); //max값 갱신
                }
            }
        }

        return max;

    }
}
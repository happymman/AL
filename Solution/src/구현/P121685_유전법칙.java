package 구현;
import java.util.*;

public class P121685_유전법칙 {
    public static void main(String[] args) {
        P121685_유전법칙_Solution s = new P121685_유전법칙_Solution();
        s.solution(new int[][]{{4, 17}, {4, 18}, {4, 19}, {4, 20}, {4, 21}, {4, 22}, {4, 23}, {4, 24}});
    }
}
class P121685_유전법칙_Solution {
    static int curGen=0;
    static int curIdx=0;
    static int gen=0;
    static List<int[]> infos = new ArrayList<>();

    public String[] solution(int[][] queries) {

        String[] result = new String[queries.length];
        for(int i=0;i<queries.length;i++){ //쿼리선택
            int[] query = queries[i];

            //curGen, curIdx, gen 초기화
            curGen = query[0];
            curIdx = query[1];
            gen = query[0];

            down(curGen, curIdx);
            String answer = up();
            result[i] = answer;
            infos.clear();
        }

        return result;
    }

    static void down(int curGen, int curIdx){
        infos.add(new int[]{curGen, curIdx});

        if(curGen==2) return;
        curGen--;
        curIdx = (curIdx-1)/4+1;
        down(curGen, curIdx);
    }

    static String up(){
        for(int i=infos.size()-1;i>=0;i--){
            int[] info = infos.get(i);
            int num = (info[1]-1)%4; //num : 몇번째에 위치했는지

            switch(num){
                case 0 : return "RR";
                case 1,2 :
                    if(info[0] == gen) return "Rr";
                    else continue;
                case 3 : return "rr";
            }
        }
        return "";
    }
}

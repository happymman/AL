package 구현;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P150370_개인정보수집유효기간 {
    public static void main(String[] args) {
        P150370_개인정보수집유효기간_Solution s = new P150370_개인정보수집유효기간_Solution();
        String today ="2022.05.19";
        String today2 = "2020.01.01";

        String[] terms = {"A 6", "B 12", "C 3"};
        String[] terms2 = {"Z 3", "D 5"};

        String[] privacies = {"2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"};
        String[] privacies2 = {"2019.01.01 D", "2019.11.15 Z", "2019.08.02 D", "2019.07.01 D", "2018.12.28 Z"};

        int[] result = s.solution(today2, terms2, privacies2);
        System.out.println(result);
    }
}

// 1차 비효율적 풀이
//class P150370_개인정보수집유효기간_Solution {
//    static Map<String, Integer> termsMap = new HashMap<>();
//
//    public int[] solution(String todayStr, String[] terms, String[] privacies) {
//
//        int[] todayDate = changeDate(todayStr);
//
//        for(String term : terms){
//            termsMap.put(term.split(" ")[0], Integer.parseInt(term.split(" ")[1]));
//        }
//
//        List<Integer> result = new ArrayList<>();
//        for(int i=0;i<privacies.length;i++){
//            String privacy = privacies[i];
//
//            //시작날짜 구하기
//            String testDateStr = privacy.split(" ")[0];
//            String type = privacy.split(" ")[1];
//
//            int[] testDate = changeDate(testDateStr);
//
//            //만료날짜 구하기
//            //termsMap에서 String으로 찾기
//            int valid = termsMap.get(type); //유효기간(월단위)구하기
//
//            testDate[1] += valid; //month에 더하고
//            testDate[2]--; //day는 빼고
//
//            if(testDate[2] == 0){
//                testDate[2] = 28;
//                testDate[1]--;
//            }
//
//            if(testDate[1] >= 13){
//                int extra;
//                if(testDate[1]%12==0){
//                    extra = (testDate[1]/12-1);
//                }else{
//                    extra = (testDate[1]/12);
//                }
//
//                testDate[0] += extra; //year++
//                testDate[1] -= extra*12; //month -=12;
//            }
//
//            //유효여부 확인
//            if(!isValid(todayDate, testDate)){
//                result.add(i+1);
//            }
//        }
//        return result.stream().mapToInt(Integer::intValue).toArray();
//    }
//
//    static int[] changeDate(String date){
//
//        int year = Integer.parseInt(date.split("\\.")[0]);
//        int month = Integer.parseInt(date.split("\\.")[1]);
//        int day = Integer.parseInt(date.split("\\.")[2]);
//
//        return new int[]{year, month, day};
//    }
//
//    static boolean isValid(int[] std, int[] test){
//        /*
//        today보다 <면 제외 return false;
//        >면 무조건 통과
//        =면 month테스트
//
//        month <면 return false;
//        >면 무조건통과
//        =면 day테스트
//         */
//
//        if(std[0] > test[0]){ //2021, 2022
//            return false;
//        }else if(std[0] < test[0]){
//            return true;
//        }else if(std[0] == test[0]){
//            if(std[1] > test[1]){
//                return false;
//            }else if(std[1] < test[1]){
//                return true;
//            }else if(std[1] == test[1]){
//                if(std[2] > test[2]){
//                    return false;
//                }else{
//                    return true;
//                }
//            }
//        }
//
//        return false; //없어도 되는
//    }
//}

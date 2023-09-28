package 구현.카카오;

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

class P150370_개인정보수집유효기간_Solution {
    static Map<String, Integer> termsMap = new HashMap<>();

    public int[] solution(String todayStr, String[] terms, String[] privacies) {

        int todayDate = getDate(todayStr); //todayStr을 파싱해서 몇day인지 return(일 : 최소단위)

        for(String term : terms){
            termsMap.put(term.split(" ")[0], Integer.parseInt(term.split(" ")[1]));
        }

        List<Integer> result = new ArrayList<>();
        for(int i=0;i<privacies.length;i++){
            String privacy = privacies[i];

            //시작날짜 구하기
            String testDateStr = privacy.split(" ")[0];
            String type = privacy.split(" ")[1];

            int testDate = getDate(testDateStr);
            int valid = termsMap.get(type); //유효기간(월단위)구하기

            testDate = testDate+valid*28-1;

            //유효여부 확인
            if(todayDate > testDate){
                result.add(i+1);
            }
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    static int getDate(String date){
        int year = Integer.parseInt(date.split("\\.")[0]);
        int month = Integer.parseInt(date.split("\\.")[1]);
        int day = Integer.parseInt(date.split("\\.")[2]);

        int totalDay = year *28*12+month*28+day;
        return totalDay;
    }

}

/*
2차 풀이

import java.util.*;

class Solution {
    static Map<String, Integer> terms = new HashMap<>();
    public int[] solution(String todayStr, String[] termsStr, String[] privacies) {
        //terms 해시맵 등록
        for(String termStr : termsStr){
            String key = termStr.split(" ")[0];
            int value = Integer.parseInt(termStr.split(" ")[1]);
            terms.put(key, value);
        }

        //todayStr -> day로 변환
        int today = getDate(todayStr);

        List<Integer> answer = new ArrayList<>();

        for(int i=0;i<privacies.length;i++){
            String privacy = privacies[i];

            //testStr -> day로 변환
            int testDay = getDate(privacy.split(" ")[0]);

            //약관 terms에서 찾아서 -> testDay+28*month-1
            int valid = terms.get(privacy.split(" ")[1]);
            testDay = testDay+28*valid-1;

            if(testDay < today){
                answer.add(i+1);
            }
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();

    }

    static int getDate(String dateStr){
        int year = Integer.parseInt(dateStr.split("\\.")[0]);
        int month = Integer.parseInt(dateStr.split("\\.")[1]);
        int day = Integer.parseInt(dateStr.split("\\.")[2]);

        return year*12*28+month*28+day;
    }
}
 */

/*
3차 풀이
유효기간, ~동안 -> 그림(시작일+만료일 포함)
split(".") -> split("\\.")

import java.util.*;

class Solution {
    static Map<String, Integer> termsMap=new HashMap<>();
    public int[] solution(String todayStr, String[] termsStr, String[] privacies) {

        //terms 등록
        for(String termStr : termsStr){
            String 약관 = termStr.split(" ")[0];
            int term = Integer.parseInt(termStr.split(" ")[1]);
            termsMap.put(약관, term);
        }

        int today = getDate(todayStr); //today 파싱

        List<Integer> result=new ArrayList<>();

        for(int i=0;i<privacies.length;i++){ //약관 선택
            String privacy = privacies[i];
            int start = getDate(privacy.split(" ")[0]);// 약관 파싱 -> 2021.05.02 A
            String term = privacy.split(" ")[1];
            int 유효기간 = termsMap.get(term)*28;

            int 만료날짜 = start+유효기간-1;

            //today랑 비교
            if(today>만료날짜) result.add(i+1); //만료 -> 인덱스 등록
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    static int getDate(String dateStr){
        int year=Integer.parseInt(dateStr.split("\\.")[0]);
        int month = Integer.parseInt(dateStr.split("\\.")[1]);
        int day = Integer.parseInt(dateStr.split("\\.")[2]);
        return year*28*12+month*28+day;
    }

}


4차풀이 - 22m

import java.util.*;

class Solution {
    static Map<String, Integer> termsMap=new HashMap<>();
    public int[] solution(String todayStr, String[] termsStr, String[] privacies) {

        //terms 등록
        for(String termStr : termsStr){
            String 약관 = termStr.split(" ")[0];
            int term = Integer.parseInt(termStr.split(" ")[1]);
            termsMap.put(약관, term);
        }

        int today = getDate(todayStr); //today 파싱

        List<Integer> result=new ArrayList<>();

        for(int i=0;i<privacies.length;i++){ //약관 선택
            String privacy = privacies[i];
            int start = getDate(privacy.split(" ")[0]);// 약관 파싱 -> 2021.05.02 A
            String term = privacy.split(" ")[1];
            int 유효기간 = termsMap.get(term)*28;

            int 만료날짜 = start+유효기간-1;

            //today랑 비교
            if(today>만료날짜) result.add(i+1); //만료 -> 인덱스 등록
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    static int getDate(String dateStr){
        int year=Integer.parseInt(dateStr.split("\\.")[0]);
        int month = Integer.parseInt(dateStr.split("\\.")[1]);
        int day = Integer.parseInt(dateStr.split("\\.")[2]);
        return year*28*12+month*28+day;
    }

}


import java.util.*;

class Solution {
    static Map<String, Integer> termsMap = new HashMap<>();
    public int[] solution(String todayStr, String[] termsStr, String[] privacies) {

        int today = getDate(todayStr); //todayStr변환하기

        for(String termStr : termsStr){ //term선택
            String key = termStr.split(" ")[0]; //split(" ")해서 termsMap에 등록하기
            int value = Integer.parseInt(termStr.split(" ")[1]);
            termsMap.put(key, value);
        }

        List<Integer> result = new ArrayList<>();
        for(int i=0;i<privacies.length;i++){ //privacy선택
            String privacy = privacies[i];

            int 시작날짜 = getDate(privacy.split(" ")[0]); //split(" ")해서
            int 유효기간 = termsMap.get(privacy.split(" ")[1])*28; //에서 찾기
            int 만료날짜 = 시작날짜+유효기간-1;

            if (today > 만료날짜) result.add(i+1);//만료
        }

        return result.stream().mapToInt(Integer::intValue).toArray();

    }

    static int getDate(String dateStr){
        int year = Integer.parseInt(dateStr.split("\\.")[0]);
        int month = Integer.parseInt(dateStr.split("\\.")[1]);
        int day = Integer.parseInt(dateStr.split("\\.")[2]);

        return year*12*28+month*28+day;
    }



}

 */


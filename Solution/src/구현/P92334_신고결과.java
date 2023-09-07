package 구현;
import java.util.*;
public class P92334_신고결과 {
    public static void main(String[] args) {
        P92334_신고결과Solution s = new P92334_신고결과Solution();
        s.solution(new String[]{"muzi", "frodo", "apeach", "neo"},	new String[]{"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"},	2);
    }
}
/*
1차풀이 - 1h

코드쓰기 - 변수 - naming - Map -> 키map(map, m) ex : reporterMap, reportedMap
코드쓰기 - Map생성 - Value가 Collection-> 먼저 모든키 put - 상황 : 모든키 사전에 알고있을때
                                      키포함여부검사+Collection생성후 put - 상황 : 모든키 사전에 알수 없을때


문제상황 : 출력오류 - 출력값 순서만 다름 -> map, set에서 무작위로 값을 추출했는지 확인

 */
class P92334_신고결과Solution {
    public int[] solution(String[] id_list, String[] reports, int k) {

        Map<String, Set<String>> reporterMap = new HashMap<>(); //맵 생성
        Map<String, Integer> reportedMap = new HashMap<>(); //reported맵 생성
        for(String id : id_list){
            reporterMap.put(id, new HashSet<>());
        }
        List<String> stoppedList = new ArrayList<>(); //stopped 집합 생성

        for(String report : reports){ //report선택
            String reporter = report.split(" ")[0]; //해서 나누기 -> reporter reported
            String reported = report.split(" ")[1]; //해서 나누기 -> reporter reported

            if(!reporterMap.get(reporter).contains(reported)){ //중복 신고 아닌 경우에만
                reporterMap.get(reporter).add(reported);
                reportedMap.put(reported, reportedMap.getOrDefault(reported,0)+1);
                if(reportedMap.get(reported) == k) stoppedList.add(reported);//기준개수 넘으면 stopped집합에 add
            }
        }

        List<Integer> answer=new ArrayList<>();
        for(Set<String> set : reporterMap.values()){ //신고자 선택
            int count=0; //신고자별 유효신고 개수
            for(String stopped : stoppedList){
                if(set.contains(stopped)) count++;
            }
            answer.add(count);
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();


    }
}
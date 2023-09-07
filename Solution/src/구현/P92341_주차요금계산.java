package 구현;

import java.util.*;
public class P92341_주차요금계산 {
    public static void main(String[] args) {
        P92341_주차요금계산_Solution s = new P92341_주차요금계산_Solution();
        int[] result = s.solution(new int[]{180, 5000, 10, 600},	new String[]{"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"});
        System.out.println();
    }
}

/*
1차풀이 - 질문게시판 보고 해결
런타임에러 원인 : map.keySet순회하는 도중의 map.remove()
 */
class P92341_주차요금계산_Solution {
    static int stdTime; //기준시간
    static int stdFee; //기준가격;
    static int unitTime; //단위시간;
    static int unitFee; //단위요금;

    public int[] solution(int[] fees, String[] records) {
        //fees분해
        stdTime=fees[0];
        stdFee=fees[1];
        unitTime=fees[2];
        unitFee=fees[3];

        Map<Integer, Integer> carIn = new HashMap<>();//recordMap 생성
        Map<Integer, Integer> carTime = new TreeMap<>();//recordMap 생성
        List<Integer> answer= new ArrayList<>(); //계산서리스트

        for(String record : records){ //레코드 선택
            String[] arr = record.split(" ");
            int time = parseTime(arr[0]);
            int carNum = Integer.parseInt(arr[1]);
            String type = arr[2];

            if(type.equals("IN")){
                //등록
                carIn.put(carNum, time); //차 들어온시간map에 put
            }else{ //OUT
                int in = carIn.get(carNum); //입차시간 가져오기 - 출차는 항상 입차뒤에 이루어지기때문에 해당메써드에서 NullpointerException이 발생할 수는 없음.
                carIn.remove(carNum);
                carTime.put(carNum, carTime.getOrDefault(carNum,0)+time-in); //기존주차시간+이번 주차시간(time-in)
            }
        }

        //출차없던 애들 계산
        for(int carNum : carIn.keySet()){
            int in = carIn.get(carNum); //입차시간 가져오기 - 현재 안에있는 차를 대상으로 이루어지기때문에 nullPointerException가능성X
            carIn.remove(carNum);
            carTime.put(carNum, carTime.getOrDefault(carNum,0)+parseTime("23:59")-in); //기존주차시간+이번 주차시간(time-in)
        }

        //주차시간 총합을 한꺼번에 계산
        for(int time : carTime.values()){
            answer.add(calculateFee(time));
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    static int parseTime(String timeStr){
        int hour = Integer.parseInt(timeStr.split(":")[0]);
        int min = Integer.parseInt(timeStr.split(":")[1]);
        return hour*60+min;
    }

    static int calculateFee(int time){
        if(time <= stdTime){
            return stdFee;
        }else{
            return stdFee+((time-1-stdTime)/unitTime+1)*unitFee;
        }
    }
}
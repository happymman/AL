package 스택큐;

//import java.util.*;
//
//public class P42584_주식가격 {
//    public int[] solution(int[] prices) {
//
//        Stack<Integer> s = new Stack<>();
//        int[] answer = new int[prices.length];
//
//        for(int i=0;i<prices.length;i++){
//            while(!s.isEmpty() && prices[i] < prices[s.peek()]){
//                answer[s.peek()] = i-s.peek();
//                s.pop();
//            }
//            s.push(i);
//        }
//
//        while(!s.isEmpty()){
//            answer[s.peek()] = prices.length - s.peek()-1;
//            s.pop();
//        }
//
//        return answer;
//    }
//}




import java.util.*;
class P42584_주식가격_Solution {
    public static void main(String[] args) {
        P42584_주식가격 s = new P42584_주식가격();
        int[] prices = new int[]{1,2,3,2,3};
        System.out.println(s.solution(prices));
    }
}
public class P42584_주식가격 {
    static class Stock{
        int second;
        int price;

        public Stock(int second, int price){
            this.second = second;
            this.price = price;
        }
    }

    public int[] solution(int[] prices) {

        //스택 생성
        Stack s = new Stack<Stock>();

        int[] answer = new int[prices.length];

        int currentTime=0;
        for(int i=0;i<prices.length;i++){
            currentTime = i+1;
            int nowPrice = prices[i];

            while(!s.isEmpty() ){
                Stock peekStock = (Stock) s.peek();
                if(peekStock.price > nowPrice){
                    int notFallTime = currentTime - peekStock.second;
                    answer[peekStock.second-1] = notFallTime;
                    s.pop();
                }
            }
            s.push(new Stock(currentTime, nowPrice));
        }

        while(!s.isEmpty()){
            Stock restStock = (Stock) s.pop();
            int notFallTime = currentTime - restStock.second;

            answer[restStock.second-1] = notFallTime;
        }

        return answer;

    }
}
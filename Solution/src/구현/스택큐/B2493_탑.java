package 구현.스택큐;

import java.util.Scanner;
import java.util.Stack;

public class B2493_탑 {

    static class Top { // 탑에 대한 정보
        int num; // 탑의 번호
        int height; // 탑의 높이

        Top(int num, int height) {
            this.num = num;
            this.height = height;
        }
    }

    public static void main(String[] args) {

        //n입력받기
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        StringBuilder answer = new StringBuilder();

        Stack<Top> s = new Stack<>();

        for(int i=1;i<=n;i++){ //
            int height = sc.nextInt();

            while(!s.isEmpty() && s.peek().height < height){ //값이 작으면
                s.pop();
            }

            //같은거나 큰거 만났거나, 못만났거나
            answer.append(s.isEmpty() ? "0 " : s.peek().num+" ");//에 등록

            s.push(new Top(i, height));

        }

        System.out.println(answer.toString().trim());

    }
}

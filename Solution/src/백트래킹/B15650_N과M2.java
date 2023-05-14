package 백트래킹;

import java.util.ArrayList;
import java.util.Scanner;

public class B15650_N과M2 {

    static boolean[] isVisited;
    static ArrayList<String> answers = new ArrayList<>();
    static int N;
    static int M;
    public static void main(String[] args) {
        //N M 입력받기
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        //N+1만큼 방문배열 만들기
        isVisited = new boolean[N+1];

        //BT(0, "")돌기기
        BT(0, "", 0);

        for(String answer : answers){
            System.out.println(answer);
        }
    }

    static void BT(int index, String letter, int lastNum){
        //길이가 M이면 출력배열에 추가하고 return
        if(index == M){
            answers.add(letter);
            return;
        }

        //for문으로 방문배열 돌면서 하나 선택
        for(int i=1;i<=N;i++){
            if(isVisited[i] || i<lastNum) continue;
            isVisited[i] = true;

            if(index == 0){
                BT(index+1, letter+i, i);
            }else{
                BT(index+1, letter+" "+i, i);
            }
            isVisited[i] = false;
        }
        //그 숫자에 방문처리
        //BT(index+1, letter + " 그숫자")
        //그 숫자 방문처리 해제
    }
}

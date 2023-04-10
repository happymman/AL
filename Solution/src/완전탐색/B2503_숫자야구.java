package 완전탐색;

import java.io.*;
import java.util.HashSet;
import java.util.StringTokenizer;

public class B2503_숫자야구 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        int[] numCalled = new int[n]; //123
        int[] strike = new int[n]; //1
        int[] ball = new int[n]; // 1

        for(int i=0;i<n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            numCalled[i] = Integer.parseInt(st.nextToken());
            strike[i] = Integer.parseInt(st.nextToken());
            ball[i] = Integer.parseInt(st.nextToken());
        }

        int count = 0;
        for(int i = 123; i<=987;i++){
            //완탐 - 탐색조건
            HashSet<Integer> number = new HashSet<>();
            int hundred = i/100;
            int ten = i%100/10;
            int one = i%10;

            number.add(hundred);
            number.add(ten);
            number.add(one);
            if(number.size() !=3) continue; //중복숫자 존재

            boolean correct = true;
            int numCalled_strike =0;
            int numCalled_ball = 0;

            //완탐 - 조건확인
            for(int j=0 ; j<n;j++) {
                numCalled_strike =0;
                numCalled_ball = 0;

                int numCalled_hundred = numCalled[j] / 100;
                int numCalled_ten = numCalled[j] % 100 / 10;
                int numCalled_one = numCalled[j] % 10;

                if (numCalled_hundred == hundred || numCalled_ten == ten || numCalled_one == one) numCalled_strike++; //147
                if (numCalled_hundred == ten || numCalled_hundred == one) numCalled_ball++;
                if (numCalled_ten == hundred || numCalled_ten == one) numCalled_ball++;
                if (numCalled_one == hundred || numCalled_one == ten) numCalled_ball++;

                if (numCalled_strike != strike[j] || numCalled_ball != ball[j]){
                    correct = false;
                    break;
                }
            }
            if(correct) count++; //true로 남아 있다면
        }
        bw.write(String.valueOf(count));
        bw.flush();
        br.close();
        bw.close();
    }
}

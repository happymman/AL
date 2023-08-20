package 해시;

import java.util.HashMap;
import java.util.*;
import java.io.*;

/*
public class B9375_패션왕신해빈 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while(T-->0) {
            HashMap<String, Integer> map = new HashMap<String, Integer>();

            int N = Integer.parseInt(br.readLine());
            while(N-->0) {
                String[] type = br.readLine().split(" ");
                map.put(type[1], map.getOrDefault(type[1], 0)+1);
            }

            Iterator<Integer> it = map.values().iterator();

            int result = 1;
            while(it.hasNext()) {
                result *= it.next().intValue() + 1;
            }

            sb.append((result-1) + "\n");
        }

        System.out.println(sb);
    }
}
 */


public class B9375_패션왕신해빈 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        //N입력받기
        int N = Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++){
            Map<String, Integer> fashion = new HashMap<>();
            int M = Integer.parseInt(br.readLine());// 입력받기
            for(int j=0;j<M;j++){
                st = new StringTokenizer(br.readLine());
                st.nextToken();
                String type = st.nextToken();

                fashion.put(type, fashion.getOrDefault(type, 0)+1);
            }

           int result=1;
            for(int value : fashion.values()){
                result *= (value+1);
            }
            System.out.println(result-1); //출력 아에 안입는 경우
        }
    }
}
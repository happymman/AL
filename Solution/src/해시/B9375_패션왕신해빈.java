package 해시;

import java.util.HashMap;
import java.util.*;
import java.io.*;

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


package 그리디;

import java.util.Arrays;
import java.util.Scanner;

public class B11399_ATM {
    public void solution(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] times = new int[n];
        for(int i=0;i<times.length;i++){
            times[i] = sc.nextInt();
        }

        Arrays.sort(times);
        int sum=0;
        for(int i=0;i<times.length;i++){
            sum+= times[i]*(times.length - i);
        }
        System.out.println(sum);
    }
}

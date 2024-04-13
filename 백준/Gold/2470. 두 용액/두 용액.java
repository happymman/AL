

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] nums;
    public static void main(String[] args) throws IOException{
        input();
        pro();
    }

    static void input() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        nums = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void pro(){
        Arrays.sort(nums); //O(NlogN) - 10^5 * 5
        int s=0;
        int e=nums.length-1;
        int minV = Integer.MAX_VALUE;
        int[] result = new int[2];

        while(s < e){
            int sum = nums[s]+nums[e];
            if(Math.abs(sum) < minV){
                result = new int[]{nums[s], nums[e]};
                minV = Math.abs(sum);
            }

            if(sum<0) s++;
            else if(sum==0){
                s++;
                e--;
            }else{
                e--;
            }
        }

        System.out.println(result[0]+" "+result[1]);
    }
}

/*
    테케
    1.
    2.
    3.1
 */
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[] ans = new int[N];
        Arrays.fill(ans, -1);
        Stack<Integer> s = new Stack<>();

        int[] nums = new int[N];
        for(int i=0;i<N;i++) {
            nums[i] = sc.nextInt();
        }

        for(int i=0;i<nums.length;i++) {
            int now = nums[i];

            while(!s.isEmpty() && nums[s.peek()] < now){
                ans[s.pop()] = now;
            }
            s.push(i);
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<ans.length;i++){
            sb.append(ans[i]).append(' ');
        }
        System.out.println(sb);
    }
}

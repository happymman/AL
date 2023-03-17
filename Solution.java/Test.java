package 
import java.util.*;

class Test{
    public static void main(){
            Scanner s = new Scanner(system.in);
            int n = sc.next();
            int[] arr;
            for(int i=0;i<n; i++){
                arr[i] = sc.nextInt();
            }
            Arrays.sort(arr);
            int sum;
            for(int i=0; i<arr.length; i++){
                sum = arr[i]*(arr.length-(i+1));
            }
            System.out.prinln(sum);
    }
}


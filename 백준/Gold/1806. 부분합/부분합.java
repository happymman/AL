

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int S;
    static int[] a;
    public static void main(String[] args) throws IOException{
        input();
        pro();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine()); //Integer.parseInt(st.nextToken())
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        a = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i< N; i++){
            a[i] = Integer.parseInt(st.nextToken());
            if(a[i] >= S){
                System.out.println(1);
                System.exit(0);
            }
        }
    }

    static void pro(){
        int s=0;
        int e=1;
        long sum=a[s]+a[e];

        int minV = Integer.MAX_VALUE;
        while(s!=e){
            if(sum >= S){
                minV = Math.min(minV, e-s+1);
                sum -= a[s];
                s++;
            }else{
                e++;
                if(e==a.length) break;
                sum += a[e];
            }
        }
        System.out.println(minV!=Integer.MAX_VALUE ? minV : 0);
    }
}

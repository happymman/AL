package 구현.스택큐;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class P10828_스택 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //N입력받기
        int N = parseInt(st.nextToken());
        List<Integer> stack = new ArrayList<>();

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            switch (command){
                case "push" :
                    int push = Integer.parseInt(st.nextToken());
                    stack.add(push);
                    break;
                case "pop" :
                    if(stack.isEmpty()){
                        System.out.println(-1);
                    }else{
                        System.out.println(stack.get(stack.size()-1));
                        stack.remove(stack.size()-1);
                    }
                    break;
                case "top" :
                    if(stack.isEmpty()){
                        System.out.println(-1);
                    }else {
                        System.out.println(stack.get(stack.size() - 1));
                    }
                    break;
                case "size" :
                    System.out.println(stack.size());
                    break;
                case "empty" :
                    System.out.println(stack.isEmpty()?1:0);
                    break;
            }
        }

    }
}

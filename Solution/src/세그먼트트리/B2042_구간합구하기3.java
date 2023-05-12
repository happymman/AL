package 세그먼트트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B2042_구간합구하기3 {
    private static long[] tree;
    private static int leafNodeStartIndex;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K =  Integer.parseInt(st.nextToken());

        //leafNode 삽입
        int treeHeight =0;
        int count = N;//3
        while(count!=0){
            count = count /2; //1, 0
            treeHeight++;//1, 2
        }
        int treeSize = (int) Math.pow(2,treeHeight+1);
        leafNodeStartIndex = treeSize/2 -1;
        tree = new long[treeSize];

        for(int i=leafNodeStartIndex+1;i<=leafNodeStartIndex+N;i++){
            tree[i] = Long.parseLong(br.readLine());
        }

        //구간합 트리 완성
        setTree(treeSize-1);

        //연산 수행하기
        for(int i=0;i<M+K;i++){
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int first = Integer.parseInt(st.nextToken());
            long second = Long.parseLong(st.nextToken());

            if(command ==1){
                first += leafNodeStartIndex;
                changeValue(first, second);
            } else if (command==2) {
                first = first+leafNodeStartIndex;
                second = second+leafNodeStartIndex;
                System.out.println(getSum(first, (int)second));
            }else{
                return;
            }
        }
        br.close();
    }

    private static void setTree(int last) { //15
        int i=last;
        while (i != 1) {
            tree[i/2] += tree[i];
            i--;
        }
    }

    private static long getSum(int sIndex, int eIndex){
        long partSum=0;
        //엇갈릴때까지 반복
        while(sIndex<=eIndex){
            if(sIndex %2==1)
                partSum+=tree[sIndex];
                sIndex++;
            if(eIndex%2==0)
                partSum+=tree[eIndex];
                eIndex--;
            sIndex /= 2;
            eIndex /= 2;
        }
        //엇갈렸으면
        return partSum;
    }

    private static void changeValue(int index, long value){
        //1 5 2 -> 5번째수를 2로 바꾸고
        long diff = value - tree[index];
        while(index !=0){
            tree[index] += diff;
            index /=2;
        }
    }
}

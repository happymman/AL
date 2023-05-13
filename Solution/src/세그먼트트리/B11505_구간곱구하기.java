package 세그먼트트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B11505_구간곱구하기 {

    private static long[] tree;

    public static void main(String[] args) throws IOException {
        //br 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //N, M 받기
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        //N으로 트리높이 구하고,
        int count = N; //5
        int treeHeight =0;
        while(count!=0){
            count /=2; //210
            treeHeight++;//123
        }
        int treeSize = (int) Math.pow(2,treeHeight+1);
        int leafNodeStartIndex = treeSize/2 -1;
        //tree[] 메모리 할당하고
        tree = new long[treeSize];
        //tree[] 값 삽입해주고
        for(int i=leafNodeStartIndex+1; i<=leafNodeStartIndex+N;i++){
            tree[i] = Long.parseLong(br.readLine());
        }
        for(int i=0;i<treeSize;i++){
            if(!(leafNodeStartIndex+1 <= i && i<=leafNodeStartIndex+N))
                tree[i] = 1L;
        }

        //구간곱 세그먼트 트리 완성 -> setTree()
        setTree(treeSize-1);

        //M+K만큼 연산하기
        for(int i=0;i<M+K;i++){
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int first = Integer.parseInt(st.nextToken());
            long second = Long.parseLong(st.nextToken());

            if(command == 1){
                int index = first+leafNodeStartIndex;
                Long value = second;
                changeValue(index, value);
            }else if(command ==2){
                int sIndex = first+leafNodeStartIndex;
                int eIndex = (int)second+leafNodeStartIndex;
                System.out.println(getMul(sIndex, eIndex));
            }else{
                return;
            }
        }
    }

    private static void setTree(int last){
        int index = last;
        while(index !=0){
            tree[index/2] = (tree[index/2] * tree[index])%1000000007;
            index--;
        }
    }

    private static void changeValue(int index, long newValue){ //newValue = 0
        //이전값을 다 나눠주고
        int i=index;
        tree[i] = newValue;
        i=i/2;
        while(i!=0){
            tree[i]= (tree[2*i] * tree[2*i+1])%1000000007;
            i=i/2;
        }
    }

    private static long getMul(int sIndex, int eIndex){
        long mul=1;
        while(sIndex<=eIndex){
            if(sIndex%2==1){
                mul = (mul * tree[sIndex])%1000000007;
                sIndex++;
            }
            if(eIndex%2==0){
                mul = (mul * tree[eIndex])%1000000007;
                eIndex--;
            }
            sIndex /= 2;
            eIndex /= 2;
        }
        return mul % 1000000007;
    }
}


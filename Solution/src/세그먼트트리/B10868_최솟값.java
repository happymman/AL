package 세그먼트트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B10868_최솟값 {

    private static int[] tree;

    public static void main(String[] args) throws IOException {
        //br 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //N, M 받기
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        //N으로 트리높이 구하고,
        int count = N;
        int treeHeight =0;
        while(count!=0){
            count /=2;
            treeHeight++;
        }
        int treeSize = (int) Math.pow(2,treeHeight+1);
        int leafNodeStartIndex = treeSize/2 -1;
        //tree[] 메모리 할당하고
        tree = new int[treeSize];
        //tree[] 값 삽입해주고
        for(int i=leafNodeStartIndex+1; i<=leafNodeStartIndex+N;i++){
            tree[i] = Integer.parseInt(br.readLine());
        }

        //세그먼트 트리 완성 -> setTree()
        setTree(treeSize/2-1);

        //M만큼 연산하기
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int sIndex = Integer.parseInt(st.nextToken())+leafNodeStartIndex;
            int eIndex = Integer.parseInt(st.nextToken())+leafNodeStartIndex;

            System.out.println(getMin(sIndex, eIndex));
        }
    }

    private static void setTree(int last){
        int index = last;
        while(index !=0){
            tree[index] = Math.min(tree[index*2], tree[index*2+1]);
            index--;
        }
    }

    private static int getMin(int sIndex, int eIndex){
        int min = Integer.MAX_VALUE;
        while(sIndex<=eIndex){
            if(sIndex%2==1){
                min = Math.min(tree[sIndex], min);
                sIndex++;
            }
            if(eIndex%2==0){
                min = Math.min(tree[eIndex], min);
                eIndex--;
            }
            sIndex /= 2;
            eIndex /= 2;
        }
        return min;
    }
}


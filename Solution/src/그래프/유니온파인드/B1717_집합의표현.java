package 그래프.유니온파인드;

import java.util.Scanner;

public class B1717_집합의표현 {

    private static int[] parent;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();


        parent = new int[n+1];
        for(int i=1;i<=n;i++){
            parent[i] = i;
        }

        for(int i=0;i<m;i++){
            int command = sc.nextInt();
            int first = sc.nextInt();
            int second = sc.nextInt();

            if(command==0){
                union(first, second);
            }else if(command==1){
                if(checkSame(first, second)){
                    System.out.println("YES");
                }else{
                    System.out.println("NO");
                }
            }
        }
    }

    private static void union(int v1, int v2){
        int v1Parent = find(v1);
        int v2Parent = find(v2);
        if(v1Parent != v2Parent){
            parent[find(v2)] = find(v1);
        }
    }

    private static int find(int v){ //parent배열에서 인덱스=배열값 같은지확인 -> 아니면 parnet이동해서 다시 확인 반복
        if(parent[v] == v){
            return v;
        }else{
            return parent[v] = find(parent[v]); //최종 부모를 찾고, 저장하면서 return
        }
    }

    private static boolean checkSame(int v1, int v2){
        if(find(v1) == find(v2)){
            return true;
        }else{
            return false;
        }
    }
}

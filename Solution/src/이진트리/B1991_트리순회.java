package 이진트리;

import java.util.Scanner;

public class B1991_트리순회 {
    private static int[][] tree = new int[26][2];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        sc.nextLine();
        //등록
        for(int i=0;i<N;i++){
            String[] input = sc.nextLine().split(" ");
            int index = input[0].charAt(0) - 'A';
            char cNode1 = input[1].charAt(0);
            char cNode2 = input[2].charAt(0);

            if(cNode1 != '.'){
                tree[index][0] = cNode1 - 'A';
            }else{
                tree[index][0] = -1;
            }

            if(cNode2 != '.'){
                tree[index][1] = cNode2 - 'A';
            }else{
                tree[index][1] = -1;
            }
        }

//        for(int i=0;i<N;i++){
//            System.out.println(tree[i][0]);
//            System.out.println(tree[i][1]);
//            System.out.println();
//        }

        preOrder(0);
        System.out.println();
        inOrder(0);
        System.out.println();
        postOrder(0);

    }

    static void preOrder(int node){
        System.out.print((char)(node+'A'));

        if(tree[node][0] != -1)
            preOrder(tree[node][0]);

        if(tree[node][1] != -1)
            preOrder(tree[node][1]);
    }

    static void inOrder(int node){
        if(tree[node][0] != -1)
            inOrder(tree[node][0]);

        System.out.print((char)(node+'A'));

        if(tree[node][1] != -1)
            inOrder(tree[node][1]);
    }

    static void postOrder(int node){
        if(tree[node][0] != -1)
            postOrder(tree[node][0]);

        if(tree[node][1] != -1)
            postOrder(tree[node][1]);

        System.out.print((char)(node+'A'));
    }
}

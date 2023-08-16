package 분할정복;
import java.util.*;
public class P150367_표현가능한이진트리 {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];

        for(int i=0;i<numbers.length;i++){
            String binaryNumber = getBinaryTree(numbers[i]);
            if(checkBinaryTree(binaryNumber)){
                answer[i] = 1;
            }else{
                answer[i] = 0;
            }
        }
        return answer;
    }

    static String getBinaryTree(long number){
        String binaryNumber = Long.toBinaryString(number);
        int height = (int) Math.ceil(Math.log10(binaryNumber.length()+1) / Math.log10(2));
        int size = (int)Math.pow(2, height)-1;

        int dummy = size-binaryNumber.length();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<dummy;i++){
            sb.append("0");
        }
        sb.append(binaryNumber);

        return sb.toString();
    }

    static boolean checkBinaryTree(String binaryTree){
        if(binaryTree.length()<=1){
            return true;
        }

        String leftTree = binaryTree.substring(0,binaryTree.length()/2);
        String rightTree = binaryTree.substring(binaryTree.length()/2+1);

        char root = binaryTree.charAt(binaryTree.length()/2);
        char leftChild = leftTree.charAt(leftTree.length()/2);
        char rightChild = rightTree.charAt(rightTree.length()/2);

        if(root=='0'&&(leftChild=='1' || rightChild=='1')){
            return false;
        }else{
            return checkBinaryTree(leftTree) && checkBinaryTree(rightTree);
        }
    }
}
package 카카오브레인;

import java.io.*;
import java.util.stream.IntStream;

class SinglyLinkedListNode {
    public int data;
    public SinglyLinkedListNode next;

    public SinglyLinkedListNode(int nodeData) {
        this.data = nodeData;
        this.next = null;
    }
}

class SinglyLinkedList {
    public SinglyLinkedListNode head;
    public SinglyLinkedListNode tail;

    public SinglyLinkedList() {
        this.head = null;
        this.tail = null;
    }

    public void insertNode(int nodeData) {
        SinglyLinkedListNode node = new SinglyLinkedListNode(nodeData);

        if (this.head == null) {
            this.head = node;
        } else {
            this.tail.next = node;
        }

        this.tail = node;
    }
}

class SinglyLinkedListPrintHelper {
    public static void printList(SinglyLinkedListNode node, String sep, BufferedWriter bufferedWriter) throws IOException {
        while (node != null) {
            bufferedWriter.write(String.valueOf(node.data));

            node = node.next;

            if (node != null) {
                bufferedWriter.write(sep);
            }
        }
    }
}



class k1_Result {

    /*
     * Complete the 'deleteEven' function below.
     *
     * The function is expected to return an INTEGER_SINGLY_LINKED_LIST.
     * The function accepts INTEGER_SINGLY_LINKED_LIST listHead as parameter.
     */

    /*
     * For your reference:
     *
     * SinglyLinkedListNode {
     *     int data;
     *     SinglyLinkedListNode next;
     * }
     *
     */

    public static SinglyLinkedListNode deleteEven(SinglyLinkedListNode listHead) {

        SinglyLinkedListNode pNode = null;
        SinglyLinkedListNode cNode = listHead;

        while(cNode != null){
            if(cNode.data %2 != 0){
                pNode = cNode;
                cNode = cNode.next;
            }else{
                if(cNode.equals(listHead)){
                    listHead = cNode.next;
                    cNode.next = null;
                    cNode = listHead;
                }else{
                    pNode.next = cNode.next;
                    cNode.next = null;
                    cNode = pNode.next;
                }
            }
        }

        return listHead;
    }

}

public class k1 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        SinglyLinkedList listHead = new SinglyLinkedList();

        int listHeadCount = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, listHeadCount).forEach(i -> {
            try {
                int listHeadItem = Integer.parseInt(bufferedReader.readLine().trim());

                listHead.insertNode(listHeadItem);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        SinglyLinkedListNode result = k1_Result.deleteEven(listHead.head);

        SinglyLinkedListPrintHelper.printList(result, "\n", bufferedWriter);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

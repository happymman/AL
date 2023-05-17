package 구현;

import java.io.*;
        import java.math.*;
        import java.security.*;
        import java.text.*;
        import java.util.*;
        import java.util.concurrent.*;
        import java.util.function.*;
        import java.util.regex.*;
        import java.util.stream.*;
        import static java.util.stream.Collectors.joining;
        import static java.util.stream.Collectors.toList;



class H4 {


    /*
     * Complete the 'renameFile' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. STRING newName
     *  2. STRING oldName
     */

    private static int answerCount =0;

    private static String newName_;
    private static String oldName_;

    public static void main(String[] args) {
        newName_ = "ccc";
        oldName_ = "cccc";

        BT("cccc".length(), "cccc"); //4, "cccc"
        System.out.println(answerCount);
    }

    public static void BT(int length, String name){
        //length가 oldName이랑 같으면
        if(length == newName_.length()){ // 4 != 3
            if(name.equals(newName_)) answerCount++;//equals검사하고 - 같으면 count++, 다르면 그냥 return
            return;
        }

        //for문 -> 첫번째 글자 뺀거 contains하면 BT넣고, 아니면 그냥 return
        for(int i=0;i<name.length(); i++){
            //뺀애 = i번째 글자 뺀거 만들고
            StringBuilder sb = new StringBuilder(name);
            sb.deleteCharAt(i);
            String resultString = sb.toString();

            //if 뺀애.contains(newName_) BT(length-1, 뺀애)
            if (resultString.contains(newName_)){
                BT(length-1, resultString);
            }
        }
    }

}
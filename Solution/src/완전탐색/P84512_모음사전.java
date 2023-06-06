package 완전탐색;

public class P84512_모음사전 {

    public int solution(String word) {

        int[] mul = {781, 156, 31, 6, 1};

        int order=word.length();
        for(int i=0;i<word.length();i++){
            order+="AEIOU".indexOf(word.charAt(i))*mul[i];
        }

        return order;
    }
}
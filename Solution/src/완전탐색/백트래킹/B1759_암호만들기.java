package 완전탐색.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
1차풀이 - 25m

 */
public class B1759_암호만들기 {
    static int L;
    static List<String> answer = new ArrayList<>();
    public static void main(String[] args) throws IOException {

        //L, C입력받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        //배열 C만큼 메모리할당
        String[] stringArr = br.readLine().split(" ");
        char[] charArr = new char[stringArr.length];
        for(int i=0;i<stringArr.length;i++){
            charArr[i] = stringArr[i].charAt(0);
        }
        Arrays.sort(charArr);//정렬

        BT("", 0, 0, charArr); //a c i s t w
        for(String s : answer){
            System.out.println(s);
        }
    }

    static void BT(String key, int start, int count, char[] arr){ //

        //노드처리 X
        if(count==L){ //탐색종료여부 검사
            int[] vowelConsonant = countVowelConsonant(key); //모음, 자음개수 세기
            if(vowelConsonant[0]>=1 && vowelConsonant[1]>=2) answer.add(key); //조건충족 -> 저장
            return;
        }

        for(int i=start;i<arr.length;i++){ //범위검사
            //유효성검사X
            //방문검사X - 상황 : 경로역할 필요X, 중복방문X(<-순차적진행)
            //방문처리X
            BT(key+arr[i], i+1, count+1, arr); //다음노드 방문
        }
    }

    static int[] countVowelConsonant(String key){
        int vowel=0;
        int consonant=0;
        for(int i=0;i<key.length();i++){
            if(key.charAt(i) == 'a' || key.charAt(i) == 'e' || key.charAt(i) == 'i' || key.charAt(i) == 'o' || key.charAt(i) == 'u'){
                vowel++;
            }else{
                consonant++;
            }
        }
        return new int[]{vowel, consonant};
    }
}

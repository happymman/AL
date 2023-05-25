package 해시;

import java.util.*;

class P42577_전화번호목록 {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        Map<String, Boolean> phone_map = new HashMap<>();

        for(String phone_number : phone_book){
            int len = phone_number.length();
            for(int i=1;i<len;i++){
                phone_map.put(phone_number.substring(0,i), false);
            }
        }

        boolean find = true;
        for(String phone_number : phone_book){
            find = phone_map.getOrDefault(phone_number, true);
            if(!find) break;
        }
        return find;
    }
}
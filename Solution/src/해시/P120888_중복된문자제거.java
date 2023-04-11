package 해시;



import java.util.HashSet;
        import java.util.Set;

class P120888_중복된문자제거 {
    public String solution(String my_string) {
        Set<Character> set = new HashSet<>();
        StringBuilder sb = new StringBuilder();

        for(char c : my_string.toCharArray()){
            if(set.contains(c)) continue;
            set.add(c);
            sb.append(c);
        }
        return sb.toString();
    }
}
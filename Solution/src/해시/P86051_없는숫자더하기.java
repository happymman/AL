package 해시;

import java.util.Arrays;
        import java.util.HashSet;
        import java.util.Set;

class P86051_없는숫자더하기 {
    public int solution(int[] numbers) {
        Integer[] array = {0,1,2,3,4,5,6,7,8,9};
        Set<Integer> set = new HashSet<Integer>(Arrays.asList(array));

        for(int i : numbers){
            if(set.contains(i)) set.remove(i);
        }

        int sum=0;
        for(int i : set){
            sum+=i;
        }

        return sum;
    }
}
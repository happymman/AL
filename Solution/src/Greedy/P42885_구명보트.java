package Greedy;

import java.util.Arrays;

//public class P42885_구명보트{
//    public int solution(int[] people, int limit){
//        Arrays.sort(people);
//
//        int count=0;
//        int i=0, j=people.length-1;
//        while(i<j){
//            if(people[j]+people[i] <= limit){
//                i++;
//            }
//            j--;
//            count++;
//        }
//        if(i==j) count++;
//
//        return count;
//    }
//}

import java.util.Arrays;
        import java.util.Collections;

class Solution{
    public int solution(int[] people, int limit){
        Arrays.sort(people);
        int count=0;
        int i=0;
        int j=people.length-1;
        while(i<j){
            if(people[i]+people[j]<=limit){
                i++;
            }
            j--;
            count++;
        }
        if(i==j) count++;
        return count;
    }
}

public class P42885_구명보트{
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] people = {70, 50, 80, 50};
        int limit = 100;
        int answer = s.solution(people, limit);
    }
}
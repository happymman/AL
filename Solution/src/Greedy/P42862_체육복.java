package Greedy;


import java.util.Arrays;
public class P42862_체육복{
    public int solution(int n, int[] lost, int[] reserve){
        int[] cloth = new int[n];
        Arrays.fill(cloth,1);

        for(int i=0;i<lost.length;i++){
            cloth[lost[i]-1] -= 1;
        }

        for(int i=0;i<reserve.length;i++){
            cloth[reserve[i]-1] += 1;
        }

        for(int i=0;i<cloth.length;i++){
            if(cloth[i]==0){
                if(i-1 >= 0 && cloth[i-1] == 2){
                    cloth[i] +=1;
                    cloth[i-1] -=1;
                }else if(i+1 < cloth.length && cloth[i+1] ==2){
                    cloth[i] +=1;
                    cloth[i+1] -=1;
                }
            }
        }

        int count=0;
        for(int i=0;i<n;i++){
            if(cloth[i] == 0) count++;
        }

        return n-count;
    }
}
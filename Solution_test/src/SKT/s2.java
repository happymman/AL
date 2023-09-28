package SKT;

public class s2 {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("안녕하세요");
        String str = "안녕하세요";

        if (sb.toString().equals(str)) {
            System.out.println("동일한 내용입니다.");
        } else {
            System.out.println("다른 내용입니다.");
        }
    }
}

class s2_Solution{
    static int[][] time;
    public int[] solution(int n, int m, int[] sends, int[][] logs){
        time = new int[sends.length][4];

        for(int i=0;i<sends.length;i++){
            int send = sends[i];

            time[i][0] = send;
            time[i][1] = send+n;
            time[i][2] = send+n+m;
            time[i][3] = send+n+m+n;
        }

        for(int[] log : logs){ //log선택
            if(log[0]==2) 전송지연(log[1]);
        }

        for(int[] log : logs){ //log선택
            if(log[0]==2) 전송실패(log[1]);
        }

        int[] answer = new int[sends.length];
        for(int i=0;i<time.length;i++){
            answer[i]=time[i][3];
        }

        return answer;
    }

    static void 전송지연(int error){
        for(int[] user : time){
            if(user[1]<=error && error<user[2]){
                int add = user[2]-error;
                user[2] += add;
                user[3] += add;
            }
        }
    }

    static void 전송실패(int error){
        for(int[] user : time){
            if(user[1]<=error && error<user[2]){
                user[3] = -1;
            }
        }
    }
}

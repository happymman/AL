package 구현.삼성;

import java.util.Scanner;

/*
1차풀이 - 2h 43m
피드백
- 상황 : 문제읽기 - 이해X(복잡한 서술) -> '그림찾기' - 이유 : 빠른 이해를 위해서는 주어진 그림을 찾아서 이해하는 것이 가장 빠르다
                                   공책 그림 - 그것이 없을때 '공책 그림'등을 통해 그림을 생성해서 이해하는 것
- 시간복잡도 계산 - 완탐 - '데이터 전체 개수', '선택 데이터 개수' 기준으로 어림 측정? - 200C4
- 점선택 How(구현방법)
 */
public class C_종전 {

    static int N;
    static int min = Integer.MAX_VALUE;
    static int[][] map;
    static int[][] 영역;

    static int[] downDot = new int[2];
    static int[] rightDot = new int[2];
    static int[] upDot = new int[2];
    static int[] leftDot = new int[2];
    static int[][] dir = {{-1,1}, {-1,-1}};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        input(sc);
        pro();
        System.out.println(min); //min 출력
    }

    static void input(Scanner sc){
        N = sc.nextInt();
        map = new int[N][N];
        영역 = new int[N][N];

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                map[i][j] = sc.nextInt();
            }
        }
    }

    static void pro(){

        for(int i=0;i<N;i++){ //행선택
            for(int j=0;j<N;j++){ //열선택
                downDot[0] = i; //글로벌 변수인 하꼭짓점 업데이트
                downDot[1] = j;
                rightDot[0] = i; //글로벌 변수인 우꼭짓점 출발점
                rightDot[1] = j;
                BT(1);
            }
        }

    }

    static void BT(int depth){ //depth : 점개수

        if(depth==3){ //탐색 종료여부 검사
            영역마킹();
            int result = 인구수세기();
            min = Math.min(min, result);
            return;
        }

        while(true){ //return나오기전까지는 계속.
            if(depth==1){
                if(!우상_탐색가능()) return;
                BT(depth+1); //다음노드 탐색
            }else if(depth==2){
                if(!좌상좌하_탐색가능()) return;
                BT(depth+1); //다음노드 탐색
            }
        }
    }

    //범위검사 이상없으면 우꼭짓점 업데이트
    static boolean 우상_탐색가능(){

        //우꼭짓점 가능여부 검사
        int rightX = rightDot[0]+dir[0][0];
        int rightY = rightDot[1]+dir[0][1];
        if(rightX<0||rightY<0||rightX>=N||rightY>=N) return false;

        //우꼭짓점+상꼭짓점 같이 업데이트
        rightDot[0] = rightX;
        rightDot[1] = rightY;
        upDot[0] = rightX;
        upDot[1] = rightY;

        return true;
    }

    static boolean 좌상좌하_탐색가능(){

        int upX= upDot[0]+dir[1][0];
        int upY= upDot[1]+dir[1][1];
        //좌상 범위검사
        if(upX<0||upY<0||upX>=N||upY>=N) return false;

        int leftX = upX - rightDot[0] + downDot[0];//(상꼭짓점 - (우꼭짓점 - 하꼭짓점));
        int leftY = upY - rightDot[1] + downDot[1];

        //좌하 범위검사
        if(leftX<0||leftY<0||leftX>=N||leftY>=N) return false;

        //위꼭짓점 업데이트
        upDot[0] = upX;
        upDot[1] = upY;

        //왼꼭짓점 업데이트
        leftDot[0] = leftX;
        leftDot[1] = leftY;

        return true;
    }

    static void 영역마킹(){

        //우상직선 : y-downY = -x+downX -> j-downDot[1] = -i+downDot[0]
        //좌상직선 : y-upY = x-upY -> j-upDot[1] = i-upDot[0]
        //좌하직선 : y-upY = -x+upY -> j-upDot[1] = -i+upDot[0]
        //우하직선 : y-downY = -x+downX -> j-downDot[1] = -i+downDot[0]

        for(int i=0;i<N;i++){ //행선택
            for(int j=0;j<N;j++){ //열선택
                if((j-downDot[1] <= -i+downDot[0])&&(j-upDot[1] <= i-upDot[0])&&(j-upDot[1] >= -i+upDot[0])&&(j-downDot[1] >= i-downDot[0])){ //1범위
                    영역[i][j] = 1;
                }else if(j-upDot[1] < -i+upDot[0] && i<leftDot[0] && j<= upDot[1]){ //2범위
                    영역[i][j] = 2;
                }else if(j-upDot[1] > i-upDot[0] && i<=rightDot[0] && j>upDot[1]){ //3범위
                    영역[i][j] = 3;
                }else if(j-downDot[1] < i-downDot[0] && i>=leftDot[0] && j<downDot[1]){ //4범위
                    영역[i][j] = 4;
                }else{ //5범위
                    영역[i][j] = 5;
                }
            }
        }

    }

    static int 인구수세기(){

        int[] counts = new int[6]; //초기화

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                counts[영역[i][j]] += map[i][j];
            }
        }

        //counts돌면서 max, min갱신
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int i=1;i<counts.length;i++){
            max = Math.max(max, counts[i]);
            min = Math.min(min, counts[i]);
        }

        return max-min;
    }


}

/*
#include <iostream>
#include <string>
#include <cmath>
#include <stack>
#include <vector>
#include <algorithm>
#define FIRST cin.tie(NULL); cout.tie(NULL); ios::sync_with_stdio(false);
#define MAX 25
#define INF 2e9

using namespace std;
int N;
int MAP[MAX][MAX];
int Region[MAX][MAX];
int answer = INF;
int answer_MAP[MAX][MAX];

void init() {
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			Region[i][j] = 5;
		}
	}
}

void Region_Arrange(int Y, int X, int D1, int D2) {
	// 모든 선거구를 5번으로 채운다.
	init();
	// 다음은 1번 선거구를 채운다.
	int Sub_Area = 0;
	for (int i = 1; i < Y + D1; i++) {
		if (i >= Y) {
			Sub_Area++;
		}
		for (int j = 1; j <= X - Sub_Area; j++) {
			Region[i][j] = 1;
		}
	}
	// 다음은 2번 선거구를 채운다.
	Sub_Area = 0;
	for (int j = N; j > X; j--) {
		if (j <= X + D2) {
			Sub_Area++;
		}
		for (int i = 1; i <= Y + D2 - Sub_Area; i++) {
			Region[i][j] = 2;
		}
	}
	// 다음은 3번 선거구를 채운다.
	Sub_Area = 0;
	for (int j = 1; j < X - D1 + D2; j++) {
		if (j >= X - D1) {
			Sub_Area++;
		}
		for (int i = N; i >= Y + D1 + Sub_Area; i--) {
			Region[i][j] = 3;
		}
	}
	// 다음은 4번 선거구를 채운다.
	Sub_Area = 0;
	for (int i = N; i > Y + D2; i--) {
		if (i <= Y + D1 + D2) {
			Sub_Area++;
		}
		for (int j = N; j >= X - D1 + D2 + Sub_Area; j--) {
			Region[i][j] = 4;
		}
	}
}

void Find_Answer() {
	int People[6] = { -1,0,0,0,0,0 };
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			People[Region[i][j]] += MAP[i][j];
		}
	}
	sort(People, People + 6);
	if (answer > People[5] - People[1]) {
		answer = People[5] - People[1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				answer_MAP[i][j] = Region[i][j];
			}
		}
	}
}

//
bool First_Condition(int D1, int D2) {
	if ((D1 >= 1) && (D2 >= 1)) {
		return true;
	}
	return false;
}

bool Second_Condition(int Y, int D1, int D2) {
	if ((Y >= 1) && (Y < Y + D1 + D2) && (Y + D1 + D2 <= N)) {
		return true;
	}
	return false;
}

bool Third_Condition(int X, int D1, int D2) {
	if ((X - D1 >= 1) && (X - D1 < X) && (X < X + D2) && (X + D2 <= N)) {
		return true;
	}
	return false;
}

int main() {
	FIRST
	cin >> N;
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			cin >> MAP[i][j];
		}
	}
	for (int Y = 1; Y <= N; Y++) {
		for (int X = 2; X <= N; X++) {
			for (int D1 = 1; D1 <= X; D1++) {
				for (int D2 = 1; D2 < N - X; D2++) {
					if (First_Condition(D1, D2) && Second_Condition(Y, D1, D2) && Third_Condition(X, D1, D2)) {
						Region_Arrange(Y, X, D1, D2);
						Find_Answer();
					}
				}
			}
		}
	}
	cout << answer << "\n";

	return 0;
}
 */
import sys
input = sys.stdin.readline
sys.setrecursionlimit(10 ** 9)

m, n = map(int, input().split())
maps = [list(map(int, input().split())) for i in range(m)]
dp = [[-1]*n for i in range(m)] #

dx = [0, 1, 0, -1]
dy = [1, 0 , -1, 0]

def is_range(x, y, now):
    return 

#bottom-up 재귀
def solution(x, y): #solution(x,y) : x, y에서 도착지점까지 가는 경로의 수
    if x == m-1 and y == n-1: #목표 도달검사
        return 1 #Q.1인 이유 정확히? 모르겠다

    if dp[x][y] == -1: #dp배열 미등록된 경우
                       # -1초기화 이유 : if 0초기화 -> 진짜 경로의 개수가 없어서(0)인지, 미등록된 상태인지(해당지점(x,y)부터 목표지점까지의 경로의 개수값이 '구해지지 않은 상태')인건지 구분 불가능
        dp[x][y] = 0 #

        for i in range(4):
            dr_x, dr_y = x + dx[i], y + dy[i]

            if 0 <= dr_x < m and 0 <= dr_y < n : #범위검사
                if maps[dr_x][dr_y] < maps[x][y] : #유효성검사 - 다음좌표로 이동가능하다면
                    dp[x][y] += solution(dr_x, dr_y) #이동전 좌표2목표지점까지의 경로의수 = 이동전 좌표2목표지점까지의 경로의수 + 이동후 좌표2목표지점까지의 경로의수
    
    return dp[x][y] #'dp배열 등록된 경우'가 의미하는 것 : 해당 지점(x,y)부터 목표지점까지의 경로의 개수값이 '구해졌다'(by x==m-1, y==n-1까지 도달해서)
        
    
print(solution(0, 0))


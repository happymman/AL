import sys
input = sys.stdin.readline

N, L = map(int, input().split())

maps = [list(map(int, input().split())) for _ in range(N)]

def check(line, L): #체크함수를 line단위로 만듬으로써, 경사로 불가판단을 break가 아닌 return False로 가독성있게 처리 가능
    vis = [False for _ in range(N)]
    
    for i in range(0, N-1): #열 선택
        # 다음 위치 높이 같은경우
        if line[i] == line[i+1] : continue
        # 다음 위치의 높이 차이가 1넘게 나는 경우
        elif abs(line[i]-line[i+1]) > 1 : return False
            
        # 현재 높이가 다음 높이 보다 높으면 -> 오른쪽 높이가 같은지 체크
        elif line[i] > line[i+1]:
            std = line[i+1] # std : 기준 높이
            for j in range(i+1, i+L+1):
                if not(0 <= j < N) : return False #범위검사 - 초기에 설정된 범위를 모두 검사 못하고 격자범위를 벗어나는 것 = 놓아야하는 경사로 길이 불충족
                if std != line[j]: return False #유효성검사
                elif vis[j]: return False #방문검사 = 경사로 존재 여부 검사
                vis[j] = True # 방문처리 -> 경사로 놓기

        # 현재 높이가 다음 높이 보다 낮으면 -> 왼쪽 높이가 같은지 체크
        else:
            std = line[i]
            for j in range(i, i-L, -1): #for문 범위설정을 '경사로 길이'를 모두 충족시킬 수 있도록
                if not(0 <= j < N) : return False #범위검사
                if std != line[j]: return False #유효성검사
                elif vis[j]: return False #방문검사 = 경사로 존재 여부 검사
                vis[j] = True # 방문처리 -> 경사로 놓기

    return True

answer = 0
# 가로 길 체크
for i in maps:
    if check(i, L) : answer += 1

# 세로 길을 체크하기 위해 변환해서 넣어줌
temp = [[0] * N for _ in range(N)]
for i in range(N) :
    for j in range(N) :
        temp[j][i] = maps[i][j]

for i in temp:
    if check(i, L) : answer += 1

print(answer)
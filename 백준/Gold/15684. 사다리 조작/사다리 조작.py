"""
    조건 : 가로선 연속X
    목표 : i번 세로선의 결과가 i로 가게하게끔하는 사다리 추가개수 최솟값(<=3)

    내가 못했던 생각 : 사다리 배치를 그냥 격자로 생각하기

    조합 - 구현방법 : 백트래킹 구현
          핵심요소 : 다음 탐색시작값 설정(start)
          종류 : 2차원 격자 -> 현재행 = si//열개수, 현재열 = si%열개수
"""

def check() : #모든 x번째줄이 x번째로 도착여부 판단
    for start in range(N) :
        j = start 
        for i in range(H) :
            if maps[i][j] : #오른쪽에 가로선 존재
                j += 1 #오른쪽 이동
            elif j > 0 and maps[i][j-1] : #왼쪽에 가로선 존재
                j -= 1 #왼쪽 이동
        if j != start : #시작위치로 안 돌아왔으면
            return False
    return True

def bt(cnt, start) : #cnt : 사다리 놓은 개수, start : 탐색시작인덱스
    global minV
    if check() :
        minV = min(minV, cnt) #1.최솟값 갱신
        return
    elif cnt == 3 or minV <= cnt : #2.탐색종료검사(목표도달, 탐색필요X) - 횟수가 3넘어가거나, 최소값 넘어가면
        return

    for si in range(start, H*N) : #si : 시작인덱스
        if si % N == N-1 : continue #마지막 세로줄에는 사다리 놓기 불가
        i = si//N #i = 현재행
        j = si%N #j = 현재열

        if maps[i][j+1] : continue #유효성검사 - 오른쪽에 사다리가 놓여진 경우 -> 
        if j>0 and maps[i][j-1] : continue #유효성 검사 - 왼쪽에 사다리 놓여진 경우 -> 패스    
        if maps[i][j] : continue #방문검사 - 이미 사다리 놓여진 자리
        maps[i][j] = True #방문처리 = 상태변경

        ci = i*N+j #ci : 현재인덱스
        bt(cnt+1, ci+2) #이유 : 사다리를 놓았으니 -> 해당지점, 해당지점+1은 자동으로 사다리 놓기 불가

        maps[i][j] = False #방문해제 = 상태복원

N, M, H = map(int, input().split()) #세로, 사다리, 가로
maps = [[False] * N for _ in range(H)] #사다리 배열

for _ in range(M) :
    a, b = map(int, input().split())
    maps[a-1][b-1] = True #사다리가 있는 곳

minV = 4 #최대 정답값
bt(0,0)
print(minV if minV < 4 else -1)

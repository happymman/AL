"""
    208~3
    1.순회
    NM <= 90000
    빙산개수 <= 10000 

    150 * 10 = 1500

    135000000 = 10의 8승

    bfs도 해야되잖아. 그냥 10의 8승에 상수배되는셈인가?

    2.자료구조 사용해서(빙산인덱스 리스트)만 처리하기

    런타임에러(Recursion Error)
        -> sys.setrecursionlimit(10**8) 설정
    34% 시초
        -> Pypy3 풀이 시도
    메모리 초과
        -> BFS로 바꿔서 해야할 것 같음
    
"""
import sys
input = sys.stdin.readline
from collections import deque

n, m = map(int, input().split())
maps = [list(map(int, input().split())) for _ in range(n)]
melt = [[0] * m for _ in range(n)]

dir = [[1,0],[0,1],[-1,0],[0,-1]]

def bfs(sx, sy) :

    q= deque()
    q.append((sx,sy))

    while q : 
        x, y = q.popleft()

        for i in range(4) :
            nx, ny = x + dir[i][0], y + dir[i][1]

            if maps[nx][ny] == 0 : continue #유효성검사 - 바다면 못가
            if vis[nx][ny] != 0 : continue #방문검사
            vis[nx][ny] = 1

            q.append((nx, ny))

bings = []
for i in range(n) :
    for j in range(m) :
        if maps[i][j] != 0 :
            bings.append((i,j))


time = 0 
while True : #1500 -> 45000000 = 10의 6승
    time += 1

    for x, y in bings : #빙산선택 - O(N)
        if maps[x][y] == 0 : continue #이미 녹은 빙산이면 pass

        sea = 0
        for i in range(4) :#옆에 바다 몇개있는지 세기
            nx, ny = x + dir[i][0], y + dir[i][1]

            if maps[nx][ny] == 0 :#바다여부 확인
                sea += 1

        melt[x][y] = sea #melt배열에 등록

    #녹게하기 - O(N)
    for x, y in bings : #빙산선택
        if maps[x][y] == 0 : continue #이미 녹은 빙산이면 pass
        maps[x][y] = max(maps[x][y] - melt[x][y], 0) #'빙산모음에서 없애기'를 하지말기(maps에서 빙산높이를 없애는 행위와 리스트에서 pop()을 동시에 해야되는데, 그럼 다른 빙산 옆바다 개수셀 때 영향을 준다.
                                                     #초기 빙산개수가 10000개여서, 이미 녹아버린 빙산도 순회를 해도 시간복잡도에 무리없다고 판단
        #melt배열 초기화 안해도 되는 이유 : melt배열이 사용되는 시점은 '녹지 않은 방산'에 대한 처리를 할 때이다.
        #녹지 않은 방산에 대한 처리를 진행한다는 것은 인접바다개수세기를 진행한 빙산좌표라는 의미이므로, 새로운 인접바다개수로 덮어쓰여진 상태이다

    #탐색가능노드 묶음 개수 세기(by DFS) - O(N)
    bundle = 0
    vis = [[0] * m for _ in range(n)]
    for x, y in bings : #빙산선택
        if maps[x][y] == 0 : continue #이미 녹은 빙산 제외
        if vis[x][y] != 0 : continue #방문검사
        vis[x][y] = 1

        bfs(x,y)
        bundle += 1

    
    #남은 빙산 양 세기 - O(N)
    rest = 0
    for x, y in bings :
        rest += maps[x][y]

    if bundle >= 2 :
        print(time)
        break
    elif rest == 0 :
        print(0)
        break


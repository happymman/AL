import sys
input = sys.stdin.readline

from collections import deque
m, n = map(int, input().strip().split())
maps = [list(input().strip()) for _ in range(n)]
stuffs = [] #
sx, sy = 0, 0
num = 1

#시작, 물건 위치 등록
for i in range(n):
    for j in range(m):
        if maps[i][j] == 'S':
            sx, sy = i, j
        if maps[i][j] == 'X':
            stuffs.append((num, i, j))
            maps[i][j] = str(num) #maps를 바꾸는 방식 - 이유 : ifX 물건좌표와 물건번호(1,2,3,4,5... 1로 시작하는)에 대한 정보를 따로 저장하는 자료구조 필요
            num += 1

visited = [[[False for _ in range(64)] for _ in range(m)] for _ in range(n)]
dy, dx = [0, 1, 0, -1], [1, 0, -1, 0]

def bfs():
    q = deque()
    q.append((sx, sy, 1, 0))
    visited[sx][sy][1] = True

    while q:
        x, y, stuffs, time = q.popleft()
        if maps[x][y] == 'E' and stuffs == (1 << num) - 1 : #목표도달 검사(ex : 물건개수 4 -> 1111)
            return time

        for i in range(4):
            nx, ny = x+dx[i], y+dy[i]
            if 0 <= nx < n and 0 <= ny < m and maps[nx][ny] != '#': #범위검사 &유효성검사
                if maps[nx][ny].isdigit(): #물건 만난 경우
                    if stuffs & (1 << int(maps[nx][ny])) != int(maps[nx][ny]): #가지고 있는 물건집합에 발견물건이 없을 때
                        if not visited[nx][ny][stuffs|(1 << int(maps[nx][ny]))]: #방문검사(of 전이하게 될) : '발견물건이 물건집합에 추가된 상태로' 방문한적이 없는지
                            visited[nx][ny][stuffs|(1 << int(maps[nx][ny]))] = True #방문처리
                            q.append((nx, ny, stuffs|(1 << int(maps[nx][ny])), time+1))
                else: #빈 곳
                    if not visited[nx][ny][stuffs]:
                        visited[nx][ny][stuffs] = True
                        q.append((nx, ny, stuffs, time+1))
    return -1
print(bfs())
from collections import deque
n, m = map(int, input().split())
maps = [list(str(input())) for _ in range(m)]
stuffs = [] #
sy, sx = 0, 0
num = 1

#시작, 물건 위치 등록
for i in range(m):
    for j in range(n):
        if maps[i][j] == 'S':
            sy, sx = i, j
        if maps[i][j] == 'X':
            stuffs.append((num, i, j))
            maps[i][j] = str(num) #maps를 바꾸는 방식 - 이유 : ifX 물건좌표와 물건번호(1,2,3,4,5... 1로 시작하는)에 대한 정보를 따로 저장하는 자료구조 필요
            num += 1

visited = [[[False for _ in range(64)] for _ in range(n)] for _ in range(m)]
dy, dx = [0, 1, 0, -1], [1, 0, -1, 0]

def ready_to_go():
    q = deque()
    q.append((sy, sx, 1, 0))
    visited[sy][sx][1] = True

    while q:
        y, x, cnt, time = q.popleft()
        if maps[y][x] == 'E' and cnt == (1 << num) - 1 : #목표도달 검사(ex : 물건개수 4 -> 1111)
            return time

        for i in range(4):
            ny, nx = y+dy[i], x+dx[i]
            if 0 <= ny < m and 0 <= nx < n and maps[ny][nx] != '#': #범위검사
                if str(maps[ny][nx]).isdigit(): #유효성 검사
                    if cnt&(1 << int(maps[ny][nx])) != int(maps[ny][nx]):
                        if not visited[ny][nx][cnt|(1 << int(maps[ny][nx]))]:
                            visited[ny][nx][cnt|(1 << int(maps[ny][nx]))] = True
                            q.append((ny, nx, cnt|(1 << int(maps[ny][nx])), time+1))
                else:
                    if not visited[ny][nx][cnt]:
                        visited[ny][nx][cnt] = True
                        q.append((ny, nx, cnt, time+1))
    return -1
print(ready_to_go())
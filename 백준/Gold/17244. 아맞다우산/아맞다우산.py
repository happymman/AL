import sys
input = sys.stdin.readline

from collections import deque
m, n = map(int, input().strip().split())
maps = [list(input().strip()) for _ in range(n)]
vis = [[[0] * 64 for _ in range(m)] for _ in range(n)]
num = 1
dir = [[1,0],[0,1],[-1,0],[0,-1]]

#시작위치, 물건위치 등록('1', '2', ... '5')
for i in range(n) :
    for j in range(m) :
        if maps[i][j] == 'S' :
            sx, sy = i,j
        if maps[i][j] == 'X' :
            maps[i][j] = str(num)
            num += 1

def bfs(sx, sy) :
    q= deque()
    q.append([sx, sy, 1])

    while q :
        x, y, stuffs = q.popleft()
        # print(str(x)+' '+str(y)+' '+str(bin(stuffs)))

        if maps[x][y] == 'E' and stuffs == (1<<num)-1: #목표도달검사
            return vis[x][y][stuffs]

        for i in range(4) :
            nx, ny = x + dir[i][0], y+dir[i][1]

            if 0<= nx <n and 0<= ny <m and maps[nx][ny] != '#': #범위검사 &유효성검사
                if maps[nx][ny].isdigit() : #물건있는땅 밟은 경우
                    if stuffs & (1 << int(maps[nx][ny])) != int(maps[nx][ny]) : #해당물건이 보유물건목록에 없는 경우 #if stuffs & (1 << int(maps[nx][ny])) ==0 : Q.이렇게 해서 틀린 이유 모르겠음
                        if vis[nx][ny][stuffs | 1 << int(maps[nx][ny])] != 0 : continue
                        vis[nx][ny][stuffs | 1 << int(maps[nx][ny])] = vis[x][y][stuffs] + 1
                        q.append([nx, ny, stuffs | 1 << int(maps[nx][ny])])

                else : #물건없는땅 밟은 경우
                    if vis[nx][ny][stuffs] != 0 : continue
                    vis[nx][ny][stuffs] = vis[x][y][stuffs] + 1
                    q.append([nx, ny, stuffs])

print(bfs(sx, sy))
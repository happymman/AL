import sys
input = sys.stdin.readline

from collections import deque

while True :
    m, n = map(int, input().strip().split())
    if n==0 and m==0 : break

    maps = [list(input().strip()) for _ in range(n)]
    vis = [[[0] * 2048 for _ in range(m)] for _ in range(n)]
    num = 1 #num : 다음 오물번호(= 오물개수-1)
    dir = [[1,0],[0,1],[-1,0],[0,-1]]

    #시작위치, 오물 위치 등록('1', '2', ... '5')
    for i in range(n) :
        for j in range(m) :
            if maps[i][j] == 'o' :
                sx, sy = i,j
            if maps[i][j] == '*' :
                maps[i][j] = str(num)
                num += 1

    def bfs(sx, sy) :
        q= deque()
        q.append([sx, sy, 1])

        while q :
            x, y, dirtys = q.popleft()
            # print(str(x)+' '+str(y)+' '+str(bin(dirtys)))

            if dirtys == (1<<num)-1: #목표도달검사
                return vis[x][y][dirtys]

            for i in range(4) :
                nx, ny = x + dir[i][0], y+dir[i][1]

                if 0<= nx <n and 0<= ny <m and maps[nx][ny] != 'x': #범위검사 &유효성검사
                    if maps[nx][ny].isdigit() : #더러운칸 밟은 경우
                        if dirtys & (1 << int(maps[nx][ny])) != int(maps[nx][ny]) : #해당 오물이 치운오물목록에 없는 경우
                            if vis[nx][ny][dirtys | 1 << int(maps[nx][ny])] != 0 : continue
                            vis[nx][ny][dirtys | 1 << int(maps[nx][ny])] = vis[x][y][dirtys] + 1
                            q.append([nx, ny, dirtys | 1 << int(maps[nx][ny])])

                    else : #깨끗한 칸 밟은 경우
                        if vis[nx][ny][dirtys] != 0 : continue
                        vis[nx][ny][dirtys] = vis[x][y][dirtys] + 1
                        q.append([nx, ny, dirtys])
        return -1

    print(bfs(sx, sy))
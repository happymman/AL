from collections import deque

dx, dy = [[-1,1,0,0],[0,0,-1,1]]

def printmap() :
    for row in maps :
        print(*row)
    print()

def bfs() :
    while q :
        cx, cy = q.popleft() #원소꺼내기
        
        # printmap()

        for i in range(4) :
            nx, ny = cx+dx[i], cy+dy[i]

            if maps[cx][cy] == 'J' and not(0<= nx < r and 0<= ny < c) : #목표도달검사 - 지훈이가 가장자리에 도달하면
                print(d[cx][cy]+1)
                exit()

            if 0<= nx < r and 0<= ny < c: #범위검사
                if (maps[nx][ny] == 'J' or maps[nx][ny] == '.') and maps[cx][cy] == 'F' : #불 유효성+방문검사
                    maps[nx][ny] = 'F' 
                    q.append((nx, ny)) #전이

                elif (maps[nx][ny] == '.') and maps[cx][cy] == 'J' : #지훈 유효성+방문검사
                    maps[nx][ny] = 'J'
                    d[nx][ny] = d[cx][cy] + 1
                    q.append((nx, ny))  #전이


r, c = map(int, input().split())
maps = [list(input()) for _ in range(r)]
d = [[0]*c for _ in range(r)] #d : 거리배열

q= deque()

for i in range(r) :
    for j in range(c) :
        if maps[i][j] == 'J' : #지훈이를 큐에 먼저넣음
            q.append((i,j))

for i in range(r) :
    for j in range(c) :
        if maps[i][j] == 'F' : #불을 나중에 큐에 넣음
            q.append((i,j))

bfs()

print("IMPOSSIBLE")
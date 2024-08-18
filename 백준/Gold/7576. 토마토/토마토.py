from collections import deque

dx, dy = [-1,1,0,0], [0,0,-1,1]

def bfs() :
    while q :
        cx, cy = q.popleft() #원소꺼내기
        # print(str(cx)+' '+str(cy))

        for i in range(4) :
           nx, ny = cx+dx[i], cy+dy[i]

           if 0<=nx<n and 0<=ny<m : #범위검사
               if maps[nx][ny] == 0 : #유효성검사+방문검사 by map
                #    print(str(nx)+' '+str(ny))
                    d[nx][ny] = d[cx][cy]+1 #방문처리 by 거리등록
                    maps[nx][ny] = 1
                    
                    q.append((nx, ny)) #전이


m,n = map(int, input().split())
maps = [list(map(int, input().split())) for _ in range(n)]

d = [[0]*m for _ in range(n)] #d : 거리배열
q= deque()

for i in range(n) :
    for j in range(m) :
        if maps[i][j] == 1 : #익은 토마토가 있으면
            q.append((i,j))

bfs()

for row in maps :
    if 0 in row : #익지 못한 토마토가 있다면
        print(-1)
        exit()

print(max(max(row) for row in d))

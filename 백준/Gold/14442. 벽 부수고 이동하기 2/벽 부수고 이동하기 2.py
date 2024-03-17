"""
    933 똑같이 3차원배열에 0(안부숨) 1(개부숨)~K개하면 되나?
    939 어디인지 모르겠지만 시간초과 뜸
        시간복잡도 : O(V+E) = O(NMK + cNMK?) = 10의 7승?
            Q.시간복잡도 얘는 어떻게 측정할까
    950 시간초과(17%)
        도대체 왜 시간초과가 날까
        문제 다시 읽기 - 부수고 이동하는 것이 좀 더 경로가 짧아진다면? 이라는 말이 붙었는데, 이걸 미리 판단할 수 있단말인가?
"""
# import sys
# sys.stdin = open('input.txt', 'r')
# input = sys.stdin.readline

# from collections import deque

# dir = [[1,0],[0,1],[-1,0],[0,-1]]
# n,m,k = map(int, input().split())

# maps = [list(map(int, input().strip())) for _ in range(n)]
# visited = [[[False] * (1+k) for _ in range(m)] for _ in range(n)]

# def bfs(sx,sy) :
    
#     q = deque()
#     q.append([sx,sy,0,1])
    
#     while q :
#         x,y,crash,move = q.popleft() #crash : 벽 부순 개수, move : 이동횟수

#         if x==n-1 and y==m-1 : #목표 도달검사
#             print(move)
#             exit()

#         for i in range(4) :
#             nx, ny = x+dir[i][0], y+dir[i][1]

#             if 0<= nx < n and 0<= ny < m : #범위검사
#                 if maps[nx][ny] == 0 : #벽안부수고 이동 가능한 경우
#                     if visited[nx][ny][crash] : continue
#                     visited[nx][ny][crash] = True

#                     q.append([nx, ny, crash, move+1])
#                 else : #벽부셔야 이동 가능한 경우
#                     if crash == k : continue #벽 이미 다 부신 상태
#                     if visited[nx][ny][crash+1] : continue
#                     visited[nx][ny][crash+1] = True
#                     q.append([nx, ny, crash+1, move+1])
# bfs(0,0)
# print(-1)

from collections import deque
q = deque()
import sys
input = sys.stdin.readline

n,m,k = map(int, input().split())
vis = [[[0]*(k+1) for _ in range(m)] for __ in range(n)] #vis : 방문, 이동거리 배열
maps = [list(map(int,input().strip())) for _ in range(n)]
dir = [[1,0],[0,1],[-1,0],[0,-1]]

def bfs() :
    q.append([0,0,k])
    vis[0][0][k] = 1

    while q :
        x,y,crash = q.popleft() #crash : 벽부수기 가능 횟수

        if x == n-1 and y == m-1 : #목표 도달 검사
            return vis[x][y][crash] 

        for i in range(4) :
            nx ,ny = dir[i][0] + x, dir[i][1]+y

            if 0<=nx<n and 0<=ny<m : #범위검사
                if maps[nx][ny]==1 and crash>0 and vis[nx][ny][crash-1]==0: #벽부셔야 이동가능한 상태 &벽 부술수 있음 &방문한적X
                    vis[nx][ny][crash-1] = vis[x][y][crash]+1 
                    q.append([nx,ny,crash-1]) 

                elif maps[nx][ny]==0 and vis[nx][ny][crash]==0: #바로이동가능한 상태 &방문한적X
                    vis[nx][ny][crash] = vis[x][y][crash]+1 
                    q.append([nx,ny,crash])  
    return -1

print(bfs())
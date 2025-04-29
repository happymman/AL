
import sys
sys.setrecursionlimit(10**6)

dirs = [[1,0],[0,1],[-1,0],[0,-1]] #dirs : 방향배열

def dfs(cx, cy) :
    global 연합인구, 연합개수, 연합번호, 이동여부, 연합번호_지도 #?되나
    global 연합인구수, 연합개수들
    
    연합인구수[연합번호] += maps[cx][cy] #목표도달검사 - X(중도중단 필요x)
    연합개수들[연합번호] += 1
    연합번호_지도[cx][cy] = 연합번호
    vis[cx][cy] = 1 #방문처리

    for dx, dy in dirs : #4방향 탐색
        nx, ny = cx+dx, cy+dy
        if 0<=nx<n and 0<=ny<n : #범위검사
            if vis[nx][ny] : continue
            if l <= abs(maps[nx][ny] - maps[cx][cy]) <= r : #인구이동 가능조건
                이동여부 = True
                dfs(nx, ny)

def 연합인구수_조정() :
    for i in range(n) :
        for j in range(n) :
            maps[i][j] = 연합인구수[연합번호_지도[i][j]] // 연합개수들[연합번호_지도[i][j]]

n, l, r = map(int, input().split())
maps = [list(map(int, input().split())) for _ in range(n)]

이동횟수=0
while True :
    연합번호_지도 = [[0] * n for _ in range(n)]
    vis = [[0] * n for _ in range(n)]
    연합인구, 연합개수, 연합번호, 이동여부 = 0, 0, 1, False
    연합인구수 = [0] * (n*n+1)
    연합개수들 = [0] * (n*n+1)

    for i in range(n) :
        for j in range(n) : 
            if not vis[i][j] : dfs(i, j)
            연합번호+=1

    연합인구수_조정()
    if 이동여부 :
        이동횟수 +=1
    else :
        break

print(이동횟수)
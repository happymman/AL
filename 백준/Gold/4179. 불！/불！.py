"""
    940
    bfs
    탈출조건 : 미로가장자리 도달
    불 -> X : 벽


    시간복잡도 : O(V+E) = V : 10의 6승
"""
import sys
input = sys.stdin.readline
from collections import deque

r, c = map(int, input().split()) #입력받기
 
maps = [list(input().strip()) for _ in range(r)] #입력받기
d = [[0] * c for _ in range(r)]
dir = [[1,0],[0,1],[-1,0],[0,-1]]


def bfs() :

    while q : 
        x, y = q.popleft()

        for i in range(4) :
            nx, ny = x + dir[i][0] , y + dir[i][1]

            if not(0<= nx < r and 0<= ny < c) : #목표도달검사
                if maps[x][y] == 'J' : #지훈인 경우
                    print(d[x][y] + 1)
                    exit()
                else : #불인 경우
                    continue

            if maps[x][y] == 'J' : #지훈
                if maps[nx][ny] == '#' or maps[nx][ny] == 'F' or maps[nx][ny] == 'J' : continue #유효성검사 &방문검사
                maps[nx][ny] = 'J'
                d[nx][ny] = d[x][y] + 1 #거리 등록
                q.append((nx, ny))
                
            else : #불
                if maps[nx][ny] == '#' or maps[nx][ny] == 'F' : continue #유효성검사 &방문검사
                maps[nx][ny] = 'F'
                q.append((nx, ny))

q = deque()

for i in range(r) :
    for j in range(c) :
        if maps[i][j] == 'J' :
            q.append((i,j))

for i in range(r) :
    for j in range(c) :
        if maps[i][j] == 'F' :
            q.append((i,j))

bfs()
print('IMPOSSIBLE')


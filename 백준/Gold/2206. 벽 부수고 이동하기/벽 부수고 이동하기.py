"""
    소요시간 : 46m
    문제분석
        1.상태정의(+의미 명확히하기)
            위치 + 벽 부셨는지 여부 -> 1,1,1 : 1,1 격자에 위치해있으면서 벽을 이미 부순 상태
                Q.'이동횟수'도 상태인가? 아닌 것 같다.
        2.행동
            상하좌우 이동 + 벽 부수기
    - 3차원으로 생각해보기
    - 똑같은 BFS인데, 벽부쉈는지 여부에 따라서 1층, 2층여부가 달라지는
    - 한번 2층으로 올라가면, 1층으로는 못 내려옴.
    - 이런 개념인 상태에서, BFS로 최단거리 구하기
        BFS여야하는 이유 : 1.그래프 환경에서의 탐색으로 2.최단거리를 구해야하므로
    - 시간복잡도 O(V+E) -> O(NM)

    출력오류
        원인 : 문법(3차원 배열)
    피드백
        - 단순하게 상태정의하고, 해당 상태의 '의미'를 명확히 하지 않았다
            ex : 1,1,1 : 1,1 격자에 위치해있으면서 벽을 이미 부순 상태
"""
import sys
input = sys.stdin.readline

from collections import deque

dir = [[1,0],[0,1],[-1,0],[0,-1]]
n,m = map(int, input().split())

maps = [list(map(int, input().strip())) for _ in range(n)]
visited = [[[False] * 2 for _ in range(m)] for _ in range(n)]

def bfs(sx,sy) :
    
    q = deque()
    q.append([sx,sy,0,1])
    
    while q :
        x,y,crash,move = q.popleft() #crash : 벽 부섰는지 여부, move : 이동횟수

        if x==n-1 and y==m-1 : #목표 도달검사
            print(move)
            exit()

        for i in range(4) :
            nx, ny = x+dir[i][0], y+dir[i][1]

            if 0<= nx < n and 0<= ny < m : #범위검사
                if maps[nx][ny] == 0 : #벽안부수고 이동 가능한 경우
                    if visited[nx][ny][crash] : continue
                    visited[nx][ny][crash] = True

                    q.append([nx, ny, crash, move+1])
                else : #벽부셔야 이동 가능한 경우
                        #Q.벽부셔야 이동가능한 상황에서 벽을 안부수는 경우의수도 있지 않나?
                        # -> 이미지 생각하기(좌표 이동을 하는 가정하에는 벽을 무조건 부셔야함)
                    if crash ==1 : continue #벽 이미 부신 상태
                    if visited[nx][ny][crash] : continue #이게 맞나? 그림이 잘 안그려진다.
                                                         #아직도 이게 헷갈린다. 같은 좌표 다른 상태 그림을 떠올리니 해결된듯하다. 다른 상태이니, 3차원 방문배열을 통해, 방문허용(1층에 있는 그림 + 2층에 있는 그림)
                    visited[nx][ny][crash] = True
                    q.append([nx, ny, 1, move+1])
bfs(0,0)
print(-1)
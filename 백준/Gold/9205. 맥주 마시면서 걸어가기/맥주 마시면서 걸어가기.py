"""
    857
    dfs가정
    중복방문X - 중복방문할 이유 없음
"""
import sys
input = sys.stdin.readline

t =  int(input()) #입력받기

def dfs(x, y) :
    if x==tx and y==ty : #목표도달검사
        global flag
        flag = True
        return

    for i in range(len(nodes)) :
        nx, ny = nodes[i]

        if abs(x-nx) + abs(y-ny) > 1000 : continue #유효성검사
        if vis[i] != 0 : continue#방문검사
        vis[i] = 1 #방문처리
        
        dfs(nx, ny) #전이

for _ in range(t) :

    n = int(input()) #n : 편의점 개수 - 입력받기
    sx, sy = map(int, input().split()) #집좌표 입력받기
    nodes = []
    for _ in range(n) : #편의점좌표 입력받기
        px, py = map(int, input().split())
        nodes.append((px, py))
    tx, ty = map(int, input().split()) #페스티발좌표 입력받기
    nodes.append((tx, ty))
    vis = [0] * (n+1)
    
    flag = False
    dfs(sx, sy)
    print('happy' if flag else 'sad')

"""
503~, ~609
석유개수 세기 - O(250000) -> 이걸 같이 할 수 없나?
"""
from collections import deque

dirs = [[1,0],[-1,0],[0,1],[0,-1]]

def solution(land) : #10^8
    maxV=0
    vis = [[0]*len(land[0]) for _ in range(len(land))]
    land_num = [[0]*len(land[0]) for _ in range(len(land))]
    counts = [0]
    num=1 #num : 묶음번호
    
    for i in range(len(land)) : #O(500)
        for j in range(len(land[0])) : #O(500)
            if i==0 and j==2 : print(i,j)
            if not land[i][j] or vis[i][j] : continue
            
            cnt=1 #cnt : 석유개수
            vis[i][j] = 1
            land_num[i][j] = num
            
            q = deque()
            q.append((i,j)) #좌표, 석유개수
            while q :
                x,y= q.popleft()

                for d in range(4) : #방향선택
                    nx, ny = x+dirs[d][0], y+dirs[d][1]
                    if 0<=nx<len(land) and 0<=ny<len(land[0]) : #범위검사
                        if land[nx][ny] == 0 : continue #유효성검사
                        if vis[nx][ny] : continue #방문검사
                        vis[nx][ny] = 1
                        land_num[nx][ny] = num
                        cnt+=1
                        
                        q.append((nx, ny))
            # for k in range(len(vis)) :
            #     print(*vis[i])
            # print()
            counts.append(cnt) #석유개수 등록
            num+=1 
    
    maxV=0
    for j in range(len(land[0])) :
        s=set()
        for i in range(len(land)) :
            s.add(land_num[i][j])
        
        val = 0
        for v in s :
            val+=counts[v]
        maxV = max(maxV, val) #석유 max값 갱신
    print(*counts)
    return maxV
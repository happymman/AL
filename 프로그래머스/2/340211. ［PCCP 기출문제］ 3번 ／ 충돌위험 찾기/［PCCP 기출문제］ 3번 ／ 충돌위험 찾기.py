"""
    1030~1037, 1049~, 1029~, ~1204, 408시간초과
    규칙 : 최단경로 이동, r좌표 이동을 c좌표 이동 보다 먼저
    로봇2대이상 모이는 위험상황 횟수
        여러좌표 위험상황 발생시, 그 횟수 모두 더함
    
    bfs로 최단경로 찾기?
        그럴필요 있나?
            루트를 어떻게 만들지? 가정하기 - 
    
    여러번 움직이는 경우의수를 고려하지 않았음(case3)
"""
from collections import defaultdict, deque
maps = defaultdict(int)

def solution(points, routes):
    global maps
    
    ans = 0 #ans : 누적 중복좌표 개수
    q = deque()
    
    for i in range(len(routes)) : #로봇선택
        q.append((points[routes[i][0]-1][0], points[routes[i][0]-1][1], i, 1))
        maps[( points[routes[i][0]-1][0], points[routes[i][0]-1][1] )] +=1 #map찍기
    
    ans += map_count()
    
    cnt=0 #cnt : 이번턴 움직인 로봇개수
    운송_로봇개수 = len(routes)
    로봇개수 = len(routes)
    while q :
        #원소꺼내기
        x, y, i, n = q.popleft()
        
        #이동(by 목적지)
        if n < len(routes[0]) : #미도착 상태였으면(과거)
            if x>points[routes[i][n]-1][0] : #다음point x좌표
                x-=1
            elif x<points[routes[i][n]-1][0] :
                x+=1
            elif y>points[routes[i][n]-1][1] : #다음point y좌표
                y-=1
            elif y<points[routes[i][n]-1][1] :
                y+=1

            maps[(x,y)] += 1 #이동후 좌표찍기
            
            if x==points[routes[i][n]-1][0] and y==points[routes[i][n]-1][1] : #도착
                if n == len(routes[0])-1: #최종도착
                    운송_로봇개수-=1
                    n+=1
                else : #다음point index 교체
                    n+=1
            
        #다시넣기 - 도착, 도착X 모두
        q.append((x, y, i, n))
        
        cnt+=1
        if cnt==로봇개수 : #모든 로봇 점검했을때 <-의미 명확히하기
            ans+=map_count() #중복좌표 개수 판단 -> O(10^2)
            
            cnt=0 #턴 초기화
            if 운송_로봇개수 == 0 : break
        
    return ans

def map_count() :
    global maps
    overlap =0
    for v in maps.values() :
        if v > 1 : overlap+=1
    maps = defaultdict(int) #map 초기화
    return overlap
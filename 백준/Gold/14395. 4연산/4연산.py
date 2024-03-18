"""
    633 
    640 그냥 dfs로 계속하면되나? 생각이 안남
    640 아이디어가 떠오르지 않은 이유 : 관찰을 너무 안함
        7에서 4연산 '한번'한 그림'만' 그려놓고선,어떻게 해야될지 안 떠올른다고 멈춰있었음
            왜 관찰을 이렇게 적게 했을까?
                다했다고 무의식적으로 생각한 것 같다.
                    다음부턴 '관찰'을 '성실'하게 라는 원칙을 세우면 다음번에 잘할 수 있을까?
    망설이는 상황 - 원인 : 정보 부족
                행위 : '정보 수집' - 그림관찰 

    648 근데 문제 무슨 소린지 모르겠음
        7에다가 7을 더하고 7을 곱하고 7을 더하라는 소리로 해석했는데 -> 14, 98, 105임
        7 256, /+*** -> /+*** : 7/7+7*7*7 하면 1+343 = 344 이방식도 아님.
        7+7=14, 14*14 = 196, 196+196=392였음
        
        식해석을 진짜 제멋대로 했음.
        s = s + s 를 '변수'로 봤으면 됐는데, 일반 사칙연산과 비슷하게 대충 봄
    657 커지는 방향이니깐 t를 넘으면 할 필요X

    사전순으로 앞서는 것을 어떻게 구별해낼까?
        자바에서는, 리스트에 저장하고 정렬했었다.
        사전순으로 앞선다는 것은 '*++++'이 '++'보다 앞선걸로 알고 있다. 그러므로 bfs가아니라 dfs로 탐색종료될때까지 답 찾기
    / -> //인지, /인지 명확하게 구분

    최소 연산횟수이면서 사전순으로 앞선것을 출력하는것이므로,
    bfs를 통해서 진행하고, for문에서 순서를 사전순으로 탐색할 수 있게 설정해야함

    시간복잡도 : O(V+E)인데
        최악 경우의수라고 치면, 2, 10의 9승-1 일려나
        2 - 4, 0, 1 - 8 16 - 64, 32 256 - ...
        가장 느린 속도로 증가하는 노드가 2의 n승 속도로 빨라지는거로 봐서, 그리 오래걸릴거 같지는 않다.
        하지만 하나의 예시로 시간복잡도를 속단할 수 있을지에 대해서는 잘 모르겠다.

"""
import sys
input = sys.stdin.readline
from collections import deque

s, t = map(int, input().strip().split())

answer = dict() #answer = s를 t로 바꾸는 방법 모음
vis = set() #vis : 방문 숫자 모음

def bfs(s) :
    if s==t : return 0

    q = deque()
    q.append([s,''])

    while q :
        s, route = q.popleft()

        if s==t : #현재수가 t랑 같다면 - 목표도달검사
            return route #탐색 종료

        for i in range(4) :
            #i에 따라서 ns구하는 방법 다르게(but s가 0아닐때만 나눌 수 있게)
            if i==0 :
                ns = s*s
            elif i==1 :
                ns = s+s
            elif i==2 :
                ns = s-s
            elif i==3 and s!=0: 
                ns = s//s

            if ns > t : continue #t보다 더 큰지 검사
            if ns in vis : continue #중복검사
            vis.add(ns)

            if i==0 :
                q.append([ns, route+'*'])
            elif i==1 :
                q.append([ns, route+'+'])
            elif i==2 :
                q.append([ns, route+'-'])
            elif i==3 and s!=0: 
                q.append([ns, route+'/'])

vis.add(s)    
answer = bfs(s)

print(answer if answer != None else -1)

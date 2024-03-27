"""
    731
    시간제한 2초
    n <= 10의 5승

    dp - 상황 : 이전계산값 활용해서 빨라질 수 있음

    시간복잡도 - 그래프탐색 -> 1.방문노드 최대개수 세기
                         1-2.노드 정의하기(ex : 무엇이 노드인가)
              공통 -> 1.최악의 경우의수 만들기

    *tip : 노드 탐색 경우의수가 많고 복잡함을 느끼는 것에 신경쓰지 않고 -> 방문노드 최대개수 세는 것에 집중할 것
        ex : 17로 시작해서 -1, +1, //2 까지 뻗어나가는 방향이 너무 많은것처럼 느껴져서, 시간복잡도 계산을 포기
    *tip2 : log2쓰면 -> 왠만한 숫자가 상수처리된다(모든숫자라고 봐도 무방)

    상태정의
        (위치, 경로)
"""

import sys
input = sys.stdin.readline
from collections import deque

n, k = map(int, input().split())
vis = [-1] * 100001 #vis : 방문, 소요시간 배열
prev = [-1] * 100001

def printAll(num) :
    answer = []
    while True :
        answer.append(num)
        if num == prev[num] : break
        num = prev[num]
    answer.reverse()
    print(*answer)

def bfs() :

    q = deque()
    q.append(n)
    vis[n] = 0 #
    prev[n] = n

    while q :
        cur = q.popleft()

        if cur == k : #목표도달검사
            print(vis[cur])
            printAll(cur)
            exit() #왜 무한대로 출력됐지?

        for next in (cur-1, cur+1, cur*2) :
            if 0<= next <= 100000 : #범위검사
                if vis[next] != -1 : continue #방문검사
                vis[next] = vis[cur] + 1
                prev[next] = cur #이전

                q.append(next) #전이

bfs()
    
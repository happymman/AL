from itertools import combinations
import sys
INF = sys.maxsize
input = sys.stdin.readline

n = int(input())
mp,mf,ms,mv = map(int, input().split())

board = [[]]
for _ in range(n):
    p,f,s,v,c = map(int, input().split())
    board.append((p,f,s,v,c))

def solv():
    minV = INF
    minList = None

    for cnt in range(1,n+1): #식재료 선택 개수
        for comb in combinations(range(1,n+1),cnt): #식재료 조합 선택

            tp=tf=ts=tv=tc=0
            for target in comb:
                tp += board[target][0]
                tf += board[target][1]
                ts += board[target][2]
                tv += board[target][3]
                tc += board[target][4]

            if tp >= mp and tf >= mf and ts >= ms and tv >= mv: #최소영양소 충족여부 검사
                if minV > tc:
                    minV = tc
                    minList = comb
                elif minV == tc:
                    minList = sorted((minList,comb))[0]

    if minV == INF:
        print(-1)
    else:
        print(minV)
        print(*minList)

solv()
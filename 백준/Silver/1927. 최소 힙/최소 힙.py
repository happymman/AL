"""
    
"""
import sys
input = sys.stdin.readline
import heapq

pq = []

n = int(input().strip())

for _ in range(n) :
    inputNum = int(input().strip())

    if inputNum == 0 : #pop
        if not pq : print(0)
        else : 
            print(heapq.heappop(pq))
    else :
        heapq.heappush(pq, inputNum)
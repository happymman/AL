import sys
input = sys.stdin.readline
from collections import deque

mydeque = deque()

n, l = map(int, input().split()) #n : 

lists = list(map(int, input().split())) #lists : 입력 리스트

for i in range(n) :
    while mydeque and mydeque[-1][1] > lists[i] : #덱 오름차순 유지 - 덱의 첫번째 원소가 최솟값
        mydeque.pop()
    
    mydeque.append((i, lists[i]))

    if mydeque[0][0] <= i-l : #슬라이딩 윈도우 개수 유지
        mydeque.popleft()
    
    print(mydeque[0][1], end =' ')
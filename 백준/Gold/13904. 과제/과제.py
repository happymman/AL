import heapq

n = int(input())

hq = []
for i in range(n):
    d, w = map(int, input().split())
    heapq.heappush(hq, (-w, d)) #큰순으로 정렬하기 위해서

assigned = [False] * (1001)

score = 0
while hq:
    w, d = heapq.heappop(hq) #원소 꺼내기
    w = -w

    # d일부터 1일 까지 거꾸로 돌면서 비어있는 날 중에 최대한 늦게 배정
    for i in range(d, 0, -1):
        if assigned[i] : continue
        assigned[i] = True

        score += w
        break

print(score)
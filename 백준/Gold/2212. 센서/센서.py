import sys

n = int(sys.stdin.readline())
k = int(sys.stdin.readline())
pos = sorted(list(map(int, sys.stdin.readline().split())))

#집중국개수가 센서보다 같거나 많은 경우
if k >= n:
    print(0)
    sys.exit()

#차이 구하기
dist = []
for i in range(1, n):
    dist.append(pos[i] - pos[i-1])

dist.sort(reverse=True)
for _ in range(k-1):
    dist.pop(0)

print(sum(dist))
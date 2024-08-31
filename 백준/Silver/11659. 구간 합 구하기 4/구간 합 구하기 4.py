import sys
input = sys.stdin.readline

n, m = map(int, input().split())
nums = list(map(int, input().split()))
sums = []
sums.append(0)

for i in range(n) :
    sums.append(sums[i]+nums[i])

for _ in range(m) :
    s, e = map(int, input().split())

    print(sums[e] - sums[s-1])
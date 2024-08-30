import sys
input = sys.stdin.readline

n, m = map(int, input().split())
nums = list(map(int, input().split()))
sums = []

for i in range(n) :

    if i!=0 :
        sums.append(sums[i-1]+nums[i])
    else :
        sums.append(nums[i])

for _ in range(m) :
    s, e = map(int, input().split())

    if s-2 == -1 :
        print(sums[e-1])
    else :
        print(sums[e-1] - sums[s-2])
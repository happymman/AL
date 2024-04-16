n = int(input())
arr = list(map(int, input().split()))

dp = [1] * (n+1)

for i in range(len(arr)):
    for j in range(i):
        if arr[j] < arr[i]:
            dp[i] = max(dp[i], dp[j]+1)

print(max(dp))
maxV = max(dp)

result = []
for i in range(n-1, -1, -1): #역순회
    if dp[i] == maxV:
        result.append(arr[i])
        maxV -= 1

result.reverse()
print(*result)
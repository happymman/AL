
x = int(input())

arr = list(map(int, input().split()))

dp = [1 for i in range(x)]

for i in range(x): #
    for j in range(i): #이전까지만 탐색
        if arr[i] > arr[j]: #탐색값이 기준값보다 작거나 같다면, 증가수열이라고 볼 수 없다.
            dp[i] = max(dp[i], dp[j]+1) #수열최장길이 갱신

print(max(dp))
n = int(input())

wine = [0]
for _ in range(n) :
    wine.append(int(input()))

dp = [[0]*(n+1) for _ in range(3)]

for i in range(1,n+1) :
    dp[0][i] = max(dp[0][i-1], dp[1][i-1],dp[2][i-1])
    dp[1][i] = dp[0][i-1]+wine[i]
    dp[2][i] = dp[1][i-1]+wine[i]

print(max(dp[0][n],dp[1][n],dp[2][n]))
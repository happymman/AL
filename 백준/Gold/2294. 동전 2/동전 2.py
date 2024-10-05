from collections import deque

n, k = map(int, input().split())
s = set()
for _ in range(n) :
    s.add(int(input()))
arr = list(s)
dp = [-1] * 10001
dp[0] = 0

q = deque()
q.append(0)
while q : 
    cur = q.popleft() #꺼내기
    # print(cur)

    for i in range(len(s)) :
        if cur+arr[i] > k : continue #배열 범위(k범위)를 넘어가서 탐색할필요X
        if dp[cur+arr[i]] == -1 or dp[cur+arr[i]] > dp[cur] +1 : #초기값이거나 최솟값갱신할필요 있는 경우
            dp[cur+arr[i]] = dp[cur] + 1
            if cur+arr[i] == k : #k값 도달했다면
                print(dp[cur+arr[i]])
                exit()
            q.append(cur+arr[i])

print(-1)
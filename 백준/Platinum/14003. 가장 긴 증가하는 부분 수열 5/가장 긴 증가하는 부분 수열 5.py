import sys
from bisect import bisect_left
input=sys.stdin.readline

n=int(input())
arr=list(map(int,input().split()))

dp=[-float('inf')] #-inf 삽입 -> 
total=[] #역추적으로 LIS구하기용 배열

for i in range(len(arr)):
    if dp[-1]< arr[i] : #
        dp.append(arr[i])  
        total.append((len(dp)-1,arr[i])) #
    else:
        idx=bisect_left(dp, arr[i])
        dp[idx] = arr[i]
        total.append((idx,arr[i]))

index=len(dp)-1
print(index)

result=[]
for i in range(len(total)-1,-1,-1): #역추적
    if total[i][0]==index:
        result.append(total[i][1])
        index-=1

print(*result[::-1])
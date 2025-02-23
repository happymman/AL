n = int(input())

arr = []

for _ in range(n) :
    s,e = map(int, input().split()) #
    arr.append((s,e)) #

arr.sort(key=lambda x:x[1])

cnt =1
std = arr[0]
for i in range(1,n) : #
    if arr[i][0] >= std[1] or std[0] == std[1] and arr[i][1] == std[1] :
        #i)기준회의=일반 and 테스트회의 시작시간이 더 느리다
        #ii)기준회의=시작과동시에 끝나는 회의 and 테스트회의 끝시간이 동일
        cnt+=1
        std = arr[i]

print(cnt)
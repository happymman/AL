
def greedy(start, tgt) :
    start_copy = start[:]

    cnt=0 #cnt : 스위치 횟수
    for i in range(1,n) :
        if start_copy[i-1] == tgt[i-1] : continue #직전 전구 같으면 pass

        #반전
        cnt += 1
        for j in range(i-1, i+2) :
            if j<n : start_copy[j] = 1-start_copy[j]

    if start_copy == tgt : return cnt
    else : return 1e9



n = int(input())

tgt = list(map(int, input()))
start = list(map(int, input()))

ans = greedy(start, tgt)

#첫번째 스위치 누르고 시작
start[0] = 1 - start[0]
start[1] = 1 - start[1]

ans = min(ans, greedy(start, tgt)+1)

if ans != 1e9 : print(ans)
else : print(-1)

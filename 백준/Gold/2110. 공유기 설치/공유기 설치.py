n, c = map(int, input().split())
array=[]
for i in range(n) :
    array.append(int(input()))
array.sort()

s=1
e=array[-1]-array[0] #
answer =0

#코드이유 : 1.문제조건 ex : 탐색대상이 공유기사이의 거리 -> 첫,마지막 공유기의 거리가 최댓값

while(s<=e) :
    mid = (s+e)//2

    #테스트
    cur = array[0] #현재 공유기설치 집
    count=1 #공유기 설치개수

    for i in range(1,len(array)) :
        if array[i] >= cur+mid :
            cur = array[i]
            count +=1
    
    if count >= c : #공유기 설치 많이했다면
        s = mid+1
    else :
        e = mid-1

print(e)
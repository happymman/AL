import sys
import heapq

left = [] #left : 중간값 미만 pq
right = [] #right : 중간값 이상 pq

n = int(input()) #입력받기

for _ in range(n) : #n번 숫자 입력받기
    num = int(sys.stdin.readline()) #num : 입력값

    if right and right[0] > num : #중간값보다 작다
        heapq.heappush(left, -num) #왼쪽pq에 넣기
        # print(1)
    else : #중간값보다 같거나크다 - 일단 이 조건으로 해보기 
        heapq.heappush(right , num) #오른pq에 넣기
        # print(2)
    
    #크기조정
    if len(right) >= len(left)+3 : #오른쪽이 3개더많으면
        #오른pq뽑아서 왼pq넣기(-전환해서)
        tmp = heapq.heappop(right)
        heapq.heappush(left, -tmp)
        # print("왼쪽으로 옮기기")

    elif len(left) == len(right) : #왼쪽이랑 같으면
        #왼pq뽑아서 오른pq넣기(-전환해서)
        tmp = heapq.heappop(left)
        heapq.heappush(right, -tmp)
        # print("오른쪽으로 옮기기")

    # print(left)
    # print(right)
    print(right[0]) #새로운 중간값 말하기(왼pq peek값)
    # print()
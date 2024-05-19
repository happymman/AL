import sys
import heapq

n = int(input())
left = []   # 중간값을 포함한 이전 값 최대힙
right = []  # 중간값 이후의 값 최소힙

for i in range(n):
    x = int(sys.stdin.readline())

    # left가 비어있거나
    # 현재 입력값이 left에서 제일 작은 값보다 작거나 같으면
    if len(left) == 0 or -left[0] >= x:
        heapq.heappush(left, -x)
    else:
        heapq.heappush(right, x)

    # left가 right에 1을 더한 것보다 더 길면
    if len(left) > len(right) + 1:
        # left에서 제일 큰 값을 빼서 right에 넣습니다.
        temp = -heapq.heappop(left)
        heapq.heappush(right, temp)
    # right가 left보다 길면
    elif len(right) > len(left):
        # right에서 제일 작은 값을 빼서 left에 넣습니다.
        temp = heapq.heappop(right)
        heapq.heappush(left, -temp)

    # 중간값은 최대힙에서 제일 큰 값입니다.
    print(-left[0])
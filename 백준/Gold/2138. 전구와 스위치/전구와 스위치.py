n = int(input())
bulb = list(map(int, input()))
target = list(map(int, input()))


def change(bulb, target):
    bulb_copy = bulb[:]
    press = 0

    for i in range(1, n):
        # 전체 비교x, 직전만 비교o(같다면 스위치 누르지 않음)
        if bulb_copy[i-1] == target[i-1] : continue
        
        press += 1
        #전구 반전
        for j in range(i-1, i+2) :
            if j<n : bulb_copy[j] = 1 - bulb_copy[j]

    if bulb_copy == target : return press 
    else : return 1e9

answer = change(bulb, target)

# 첫번째 전구스위치 누르기
bulb[0] = 1 - bulb[0]
bulb[1] = 1 - bulb[1]

#비교
answer = min(answer, change(bulb, target) + 1)# min(첫번째 전구x, 첫번째 전구 o)
if answer != 1e9:
    print(answer)
else:
    print(-1)
"""
    생각의 흐름 :
    절댓값 가장큰수끼리 묶고,
    0 * + -> X
    - * + -> X
    0 * - -> X
    
    N=50개이기때문에, 완전탐색은 시간 많이 걸릴듯

    0부터 처리한다고 가정
    i)-처리
        -두개이상 -> 절댓값 높은애들끼리 곱
        남은 - -> 0과 곱
                없으면 그냥 더하기
    ii)남은0 -> 더하기
    iii)+처리
        절댓값 높은애들끼리 곱
        if 1 o -> 그냥 더하기

    [-4, -3, 0, 3,5,6,7]
    어떤 테케를 더 만들어야될지 모르겠다.
    로직에 영향을 주는 요소의 '가능한 경우의 수'를 다양하게 생각해봤으므로
    * 로직에 영향을 주는 요소 : 음수, 0, 1, 양수의 크기 및 개수
    -> 다양하게 조합해서 최대한의 경우의수를 만들었으면, 충분히 만들었다고 판단 가능
"""

import sys
sys.stdin = open('input.txt', 'r')
input = sys.stdin.readline

n = int(input())
minus = []
zero = []
plus = []
result = 0
for i in range(n) :
    num = int(input())
    if num < 0 :
        minus.append(num)
    elif num == 1 :
        result += 1 #넣어줄필요없이 바로 1더해주기
    elif num == 0 :
        zero.append(num)
    else :
        plus.append(num)

minus.sort()
plus.sort(reverse=True)


for i in range(0,len(minus),2) : #minus : [-4, -3, -1], zero : [0,0,0], plus : [7,6,5,3,1,1]
    if i+1 != len(minus) : #남은 음수 개수 >= 2
        result += minus[i] * minus[i+1] #i번째 i+1번째음수 곱
    else :
        if zero :
            zero.pop()
            result += 0
        else :
            result += minus[i] #i번째 음수

for i in range(0,len(plus),2) :
    if i+1 != len(plus) :
        result += plus[i] * plus[i+1] #i번째 양수 * i+1번째 양수
    else :
        result += plus[i] #i번째 양수
    
print(result)




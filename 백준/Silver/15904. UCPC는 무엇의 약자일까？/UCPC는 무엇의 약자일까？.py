"""
    233
    축약 - 문자열에서 임의의 문자들 제거
    상태 : X -> U -> UC -> UCP -> UCPC
        상태정의를 했더니 문제가 품
    테케 만들기 : UUCPC
    247 30%에서 틀림

    문제 다시 읽기
        시간복잡도 : 문자열길이 최대1000이니깐 문제없음
"""
import sys
input = sys.stdin.readline

strs = input().strip() #입력받고

flag = 0
for s in strs :
    if flag == 0 : #'U'CPC 찾는모드
        if s == 'U':
            flag += 1
    elif flag == 1 : #U'C'PC 찾는모드
        if s == 'C' :
            flag += 1
    elif flag == 2 : #UC'P'C 찾는모드
        if s == 'P':
            flag += 1
    elif flag == 3 : #UCP'C' 찾는모드
        if s == 'C':
            flag += 1
    """
    #다 못찾았으면, UCPC를 다 못찾았으면 -> UCPC로 절대 변환 불가
    #UCPC를 다 찾았는데도 축약불가능한 경우가 있는지 생각해보기

    """
    
    
print("I love UCPC" if flag==4 else "I hate UCPC")

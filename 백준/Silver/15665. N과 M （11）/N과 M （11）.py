"""
    1037
    4개중 2개뽑기
    1 7 9 9

    중복순열 뽑기 어떻게 했더라
        에 대한 내용은 직접 BT코드 내가 다시 짜보면서 떠올려야함
    어떻게 저렇게 뽑지?(1 1 2 2 있는데, 1 1 1 1 , 1 1 1 2?)
        는 가정(방법론을 공책그림에 그리기 ex : 1122 -> 12중에서만 뽑기로)
    망설이는 상황 - '아닌 것 같다'느낌 ->'일단'쓰기
                                  '일단'그림 그리기

    1027 테케오류(0%)
        처음부터 틀렸다는 사실 자체가 매우 의아.
        감이 안온다.

        문제 다시읽기
            중복되는 수열을 여러번 출력 안하지 않나 내 풀이도?
            다른 사람 풀이는 set으로 중복제거를 하지 않음.
            다른사람 풀이를 봤을 때, 너무 다른 부분이 있다면 -> 그것은 내가 '문제읽기 도중 해석의 오류가 있었고, 예제 테케에서는 그것을 판별해낼 수 없었다는 것'을 의미
    - '1 1 1 1' 출력형태를 봤을때 -> java에서는 불가능했던 1.join 2.print(*배열) 등이 파이썬에는 있으니, 상기해볼 것
    솔루션 이해 -> 예시 만들기
    변수 영역 정리
        함수안에서 선언되도 -> 글로벌?
"""
import sys
input = sys.stdin.readline

n, m = map(int, input().strip().split()) #입력받기, m : 숫자뽑는개수

nums = list(set(map(int, input().split()))) #nums : 숫자집합 - set으로 중복제거후, 리스트로 만들기면서 받기
nums.sort()

temp = []

# def dfs(route, cnt) : #'' 0 -> '1' 1 -> '1 1' 2
#     if cnt == m : #숫자 뽑아야하는 개수 다 뽑음 - #목표도달검사
#         answer.append(route) #정답리스트에 추가
#         return
    
#     for num in nums : #1 7 9 
#         #방문검사, 방문처리X - 해서 뽑은 1을 다시 뽑음
#         if cnt==0 :
#             dfs(str(num), cnt+1)
#         else :
#             dfs(route+' '+str(num), cnt+1)
# dfs('', 0)

# answer.sort()#정렬
# #출력
# for route in answer :
#     print(route)

def bt() :
    if len(temp) == m : 
        print(*temp)
        return

    for num in nums :
        temp.append(num)
        bt()
        temp.pop()
bt()
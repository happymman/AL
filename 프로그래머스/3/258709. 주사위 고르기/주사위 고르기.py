# """
#     124
#     조건
#     - 2<= dice <= 10
#     목표 : A승리확률 가장 높도록 주사위 조합(오름차순)(only 1쌍만 나옴)

# """
# from itertools import combinations, product
# from bisect import bisect_left

# def solution(dice):
#     answer = dict()
#     L = len(dice) #L : 주사위 개수
    
#     #조합만들기
#     for A주사위_조합 in combinations(range(L), L//2) : #A주사위 조합 선택
#                                                     #01 02 03 12 13 23
#         B주사위_조합 = [x for x in range(L) if x not in A주사위_조합]
    
#         #합배열만들기
#         A_sums = []
#         B_sums = []
#         for 주사위눈_순번 in product(range(6), repeat=L//2) : #주사위눈 순번을 주사위개수만큼 뽑음(ex : 000 001 002 003 ...)
#             A_sums.append(sum(dice[i][j] for i,j in zip(A주사위_조합, 주사위눈_순번)))
#             B_sums.append(sum(dice[i][j] for i,j in zip(B주사위_조합, 주사위눈_순번)))
        
#         B_sums.sort()
        
#         wins = sum(bisect_left(B_sums, A_sum) for A_sum in A_sums)
#         answer[wins] = list(A주사위_조합)
    
#     return [x+1 for x in answer[max(answer.keys())]]
    
    
"""
    목표 : A승리 확률 가장 높은 주사위 조합?
    구현방법 질문하기(ex:이걸 어떻게 구현하지?)
    다른 구현방법 질문하기(ex:for문 말고 다른건 없나?)
    BT?
        Q.어떻게해서 떠올랐나?
    
    어떻게 해야할지 모르겠다
    -> 일단 가정하기
    
    코드쓰기after(ex : 함수 완료) -> 점검하기(by (천천히)예시 가정하기, 코드돌리기)
    오류메세지 - list index out of range -> '원인후보 출력(&return)'
    - 올바른 방법의 중요성.. 아느냐 모르느냐로 수십시간 효율 차이가 난다.
    Q.가장 효율적방법 질문하기.....
    
    dice가 []빈값이라는 것은 생각치도 못했던 것이다. global
"""
from itertools import combinations
from collections import defaultdict
maxComb = []
maxV = 0
dice = []
def solution(dice_param) :
    global maxV, maxComb, dice
    dice = dice_param
    A조합목록 = combinations(range(len(dice)),len(dice)//2) 
    
    for A조합 in A조합목록 : #(0,1), (0,2), (0,3)
        #B조합 만들기
        B조합 = [x for x in range(len(dice)) if x not in A조합] #?

        Avalues = defaultdict(int)
        Bvalues = defaultdict(int)
        
        #Avalues에 넣기
        values_make(A조합,Avalues,0,0)
        #Bvalues에 넣기
        values_make(B조합,Bvalues,0,0)
        
        Awin=0
        # print(Avalues.keys())
        # print(Bvalues.keys())
        for Avalue in Avalues.keys() :
            for Bvalue in Bvalues.keys() : #Bvalue선택
                #A승리계산
                if Avalue > Bvalue : Awin += Avalues[Avalue]*Bvalues[Bvalue]
        # return
        #maxV 갱신
        if maxV < Awin :
            maxV = max(maxV, Awin)
            maxComb = [x+1 for x in A조합] #틀린이유 : n=4인 경우에 대해서만 해결책 - 왜 이렇게 썼을까? 공책그림 보고 
            print('maxV : '+str(maxV))
            print('maxComb : '+str(maxComb))
    
    return maxComb
    
def values_make(주사위목록,values,depth, 합) : #주사위 [0,1], 0,0
    if depth == len(주사위목록) :
        values[합] += 1
        return
    
    for i in range(6):#주사위 눈 선택
        #유효성검사X
        #방문검사X
        values_make(주사위목록, values,depth+1, 합+dice[주사위목록[depth]][i])
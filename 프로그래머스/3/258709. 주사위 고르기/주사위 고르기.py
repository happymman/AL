"""
    124
    조건
    - 2<= dice <= 10
    목표 : A승리확률 가장 높도록 주사위 조합(오름차순)(only 1쌍만 나옴)
    ---------------------------------------------------------------
    1,2를 가져간다고 가정하기
    for문돌고
    4,4,4,4,5,5
    5,5,5,5,6,6
    ...
    -> 4가 ~개, 5가 ~개, 6이 ~개
    -> map만들기
    
    B는 3,4
    2,2,5,5,6,6
    4,4,7,7,8,8
    ...
    
    map정렬해서 오름차순으로 정렬하고
    map끼리 순환하면서 A가 이기는 경우의수만 계산(+그때 주사위조합 저장)
    
"""
from itertools import combinations, product
from bisect import bisect_left

def solution(dice):
    answer = dict()
    L = len(dice) #L : 주사위 개수
    
    #조합만들기
    for A주사위_조합 in combinations(range(L), L//2) : #A주사위 조합 선택
                                                    #01 02 03 12 13 23
        B주사위_조합 = [x for x in range(L) if x not in A주사위_조합]
    
        #합배열만들기
        A_sums = []
        B_sums = []
        for 주사위눈_순번 in product(range(6), repeat=L//2) : #주사위눈 순번을 주사위개수만큼 뽑음(ex : 000 001 002 003 ...)
            A_sums.append(sum(dice[i][j] for i,j in zip(A주사위_조합, 주사위눈_순번)))
            B_sums.append(sum(dice[i][j] for i,j in zip(B주사위_조합, 주사위눈_순번)))
        
        B_sums.sort()
        
        wins = sum(bisect_left(B_sums, A_sum) for A_sum in A_sums)
        answer[wins] = list(A주사위_조합)
    
    return [x+1 for x in answer[max(answer.keys())]]
    
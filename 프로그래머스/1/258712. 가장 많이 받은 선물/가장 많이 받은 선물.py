# """
#     소요시간 : 40m(854~904, 946~1014)
    
#     문법 : 인덱스 문법검색, print(*A), for row in A : print(*row)
    
#     문제상황 : 'list' object cannot be interpreted as an integer
#         -> (정수필요)메써드, ([])에 감싸진 '리스트'찾기
#         원인 : 오타 - for i in range(gifts) *gifts : 리스트
        
#     출력오류 -> 1.출력오류 원인 질문하기
#               2.
# """

# def giftScore(i, A) :
#     준선물 = sum(A[i]) #!
#     받은선물=0
#     for j in range(len(A)) :
#         받은선물 += A[j][i]
#     return 준선물 - 받은선물
    
    
# def solution(friends, gifts):
    
#     A = [[0] * len(friends) for _ in range(len(friends))] #A : 선물기록
    
#     for gift in gifts : #선물기록 선택
#         give, receive = gift.split()
#         A[friends.index(give)][friends.index(receive)] += 1

#     next = [0] * len(friends)
#     for i in range(len(friends)) : #friend선택
#         for j in range(len(friends)) :
#             if i==j : continue
            
#             if A[i][j] == A[j][i] : #선물기록X or 선물주고받은개수 동일
#                 if giftScore(i,A) > giftScore(j,A) : #선물지수 큰사람이 하나받음 
#                     next[i] +=1
#                 elif giftScore(i,A) < giftScore(j,A) :
#                     next[j] +=1
#             else :
#                 if A[i][j] > A[j][i] : #선물많이 준 사람이 받음
#                     next[i] +=1
#                 elif A[i][j] < A[j][i] :
#                     next[j] +=1
    
#     return max(next)


"""
    배열 - 일반 배열
          문자인덱스 배열
          
    
          
    1118~

    
"""

def solution(friends, gifts):
    A = [[0] * len(friends) for _ in range(len(friends)) ] #A : 선물 주고받은 내역
    
    def gift_score(i) :
        give=0
        for j in range(len(A)) :
            give += A[i][j]

        receive=0
        for j in range(len(A)) :
            receive += A[j][i]

        return give - receive
    
    #선물기록 등록
    for gift in gifts : #선물기록 선택
        give, receive = gift.split()
        A[friends.index(give)][friends.index(receive)] += 1
    
    #다음달 선물받는 개수 구하기
    max = -1
    for i in range(len(A)) : #사람 선택
        cnt = 0 #cnt : 다음달 선물받는 개수
        for j in range(len(A)) : #상대 선택
            if i==j : continue
            if A[i][j] > A[j][i] : cnt+=1 #더 많이 줬을때
            elif A[i][j] == A[j][i] :
                if gift_score(i) > gift_score(j) : cnt+=1 #선물지수가 더 높은경우
    
        #최댓값 갱신
        if max < cnt : max = cnt
        
    return max #최댓값 return
    
    













"""
854~904, 946~

    
        
    *선물지수 : 준선물 - 받은선물
"""

def giftScore(i, A) :
    준선물 = sum(A[i]) #!
    받은선물=0
    for j in range(len(A)) :
        받은선물 += A[j][i]
    return 준선물 - 받은선물
    
    
def solution(friends, gifts):
    
    A = [[0] * len(friends) for _ in range(len(friends))] #A : 선물기록
    
    for gift in gifts : #선물기록 선택
        give, receive = gift.split()
        A[friends.index(give)][friends.index(receive)] += 1
    
    # print(*A)

    next = [0] * len(friends)
    for i in range(len(friends)) : #friend선택
        for j in range(len(friends)) : #
            if i==j : continue
            
            if A[i][j] == A[j][i] : #선물기록X or 선물주고받은개수 동일
                if giftScore(i,A) > giftScore(j,A) : #선물지수 큰사람이 하나받음 
                    next[i] +=1
                elif giftScore(i,A) < giftScore(j,A) :
                    next[j] +=1
            else :
                if A[i][j] > A[j][i] : #선물많이 준 사람이 받음
                    next[i] +=1
                elif A[i][j] < A[j][i] :
                    next[j] +=1
    
    return max(next) /2
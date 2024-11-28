from itertools import permutations

def solution(user_id, banned_id) :
    def convertible(user, ban) :
        if len(user) != len(ban) : return False

        for i,j in zip(user, ban) :
            if j=='*' : continue
            if i!=j : return False
        
        return True

    answer = []
    for permu in permutations(user_id, len(banned_id)) : #순열선택
        
        flag = True
        for i in range(len(banned_id)) : #순열 원소 선택
            if not convertible(permu[i], banned_id[i]) : flag = False

        if not flag : continue

        if set(permu) not in answer : #아이디 목록이 없다면
            answer.append(set(permu))
    
    return len(answer)

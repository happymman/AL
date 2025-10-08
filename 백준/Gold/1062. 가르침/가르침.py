"""
    rc, helo, car ->
    어떤 경우의수가 또 나올수가 있지? 전혀 모르겠다.
    문제 다시 읽기(놓친 조건 없는지 확인)
"""

def BT(start, 선택목록, letters) :
    global maxV
    if len(선택목록)==K-5 or len(선택목록)==len(letters) : #목표도달검사
        #차집합이 공집합이니?
        cnt=0
        for i in range(len(words)) :
            if len(words[i]-선택목록)==0 : cnt+=1
        #최댓값 갱신
        maxV = max(maxV, cnt)
        return

    for i in range(start, len(letters)) : #알파벳 선택
        선택목록.add(letters[i])
        BT(i+1, 선택목록, letters)

        선택목록.remove(letters[i])

maxV=0
#N, K 입력받기
N, K = map(int, input().split())
#단어 입력받기
letters=set()
words = []
for i in range(N) :
    temp = set(input()[4:-4]) - {'a','c','i','t','n'}
    words.append(temp)
    letters.update(words[i])
if K<=4 :
    print(0)
    exit()
#알파벳 목록 만들기
letters=list(letters)
# print(*words)

BT(0, set(), letters)
print(maxV)




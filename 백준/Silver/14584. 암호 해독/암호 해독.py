"""
100이하문자열을 1~25번 밀어서 만들기 -> 2500
    파이썬에서는 문자+3 -> 세번째다음문자 이게 안되는걸로 알고 있음
        안되는게 아니라 다른 방법 ord(), chr()메써드 사용
    답 한가지의 경우만 들어오므로 -> 굳이 1~25만 할필요없이, +1만큼 계속 밀어보면 됨
- 단어 dict에서 있나 확인하기(20개)
    굳이 dict 쓸 필요없이 list로도 차이x?
    포함검사 - find()되지 않나? in
시간 : 50000

"""
import sys
input = sys.stdin.readline

word = input().strip()
num = int(input())
tgt_word_list = []

for i in range(num) :
    tgt_word_list.append(input().strip())

while True :
    shifted_word_list = []
    
    for s in word : #srbvaffefeczevaluxv
        shifted_word_list.append(chr((((ord(s)-97)+1)%26)+97)) #빼기보정(to 0~끝 범위) -> 이동 -> 나머지 보정(to 0~끝 범위)
        
    shifted_word = "".join(shifted_word_list)

    #있나 확인
    for tgt_word in tgt_word_list :
    
        if shifted_word.find(tgt_word) != -1 : #찾았으면
            print(shifted_word)
            exit()
    
    word = shifted_word #한칸 옮긴 단어로 변환
"""
    소요시간 : 1h30m
    문제상황 : 시간초과(+무한루프) -> 종료검사 - 종료검사로직 오타/오류
                                        return유무
    문제상황 : 메모리 초과 -> 스택오버플로우

    상황 : 전체로직 완료 -> 시간복잡도(+메모리) 계산('완벽하게')
        이유 : 전체로직 수정 가능성O(1시간 낭비 가능)
        *이전로직 시간복잡도 : 10의 15승

    문제상황 : 시간 초과(+메모리 초과 for 재귀) -> for문 변경 가능여부 검토
                                           로직 자체 변경(ex : bottom up -> top down)    

    시간복잡도 : 풀로 다하면 10의 15승이지만
        But 중간에 발산 안하는 경우가 매우 많이 생기기 때문에, 시간 매우 줄여짐


"""
import sys
sys.stdin = open('input.txt', 'r')
input = sys.stdin.readline

s = input().strip()
t = input().strip()

def dfs(cur) : #cur : 현재문자
    if len(cur) == len(s) :
        if cur == s : #s로 변환가능
            print(1)
            exit()
        return
    
    if cur[-1] == 'A' : #맨뒤에 A이면 -> A뺀애를 dfs
        dfs(cur[:-1])
    if cur[0] == 'B' : #맨앞에B이면, -> 앞B빼고 뒤집기 dfs
        dfs(cur[1:][::-1])
    
dfs(t)
print(0) 

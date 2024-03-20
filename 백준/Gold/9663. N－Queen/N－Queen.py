"""
    소요시간 : 40m(+40m 시간낭비 for 파이썬 시간)
    
    생각의 흐름 : 
    0번째열부터 퀸 놓을 수 있는 자리 놓고
    ... 1번째 ~
    그러다가 8(n)개 되면 방법수 +=1

    0열 퀸 놓을때 검사 -> X
    1열 퀸 놓을때 검사 -> 0
    ~ -> 0~1
    ~ -> 0~2 열 퀸 검사

    출력오류 -> 테케 돌려보기(천천히, 코드 이유 설명하기 + 변수의미, 함수의미 주석달기)
           원인 : 문법 : in range(0, cCol-1) #이전열까지 검사 -> 이미 cCol이여도 이전열까지만 검사인데
           코드 종류 - in range(0,end) -> in (0~end-1) 그림그리기
    
    테케 돌리기 - DFS, BFS, BT -> for문 마지막 부분 잊지말고 돌려보기 ex : N-queen
    
"""
import sys
sys.stdin = open('input.txt', 'r')
input = sys.stdin.readline

n = int(input().strip())

queen = [-1 for _ in range(n)]

cnt=0 
def BT(cCol) :
    global cnt

    if cCol == n : #목표 도달 검사
        cnt +=1 #놓는 방법 개수 증가
        return

    for i in range(n) :
        queen[cCol] = i #퀸 위치시키기 - 통과하기전 위치시켜도 되는 이유 : 통과안되면, 다음열로 안넘어가므로, 상관X
        if not canLocate(cCol) : continue

        BT(cCol+1)
        #퀸 위치빼기 - 안해도 되는게, 다시 덮어쓰이기전에, 빼야하는 퀸의 영향을 받는 부분이 없음
        
def canLocate(cCol) : #현재열에 퀸을 놓을 수 있는지 없는지 검사
    for col in range(0, cCol) : #이전열에 놓여져있는 퀸 검사
        if queen[col] == queen[cCol] : #같은행에 있는지 검사
            return False
        if abs(col-cCol) == abs(queen[col] - queen[cCol]) : #대각위치에 있는지 검사
            return False
    
    return True

BT(0)
print(cnt)
    

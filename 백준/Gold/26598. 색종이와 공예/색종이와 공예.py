"""
    921 모두 직사각형 모양인지 검사
    직사각형 모양이라는 것을 어떻게 알아낼까

    상태 정의
    1.새로운 알파벳 발견(직사각형 여부 검사 mode)
    -> 2.직사각형 변길이 판단 mode : 오른쪽, 아래 해당 알파벳 유지될때까지 계속 이동
        테두리 안쪽애들 -> 전부 같은 알파벳인지 검사
        테두리 애들 - 아래 -> 같은 알파벳 &아래도 같은 알파벳인지 검사
                    옆 -> 같은 알파벳 &옆도 같은 알파벳인지 검사
    
    940 이걸 격자 순회하면서 진행 &중복방문 방지처리

    949 수업받은대로 해보기
        952 그림 완성 But 이런 방식이 맞는건진 잘 모르겠음
    953 탐색 방법 결정 - 내가 세운 로직상으로는 dfs, bfs, bt, dp 중 어디에 속하는지 모르겠다
    955 시간복잡도 분석 - O(nm) 노드를 까보니깐

    1011 수도코드 완료
    1024 구체코드 완료
    1026 컴파일 오류 수정 완료
    1026 출력오류
        1033 방문처리를 안했다라는 사실을 발견 - 왜 안했을까? 수도코드 로직 쓸때는 기억하고 있었음(군더더기 없이 저정도만 쓰고 옮길때 하면되겠지라는 생각 But 구체코드 쓸때 까먹음)
        1047 check()에서 return하면 완전히 밖으로 나간다고 착각(->exit()로 변경)(fact : 다른 포문들을 돌게됨)
    1105(1025) 시간초과(15%)
        왜 시간초과가 날까? 난 중복방문 방지했다고 생각했는데 -> 그부분 검토
        테두리 부분에 대한 방문처리를 안해줬다.
    1120 배열인덱스범위 오류
        visited[x][right+1] = True 하기전에
        right += 1 를 해버려서, 원하는 위치에 방문처리 못함. 말미에는 범위를 벗어남
        오류원인 : 방문처리를 안했다 -> 방문처리'만'하면 된다 -> 방문처리 코드 넣고 검토 안함
        상황 : 코드추가, 변경시 -> 영향 주는, 영향 받는 범위를 무조건 검토해야함
    1124 출력오류(93%)
        답지 봄.
        답지봤는데, BFS라고해서 충격받음
        '직사각형 = 가로 x 세로'성질을 이용해서, BFS 탐색노드랑 개수가 맞지 않을때, 직사각형이 아니다라는 판단
        BFS를 하면서, 
        알게된 사실 : BFS를 통해서, 탐색노드 중 

        테스트케이스 만들어보기
            가장 작은 경우의수 1 1 A -> 통과 : 잘 모르겠다
        반례게시판의 반례
            2 2
            AB
            BB
            를 통과하지 못함.
            왜 해당 케이스를 고려하지 못했을까?
                일단 BFS로 풀었으면, 자동적으로 고려될 케이스
                하지만, 내 풀이로도 풀 수 있었어야함.
                해당 케이스를 고려하지 못한 이유는 다음과 같다.

                     1.새로운 알파벳 발견(직사각형 여부 검사 mode)
                    -> 2.직사각형 변길이 판단 mode : 오른쪽, 아래 해당 알파벳 유지될때까지 계속 이동
                    테두리 안쪽애들 -> 전부 같은 알파벳인지 검사
                    테두리 애들 - 아래 -> 같은 알파벳 &아래도 같은 알파벳인지 검사
                               옆 -> 같은 알파벳 &옆도 같은 알파벳인지 검사
                해당 로직을 짤 때, 다른 경우의 수를 만들어볼 생각을 못함.
                    그저, 1번테케 통과 가능, 2번테케 통과 가능하다라는 사실에만 초점
                    이런 경우는 어떨까? 하면서 주어진 테스트 케이스의 변형 형태를 머릿속으로 그림그릴 수 있어야함.
                    문제점은 이렇게 테스트케이스를 만드는 모드에 들어가면, '모든 테스트 케이스를 다 만들었다'라는 확신을 얻기 힘들어 함
                    그렇기 때문에, 로직 작성시 테스트케이스 케이스 만드는 mode에 돌입하지 못했던 것 같다.

                    상황 - 로직 작성
                          메인 로직 작성 -> 모든 상황 경우의 수 고려 - 1.주어진 tc -> 변형
                                                              2.가장 작은 tc -> 변형(to 증가시키기)
                                                              3.가장 큰 tc
                                                              4.특이한 tc
                                                            How. 1.tc 공책그림 후
                                                                 2.천천히 그림

                    으로 분류하여 인식하고, '메인 로직 작성'시에는 반드시 여러가지 tc를 검증하는 절차를 가져보는걸로 연습해보기 
                    '메인 로직 작성시, 모든 상황 경우의 수를 고려'해야하는 이유는 5분 고민을 안해서, 2시간을 날릴 수 있기 때문(해당 문제를 풀때 발생한 시간 손실)
"""
# 1차 잘못된 풀이
# import sys
# sys.stdin = open('input.txt', 'r')
# input = sys.stdin.readline

# n, m = map(int, input().strip().split())

# graph = [list(input().strip()) for _ in range(n)]
# visited = [[False] * m for _ in range(n)]
# flag = True

# def check(x,y) :
#     global visited
#     std = graph[x][y] #기준알파벳 저장

#     left, up = y,x
#     right, down = y, x
#     while right+1 < m and graph[x][right+1] == std : # y+1하면서 언제까지 같은 알파벳인지 검사(with 범위 안벗어나기)
#         visited[x][right+1] = True
#         right += 1
#     while down+1 < n and graph[down+1][y] == std : # x+1하면서 언제까지 같은 알파벳인지 검사(with 범위 안벗어나기)
#         visited[down+1][y] = True
#         down += 1 

#     #왼쪽,위테두리 제외한 부분 검사(직사각형 여부 검사)
#     for i in range(up+1, down+1):
#         for j in range(left+1, right+1):

#             if std != graph[i][j] : #같은 알파벳인지 검사
#                 print("BaboBabo")
#                 exit()
#             if i == down  : #아래테두리 -> 아래는 다른 알파벳인지 검사
#                 if i+1 < n and graph[i+1][j] == std : #!로직 실수
#                     print("BaboBabo")
#                     exit()
#             if j == right : #오른쪽 테두리 -> &오른쪽은 다른 알파벳인지 검사
#                 if j+1 < m and graph[i][j+1] == std :
#                     print("BaboBabo")
#                     exit()

#             visited[i][j] = True #방문처리

# for i in range(n) :
#     for j in range(m) :
#         if visited[i][j] : continue #방문여부 검사
#         visited[i][j] = True

#         check(i,j)

# print("dd")

"""
    
"""
import sys
input = sys.stdin.readline

from collections import deque

n, m = map(int, input().strip().split())
maps = [list(input().strip()) for _ in range(n)]
visited = [[False] * m for _ in range(n)]

dir = [[1,0],[0,1],[-1,0],[0,-1]]

def bfs(sx, sy) : #sx,sy : 탐색시작x, 탐색시작y좌표
    q = deque()
    q.append([sx,sy])

    std = maps[sx][sy] #std : 기준 알파벳
    left, right, up, down = sy, sy, sx, sx #최상단, 최하단, 최좌단, 최우단 좌표
    cnt = 0 #cnt : 탐색범위 넓이

    while q :
        x,y = q.popleft()

        #탐색범위 정보 업데이트
        left, right, up, down = min(left,y), max(right,y), min(up,x), max(down, x) 
        cnt +=1

        for d in range(4) :
            nx, ny = x + dir[d][0], y + dir[d][1]

            if 0<= nx <n and 0<= ny <m : #범위검사
                if maps[nx][ny] != std : continue #유효성검사
                if visited[nx][ny] : continue #방문검사
                visited[nx][ny] = True

                q.append([nx, ny])

    return [cnt, left, right, up, down]
                

for i in range(n) :
    for j in range(m) :
        if visited[i][j] : continue
        visited[i][j] = True

        cnt, left, right, up, down = bfs(i,j)

        if cnt != (right-left+1)*(down-up+1) :
            print("BaboBabo")
            exit()

print("dd")
    

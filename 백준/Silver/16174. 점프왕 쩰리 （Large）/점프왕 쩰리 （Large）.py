"""
    bt로 -> dfs로
        Q.왜 내가 bt라고 생각했지? 개념 다시 검색해보니, 굳이 분리하기 애매한 개념일수도 있다고한다. 질문하기
    이동가능수대로, 2가지 방향 전이 탐색
    if 도착하면 하루하루출력하고
    if 범위벗어나면 hing

    2의 12승 = 4000 -> 시간복잡도 염려 없을 것 같음.

    시간초과
        문제조건 다시봄. 0이 될수도 있다는 조건을 놓침 - 0대입 -> 무한루프돔
    시간초과(37%)
        dfs 재귀 시간복잡도에 대해서 다시 학습
        4방향이 아니라 2방향이라고 중복방문의 여지가 없다고 근거없이 생각함
            New(한번도 해본적 없는 로직)라는 것을 인지하고 -> 조심스럽게, 명확한 논리적 근거를 가지고 생각할 것
            내가 아는것, 해본것과 모르는 것과 안해본 것과의 명확한 구분하는 능력 부족이 많이 느껴짐
        dfs 시간복잡도 = O(V+E)
            상황 : n*m 4방향 격자
                V = nm, E = 2nm - n - m 이므로
                O(nm)라고 생각해도됨

"""
import sys
input = sys.stdin.readline

def dfs(x,y) :
    if x == n-1 and y == n-1 :
        print("HaruHaru")
        exit()

    move = graph[x][y]
    if move == 0 : return

    for d in [[1,0],[0,1]] :
        nx, ny = x+d[0]*move , y+d[1]*move

        if 0<= nx < n and 0 <= ny < n :
            if visited[nx][ny] : continue
            visited[nx][ny] = True

            dfs(nx, ny)


n = int(input())
graph = [list(map(int, input().strip().split())) for _ in range(n)]
visited = [[False] * n for _ in range(n)]

dfs(0,0)
print("Hing")
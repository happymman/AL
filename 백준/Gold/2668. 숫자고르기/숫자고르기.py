import sys
from collections import defaultdict
sys.setrecursionlimit(10**6)

def dfs(u, visited): #u : 현재 방문정점, visted : 현재탐색에서의 방문목록
    visited.add(u)
    checked[u] = 1

    for v in g[u]: 
        if v not in visited: #방문검사
            #방문처리
            dfs(v, visited.copy()) #~.copy() : 깊은복사?를 왜 해야하는데?
        else: # 사이클이 생기면 뽑는다. 요게 딱 어떤순간인데?를 알려면 간단한예시'부터' 코드 돌려보기
            result.extend(list(visited))
            return

n = int(sys.stdin.readline().strip())
g = defaultdict(list)
for i in range(1, n+1):
    v = int(sys.stdin.readline().strip())
    g[v].append(i)

checked = [0 for _ in range(n+1)] #checked : 방문배열
result = []
for i in range(1, n+1):
    if not checked[i]:
        dfs(i, set())

result.sort()
print(len(result)) 
for x in result:
    print(x)
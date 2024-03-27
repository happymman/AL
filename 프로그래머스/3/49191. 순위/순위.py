"""
    1 - 2
    2 - 5
    3 - 2
    4 - 2,3
    5
    
    dfs탐색시, 
    
    인접리스트 - 상황 : 단방향
    인접배열 - 상황 : 양방향
"""

def win_dfs(n, vis, win_tree) :
    for next in win_tree[n] :
        if vis[next] != 0 : continue #방문검사
        vis[next] = 1
        
        win_dfs(next, vis, win_tree)
        
def lose_dfs(n, vis, lose_tree) :
    for next in lose_tree[n] :
        if vis[next] != 0 : continue #방문검사
        vis[next] = 1
        
        lose_dfs(next, vis, lose_tree)
        
def solution(n, results):
    win_tree = [[] for _ in range(n+1)]
    lose_tree = [[] for _ in range(n+1)]
    
    for result in results :
        win = result[0]
        lose = result[1]
        
        win_tree[win].append(lose)
        lose_tree[lose].append(win)
    
    answer =0
    for i in range(1, n+1) :
        vis = [0] *(n+1) #리스트가 mutable 객체이므로 주소값 공유
        vis[i] = 1
        win_dfs(i, vis, win_tree)
        lose_dfs(i, vis, lose_tree)
        if sum(vis) == n : answer += 1
        
    return answer
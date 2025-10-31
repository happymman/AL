from collections import deque

# ✅ 방향 벡터 (상하좌우)
dr = [1, -1, 0, 0]
dc = [0, 0, 1, -1]


def normalize(block):
    """블록 좌표를 (0,0) 기준으로 정규화 + 정렬"""
    block = sorted(block)
    min_r = block[0][0]
    min_c = min(c for _, c in block)
    return sorted([(r - min_r, c - min_c) for r, c in block])


def rotate(block):
    """블록을 90도 회전 후 정규화"""
    rotated = [(c, -r) for r, c in block]
    return normalize(rotated)


def find_blocks(board, target):
    """board 내에서 target(0 또는 1)인 영역을 BFS로 찾아 블록 리스트 반환"""
    n = len(board)
    visited = [[False] * n for _ in range(n)]
    blocks = []

    for r in range(n):
        for c in range(n):
            if board[r][c] == target and not visited[r][c]:
                queue = deque([(r, c)])
                visited[r][c] = True
                block = [(r, c)]

                while queue:
                    cr, cc = queue.popleft()
                    for i in range(4):
                        nr, nc = cr + dr[i], cc + dc[i]
                        if (0 <= nr < n and 0 <= nc < n
                        and not visited[nr][nc]
                        and board[nr][nc] == target):
                            
                            visited[nr][nc] = True
                            queue.append((nr, nc))
                            block.append((nr, nc))

                blocks.append(normalize(block))

    return blocks


def match(blank, puzzle):
    """blank와 puzzle이 회전 시 포함 동일한지 판단"""
    if len(blank) != len(puzzle):
        return False

    rotated = puzzle
    for _ in range(4):
        if blank == rotated:
            return True
        rotated = rotate(rotated)

    return False


def solution(game_board, table):
    blanks = find_blocks(game_board, 0)
    puzzles = find_blocks(table, 1)

    used = [False] * len(puzzles)
    answer = 0

    for blank in blanks:
        for i in range(len(puzzles)):
            if used[i] or len(blank) != len(puzzles[i]):
                continue

            if match(blank, puzzles[i]):
                answer += len(blank)
                used[i] = True
                break

    return answer

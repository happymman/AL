import sys
input = sys.stdin.readline

def move_cost(past_op, now_op):
    """발 이동 비용 계산"""
    if past_op == 0:  # 0(중앙)에서 출발
        return 2
    elif past_op == now_op:  # 같은 위치
        return 1
    elif (past_op + now_op) % 2 == 0:  # 반대방향 (1+3=4, 2+4=6)
        return 4
    else:  # 인접방향
        return 3

def solution():
    commands = list(map(int, input().split()))
    steps = []
    
    for cmd in commands:
        if cmd == 0: break  # 종료 신호
        steps.append(cmd)
    
    if not steps:  # 명령이 없는 경우
        print(0)
        return
    
    MAX_VALUE = 100000 * 4
    n = len(steps)
    
    # dp[왼발][오른발][step] = 최소 비용
    dp = [[[MAX_VALUE] * (n+1) for _ in range(5)] for _ in range(5)]
    
    # 초기 상태: 두 발 모두 중앙
    dp[0][0][0] = 0
    
    # DP 진행
    for idx in range(n):
        op = steps[idx]  # 이번에 밟을 위치 (1,2,3,4 중 하나)
        
        for i in range(5):  # 왼발 위치
            for j in range(5):  # 오른발 위치
                if dp[i][j][idx] == MAX_VALUE:
                    continue
                
                # 오른발을 op로 이동 (왼발 i 유지)
                if op != i:  # op는 1~4, i가 op와 다르면 OK
                    dp[i][op][idx+1] = min(dp[i][op][idx+1], 
                                          dp[i][j][idx] + move_cost(j, op))
                
                # 왼발을 op로 이동 (오른발 j 유지)
                if op != j:  # op는 1~4, j가 op와 다르면 OK
                    dp[op][j][idx+1] = min(dp[op][j][idx+1], 
                                          dp[i][j][idx] + move_cost(i, op))
    
    # 정답 계산
    answer = MAX_VALUE
    for i in range(5):
        for j in range(5):
            answer = min(answer, dp[i][j][n])
    
    print(answer)

solution()
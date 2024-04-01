""" 
    스택, 큐 - 1.원소 대상(값, '인덱스')
             2.방법(다넣고 시작, '하나씩 넣기')
             3.pop대상 = 더이상 저장필요없는 대상(해당대상에 대한 정보가 구해져서)(ex : 2보다 더큰수가 들어와서 3으로 정해졌으니, 더이상 구할필요가 없다)
                   
"""
def solution(numbers):
    stack = []
    answer = [-1] * len(numbers) #초기값 설정

    for i, num in enumerate(numbers):
        while stack and numbers[stack[-1]] < num: #pop
            idx = stack.pop()
            answer[idx] = num
        stack.append(i)

    return answer
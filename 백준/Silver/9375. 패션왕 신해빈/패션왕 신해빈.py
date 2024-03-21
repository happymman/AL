"""
    
"""
import sys
input = sys.stdin.readline

n = int(input())

for _ in range(n) :
    dic = {}
    m = int(input())

    for _ in range(m) :
        _, k = input().split()

        dic[k] = dic.get(k,0)+1

    answer = 1
    for v in dic.values() :
        answer *= v+1
    print(answer-1)
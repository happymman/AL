"""
    822
    문제조건
    - 나라별 최대 메달 개수 2개

    정렬문제인 것 같다. 파이썬에서는 3번째에만
    '1 1' : 230 을 저장할까?
"""
import sys
input = sys.stdin.readline

n = int(input().strip())

arr = []
award = {}

for _ in range(n) :
    country, student, grade = map(int, input().strip().split())

    if country not in award.keys() : award[country] = 0
    arr.append((country, student, grade))

arr = sorted(arr, key= lambda x:x[2], reverse = True)


num = 0
for entry in arr :
    if award[entry[0]] != 2 :
        award[entry[0]] += 1
        num +=1
        print(str(entry[0])+' '+str(entry[1]))
    
    if num ==3 : break


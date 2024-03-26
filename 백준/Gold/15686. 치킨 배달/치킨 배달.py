"""
    소요시갼 : 30m
    도시 치킨거리 = 모든집의 치킨거리 합
    치킨거리 = 가장 가까운 치킨집 사이의 거리
    2<= N <= 50, 1<= 치킨집 개수 <= 3

    치킨집 m개 고르기(조합)(13CM = 13*12*11*10*9*8 / 1,2,3,4,5,6 = 약 10의 3승)
    집마다 치킨집과의 거리 비교해보기(2n개 * m개)(100 * 10)

    TypeError: 'list' object is not callable
    -> 호출 불가능한 대상(list object)을 호출(=함수처럼 사용) 
"""


import sys
input = sys.stdin.readline
MAX = sys.maxsize

n, m = map(int, input().split())#입력받기
maps = [list(map(int, input().split())) for _ in range(n)] #

chickens = []
houses = []
for i in range(n) :
    for j in range(n) :
        if maps[i][j] == 2 :
            chickens.append((i,j))
        elif maps[i][j] == 1 :
            houses.append((i,j))


combs = []
#조합(미리 만들어놓기)
def make_comb(start, route) :
    if len(route) == m : #목표도달검사
        global combs
        combs.append(route) #조합등록
        return

    for i in range(start, len(chickens)) :
        make_comb(i+1, route+[chickens[i]]) #이번고른거 다음거부터 고르기 

make_comb(0, [])

minV = MAX
for comb in combs : #치킨집 조합 선택
    cDistSum = 0 #조합별 도시치킨거리
    for house in houses : #집선택
        cDist = MAX
        for chicken in comb : #치킨집 선택
            dist = abs(chicken[0] - house[0]) + abs(chicken[1] - house[1])
            cDist = min(cDist, dist) 
        cDistSum += cDist
    
    minV = min(minV, cDistSum) #최소 도시치킨거리 갱신

print(minV)
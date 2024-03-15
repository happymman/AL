"""
    1155
    1158 연속된 A가 끝나는 곳
    1200 무슨 기준이지?라며 명확한 논리를 원했지만,
        이 행동을 반복, '다해보면 되는 것'(완탐)(+최솟값 찾기)(O(n)만큼 탐색)
        *원칙 : 완전탐색 먼저 시간복잡도 측정
    115~141, 146~152
    152 알파벳 바꾸는걸(위아래 이동) 까먹음
    157 85점
        히든테케오류
            1.모두 점검(처음부터 코드 읽기)(천천히, 그림)
                도저히 모르겠다
            2.테케 만들기
"""
def solution(name):
    
    sum=0
    for s in name :
       sum += min(ord(s)-ord('A'), ord('Z')-ord(s)+1)

    minV = len(name)-1 #최댓값 설정(A가 없을때)
    for i in range(len(name)) :
        if name[i] != 'A' : continue
        #A찾음
        a_start, a_end = i, i
        
        while a_end+1 < len(name) and name[a_end+1] == 'A' :
            a_end += 1
        
        if a_start !=0 :
            first = (a_start-1)*2 + len(name) - a_end -1
            second = a_start-1 + (len(name) - a_end -1)*2
            minV = min(minV , first, second)
        else :
            first = len(name) - a_end -1
            second = (len(name) - a_end -1)*2
            minV = min(minV , first, second)
    return sum+minV
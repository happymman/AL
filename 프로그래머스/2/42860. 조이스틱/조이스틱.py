"""
    소요시간 : 1h
    방법론 가정 원칙 : 완전탐색 먼저 시간복잡도 측정
        이유 : ifX 최소최대값의 논리가 없는 경우에도 계속 찾고 있음
            ifO -> 완탐 시간복잡도 측정으로 유효한 방법론 유추 가능
    
    AL 방법론
    1.문제분석
    2.수도코드 - 수도코드 작성
               수도코드 작성 완료 -> 테케 돌리기 - 이유 : 빼먹은 문제조건 발견 가능(ex : 알파벳 바꾸는걸(위아래 이동) 까먹음)
    3.구체코드 - 구체코드 작성

    히든테케오류 -> 1.모두 점검(처음부터 코드 '설명하기')(천천히, 그림)
                    이유 : '설명하기'행위가 시간이 오래걸리더라도, 한번에 찾을 수 있는 방법이긴함
                2.테케 만들기
    메인로직 작성ing -> 성립 이유 설명하기(in 모든케이스)(ex : '시작인덱스-1'라고 가정후, 모든케이스에서 성립함을 바로 test했어야했다)
    메인로직 작성 -> 1.주어진 tc검증 -> 변형
                  2.가장 작은tc검증 -> 변형
                  3.가장 큰tc검증(for 시간복잡도)
                  4.특이한 tc ex : A가 맨처음부터 시작하는 케이스
           how - tc를 공책에다 그린후, 천천히 그림그려서 '메인로직'에 대입하기
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

"""
    소요시간 : 1h 10m(파이썬 문법숙달 및 연구모드)
    
    - 동일 유저 신고횟수는 1회로 처리
    - k번 이상 신고된 유저 -> 정지
    
    id_list랑 result순서 대응 방법
        그냥 신고, 피신고맵 따로 만든 이후에
        id_list for문 돌림으로써, 순서를 유지하면 된다.
    유형 : 구현(해시)(in 프로그래머스) -> 문제 조건에서 연상되는 자료구조(가정 & 확인)
    문제조건 : 중복 -> 애초에 중복제거함으로써, 로직 줄일 수 있는지 검토(like n과m(11))
"""
from collections import defaultdict
def solution(id_list, reports, k):
    result = []
    
    reports = list(set(reports)) #중복 신고 결과 제거
    
    reporterD = defaultdict(set)
    reportedD = defaultdict(int)
    
    #신고 등록
    for report in reports : #report 선택
        reporter, reported = report.split() #reporter : 신고자, reported : 피신고자
        
        if reported not in reporterD[reporter] : #신고자 신고목록에 없으면
            reporterD[reporter].add(reported) #신고자 신고목록에 추가
            reportedD[reported] = reportedD.get(reported, 0)+1
    
    #기준 신고횟수 넘어간 사람 등록
    for id in id_list : #신고자 선택
        sum=0
        for reported in reporterD[id] : #피신고자 선택
            if reportedD[reported] >= k : sum +=1
        result.append(sum)

    return result

"""
    1050~1055, 150~2, 210~230, 237~246, 252~307, 312~
    - 동일 유저 신고횟수는 1회로 처리
    - k번 이상 신고된 유저 -> 정지
    
    id_list랑 result순서 대응을 어떻게할까?
        그냥 신고, 피신고맵 따로 만든 이후에
        id_list for문 돌림으로써, 순서를 유지하면 된다.
    신고dict, 피신고dict를 떠올릴 수 있는 방법('문제에서 힌트줌' -> 그걸로 가정)
"""
def solution(id_list, reports, k):
    result = []
    
    reported_dict = {}
    reporter_dict = {}
    for id in id_list :
        reporter_dict[id] = set()
    
    #신고
    for report in reports : #report 선택
        reporter, reported = report.split() #reporter : 신고자, reported : 피신고자
        
        if reported not in reporter_dict[reporter] : #신고자 신고목록에 없으면
            reporter_dict[reporter].add(reported) #신고자 신고목록에 추가
            reported_dict[reported] = reported_dict.get(reported, 0)+1
        
    stop = set()
    for key, value in reported_dict.items() :
        if value >= k : stop.add(key) #정지목록 = 신고횟수 넘는 사람 추가
    
    #정지 목록에 있는 사람이 몇명있는지 체크
    for id in id_list : #신고자 선택
        sum=0
        for reported in reporter_dict[id] : #피신고자 선택
            if reported in stop : sum +=1
        result.append(sum)

    return result
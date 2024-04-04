"""
    소요시간 : 50m 
    조건 : n<= 1000

    피드백
    1.우선순위큐 - 원소 : [리스트] -> 일때 전부 안넣어도 됨. ex : 퇴실시간만 넣어도됨
    2.코드 - All -> 코드그림 보기/그리기(특히, 배열[인덱스] 코드 작성할때, 확인해줘야한다. 실수 자주 발생)
    3.투포인터 - O상황 : FIFO, 우선순위FO(1개의 기준)
        포인터 간격을 방개수라고 가정했을때, 일관된 기준에의해서 포인터 이동이 불가
            이유 : 기준2개(입실시간, 퇴실시간)
"""
import heapq
def convert(time) :
    inHour, inMinute = map(int, time[0].split(':'))
    outHour, outMinute = map(int, time[1].split(':'))
    
    return [inHour*60+inMinute, outHour*60+outMinute+10]

def solution(book_time):
    
    #대기열 시간변환(입실시간-> 그대로, 퇴실시간-> +10)
    book_time = list(map(convert, book_time))
        
    #대기열 정렬(입실시간 기준(거꾸로) - pop for O(1))
    book_time.sort(key = lambda x:x[0], reverse = True)
    
    pq = [] # pq : 호텔
    
    maxV = 0
    while book_time : #대기열 안비었을때
        time = book_time[-1][0] #시간 업데이트(by 다음 입실시간)(of 대기열)
        
        while pq and pq[0] <= time : #퇴실 가능
             heapq.heappop(pq) #퇴실(from 호텔)
        
        while book_time and book_time[-1][0] <= time : #입실 가능
            heapq.heappush(pq, book_time.pop()[1]) #입실(from 대기열)
        
        maxV = max(maxV, len(pq)) #방개수 최댓값 업데이트
    
    return maxV

"""
    704~711, 719~731
    n<= 1000
    
    이걸 어떻게 하지?
    1개 필요하다가 2개 필요 
    
    735 뒤늦게 투포인터 풀이여부를 판단했다.
        판단 기준이 한개만 있으면, 가능한거 같은데
        입실 퇴실시간 기준이 두개여서 안되는 것 같다.
        FIFO가 아니여서. 
    
"""
import heapq
def convert(time) :
    inHour, inMinute = map(int, time[0].split(':'))
    outHour, outMinute = map(int, time[1].split(':'))
    
    return [inHour*60+inMinute, outHour*60+outMinute+10]

def solution(book_time):
    
    #대기열 시간변환(입실시간-> 그대로, 퇴실시간-> +10)
    book_time = list(map(convert, book_time))
        
    #대기열 정렬(입실시간 기준(거꾸로) - pop하기 위해서))
    book_time.sort(key = lambda x:x[0], reverse = True)
    
    pq = [] #방 우선순위큐 선언(퇴실시간 기준)
    
    print('book_time : '+str(book_time))
    print()
    
    maxV = 0
    # cnt = 0
    while book_time : #대기열 안비었을때
        time = book_time[-1][0] #시간 업데이트 by 그다음 빠른 입실시간(of 대기열)
        
        while pq and pq[0] <= time : #방 우선순위큐 안비었을때 & 맨앞 뺄수 있다(퇴실시간 <= 현재시간)
             heapq.heappop(pq) #방 우선순위큐에서 뺄수 있는애들 뺀다
        
        while book_time and book_time[-1][0] <= time : #대기열 안비었을때 & 맨뒤 넣을 수 있다: 
            person = book_time.pop()
            heapq.heappush(pq, person[1]) #한걸 heappush()로 계속 넣기
        
        maxV = max(maxV, len(pq)) #max방개수 갱신
        
        # cnt +=1
        # print(str(time)+' '+str(maxV)+' '+ str(cnt))
        # print('대기열 : '+str(book_time))
        # print('방 : '+str(pq))
    
    return maxV #max방개수
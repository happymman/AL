"""
    i)앞넘어간경우 ii)뒤넘어간경우
    왜 이게 문젠거지?
"""
def solution(video_len_param, pos_param, op_start_param, op_end_param, commands):
    
    video_len = time2second(video_len_param)
    pos=time2second(pos_param)
    op_start=time2second(op_start_param)
    op_end=time2second(op_end_param)

    if op_start<=pos<op_end : pos = op_end #오프닝여부검사
        
    for command in commands : #커맨드 선택
        if command == 'prev' :
            if pos-10<0 : pos=0
            else : pos-=10
            
            if op_start<=pos<op_end : pos = op_end #오프닝여부검사
        else :
            if pos+10>video_len : pos=video_len
            else : pos+=10
            
            if op_start<=pos<op_end : pos = op_end #오프닝여부검사
    return second2time(pos)

def time2second(timeStr) :
    mm, ss = map(int,timeStr.split(':'))
    return mm*60+ss
def second2time(time):
    mm,ss=divmod(time,60)
    return f'{mm:02d}:{ss:02d}'
    
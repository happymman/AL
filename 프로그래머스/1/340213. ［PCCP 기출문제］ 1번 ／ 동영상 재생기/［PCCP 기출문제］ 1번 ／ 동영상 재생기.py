"""
    i)앞넘어간경우 ii)뒤넘어간경우
    왜 이게 문젠거지?
"""
def solution(video_len_param, pos_param, op_start_param, op_end_param, commands):
    
    video_len = convert(video_len_param)
    pos=convert(pos_param)
    op_start=convert(op_start_param)
    op_end=convert(op_end_param)

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
    return d(pos)

def convert(timeStr) :
    return int(timeStr.split(':')[0])*60+int(timeStr.split(':')[1])
def d(time) :
    mm = str(time//60) if time//60>=10 else '0'+str(time//60)
    ss = str(time%60) if time%60>=10 else '0'+str(time%60)
    
    return mm+':'+ss
    
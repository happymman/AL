"""
    838~859~915, 완벽풀이, 코드작성완료
    time = 틀린횟수(=diff-level)*(time_cur+time_prev)+time_cur
    level최솟값?
"""
def solution(diffs, times, limit):
    left = 1
    right = 100000
    
    while left<=right :
        mid = (left+right)//2
        #검사
        sum=0
        for i in range(0,len(diffs)) :
            diff = diffs[i]
            if diff-mid>0 : 
                sum+=(diff-mid)*(times[i]+times[i-1])+times[i]
            else :
                sum+=times[i]
        if sum<=limit :
            right = mid-1
        else :
            left = mid+1
        
    return left
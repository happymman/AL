def solution(storey):
    result = 0
    #문자열로 바꾼이유 : len()을 사용하기 위해서
    storey = str(storey) 
    
    old = len(storey) #old = 기존에 몇자리수였는지
    i=len(storey)-1 #문자열index를 사용하기위해 -1
    
    #for대신 while을 쓴 이유 : 
    while(i>=0) :
        if(int(storey[i]) < 5 or int(storey[i])==5 and i-1>=0 and int(storey[i-1])<5 or int(storey[i])==5 and i-1==-1) :
            sub = int(storey[i])
            result += sub
            storey = str(int(storey)-sub*(10**(len(storey)-1-i)))
            
        elif(int(storey[i]) > 5 or int(storey[i])==5 and i-1>=0 and int(storey[i-1])>=5) :
            add = 10-int(storey[i])
            result += add
            storey = str(int(storey)+add*(10**(len(storey)-1-i)))
            
        if(old == len(storey)-1) : #자릿수가 커지면
            return result+1
        elif(old == len(storey)) : #자릿수
            i-=1
        elif(storey == '0') : #0이면
            return result
    return result

# %10 -> 1의자릿수 return
# %100 //10 -> 10의자릿수 return
# //10 %10
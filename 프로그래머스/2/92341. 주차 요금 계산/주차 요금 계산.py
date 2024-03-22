"""
    401
    
"""
from collections import defaultdict
import math

def calculate(fees, fee) : #요금계산 ex : [180, 5000, 10, 600], 334

    sum = fees[1] #기본요금
    if fee > fees[0] : #기본시간 초과했을시, 
        sum += int(math.ceil((fee - fees[0])/fees[2])*fees[3])
    return sum
    
def convert(time) : #시간변환 ex : "09:49"
    h, m = map(int, time.split(':'))
    return h*60+m
    
    
def solution(fees, records):
    answer = []
    
    carIn = defaultdict(int)
    carTime = defaultdict(int)
    
    for record in records : #기록 선택
        time, carNum, inOut = record.split()
        if inOut == 'IN' :
            carIn[carNum] = convert(time) #carIn에 등록
        else :
            # print(str(convert(time) - carIn[carNum]))
            carTime[carNum] += convert(time) - carIn[carNum]
            del carIn[carNum]
            
    
    for carNum in carIn.keys() : #안나간차있으면 23:59출차로해서 등록
        carTime[carNum] += convert("23:59") - carIn[carNum]
        # del carIn[carNum]
        
    # for k, v in carTime.items() :
    #     print("carNum : "+str(k)+" "+"carTime : "+str(v))
        
    for carNum in sorted(carTime.keys()) : #요금계산
        answer.append(calculate(fees, carTime[carNum]))
        
    return answer
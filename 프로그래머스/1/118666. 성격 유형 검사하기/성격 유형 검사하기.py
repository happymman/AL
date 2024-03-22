"""
    17m
    'NA' -> split('')(X : 이런문법 없음), list('NA') & ''.join(리스트)하거나 하는식
"""
import math
from collections import defaultdict

def solution(surveys, choices):
    
    character = defaultdict(int)
    
    for i in range(len(surveys)) :#설문 선택
        survey = surveys[i]
        a,b = survey
        
        #점수 추가
        character[a if choices[i] < 4 else b] += abs(4-choices[i])
             
    answer=[]
    if character['T'] > character['R'] :
        answer.append('T')
    else : 
        answer.append('R')
        
    if character['F'] > character['C'] :
        answer.append('F')
    else : 
        answer.append('C')
        
    if character['M'] > character['J'] :
        answer.append('M')
    else : 
        answer.append('J')
        
    if character['N'] > character['A'] :
        answer.append('N')
    else : 
        answer.append('A')
        
    
    return ''.join(answer)

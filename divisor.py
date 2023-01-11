#약수의 개수는 어떤 역할을 하면 될까
#정렬 - 약수개수 홀수 -> 가운데 제곱
#      약수개수 짝수 -> 가운데 가장 가까운, 둘 곱
count = int(input())
List = sorted(list(map(int, input().split())))
print(List[count//2]*List[count//2] if count%2==1 else List[count//2-1]*List[count//2])
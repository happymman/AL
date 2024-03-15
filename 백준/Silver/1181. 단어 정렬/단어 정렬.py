
import sys
input = sys.stdin.readline

n = int(input().strip())

dic = dict()
for i in range(1,51) :
    dic[i] = set()

for _ in range(n) :
    word = input().strip()

    dic.setdefault(len(word), set()).add(word)

for length in sorted(dic.keys()) :
    for word in sorted(dic[length]) :
        print(word)


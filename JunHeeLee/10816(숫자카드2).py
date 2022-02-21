import sys
from collections import defaultdict
N = int(input())
num1 = list(map(int,sys.stdin.readline().split()))
num1.sort()
M = int(input())
num2 = list(map(int,sys.stdin.readline().split()))
d = defaultdict(int)
for num in num1:
    d[num]+=1

for target in num2:
    if target not in d.keys():
        print(0,end= " ")
    else:
        print(d[target],end= " ")
    
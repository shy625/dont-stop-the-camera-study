import sys

S = sys.stdin.readline().split("-")
answer = 0
print(S)
for i in S[0].split("+"):
    answer+=int(i)
for i in S[1:]:
    for j in i.split("+"):
        answer-=int(j)
print(answer)
    
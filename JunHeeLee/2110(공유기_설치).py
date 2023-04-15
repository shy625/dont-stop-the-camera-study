import sys
from math import inf
N,C = map(int,sys.stdin.readline().split())
info = []
for _ in range(N):
    info.append(int(sys.stdin.readline())) ##공유기 위치 저장
info.sort() ##공유기위치 오름차순으로 정렬

left = 0 ##이분탐색 초기값 지정
right = info[-1]
answer = inf
while left<=right:
    prev = info[0] #while문을 돌며 맨 처음 초기값은 제일 앞 공유기
    cnt = 1
    mid = (left+right)//2 ##mid를 바꿔가며 거리 측정
    for i in range(N):
        if info[i]-prev >=mid:
            cnt+=1
            prev = info[i]
    if cnt>=C: #cnt가 C보다 많다는 것은 mid값이 작다는 뜻이므로 left = mid+1
        answer = mid
        left = mid+1
    else:
        right = mid-1
print(answer)
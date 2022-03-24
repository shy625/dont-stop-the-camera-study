import sys
from math import inf

N,M = map(int,sys.stdin.readline().split())
ground = [] # 지도 저장
chicken = [] # 치킨집 좌표 저장
house = [] #집 좌표 저장

for i in range(N):
    ground.append(list(map(int,sys.stdin.readline().split()))) ##여기까지 input 받기
    for j in range(N):
        if ground[i][j] == 2:
            chicken.append((i,j))
        elif ground[i][j] == 1:
            house.append((i,j))
picked = [-1]*M #뽑힌 치킨집 index 담을 배열
answer= inf #초기 answer 값은 inf로 초기화(무한대)

def calc(pick_chicken):
    total = 0
    for i in range(len(house)): # i번쨰 집 부터 가장 가까운 거리의 치킨집 더해줌
        temp = inf
        x,y = house[i]
        for j in range(M):
            index = pick_chicken[j]
            cx,cy = chicken[index]
            temp = min(temp, abs(cx-x)+abs(cy-y))
        total += temp
    return total #전체 치킨거리합 return

def pick(cnt, idx):
    global answer
    if cnt==M: # 치킨집 다 골랐으면 모든집에서부터 치킨거리 측정
        answer = min(calc(picked),answer)     
        return
    for i in range(idx,len(chicken)):
        picked[cnt] = i
        pick(cnt+1,i+1)

pick(0,0)
print(answer)
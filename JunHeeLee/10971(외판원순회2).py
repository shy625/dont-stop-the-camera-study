import sys
from math import inf
N = int(sys.stdin.readline())
graph= [[]for _ in range(N)] ##그래프 생성
for s in range(N):
    temp = list(map(int,sys.stdin.readline().split()))
    for e in range(len(temp)):
        if temp[e]!=0:
            graph[s].append((e,temp[e])) ##그래프 연결

visit = []
answer= inf
def dfs(node,dest,total): ##출발지와 최종 목적지는 같아야 하므로 dest 설정
    global answer
    if len(visit)==N: ##여행을 다함, 이제 출발지로 돌아갈 일만 남음
        for nnode, ncost in graph[node]:
            if nnode == dest: ##마지막 여행지에서 출발지까지의 cost 더해줌
                answer = min(total+ncost, answer)
                return
    
    for next_node,next_cost in graph[node]: ##만든 그래프에서 다음 여행지와 다음 거리
        if next_node not in visit: ##방문한 적이 없다면
            visit.append(next_node) ##방문
            dfs(next_node, dest,total+next_cost)
            visit.pop()

for i in range(N): ##0번 출발지부터 N-1 출발지까지 돌면서 dfs 실행
    visit.append(i) 
    dfs(i,i,0)
    visit.pop()

print(answer)
            
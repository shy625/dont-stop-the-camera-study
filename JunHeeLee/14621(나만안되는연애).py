import sys

N,M = map(int,sys.stdin.readline().split())
info = list(map(str,sys.stdin.readline().split())) #남녀 정보 받을 배열
graph = [] #그래프 정보 저장
for _ in range(M):
    u,v,d = map(int,sys.stdin.readline().split())
    if info[u-1] != info[v-1]:  #동성을 연결하는 간선은 불필요하므로 이성 연결 간선만 저장
        graph.append((u,v,d))

graph.sort(key=lambda x:x[2]) #거리가 짧은 순으로 정렬
parent = [i for i in range(N+1)]

def find(x):
    if parent[x]!=x:
        parent[x] = find(parent[x])
    return parent[x]

def union(x,y):
    x = find(x)
    y = find(y)
    if x<y:
        parent[x] = y
    else:
        parent[y] = x
total = 0
cnt = 0 #간선 연결하는 횟수 count
for u,v,d in graph:
    if find(u)!=find(v):
        union(u,v)
        total+=d
        cnt+=1
if cnt==N-1: #N개의 node들을 연결하기위해선 N-1개의 간선 필요
    print(total)
else:
    print(-1)

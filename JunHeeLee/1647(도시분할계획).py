import sys

N,M = map(int,sys.stdin.readline().split())
road = [] # node1, node2, cost 저장
for _ in range(M):
    u,v,w = map(int,sys.stdin.readline().split())
    road.append((u,v,w))
    
parent = [i for i in range(N+1)] #초기 parent값은 인덱스와 동일 

def find(x): #x의 부모 찾기
    if x!=parent[x]:
        parent[x] = find(parent[x])
    return parent[x]

def union(x,y): #x,y 부모 합치기
    x = find(x)
    y = find(y)
    if x<y:
        parent[x] = y
    else:
        parent[y] = x

road.sort(key = lambda x:x[2]) # MST를 만들기위해 cost가 작은순으로 정렬


total = 0 #초기비용
edge = [] #MST를 만들때 사용된 cost 저장

#하나의 MST에서 가장큰 cost 하나만 빼주면 두개의 마을로 나눠짐

for u,v,w in road:
    if find(u)!=find(v): #두개가 연결되어있지 않은 경우
        union(u,v) #합침
        total+=w 
        edge.append(w) #MST에 사용된 cost 저장

print(total - max(edge)) #MST에서 가장큰 간선하나 제거
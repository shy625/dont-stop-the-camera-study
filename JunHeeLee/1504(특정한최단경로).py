import sys
import heapq
from math import inf
N,E = map(int,sys.stdin.readline().split())
graph = [[] for _ in range(N+1)]
for _ in range(E):
    S,E,C = map(int,sys.stdin.readline().split())
    graph[S].append((E,C))
    graph[E].append((S,C))

point1, point2 = map(int,sys.stdin.readline().split())
answer = inf

def dijkstra(start,end):
    q = []
    dist = [inf]*(N+1)
    dist[start] = 0
    heapq.heappush(q,(0,start))
    while q:
        cdist, cnode = heapq.heappop(q)
        if dist[cnode]<cdist: continue
        
        for next_node, next_cost in graph[cnode]:
            distance = cdist+next_cost
            if dist[next_node]>distance:
                dist[next_node] = distance
                heapq.heappush(q,(distance,next_node))
    return dist[end]
answer = min(dijkstra(1,point1)+dijkstra(point1,point2)+dijkstra(point2,N), dijkstra(1,point2)+dijkstra(point2,point1)+dijkstra(point1,N))
if answer==inf:
    answer = -1
print(answer)
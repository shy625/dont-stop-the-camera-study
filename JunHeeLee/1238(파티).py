import sys
import heapq
from math import inf
N,M,X = map(int,sys.stdin.readline().split())
graph = [[] for _ in range(N+1)]
comeback = [inf]*(N+1)
for _ in range(M):
    S,E,T = map(int,sys.stdin.readline().split())
    graph[S].append((E,T))
answer = -1
def dijkstra(node):
    global comeback, answer
    if node == X:
        comeback = [inf]*(N+1)
        comeback[node] = 0
        sq = []
        heapq.heappush(sq,(0,node))
        while sq:
            cdist, cnode = heapq.heappop(sq)
            if comeback[cnode]<cdist:
                continue
            for nextnode, ndist in graph[cnode]:
                distance = ndist + comeback[cnode]
                if comeback[nextnode]>distance:
                    comeback[nextnode] = distance
                    heapq.heappush(sq,(distance,nextnode))
    else:
        dist = [inf]*(N+1)
        dist[node] = 0
        sq = []
        heapq.heappush(sq,(0,node))
        while sq:
            cdist, cnode = heapq.heappop(sq)
            if dist[cnode]<cdist:
                continue
            for nextnode, ndist in graph[cnode]:
                distance = ndist + dist[cnode]
                if dist[nextnode]>distance:
                    dist[nextnode] = distance
                    heapq.heappush(sq,(distance,nextnode))
        answer = max(answer, dist[X] + comeback[node])
dijkstra(X) 
for i in range(1,N+1):
    if i==X:
        continue
    dijkstra(i)
print(answer)

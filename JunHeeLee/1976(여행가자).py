from cmath import inf
import sys
from collections import deque
import heapq
from math import inf

N = int(sys.stdin.readline())
M = int(sys.stdin.readline())
graph = [ set() for _ in range(N+1)]

for i in range(N):
    temp = list(map(int,sys.stdin.readline().split()))
    for j in range(len(temp)):
        if temp[j] == 1:
            graph[i+1].add(j+1)
            graph[j+1].add(i+1)
road = list(map(int,sys.stdin.readline().split()))

def dijkstra(node,dest):
    q = []
    dist = [inf]*(N+1)
    dist[node] = 0
    heapq.heappush(q,(0,node))
    while q:
        cdist,cnode = heapq.heappop(q)
        if dist[cnode] < cdist:
            continue
        for next_node in graph[cnode]:
            distance = cdist+1
            if dist[next_node]>distance:
                dist[next_node] = distance
                heapq.heappush(q,(distance,next_node))
    if dist[dest]!=inf:
        return True
    return False
for i in range(len(road)-1):
    check = dijkstra(road[i],road[i+1])
    if check==False:
        print("NO")
        exit()
print("YES")
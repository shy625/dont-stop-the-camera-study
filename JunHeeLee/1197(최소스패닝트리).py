import sys

V,E = map(int,sys.stdin.readline().split())
edge = []
for _ in range(E):
    S,E,cost = map(int,sys.stdin.readline().split())
    edge.append((cost,S,E))
edge.sort()

parent = [0]*(V+1)
for i in range(V+1):
    parent[i] = i

def findparent(parent,x):
    if parent[x]!=x:
        parent[x] = findparent(parent,parent[x])
    return parent[x]
def union(parent,a,b):
    a = findparent(parent,a)
    b = findparent(parent,b)
    if a<b:
        parent[a] = parent[b]
    else:
        parent[b] = parent[a]
answer = 0
for cost,node1,node2 in edge:
    if findparent(parent,node1)!= findparent(parent,node2):
        union(parent,node1,node2)
        answer+=cost
print(answer)
        


        
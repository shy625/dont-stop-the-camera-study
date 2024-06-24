#15:00 - 15:29

import sys

N,M,K = map(int,sys.stdin.readline().split())
root = list(map(int,sys.stdin.readline().split())) ##발전소
edge = [] ##간선 저장
for _ in range(M):
    u,v,w = map(int,sys.stdin.readline().split())
    edge.append((u,v,w))
edge.sort(key = lambda x:x[2]) #cost를 기준으로 오름차순으로 정렬

parent = [i for i in range(N+1)] #parent[i] = i로 초기화

def find(x): ##x의 부모 찾기
    if x!=parent[x]:
        find(parent[x])
    
    return parent[x]

def union(x,y): ##x,y의 부모를 하나로 합치기, 기존과 다르게 x나 y의 부모가 발전소 인 경우 부모를 발전소로 지정
    x = find(x)
    y = find(y)
    
    if x in root: ##x의 부모가 발전소인 경우
        parent[y] = x
    else: ##y의 부모가 발전소인 경우
        parent[x] = y
total = 0
check = [-1]*(N+1) #발전소를 제외한 일반 집들이 연결되어있는지 확인하기 위한 배열
while(1): ##모두 연결될때까지 무한 loop
    for i in range(len(edge)):
        u,v,w = edge[i]
        if find(u) in root and find(v) in root: #발전소 끼리 연결하는 경우는 필요없으므로 pop하고 break
            edge.pop(i)
            break
        if find(u) in root or find(v) in root: #둘 중 하나만 발전소와 연결되어있을경우
            union(u,v) #연결
            check[u] = 0 #0이면 연결되어있다는 뜻
            check[v] = 0
            total+=w
            edge.pop(i)
            break
    flag = True #while문 탈출을 위한 변수
    for i in range(1,N+1):
        if check[i]==-1:##연결이 되어있지 않은 node
            if i not in root: #그 node가 발전소가 아니면 모두 연결된게 아니므로 다시 while문
                flag = False
                break
    if flag==True: ##모두 연결되었으므로 while문 탈출
        break
print(total)
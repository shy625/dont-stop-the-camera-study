import sys
from collections import deque

M,N = map(int,sys.stdin.readline().split())
ground = []
q = deque()
visit = [[-1]*M for _ in range(N)]
dx = [-1,1,0,0]
dy = [0,0,1,-1]
check = 0
for i in range(N):
    ground.append(list(map(int,sys.stdin.readline().split())))
    for j in range(M):
        if ground[i][j] == 1:
            q.append((i,j))
            visit[i][j] = 0
        elif ground[i][j] ==0:
            check+=1
            
if check==0:
    print(0)
    exit()

def bfs():
    cnt = 0
    while q:
        qlen = len(q)
        while qlen>0:
            x,y = q.popleft()
            for i in range(4):
                nx = x+dx[i]
                ny = y+dy[i]
                if 0<=nx<N and 0<=ny<M and ground[nx][ny]==0 and visit[nx][ny]==-1:
                    ground[nx][ny] = 1
                    visit[nx][ny] = 0
                    q.append((nx,ny))
            qlen-=1
        cnt+=1
    for i in range(N):
        for j in range(M):
            if ground[i][j] == 0:
                print(-1)
                exit()
    print(cnt-1)
    exit()
bfs()

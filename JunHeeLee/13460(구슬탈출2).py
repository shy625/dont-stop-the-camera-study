import sys
from collections import deque
N,M = map(int,sys.stdin.readline().split())
board = []
rx,ry,bx,by = 0,0,0,0
ex,ey = 0,0
for i in range(N):
    board.append(list(map(str,sys.stdin.readline().strip())))
    for j in range(M):
        if board[i][j]=='R':
            rx = i
            ry = j
            board[i][j] = '.'
        elif board[i][j] == 'B':
            bx = i
            by = j
            board[i][j] = '.'
dx = [-1,1,0,0] #위 아래 오 왼
dy = [0,0,1,-1]

def bfs(rx,ry,bx,by):
    q = deque()
    q.append((rx,ry,bx,by,0))
    visit = [[[[-1]*M for _ in range(N)]for _ in range(M)] for _ in range(N)]
    visit[rx][ry][bx][by] = 0
    while q:
        crx, cry, cbx, cby, count = q.popleft()
        if count>10:
            print(-1)
            exit()
        for dir in range(4):
            check1 = 0 #빨간구슬 빠졌는지 확인
            check2 = 0 #파란구슬 빠졌는지 확인
            dist1 = 0 #빨간구슬 겹쳤을때 거리
            dist2 = 0 #파란구슬 겹쳤을때 거리
            temp1x = crx
            temp1y = cry
            temp2x = cbx
            temp2y = cby
            while(1):
                nrx = temp1x+dx[dir]
                nry = temp1y+dy[dir]
                if 1<=nrx<N-1 and 1<=nry<M-1:
                    if board[nrx][nry]=='.':
                        temp1x = nrx
                        temp1y = nry
                        dist1+=1
                    elif board[nrx][nry] == 'O':
                        check1 = 1
                        break
                    else:
                        break
                else:
                    break
            while(1):
                nbx = temp2x+dx[dir]
                nby = temp2y+dy[dir]
                if 1<=nbx<N-1 and 1<=nby<M-1:
                    if board[nbx][nby]=='.':
                        temp2x = nbx
                        temp2y = nby
                        dist2+=1
                    elif board[nbx][nby]=='O':
                        check2 = 1
                        break
                    else:
                        break
                else:
                    break
            if check1==1 and check2==0:
                print(count+1)
                exit()
            elif check1==0 and check2==0:
                if temp1x == temp2x and temp1y == temp2y:
                    if dist1>dist2:
                        temp1x = temp1x - dx[dir]
                        temp1y = temp1y - dy[dir]
                    else:
                        temp2x = temp2x - dx[dir]
                        temp2y = temp2y - dy[dir]
                if visit[temp1x][temp1y][temp2x][temp2y]==0:
                    continue
                else:
                    visit[temp1x][temp1y][temp2x][temp2y]=0
                    q.append((temp1x,temp1y,temp2x,temp2y,count+1))
    print(-1)    
        
bfs(rx,ry,bx,by)
            
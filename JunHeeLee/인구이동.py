import sys
from collections import deque

N,L,R = map(int,sys.stdin.readline().split())
ground = []
for _ in range(N):
    ground.append(list(map(int,sys.stdin.readline().split()))) ##N*N 땅 생성

dx = [-1,1,0,0] #위 아래 오 왼
dy = [0,0,1,-1]

def bfs(i,j):# ground[i][j]로 부터 bfs를 이용하여 인구이동
    q = deque() # queue 선언
    q.append((i,j))
    visit[i][j] = 0 #visit은 밖에 이중for문을 위한 방문체크
    total = ground[i][j] #total은 인구의 합
    cnt = 1
    check = [] #check배열은 bfs(i,j)에서 방문한 좌표 저장
    check.append((i,j))
    global flag
    
    while q: #q가 빌때까지
        x,y = q.popleft()
        temp1 = ground[x][y] ##현재 위치의 인구
        for i in range(4):
            nx = x+dx[i]
            ny = y+dy[i]
            if 0<=nx<N and 0<=ny<N and visit[nx][ny]==-1: # 방문한적이 없고 범위내에 있고
                if L<=abs(ground[nx][ny] - temp1)<=R: #인접한 땅과 인구 차이가 L이상 R이하
                    visit[nx][ny] = 0
                    check.append((nx,ny))
                    q.append((nx,ny))
                    cnt+=1
                    total+=ground[nx][ny]
    amount = total//cnt #연합의 인구수 / 연합의 칸수 
    if cnt!=1:
        flag = True #cnt가 1이 아니면 인구이동이 일어났으므로 flag True
        for x,y in check: #이번 bfs에서 방문한 곳들은 (연합의 인구수 / 연합의 칸수) 값으로 업데이트
            ground[x][y] = amount
    
day = 0 # 인구이동이 일어나는 날을 세기 위한 변수

while(1):
    visit = [[-1]*N for _ in range(N)] #매 횟수별로 visit배열 초기화
    flag = False #인구이동이 일어났는지 확인하기 위한 flag
    for i in range(N):
        for j in range(N):
            if visit[i][j]==-1:
                bfs(i,j)
    if flag==False:
        print(day)
        exit()
    day+=1
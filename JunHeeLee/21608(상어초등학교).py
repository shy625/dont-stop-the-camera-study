import sys
N = int(sys.stdin.readline())

dx = [-1,1,0,0]
dy = [0,0,1,-1]

d = {}
for _ in range(N*N):
    temp = list(map(int,sys.stdin.readline().split()))
    d[temp[0]]=temp[1:] ##학생번호를 key값 그 학생이 좋아하는 학생들의 번호를 value값으로 넣어줌
ground = [[-1]*N for _ in range(N)] #초기 테이블 -1로 초기화

def find(): #자리배치하는 함수
    for key in d.keys(): ##처음 학생부터 끝 학생까지
        temp = [] #좋아하는 학생수, 인접한 빈칸수, 좌표를 담는 배열
        for i in range(N):
            for j in range(N):
                cnt1=0 ##좋아하는 학생
                cnt2=0 ##인접한 빈칸
                if ground[i][j]!=-1: ##이미 차있는 경우라면 continue
                    continue
                for k in range(4):
                    nx = i+dx[k]
                    ny = j+dy[k]
                    if 0<=nx<N and 0<=ny<N: #범위내에 있으면
                        if ground[nx][ny]==-1: ##빈칸
                            cnt2+=1
                        elif ground[nx][ny] in d[key]: ##인접한 칸에 좋아하는 학생이 있음
                            cnt1+=1
                temp.append((cnt1,cnt2,i,j)) ##각 칸별로 좋아하는학생, 인접한빈칸수, 행렬 좌표 추가
        temp.sort(key= lambda x:(-x[0], -x[1], x[2],x[3])) #기준에 맞게 정렬
        x,y = temp[0][2],temp[0][3] ##정렬된 리스트에서 맨앞 tuple의 3번째 4번째(index = 2,3) 값이 좌표이므로
        ground[x][y] = key #그 좌표에 학생 배치
        
        
find()#자리배치 끝남            
answer = 0 #만족도

for i in range(N):
    for j in range(N):
        target = ground[i][j]
        cnt = 0 #인접한 좋아하는 학생수 count
        for k in range(4):
            nx = i+dx[k]
            ny = j+dy[k]
            if 0<=nx<N and 0<=ny<N: #범위내에 있고
                if ground[nx][ny] in d[target]: #인접한 학생이 좋아하는 학생이라면
                    cnt+=1
        if cnt==1:
            answer+=1
        elif cnt==2:
            answer+=10
        elif cnt==3:
            answer+=100
        elif cnt==4:
            answer+=1000
print(answer)


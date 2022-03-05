import sys
import copy

N = int(sys.stdin.readline())

now = list(map(int,sys.stdin.readline().strip())) #초기 전구
last = list(map(int,sys.stdin.readline().strip())) #맞춰야 하는 전구

r1 = copy.deepcopy(now) #r1,r2에 초기전구를 카피
r2 = copy.deepcopy(now)

def two(i,j): #양끝일경우 현재,앞 혹은 현재,뒤 두개만 바꿔줌
    now[i] = 1-now[i]
    now[j] = 1-now[j]

def three(i,j,k): #그외는 3개 바꿔줌
    now[i] = 1-now[i]
    now[j] = 1-now[j]
    now[k] = 1-now[k]

for i in range(2): #맨 앞부터 바꾸고 시작할지, 아니면 두번째 칸부터 바꿀지 
    if i==0:
        now = r1
    else:
        now = r2
    cnt = 0
    for j in range(N):
        if j==0: 
            if i==0 and now!=last: # i가 0이면 맨앞부터 바꾸는 경우
                cnt+=1
                two(j,j+1)
        elif 1<=j<N-1:
            if now[j-1]!=last[j-1]:
                cnt+=1
                three(j-1,j,j+1)
        else:
            if now[j-1]!=last[j-1]:
                cnt+=1
                two(j-1,j)
    if now == last: #결과값이랑 같다면 cnt 프린트하고 exit()
        print(cnt)
        exit()
print(-1) #두번 돌렸을때 안나온다면 -1 print
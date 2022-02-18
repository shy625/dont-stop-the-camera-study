import sys
ground = []
zero = []
for i in range(9):
    ground.append(list(map(int,sys.stdin.readline().split())))
    for j in range(9):
        if ground[i][j] ==0:
            zero.append((i,j))

def rowcheck(j,num):
    for a in range(9):
        if ground[a][j] == num:
            return False
    return True
def colcheck(i,num):
    for a in range(9):
        if ground[i][a]==num:
            return False
    return True
def check(x,y,num):
    x = (x//3)*3
    y = (y//3)*3
    for i in range(x,x+3):
        for j in range(y,y+3):
            if ground[i][j] == num:
                return False
    return True
def dfs(cnt):
    if cnt==len(zero):
        for i in range(9):
            for j in range(9):
                print(ground[i][j], end= " ")
            print()        
        
        
        
        exit()
    for i in range(1,10):
        x = zero[cnt][0]
        y = zero[cnt][1]
        if rowcheck(y,i) and colcheck(x,i) and check(x,y,i):
            ground[x][y] = i
            dfs(cnt+1)
            ground[x][y] = 0
dfs(0)
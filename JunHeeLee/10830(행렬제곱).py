import sys

N,B = map(int,sys.stdin.readline().split())
matrix = []
for _ in range(N):
    matrix.append(list(map(int,sys.stdin.readline().split())))
def mul(temp1, temp2):
    tmp = [[0]*N for _ in range(N)]
    for i in range(N):
        for j in range(N):
            for k in range(N):
                tmp[i][j] += temp1[i][k]*temp2[k][j]
            tmp[i][j] %=1000
    return tmp

def square(mat, count):
    if count==1:
        for i in range(N):
            for j in range(N):
                mat[i][j] %=1000
        return mat
    tmp = square(mat,count//2)
    
    if count%2==1:
        return mul(mul(tmp,tmp),matrix)
    
    else:
        return mul(tmp,tmp)
result = square(matrix,B)
for r in result:
    print(*r)
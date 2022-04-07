import sys

N,K = map(int,sys.stdin.readline().split())
num = list(map(int,sys.stdin.readline().strip())) ##strip을 사용해서 1524인 경우 [1,5,2,4]로 num 배열에 저장됨

stack = []
cnt = 0 ##지우는 갯수 count
for i in range(N):
    while len(stack)>0 and cnt<K: #스택이 비어있지 않고, 지운 갯수가 K보다 작은 경우
        if stack[-1]<num[i]: #스택의 가장 위에값이 num[i] 보다 작으면 pop
            stack.pop()
            cnt+=1
        else:
            break
    stack.append(num[i]) #스택에 쌓아줌


if cnt==K: # K 만큼 지운경우 이므로 stack에 들어있는 값을 join하여 print
    print(''.join(map(str,stack)))
else: #K개보다 적게 지운경우 뒤에서부터 지운뒤 join
    print(''.join(map(str,stack[:-(K-cnt)])))


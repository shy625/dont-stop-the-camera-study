import sys

N = int(sys.stdin.readline())
num = list(map(int,sys.stdin.readline().split())) #숫자 저장

dp = [-1]*N #합을 저장하는 dp 배열
dp[0] = num[0]  #dp배열의 처음은 num의 처음값으로 지정
for i in range(1,N):
    amount = dp[i-1]+num[i] 
    if amount<=num[i]: #구간합이 i번째 수보다 작거나 같다면 dp[i]는 합이 아닌 i번째 수로 저장
        dp[i] = num[i]
    else: #구간합이 i번쨰 수보다 크면 i-1까지 합 더하기 i번째 수로 저장
        dp[i] = amount
        
print(max(dp))

#include<iostream>

using namespace std;

//0부터 시작하는 구간합을 구하고 뺴주면 되지 않나?

main(){
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    int N, M;
    cin >> N >> M;
    int map[N+1][N+1]={0,};

    for (int i = 1; i <=N; i++)
    {
        for (int j = 1; j <=N; j++)
        {
            cin >> map[i][j];
        }
    }

    //dp
    int dp[N + 1][N + 1] = {
        0,
    };

    //i,j 의 바로 위인 i-1,j의 누적합과 현재 열의 누적 합을 더해주면 누적합 구하기 가능
    for (int i = 1; i <=N; i++)
    {
        int sum = 0;
        for (int j = 1; j <=N; j++)
        {
            sum += map[i][j];

            dp[i][j] = sum + dp[i - 1][j];
        }
    }

    for (int i = 0; i < M; i++)
    {
        int x1, y1, x2, y2;
        cin >> x1 >> y1 >> x2 >> y2;

        cout << dp[x2][y2] - (dp[x2][y1-1] + dp[x1-1][y2] - dp[x1-1][y1-1]) << '\n';
    }
    
    
}
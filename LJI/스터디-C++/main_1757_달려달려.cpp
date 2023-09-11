#include <iostream>
#include <vector>
#include <queue>
using namespace std;

int N, M;
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> M;

    vector<int> vec;
    for (int i = 0; i < N; i++)
    {
        int tmp;
        cin >> tmp;
        vec.push_back(tmp);
    }

    // DP
    int dp[N + 1][M + 1];
    fill(&dp[0][0], &dp[N][M + 1], -1);

    dp[0][0] = 0;

    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j <= M; j++)
        {
            if (dp[i][j] < 0)
                continue;

            // 현재 위치의 거리가 있다면

            // 뛰거나
            // 뛰려면 지침 지수가 M보다 작아야함
            if (j < M)
            {
                dp[i + 1][j + 1] = max(dp[i + 1][j + 1], dp[i][j] + vec[i]);
            }
            // 쉬거나
            if (j == 0) // 지침 지수가 이미 0이면 현상 유지
            {
                dp[i + 1][0] = max(dp[i + 1][0], dp[i][0]);
            }
            else // 지침 지수가 0이 될 때까지 쉬어야한다
            {
                if (i + j <= N)
                {
                    dp[i + j][0] = max(dp[i + j][0], dp[i][j]);
                }
            }
        }
    }

    cout << dp[N][0];
}
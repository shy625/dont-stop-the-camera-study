#include <iostream>

using namespace std;

int T, k;
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> T >> k;

    int dp[T + 1];
    fill(&dp[0], &dp[T + 1], 0);
    dp[0] = 1; // 초기화

    for (int i = 1; i <= k; i++)
    {
        int P, N;
        cin >> P >> N;

        // 중복값 막기 위해 역순
        for (int j = T; j >= 0; j--)
        {
            // 현재값에서 동전값 뺀 위치의 경우의 수 체크
            for (int n = 1; n <= N; n++)
            {
                int tmp = P * n;
                if (j - tmp < 0)
                    break;

                dp[j] += dp[j - tmp];
            }
        }
    }

    cout << dp[T];
}
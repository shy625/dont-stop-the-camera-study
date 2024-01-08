#include <iostream>
#include <vector>
using namespace std;
int N, M, K;
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> M >> K;

    vector<int> orange(N + 1, 0);
    vector<long long> dp(N + 1, 0);

    // orange를 각 상자에 담았을 때의 최대값으로 dp초기화
    for (int i = 1; i <= N; i++)
    {
        cin >> orange[i];

        dp[i] = (long long)K * i;
    }

    // 각 위치부터 새로운 상자에 M개까지 담는다
    // 최대 최소값 갱신해가면서 M개까지 위치의 dp값 갱신
    for (int i = 0; i < N; i++)
    {
        int maxVal = orange[i + 1];
        int minVal = orange[i + 1];

        // 오렌지 추가
        for (int j = 1; j <= M; j++)
        {
            if (i + j > N)
                break;

            maxVal = max(maxVal, orange[i + j]);
            minVal = min(minVal, orange[i + j]);

            dp[i + j] = min(dp[i + j], dp[i] + K + (long long)j * (maxVal - minVal));
        }
    }
    cout << dp[N];
}
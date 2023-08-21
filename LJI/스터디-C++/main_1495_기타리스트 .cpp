#include <iostream>
#include <vector>
using namespace std;

int N, S, M;
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> S >> M;
    vector<int> V;
    V.push_back(0);
    for (int i = 0; i < N; i++)
    {
        int tmp;
        cin >> tmp;
        V.push_back(tmp);
    }

    // 해당 볼륨이 가능한지 여부를 체크하는 dp
    bool dp[N + 1][M + 1];
    fill(&dp[0][0], &dp[N][M + 1], false);

    dp[0][S] = true;

    for (int i = 1; i <= N; i++)
    {
        int pre = i - 1;
        for (int m = 0; m <= M; m++)
        {
            if (dp[pre][m])
            { // 이전 볼륨이 가능하면 거기서 V[i]를 더하거나 뺸것이 가능한지
                int plusV = m + V[i];
                int minusV = m - V[i];

                if (plusV <= M)
                    dp[i][plusV] = true;

                if (minusV >= 0)
                    dp[i][minusV] = true;
            }
        }
    }

    // 최종 볼륨 찾기
    int answer = -1;

    for (int m = M; m >= 0; m--)
    {
        if (dp[N][m])
        {
            answer = m;
            break;
        }
    }
    cout << answer;
}
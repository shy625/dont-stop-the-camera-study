#include <iostream>
#include <vector>
using namespace std;
int N, S, T;
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    while (true)
    {
        cin >> N;

        if (N == 0)
        {
            break;
        }

        cin >> S >> T;

        vector<int> Board(N + 2, 0);

        for (int i = 1; i <= N; i++)
        {
            cin >> Board[i];
        }

        // 최대수익을 찾는데 -도 가능하므로
        vector<vector<int>> DP(N + 2, vector<int>(T + 1, INT32_MIN / 1000));

        // 시작 비용은 0
        DP[0][0] = 0;

        for (int t = 1; t <= T; t++)
        {
            for (int i = 1; i <= N + 1; i++)
            {
                // t-1턴에 S값만큼 이전에서 도달 가능
                for (int j = 1; j <= S; j++)
                {
                    if (i - j < 0)
                        break;

                    DP[i][t] = max(DP[i][t], DP[i - j][t - 1] + Board[i]);
                }
            }
        }

        int answer = -10001;

        for (int t = 0; t <= T; t++)
        {
            answer = max(answer, DP[N + 1][t]);
        }

        cout << answer << '\n';
    }
}
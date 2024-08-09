#include <iostream>
#include <vector>
using namespace std;
int N, T;
// 배낭 문제
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> T;

    vector<pair<int, int>> Quest(N + 1);
    int MAX = 0;
    for (int i = 1; i <= N; i++)
    {
        int Day, Cost;
        cin >> Day >> Cost;
        MAX += Cost;
        Quest[i] = make_pair(Day, Cost);
    }

    // 배낭 문제
    vector<vector<int>> DP(N + 1, vector<int>(T + 1, MAX));

    for (int i = 1; i <= N; i++)
    {
        int Day = Quest[i].first;
        int Cost = Quest[i].second;

        for (int j = 1; j <= T; j++)
        {
            DP[i][j] = DP[i - 1][j];

            if (j - Day >= 0)
            {
                DP[i][j] = min(DP[i - 1][j], DP[i - 1][j - Day] - Cost);
            }
        }
    }

    cout << DP[N][T];
}
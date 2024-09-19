#include <iostream>
#include <vector>
#include <set>
using namespace std;
int N, M;
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> M;

    vector<vector<long long>> DP(N + 1, vector<long long>(M + 1, 0));
    DP[0][0] = 1;

    int K;
    cin >> K;
    set<pair<pair<int, int>, pair<int, int>>> FixRoad;
    for (int i = 0; i < K; i++)
    {
        int a, b, c, d;
        cin >> a >> b >> c >> d;

        // 더 값이 큰 위치가 뒤에 위치로 배치
        if (a + b < c + d)
        {
            FixRoad.insert(make_pair(make_pair(a, b), make_pair(c, d)));
        }
        else
        {
            FixRoad.insert(make_pair(make_pair(c, d), make_pair(a, b)));
        }
    }

    for (int i = 0; i <= N; i++)
    {
        for (int j = 0; j <= M; j++)
        {
            if (i == 0 && j == 0)
                continue;

            // 왼쪽에서 오는길
            if (j - 1 >= 0)
            {
                // 도로 공사 X
                if (FixRoad.find(make_pair(make_pair(i, j - 1), make_pair(i, j))) == FixRoad.end())
                {
                    DP[i][j] += DP[i][j - 1];
                }
            }
            // 위쪽에서 오는길
            if (i - 1 >= 0)
            {
                // 도로 공사 X
                if (FixRoad.find(make_pair(make_pair(i - 1, j), make_pair(i, j))) == FixRoad.end())
                {
                    DP[i][j] += DP[i - 1][j];
                }
            }
        }
    }

    cout << DP[N][M];
}
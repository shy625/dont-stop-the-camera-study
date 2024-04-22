#include <iostream>
#include <vector>
#define MAX 100000000
using namespace std;
int N, M;
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> M;

    vector<vector<int>> Dis(N + 1, vector<int>(N + 1, MAX));

    for (int i = 0; i < N - 1; i++)
    {
        int a, b, dis;
        cin >> a >> b >> dis;

        Dis[a][b] = dis;
        Dis[b][a] = dis;
    }

    // 크루스칼
    for (int k = 1; k <= N; k++)
    {
        for (int i = 1; i <= N; i++)
        {
            if (i == k)
                continue;
            for (int j = 1; j <= N; j++)
            {
                if (i == j || k == j)
                    continue;

                if (Dis[i][j] > Dis[i][k] + Dis[k][j])
                {
                    Dis[i][j] = Dis[j][i] = Dis[i][k] + Dis[k][j];
                }
            }
        }
    }

    // 출력
    for (int i = 0; i < M; i++)
    {
        int a, b;

        cin >> a >> b;
        cout << Dis[a][b] << endl;
    }
}
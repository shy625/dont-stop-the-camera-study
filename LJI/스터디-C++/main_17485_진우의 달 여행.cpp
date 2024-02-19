#include <iostream>
#include <vector>
#include <queue>
using namespace std;
int N, M;
int dr[] = {-1, 1};
int dc[] = {-1, 0, 1};
bool Check(int r, int c)
{
    return r >= 0 && r <= N && c >= 0 && c < M;
}
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    cin >> N >> M;

    vector<vector<int>> Vec(N + 1, vector<int>(M, 0));
    vector<vector<vector<int>>> DP(N + 1, vector<vector<int>>(M, vector<int>(3, 0)));

    for (int i = 1; i <= N; i++)
    {
        for (int j = 0; j < M; j++)
        {
            cin >> Vec[i][j];
        }
    }

    // DP갱신
    int nr, nc;
    for (int i = 1; i <= N; i++)
    {
        for (int j = 0; j < M; j++)
        {

            for (int d = 0; d < 3; d++)
            {

                // D방향으로 나가는 DP 값은 D방향을 제외한 값의 방향들의 DP값중 최소값
                int least = INT32_MAX;
                for (int k = 0; k < 3; k++)
                {
                    if (d == k)
                        continue;
                    int preR = i - 1;
                    int preC = j + dc[k];

                    if (!Check(preR, preC))
                        continue;
                    least = min(least, DP[preR][preC][k]);
                }

                DP[i][j][d] = least + Vec[i][j];
            }
        }
    }

    int answer = INT32_MAX;

    for (int i = 0; i < M; i++)
    {
        for (int j = 0; j < 3; j++)
        {
            answer = min(answer, DP[N][i][j]);
        }
    }

    cout << answer;
}
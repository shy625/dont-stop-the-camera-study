#include <iostream>
#include <vector>
using namespace std;
int N, M;

main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> M;

    vector<vector<int>> Dessert(N + 1, vector<int>(M + 1, 0));

    for (int i = 1; i <= M; i++)
    {
        for (int j = 1; j <= N; j++)
        {
            int tmp;
            cin >> tmp;

            Dessert[j][i] = tmp;
        }
    }

    vector<vector<int>> DP(N + 1, vector<int>(M + 1, 0));

    for (int i = 1; i <= N; i++)
    {
        for (int j = 1; j <= M; j++)
        {
            // 첫째날 //기본값 초기화
            if (i == 1)
            {
                DP[i][j] = Dessert[i][j];
                continue;
            }

            // 이전 날들의 만족도와 오늘의 만족도 총합으로 구하기
            // 이전 날의 k음식을 먹었을 때 오늘의 만족도
            for (int k = 1; k <= M; k++)
            {
                if (k == j)
                    DP[i][j] = max(DP[i][j], DP[i - 1][k] + Dessert[i][j] / 2);
                else
                    DP[i][j] = max(DP[i][j], DP[i - 1][k] + Dessert[i][j]);
            }
        }
    }

    int answer = 0;
    for (int i = 1; i <= M; i++)
    {
        answer = max(answer, DP[N][i]);
    }

    cout << answer;
}
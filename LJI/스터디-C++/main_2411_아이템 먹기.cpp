#include <iostream>
#include <vector>
using namespace std;
// 상하 반전해서 작업하기 편하게 사용
int N, M, A, B;

main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> M >> A >> B;

    // 1:아이템,-1:벽
    vector<vector<int>> MAP(N + 1, vector<int>(M + 1, 0));

    vector<vector<vector<int>>> DP(N + 1, vector<vector<int>>(M + 1, vector<int>(A + 1, 0)));

    int x, y;
    for (int i = 0; i < A; i++)
    {
        cin >> x >> y;
        MAP[x][y] = 1;
    }

    for (int i = 0; i < B; i++)
    {
        cin >> x >> y;
        MAP[x][y] = -1;
    }

    if (MAP[1][1] == 1)
    {
        DP[1][1][1] = 1;
    }
    else
    {
        DP[1][1][0] = 1;
    }

    for (int i = 1; i <= N; i++)
    {
        for (int j = 1; j <= M; j++)
        {
            // 맨 처음은 초기화 완료
            if (i == 1 && j == 1)
                continue;

            // 벽이면 패스
            if (MAP[i][j] == -1)
                continue;

            // 현재위치가 아이템
            if (MAP[i][j] == 1)
            {
                for (int k = 1; k <= A; k++)
                {
                    DP[i][j][k] = DP[i - 1][j][k - 1] + DP[i][j - 1][k - 1];
                }
            }
            // 현재 위치가 빈공간
            else
            {
                for (int k = 0; k <= A; k++)
                {
                    DP[i][j][k] = DP[i - 1][j][k] + DP[i][j - 1][k];
                }
            }
        }
    }

    cout << DP[N][M][A];
}
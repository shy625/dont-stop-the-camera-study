#include <iostream>
#include <vector>
using namespace std;

// 총 하얀칸 갯수 세고 최소 갯수로 도달하는 경우 구해서 빼기
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    int M;

    cin >> M;

    vector<vector<int>> Path(2, vector<int>(M, 0));

    int WhiteCnt = 0;
    for (int i = 0; i < 2; i++)
    {
        string Tmp;
        cin >> Tmp;
        for (int j = 0; j < M; j++)
        {
            if (Tmp[j] == '#')
            {
                Path[i][j] = 1;
            }
            else
            {
                WhiteCnt++;
            }
        }
    }

    vector<vector<int>> DP(2, vector<int>(M, INT32_MAX / 10));

    // 초기화
    for (int i = 0; i < 2; i++)
    {
        if (Path[i][0] == 0)
        {
            DP[i][0] = 1;
        }
    }

    for (int i = 0; i < M - 1; i++)
    {
        // 위아래 먼저
        if (Path[0][i] == 0 && Path[1][i] == 0)
        {
            DP[0][i] = min(DP[0][i], DP[1][i] + 1);
            DP[1][i] = min(DP[0][i] + 1, DP[1][i]);
        }

        // 옆 체크
        for (int j = 0; j < 2; j++)
        {
            if (Path[j][i] == 0 && Path[j][i + 1] == 0)
            {
                DP[j][i + 1] = DP[j][i] + 1;
            }
        }
    }

    int MinWhite = INT32_MAX;
    for (int i = 0; i < 2; i++)
    {
        if (Path[i][M - 1] == 0)
        {
            MinWhite = min(MinWhite, DP[i][M - 1]);
        }
    }

    cout << WhiteCnt - MinWhite;
}
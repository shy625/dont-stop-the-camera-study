#include <iostream>
#include <vector>
using namespace std;
vector<int> bead(3, 0);

// 한쪽 통의 0개 반대쪽의 뺼 수 있는 구슬 개가 남으면 그땐 플레이어의 승리

main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    for (int i = 0; i < 3; i++)
    {
        cin >> bead[i];
    }

    vector<vector<bool>> DP(501, vector<bool>(501, false));
    // true일 때 플레이어의 승리
    // i,j일때 bead만큼 뺀 위치의 값의 반대값
    // 플레이어는 최대한 승리를 향한다(true값이 가능하면 true)
    for (int i = 0; i < 501; i++)
    {
        for (int j = 0; j < 501; j++)
        {

            for (int b = 0; b < 3; b++)
            {
                // i,j 통에서 전부 빼기 가능
                if (i >= bead[b] && j >= bead[b])
                {
                    // 둘 중 하나만 false 여도 true가능
                    if (!DP[i - bead[b]][j] || !DP[i][j - bead[b]])
                        DP[i][j] = true;
                    else
                        DP[i][j] = false;
                }
                else if (i >= bead[b] && !DP[i - bead[b]][j])
                    DP[i][j] = true;
                else if (j >= bead[b] && !DP[i][j - bead[b]])
                    DP[i][j] = true;

                if (DP[i][j])
                    break;
            }
        }
    }

    for (int t = 1; t <= 5; t++)
    {
        int a, b;
        cin >> a >> b;

        int Winner = 1;

        if (DP[a][b])
        {
            cout << "A\n";
        }
        else
        {
            cout << "B\n";
        }
    }
}
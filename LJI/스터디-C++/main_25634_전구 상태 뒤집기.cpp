#include <iostream>
#include <vector>
using namespace std;
int N;

// DP[전구 번호][ON/OFF][Turn 여부]
//
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N;

    vector<int> Bulb(N + 1, 0);
    vector<int> OnOff(N + 1, 0);

    for (int i = 1; i <= N; i++)
    {
        cin >> Bulb[i];
    }

    for (int i = 1; i <= N; i++)
    {
        cin >> OnOff[i];
    }

    // DP[전구 번호][ON/OFF][Turn 여부]
    // Turn 여부 -> 0: 미사용 1: 사용 중 2: 사용했었음
    vector<vector<vector<int>>> DP(N + 1, vector<vector<int>>(2, vector<int>(3, -1)));

    // 처음엔 아무 연산 하지 않은 상태만 0으로 시작
    for (int i = 0; i < 2; i++)
    {
        DP[0][i][0] = 0;
    }

    for (int i = 1; i <= N; i++)
    {
        // 뒤집은 연산 한번도 안한 현재 전구 상태 DP[i][][0]

        for (int j = 0; j < 2; j++)
        {
            if (DP[i - 1][j][0] != -1)
            {
                if (OnOff[i])
                {
                    DP[i][1][0] = max(DP[i][1][0], DP[i - 1][j][0] + Bulb[i]);
                }
                else
                {
                    DP[i][0][0] = max(DP[i][0][0], DP[i - 1][j][0]);
                }
            }
        }

        //  현재 위치 연산 시도
        for (int j = 0; j < 2; j++)
        {
            // 0: 처음 연산 시도 , 1: 이전 연산에서 이어서 연산 시도
            for (int k = 0; k <= 1; k++)
            {
                if (DP[i - 1][j][k] != -1)
                {
                    if (OnOff[i])
                    {
                        DP[i][0][1] = max(DP[i][0][1], DP[i - 1][j][k]);
                    }
                    else
                    {
                        DP[i][1][1] = max(DP[i][1][1], DP[i - 1][j][k] + Bulb[i]);
                    }
                }
            }
        }

        // 현재 위치는 그대로 이전 연산 사용했던 경우
        for (int j = 0; j < 2; j++)
        {
            // 1: 이전까지 연산 중이었고 이번에 끊기 , 2: 과거에 연산이 있었던 경우
            for (int k = 1; k <= 2; k++)
            {
                if (DP[i - 1][j][k] != -1)
                {
                    if (OnOff[i])
                    {
                        DP[i][1][2] = max(DP[i][1][2], DP[i - 1][j][k] + Bulb[i]);
                    }
                    else
                    {
                        DP[i][0][2] = max(DP[i][0][2], DP[i - 1][j][k]);
                    }
                }
            }
        }
    }

    int Answer = 0;
    for (int i = 0; i < 2; i++)
    {
        // 최소 한번의 연산 필요하므로 1~2에서 탐색
        for (int j = 1; j < 3; j++)
        {
            Answer = max(Answer, DP[N][i][j]);
        }
    }

    cout << Answer;
}
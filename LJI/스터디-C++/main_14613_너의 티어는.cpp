#include <iostream>
#include <algorithm>
#include <cmath>
using namespace std;

// 점수는 50점 단위
// 판은 20판 , 2000점 시작, 따라서 최대 범위 1000~3000점 (50점 단위씩 40칸 나온다)
// 0~40을 구간으로 나눠보면
// 0~9 브론즈
// 10~19 실버
// 20~29 골드
// 30~39 플래
// 40 다이아

// 2000점 이니 20 칸에서 시작 1칸당 50점이니 승패는 앞뒤로 한칸

// 각칸에 도달할 확률을 기록

const long MAX = pow(10, 8);
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    double win, lose, draw; // 확률 기록
    cin >> win >> lose >> draw;

    double dp[41][21];
    fill(&dp[0][0], &dp[40][21], 0);

    // 2000점 스타트
    dp[20][0] = 1.0f;

    // 20번의 랭크 스타트
    for (int i = 1; i <= 20; i++)
    {
        // 모든 구간에서 확률이 0이 아니면 확률 계산
        for (int j = 0; j <= 40; j++)
        {
            if (dp[j][i - 1] != 0)
            {
                int loseIdx = j - 1, winIdx = j + 1, drawIdx = j;

                // 진 경우
                if (lose != 0 && loseIdx >= 0)
                {
                    dp[loseIdx][i] += dp[j][i - 1] * lose;
                }

                // 이긴 경우
                if (win != 0 && winIdx <= 40)
                {
                    dp[winIdx][i] += dp[j][i - 1] * win;
                }
                // 비긴 경우
                if (draw != 0)
                {
                    dp[drawIdx][i] += dp[j][i - 1] * draw;
                }
            }
        }
    }

    // 확률 계산 후 출력
    int idx = 0;
    double rate[5];
    fill(&rate[0], &rate[5], 0);

    for (int i = 0; i <= 40; i++)
    {
        rate[i / 10] += dp[i][20];
    }

    // cout에서 지수 표기법으로 출력되는 것을 고치기 위함
    cout << fixed;
    cout.precision(8);

    for (int i = 0; i < 5; i++)
    {
        cout << round(rate[i] * MAX) / MAX << endl;
    }
}
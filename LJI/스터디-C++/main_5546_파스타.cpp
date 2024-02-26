#include <iostream>
#include <vector>
#include <queue>
using namespace std;
int N, K;
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    cin >> N >> K;

    // DP[날짜][파스타 종류][중첩수]
    vector<vector<vector<int>>> DP(N + 1, vector<vector<int>>(4, vector<int>(3, 0)));
    vector<vector<bool>> Check(N + 1, vector<bool>(4, true));

    for (int i = 0; i < K; i++)
    {
        int day, souce;
        cin >> day >> souce;
        // 못먹는 소스들 false 체크
        for (int i = 1; i <= 3; i++)
        {
            if (i == souce)
                continue;
            Check[day][i] = false;
        }
    }

    // 첫째날 초기화//혹시 먹는게 정해졌다면 그날만 1 기입
    for (int i = 1; i <= 3; i++)
    {
        if (Check[1][i])
        {
            DP[1][i][1] = 1;
        }
    }

    // 둘째날 부터 기록 시작
    for (int i = 2; i <= N; i++)
    {
        for (int j = 1; j <= 3; j++)
        {
            // 못 먹는 날이면 패스
            if (!Check[i][j])
                continue;

            // 한번 중첩된 날 계산=
            // 다른 소스는 1번,2번 중첩된 경우+
            // 같은 소스는 0번 포함
            for (int k = 1; k <= 3; k++)
            {
                if (k == j)
                    continue;
                for (int r = 1; r <= 2; r++)
                {
                    DP[i][j][1] += DP[i - 1][k][r];
                }
            }
            DP[i][j][1] %= 10000;

            // 두번 중첩된 경우는 전날 한번 중첩된 경우와 동일
            DP[i][j][2] = DP[i - 1][j][1];
        }
    }

    int answer = 0;
    for (int i = 1; i <= 3; i++)
    {
        for (int j = 1; j <= 2; j++)
        {
            answer += DP[N][i][j];
        }
    }

    cout << answer % 10000;
}
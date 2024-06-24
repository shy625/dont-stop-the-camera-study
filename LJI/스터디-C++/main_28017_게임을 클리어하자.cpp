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

    vector<vector<int>> Weapon(N + 1, vector<int>(M + 1, 0));

    // i번 회차에 j무기가 클리어하는 시간
    for (int i = 1; i <= N; i++)
    {
        for (int j = 1; j <= M; j++)
        {
            int tmp;
            cin >> tmp;
            Weapon[i][j] = tmp;
        }
    }

    vector<vector<int>> DP(N + 1, vector<int>(M + 1, 0));

    // 첫회차 초기화
    for (int i = 1; i <= M; i++)
    {
        DP[1][i] = Weapon[1][i];
    }

    // 2회차부터 DP 시작
    for (int i = 2; i <= N; i++)
    {
        // i회차일 때 j 무기를 사용했을 경우
        for (int j = 1; j <= M; j++)
        {
            int MinTime = INT32_MAX;

            // 이전 회차 중 j무기를 사용하지 않은 최소값
            for (int k = 1; k <= M; k++)
            {
                if (j == k)
                    continue;

                MinTime = min(MinTime, DP[i - 1][k]);
            }

            DP[i][j] = MinTime + Weapon[i][j];
        }
    }

    int answer = INT32_MAX;
    for (int i = 1; i <= M; i++)
    {
        answer = min(answer, DP[N][i]);
    }

    cout << answer;
}
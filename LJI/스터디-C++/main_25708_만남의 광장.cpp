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

    vector<vector<int>> map(N, vector<int>(M));

    vector<int> rSum(N, 0);
    vector<int> cSum(M, 0);

    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < M; j++)
        {
            cin >> map[i][j];

            rSum[i] += map[i][j];
            cSum[j] += map[i][j];
        }
    }

    int answer = -1000000;
    // r2 ,c2가 큰 값
    for (int r1 = 0; r1 < N; r1++)
    {
        for (int r2 = r1 + 1; r2 < N; r2++)
        {
            for (int c1 = 0; c1 < M; c1++)
            {
                for (int c2 = c1 + 1; c2 < M; c2++)
                {
                    // 둘러쌓인 녹지 개수 구하기
                    int greenCnt = (c2 - c1 - 1) * (r2 - r1 - 1);

                    // 길의 합 구하기//교차로 4곳 한번씩 빼주기
                    int roadSum = rSum[r1] + rSum[r2] + cSum[c1] + cSum[c2] - (map[r1][c1] + map[r2][c1] + map[r1][c2] + map[r2][c2]);

                    answer = max(answer, greenCnt + roadSum);
                }
            }
        }
    }

    cout << answer;
}
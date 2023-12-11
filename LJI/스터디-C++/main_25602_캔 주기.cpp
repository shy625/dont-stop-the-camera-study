#include <iostream>
#include <vector>
using namespace std;
int N, K;
vector<int> can;
vector<vector<int>> R;
vector<vector<int>> M;

void FeedCan(int day, int sum, int &answer)
{
    if (day == K)
    {
        answer = max(answer, sum);
        return;
    }

    // r,m이 각각 준 캔의 번호
    for (int r = 0; r < N; r++)
    {
        for (int m = 0; m < N; m++)
        {
            can[r]--;
            can[m]--;

            if (can[r] >= 0 && can[m] >= 0)
            {
                FeedCan(day + 1, sum + R[day][r] + M[day][m], answer);
            }

            can[r]++;
            can[m]++;
        }
    }
}
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    cin >> N >> K;
    can = vector<int>(N, 0);
    R = vector<vector<int>>(K, vector<int>(N));
    M = vector<vector<int>>(K, vector<int>(N));

    for (int i = 0; i < N; i++)
    {
        cin >> can[i];
    }

    for (int i = 0; i < K; i++)
    {
        for (int j = 0; j < N; j++)
        {
            cin >> R[i][j];
        }
    }

    for (int i = 0; i < K; i++)
    {
        for (int j = 0; j < N; j++)
        {
            cin >> M[i][j];
        }
    }

    int answer = 0;
    FeedCan(0, 0, answer);
    cout << answer;
}
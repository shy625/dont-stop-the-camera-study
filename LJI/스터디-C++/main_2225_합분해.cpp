#include <iostream>
#include <vector>
using namespace std;
int N, K;

// DP
// DP[N][K]가 있을 때 DP[i][j]의 경우의 수는?
// DP[0~i][j-1]의 합
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> K;

    vector<vector<int>> DP(N + 1, vector<int>(K + 1, 0));

    for (int i = 0; i <= N; i++)
    {
        DP[i][1] = 1;
    }

    int Div = 1000000000;
    for (int i = 2; i <= K; i++)
    {
        int Sum = 0;
        for (int j = 0; j <= N; j++)
        {
            Sum = (Sum + DP[j][i - 1]) % Div;
            DP[j][i] = Sum;
        }
    }

    cout << DP[N][K];
}
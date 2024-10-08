#include <iostream>
#include <vector>
using namespace std;

// DP
// i번째가 A수가 가능한 경우는 i-1에서  A보다 작은 모든 경우
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    int N;

    cin >> N;
    int Div = 10007;

    vector<vector<int>> DP(N + 1, vector<int>(10, 0));

    // 첫번째 초기화
    for (int i = 0; i <= 9; i++)
    {
        DP[1][i] = 1;
    }

    for (int i = 2; i <= N; i++)
    {
        int Sum = 0;
        for (int j = 0; j <= 9; j++)
        {
            Sum = (Sum + DP[i - 1][j]) % Div;
            DP[i][j] = Sum;
        }
    }

    int Answer = 0;
    for (int i = 0; i <= 9; i++)
    {
        Answer += DP[N][i];
        Answer = Answer % Div;
    }

    cout << Answer;
}
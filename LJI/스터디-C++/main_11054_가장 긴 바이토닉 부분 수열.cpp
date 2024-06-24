#include <iostream>
#include <vector>
using namespace std;

// 정방향의 커지는 값을 기억하는 DP
// 역방향으로 커지는 값을 기억하는 DP
// 각 위치마다 그 합을 구하면?

int N;
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N;
    vector<int> Vec(N);
    for (int i = 0; i < N; i++)
    {
        cin >> Vec[i];
    }

    // 정방향
    vector<int> ForwardDP(N, 1);
    for (int i = 1; i < N; i++)
    {
        for (int j = i - 1; j >= 0; j--)
        {
            if ((Vec[j] < Vec[i]) && (ForwardDP[i] < ForwardDP[j] + 1))
            {
                ForwardDP[i] = ForwardDP[j] + 1;
            }
        }
    }

    // 역방향
    vector<int> BackwardDP(N, 1);
    for (int i = N - 1 - 1; i >= 0; i--)
    {
        for (int j = i + 1; j < N; j++)
        {
            if ((Vec[j] < Vec[i]) && (BackwardDP[i] < BackwardDP[j] + 1))
            {
                BackwardDP[i] = BackwardDP[j] + 1;
            }
        }
    }

    int answer = 0;
    for (int i = 0; i < N; i++)
    {
        answer = max(answer, ForwardDP[i] + BackwardDP[i] - 1);
    }

    cout << answer;
}
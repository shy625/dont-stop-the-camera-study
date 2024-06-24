#include <iostream>
#include <vector>
using namespace std;
int N;

// K개를 모두 다른 글자로 변경 가능하기에
// 올 A로 만드는 경우와 올 B로 만들고 +1하는 경우의 수가 있다
// all A: 0 ,all B:1
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    string str;
    cin >> N >> str;

    vector<vector<int>> DP(N + 1, vector<int>(2, 1000001));

    // 시작 초기화

    if (str[0] == 'A')
    {
        DP[0][0] = 0;
        // all B로 만드려면 +1 필요
        DP[0][1] = 1;
    }
    else
    {
        DP[0][0] = 1;
        DP[0][1] = 0;
    }

    for (int i = 1; i < N; i++)
    {
        if (str[i] == 'A')
        {
            // A로 만드는 경우
            // A는 그대로니 여긴 바꾸는 연산 필요 X
            // 이전 all A 그대로와 all B+1 중 작은 값
            DP[i][0] = min(DP[i - 1][0], DP[i - 1][1] + 1);
            // B로 만드는 경우
            // all A와 함께 전체 뒤집는 연산 or all B에서 현재만 바꾸는 연산
            DP[i][1] = min(DP[i - 1][0] + 1, DP[i - 1][1] + 1);
        }
        // 현재 값이 B
        else
        {
            DP[i][0] = min(DP[i - 1][0] + 1, DP[i - 1][1] + 1);
            DP[i][1] = min(DP[i - 1][0] + 1, DP[i - 1][1]);
        }
    }

    // 정답은 allA를 만드는 경우와 allB를 만들고 뒤집는 경우 중 더 짧은 값을 출력
    cout << min(DP[N - 1][0], DP[N - 1][1] + 1);
}
#include <iostream>
#define NUM 1000000;
using namespace std;
// 현재와 -1자리까지 해서 1 이상 26이하이면 dp[n-2]개수 가능
// 현재가 1과 9사이면 dp[n-1]가능
int dp[5001] = {
    0,
};
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    int n;
    string str;
    cin >> str;
    n = str.length();
    fill(&dp[0], &dp[n + 1], 0);
    // 0,1의 자리는 초기화 해주어야할 듯

    if (str[0] == '0')
    {
        cout << 0 << endl;
        return 0;
    }

    dp[0] = dp[1] = 1;

    for (int i = 2; i <= n; i++)
    {

        // 10~26
        int tmp = (str[i - 2] - '0') * 10 + (str[i - 1] - '0');
        if (tmp >= 10 && tmp <= 26)
            dp[i] += dp[i - 2] % NUM;

        // 1~9;
        if (str[i - 1] != '0')
        {
            dp[i] += dp[i - 1] % NUM;
        }

        dp[i] %= NUM;
    }

    cout << dp[n];
}
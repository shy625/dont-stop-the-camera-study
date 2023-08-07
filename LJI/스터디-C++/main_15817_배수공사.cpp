#include <iostream>
#include <vector>
using namespace std;
// 배낭 문제 //여러개는 그냥 별개의 값이라 생각하고 진행하자
int N, x;
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> x;

    // 최대 길이
    int dp[10001] = {
        0,
    };
    // 0길이를 만드는 법 1개
    dp[0] = 1;

    for (int i = 0; i < N; i++)
    {
        // 파이프 길이와 개수 받기
        int len, n;
        cin >> len >> n;

        // 총 길이를 목표 x부터 역순으로 내려가면서 현재 길이 j에 도달할 수 있는 경우의 수들을 찾아서 더해준다
        for (int j = x; j > 0; j--)
        {
            int newLen = 0;
            for (int k = 0; k < n; k++)
            {
                newLen += len;
                if (j - newLen < 0)
                    break;
                dp[j] += dp[j - newLen];
            }
        }
    }

    cout << dp[x];
}
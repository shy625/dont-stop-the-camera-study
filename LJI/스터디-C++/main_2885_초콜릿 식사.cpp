#include <iostream>

using namespace std;

main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    int K;

    cin >> K;

    // 구매해야하는 초콜릿 크기
    int Max = 1;

    while (Max < K)
    {
        Max *= 2;
    }

    cout << Max << ' ';

    // 쪼개는 횟수
    int cnt = 0;

    while (K != 0)
    {
        if (Max <= K)
        {
            K -= Max;
            if (K == 0)
                break;
        }

        Max /= 2;
        cnt++;
    }

    cout << cnt;
}
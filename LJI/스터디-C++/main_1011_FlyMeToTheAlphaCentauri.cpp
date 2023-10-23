#include <iostream>
#include <queue>
using namespace std;
// 1 ->1
// 1 1 -> 2
// ----
// 1 1 1
// 1 2 1 -> 4
// 1 2 1 1
// 1 2 2 1 -> 6
// ----
// 1 2 2 1 1
// 1 2 2 2 1
// 1 2 3 2 1 -> 9
// 1 2 3 2 1 1
// 1 2 3 2 2 1
// 1 2 3 3 2 1 -> 12
// 위처럼 보면 이동 횟수가 홀수 짝수 쌍을 이루며 두번씩 같은 거리를 커버한다.
// 1 *1
// 1 *2
// --
// 2* 2
// 2* 3
// --
// 3* 3
// 3* 4
// 이대로 커버 거리별로 나눠보면 아래와 같은 식이 세워진다.
// 따라서 이를 바탕으로 식을 세우면 될 듯
int T;
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    cin >> T;

    long long a, b;
    for (int t = 0; t < T; t++)
    {

        int x, y;
        cin >> x >> y;

        int dis = y - x;
        a = 1;
        b = 1;

        int answer = 0;
        while (true)
        {

            if (a * b >= dis)
            {
                answer = a + b - 1;
                break;
            }
            else
            {
                if (a != b)
                {
                    a++;
                }
                else
                {
                    b++;
                }
            }
        }
        cout << answer << '\n';
    }
}
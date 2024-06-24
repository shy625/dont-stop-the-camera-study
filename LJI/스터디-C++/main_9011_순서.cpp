#include <iostream>
#include <vector>
using namespace std;
int T, n;
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> T;
    for (int t = 0; t < T; t++)
    {
        cin >> n;
        vector<int> R(n);
        vector<int> S(n);

        for (int i = 0; i < n; i++)
        {
            cin >> R[i];
        }

        // 사용하지 않은 숫자를 낮은 숫자부터 카운트 해서 목표한 개수+1이 존재하면 해당 값 배치
        bool fail = false;
        vector<bool> Used(n + 1, false);
        for (int i = n - 1; i >= 0; i--)
        {
            int need = R[i] + 1;

            int cnt = 0;
            // 남은 해의 개수 아래부터 세기

            bool find = false;
            for (int j = 1; j <= n; j++)
            {
                if (!Used[j])
                    cnt++;

                // 원하는 해가 존재
                if (cnt == need)
                {
                    Used[j] = true;
                    find = true;
                    S[i] = j;
                    break;
                }
            }

            if (!find)
            {
                fail = true;
                break;
            }
        }

        if (fail)
        {
            cout << "IMPOSSIBLE";
        }
        else
        {
            for (int cur : S)
            {
                cout << cur << ' ';
            }
        }
        cout << '\n';
    }
}
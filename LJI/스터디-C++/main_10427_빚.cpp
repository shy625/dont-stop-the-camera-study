#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int N, M, T;
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> T;

    for (int t = 0; t < T; t++)
    {

        cin >> N;
        vector<int> vec(N + 1);
        for (int i = 1; i <= N; i++)
        {
            cin >> vec[i];
        }

        // 오름차순 정렬
        sort(vec.begin(), vec.end());

        // 누적합 계산
        vector<int> Sum(N + 1, 0);

        for (int i = 1; i <= N; i++)
        {
            Sum[i] = vec[i] + Sum[i - 1];
        }

        // S(1)은 무조건 0이니 패스

        long long answer = 0;
        for (int i = 2; i <= N; i++)
        {
            int diffMin = INT32_MAX;
            for (int j = i; j <= N; j++)
            {
                int Pay = vec[j] * i - (Sum[j] - Sum[j - i]);
                diffMin = min(diffMin, Pay);
            }

            answer += diffMin;
        }

        cout << answer << '\n';
    }
}
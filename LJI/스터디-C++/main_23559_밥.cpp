#include <iostream>
#include <vector>
#include <algorithm>

// 5000원 짜리 메뉴-1000원 짜리 메뉴의 만족도가 가장 큰 것을 우선해서 구매
using namespace std;
int N, X;
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> X;

    vector<vector<int>> Menu(N, vector<int>(2, 0));

    vector<pair<int, int>> Gap;
    for (int i = 0; i < N; i++)
    {
        cin >> Menu[i][0] >> Menu[i][1];

        Gap.push_back(make_pair(Menu[i][0] - Menu[i][1], i));
    }

    // Gap을 차이 많이나는 순으로 정렬
    sort(Gap.begin(), Gap.end(), greater<pair<int, int>>());

    // 돈이 허락하고 Gap.first가 -가 아니면 5000원 짜리 구매
    int Answer = 0;
    for (int i = 0; i < N; i++)
    {
        // 5000원 짜리 메뉴가 이득이며 5000원짜리를 사도 다음날에 최소 1000원짜리를 살 수 있을 때
        if (Gap[i].first > 0 && X - 5000 >= 1000 * (N - 1 - i))
        {
            X -= 5000;
            Answer += Menu[Gap[i].second][0];
        }
        else
        {
            X -= 1000;
            Answer += Menu[Gap[i].second][1];
        }
    }

    cout << Answer;
}
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int T;
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    cin >> T;

    for (int t = 0; t < T; t++)
    {
        int n;
        cin >> n;

        vector<pair<int, int>> Cafe;
        for (int i = 0; i < n; i++)
        {
            int x, y;
            cin >> x >> y;
            Cafe.push_back(make_pair(x, y));
        }
        sort(Cafe.begin(), Cafe.end());

        for (int i = 0; i < n; i++)
        {
            // 오른쪽 방향으로 전진했거나 시작 위치일 때
            if (i == 0 || Cafe[i - 1].first != Cafe[i].first)
            {
                // 같은 x값의 개수 세기
                int cnt = 0;
                int idx = i;
                while (idx < n)
                {
                    if (Cafe[idx].first == Cafe[i].first)
                    {
                        cnt++;
                        idx++;
                    }
                    else
                    {
                        break;
                    }
                }

                if (cnt <= 1)
                {
                    continue;
                }
                // 위 아래 방향 정하기
                // 만약 이전 위치와 y축 좌표가 달라졌다면 역방향 정렬 필요
                // i==0인 시작 위치는 i+1의 x좌표로 계산
                if (i == 0)
                {
                    if (Cafe[i + 1].second < 0)
                    {
                        sort(Cafe.begin() + i, Cafe.begin() + i + cnt, greater<>());
                    }
                }
                else if (Cafe[i - 1].second != Cafe[i].second)
                {
                    sort(Cafe.begin() + i, Cafe.begin() + i + cnt, greater<>());
                }
            }
        }

        int m;
        cin >> m;
        for (int i = 0; i < m; i++)
        {
            int idx;
            cin >> idx;
            idx--;
            cout << Cafe[idx].first << ' ' << Cafe[idx].second << '\n';
        }
    }
}
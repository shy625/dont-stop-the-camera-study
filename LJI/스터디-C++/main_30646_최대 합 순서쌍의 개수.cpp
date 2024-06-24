#include <iostream>
#include <vector>
#include <map>
// 같은 값일 때 제일 왼쪽과 제일 오른쪽이 무조건 최대값
using namespace std;
int N;
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    cin >> N;

    // 누적합 기록
    vector<int> origin(N, 0);
    vector<long long> vec(N, 0);
    long long tmp;

    // 순서쌍의 왼쪽 오른쪽 끝 기억
    map<int, pair<int, int>> Map;
    for (int i = 0; i < N; i++)
    {
        cin >> tmp;
        origin[i] = tmp;

        // Map에 기록
        if (Map.find(tmp) == Map.end())
        {
            Map[tmp] = make_pair(i, i);
        }
        else
        {
            Map[tmp].second = i;
        }

        vec[i] = tmp;
        if (i > 0)
        {
            vec[i] += vec[i - 1];
        }
    }

    int cnt = 0;
    long long Max = 0;

    auto iter = Map.begin();
    while (iter != Map.end())
    {
        long long sum = 0;
        pair<int, int> value = iter->second;
        if (value.first == 0)
        {
            sum = vec[value.second];
        }
        else
        {
            sum = vec[value.second] - vec[value.first - 1];
        }

        if (Max == sum)
        {
            cnt++;
        }
        else if (Max < sum)
        {
            Max = sum;
            cnt = 1;
        }
        iter++;
    }

    cout << Max << ' ' << cnt;
}
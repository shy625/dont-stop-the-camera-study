#include <iostream>
#include <vector>
#include <algorithm>
// 끝내야하는 시간을 기준으로 내림차순 정렬
// 하나씩 꺼내보기
using namespace std;
int N;

bool compare(const pair<int, int> &a, const pair<int, int> &b)
{
    if (a.first == b.first)
        return a.second > b.second;
    return a.first > b.first;
}
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N;

    int t, s;
    vector<pair<int, int>> vec;
    for (int i = 0; i < N; i++)
    {
        cin >> t >> s;
        vec.push_back(make_pair(s, t));
    }

    sort(vec.begin(), vec.end(), compare);

    int answer = 1000001;

    for (int i = 0; i < N; i++)
    {
        s = vec[i].first;
        t = vec[i].second;

        answer = min(s, answer);
        answer -= t;

        if (answer < 0)
        {
            break;
        }
    }

    if (answer < 0)
    {
        cout << "-1";
    }
    else
    {
        cout << answer;
    }
}
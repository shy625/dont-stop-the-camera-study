#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int N;

bool compare(pair<int, int> a, pair<int, int> b)
{

    return a.first > b.first;
}

main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    cin >> N;

    vector<pair<int, int>> vec;
    for (int i = 0; i < N; i++)
    {
        int a, b;
        cin >> a >> b;

        // 끝내야하는 시간 앞으로 바꾸기
        vec.push_back(make_pair(b, a));
    }

    sort(vec.begin(), vec.end(), compare);

    // 시작 값 초기화
    int sT = vec[0].first;
    int t = vec[0].first - vec[0].second;

    for (int i = 1; i < N; i++)
    {
        if (t < 0)
            break;

        if (t > vec[i].first)
        {
            t = vec[i].first;
        }

        t -= vec[i].second;
    }

    if (t < 0)
        cout << -1;
    else
        cout << t;
}
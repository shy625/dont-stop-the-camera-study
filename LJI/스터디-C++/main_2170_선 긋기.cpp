#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int N;
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

        vec.push_back(make_pair(a, b));
    }

    // 낮은 순 정렬
    sort(vec.begin(), vec.end());

    int start = -1000000001;
    int end = -1000000001;

    int answer = 0;
    for (pair<int, int> p : vec)
    {
        // cout << p.first << ' ' << p.second << endl;
        //   이전 선분과 연결이 끊겼다.
        if (p.first > end)
        {
            answer += end - start;
            start = p.first;
        }

        if (end < p.second)
        {
            end = p.second;
        }
    }

    // 남은 선분 처리
    answer += end - start;

    cout << answer;
}
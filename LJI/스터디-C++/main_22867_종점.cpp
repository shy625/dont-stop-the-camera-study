#include <iostream>
#include <vector>
#include <queue>

using namespace std;
int N;

struct Compare
{
    bool operator()(const pair<string, pair<string, int>> &a, const pair<string, pair<string, int>> &b)
    {
        // 시간 기준
        if (a.first == b.first)
        {
            // in out기준
            if (a.second.first == b.second.first)
            {
                return a.second.second < b.second.second;
            }
            else
            {
                // 버스 번호
                return a.second.first == "in";
            }
        }
        else
        {
            return a.first > b.first;
        }
    }
};

main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N;

    priority_queue<pair<string, pair<string, int>>, vector<pair<string, pair<string, int>>>, Compare> PQ;

    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < 2; j++)
        {
            string str;
            cin >> str;
            if (j == 0)
            {
                PQ.push(make_pair(str, make_pair("in", i)));
            }
            else
            {
                PQ.push(make_pair(str, make_pair("out", i)));
            }
        }
    }

    int answer = 0;
    int now = 0;
    while (!PQ.empty())
    {
        // cout << PQ.top().first << ' ' << PQ.top().second.first << endl;
        if (PQ.top().second.first == "in")
        {
            now++;
        }
        else
        {
            now--;
        }
        answer = max(answer, now);
        PQ.pop();
    }

    cout << answer;
}
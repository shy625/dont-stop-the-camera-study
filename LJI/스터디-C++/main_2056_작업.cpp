#include <iostream>
#include <queue>
#include <vector>
using namespace std;
int N;

struct Comp
{
    bool operator()(pair<int, int> a, pair<int, int> b)
    {
        return a.second > b.second;
    }
};

main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N;

    vector<int> Time(N + 1, -1);

    vector<pair<int, vector<int>>> List(N + 1, make_pair(0, vector<int>()));
    vector<vector<int>> NeedList(N + 1);
    priority_queue<pair<int, int>, vector<pair<int, int>>, Comp> PQ;
    for (int i = 1; i <= N; i++)
    {
        int t, n, tmp;

        // 시간 정보와 연결 개수 받기
        cin >> t >> n;

        List[i].first = t;

        if (n == 0)
        {

            PQ.push(make_pair(i, 0));
        }
        else
        {
            // tmp를 끝냈으면 i를 끝낼 가능성이 있다.
            for (int j = 0; j < n; j++)
            {
                cin >> tmp;
                List[tmp].second.push_back(i);
                NeedList[i].push_back(tmp);
            }
        }
    }

    int cnt = 0;
    int answer = -1;
    while (cnt != N)
    {
        pair<int, int> cur = PQ.top();
        PQ.pop();

        // 이미 작업을 완료했다면 넘기자
        if (Time[cur.first] != -1)
            continue;

        // 작업이 가능한지 체크
        int time = cur.second;
        bool check = true;
        for (int need : NeedList[cur.first])
        {
            if (Time[need] == -1)
            {
                check = false;
                break;
            }
            time = max(time, Time[need]);
        }

        if (!check)
            continue;

        Time[cur.first] = List[cur.first].first + time;
        answer = max(answer, Time[cur.first]);
        cnt++;

        for (int next : List[cur.first].second)
        {
            PQ.push(make_pair(next, Time[cur.first]));
        }
    }

    cout << answer;
}
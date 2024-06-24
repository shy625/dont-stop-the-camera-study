#include <iostream>
#include <vector>
#include <queue>
using namespace std;
int n, m, a, b, c, s, t;
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> n >> m;

    vector<vector<pair<int, int>>> Graph(n + 1);
    vector<int> Dijk(n + 1, 10000000);
    vector<bool> Visited(n + 1, false);
    for (int i = 0; i < m; i++)
    {
        cin >> a >> b >> c;

        Graph[a].push_back(make_pair(c, b));
        Graph[b].push_back(make_pair(c, a));
    }

    cin >> s >> t;

    // 시작 위치 설정
    // pq의 pair는 거리값,정점 순
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
    pq.push(make_pair(0, s));
    Dijk[s] = 0;

    while (!pq.empty())
    {
        int cur = pq.top().second;
        int dist = pq.top().first;
        pq.pop();

        // PQ를 사용했기에 방문을 처음 했을 때가 거리가 최소값이므로 두번 방문할 필요 없다.
        if (Visited[cur])
            continue;
        Visited[cur] = true;
        // 종료 조건
        if (cur == t)
        {
            break;
        }

        for (pair<int, int> next : Graph[cur])
        {
            int nextNode = next.second;
            int nextDist = next.first + dist;

            // 만약 기존 거리 값보다 작아졌다면
            if (nextDist < Dijk[nextNode])
            {
                Dijk[nextNode] = nextDist;
                pq.push(make_pair(nextDist, nextNode));
            }
        }
    }

    cout << Dijk[t];
}
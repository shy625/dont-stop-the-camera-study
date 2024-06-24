#include <iostream>
#include <vector>
#include <queue>
#define INF 99999999
using namespace std;
int N, V, E;
int KIST, CR;
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> V >> E;
    cin >> KIST >> CR;

    // 팀원 집 정보
    vector<int> member;
    for (int i = 0; i < N; i++)
    {
        int n;
        cin >> n;
        member.push_back(n);
    }

    // 그래프 만들기
    vector<vector<pair<int, int>>> graph;
    for (int i = 0; i <= V; i++)
    {
        graph.push_back(vector<pair<int, int>>());
    }
    for (int i = 0; i < E; i++)
    {
        int a, b, len;
        cin >> a >> b >> len;
        graph[a].push_back(make_pair(b, len));
        graph[b].push_back(make_pair(a, len));
    }

    // 각 기사단 팀원 수 만큼 다익스트라
    int answer = 0;
    for (int t = 0; t < N; t++)
    {
        int start = member[t];

        vector<int> dist(V + 1, INF);
        priority_queue<pair<int, int>> pq;
        pq.push(make_pair(0, start)); // 거리,정점
        dist[start] = 0;

        while (!pq.empty())
        {
            int Cost = -pq.top().first;
            int Cur = pq.top().second;
            pq.pop();

            int Size = graph[Cur].size();
            for (int i = 0; i < Size; i++)
            {
                int Next = graph[Cur][i].first;
                int NextCost = graph[Cur][i].second;

                if (dist[Next] > Cost + NextCost)
                {
                    dist[Next] = Cost + NextCost;
                    pq.push(make_pair(-dist[Next], Next));
                }
            }
        }

        // 거리 구하기
        int sum = 0;
        if (dist[KIST] == INF)
        {
            dist[KIST] = -1;
        }
        if (dist[CR] == INF)
        {
            dist[CR] = -1;
        }

        // cout << dist[KIST] << " CR: " << dist[CR] << endl;
        sum = dist[KIST] + dist[CR];
        answer += sum;
    }
    cout << answer;
}
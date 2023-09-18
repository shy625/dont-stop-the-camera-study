#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
#define INF 10000001
using namespace std;

// 각 집까지 거리를 다익스트라로 구하고 거리*2의 값이 X를 넘지 않는 선까지 하루동안 배달
int N, M, X, Y;
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> M >> X >> Y;

    vector<vector<pair<int, int>>> Graph;
    for (int i = 0; i < N; i++)
    {
        Graph.push_back(vector<pair<int, int>>());
    }

    for (int i = 0; i < M; i++)
    {
        int a, b, len;
        cin >> a >> b >> len;
        Graph[a].push_back(make_pair(b, len));
        Graph[b].push_back(make_pair(a, len));
    }

    // 다익스트라 개시
    vector<int> Dist(N, INF);
    priority_queue<pair<int, int>> PQ;
    PQ.push(make_pair(0, Y)); // Y에서 시작
    Dist[Y] = 0;

    while (!PQ.empty())
    {
        int Cost = -PQ.top().first;
        int Cur = PQ.top().second;
        PQ.pop();

        int Size = Graph[Cur].size();
        for (int i = 0; i < Size; i++)
        {
            int Next = Graph[Cur][i].first;
            int NextCost = Graph[Cur][i].second;
            if (Dist[Next] > Cost + NextCost)
            {
                Dist[Next] = Cost + NextCost;
                PQ.push(make_pair(-Dist[Next], Next));
            }
        }
    }

    // 며칠 걸리는지 계산하기
    // 집 낮은 순으로 정렬
    sort(Dist.begin(), Dist.end());
    int answer = 0;
    int sum = 0;
    // N개의 집을 전부 돌면서 검사할 것
    for (int i = 0; i < N; i++)
    {
        if (2 * Dist[i] > X)
        { // 절대 갈 수 없는 집이 있다
            sum = 0;
            answer = -1;
            break;
        }

        int newSum = sum + Dist[i];
        if (newSum * 2 > X)
        {                  // 이전 거리까지만 배달 가능
            sum = Dist[i]; // 현재 집은 다음날 배달에 포함
            answer++;      // 이전거리까지만 하루 배달
        }
        else
        { // 현재 집까지 하루에 가능
            sum = newSum;
        }
    }
    if (sum != 0) // 남은 집 처리
    {
        answer++;
    }

    cout << answer;
}
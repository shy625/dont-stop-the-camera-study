#include <iostream>
#include <vector>
#include <queue>
using namespace std;
int N, M, S;
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    cin >> N >> M;

    // 단방향 그래프
    vector<vector<int>> Graph(N + 1);

    for (int i = 0; i < M; i++)
    {
        int u, v;

        cin >> u >> v;

        Graph[u].push_back(v);
    }

    cin >> S;
    vector<bool> Fan(N + 1, false);
    for (int i = 0; i < S; i++)
    {
        int s;
        cin >> s;

        Fan[s] = true;
    }

    if (Fan[1])
    {
        cout << "Yes";
        return 0;
    }

    bool FindWay = false;

    // BFS를 통해서 길 찾기
    // 사이클 없음이 보장됨

    vector<bool> Visited(N + 1, false);
    queue<int> Que;
    Que.push(1);
    Visited[1] = true;
    while (!Que.empty())
    {
        int Cur = Que.front();
        Que.pop();
        if (Graph[Cur].empty())
        {
            FindWay = true;
            break;
        }

        for (int Next : Graph[Cur])
        {
            if (Fan[Next] || Visited[Next])
                continue;

            Visited[Next] = true;
            Que.push(Next);
        }
    }

    if (FindWay)
        cout << "yes";
    else
        cout << "Yes";
}
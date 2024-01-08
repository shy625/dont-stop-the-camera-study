#include <iostream>
#include <vector>

using namespace std;
int N, M;
// dfs를 4번 이상 반복했으면 가능

void DFS(int cnt, int now, vector<vector<int>> &graph, vector<bool> &visited, bool &find)
{
    if (cnt == 4)
    {
        find = true;
        return;
    }

    visited[now] = true;
    for (int next : graph[now])
    {
        if (!visited[next])
        {
            DFS(cnt + 1, next, graph, visited, find);
            if (find)
                return;
        }
    }
    visited[now] = false;
}
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    cin >> N >> M;
    vector<vector<int>> graph(N);
    vector<bool> visited(N, false);
    int a, b;
    for (int i = 0; i < M; i++)
    {
        cin >> a >> b;

        graph[a].push_back(b);
        graph[b].push_back(a);
    }

    bool find = false;

    for (int i = 0; i < N; i++)
    {
        DFS(0, i, graph, visited, find);
        if (find)
            break;
    }

    if (find)
    {
        cout << "1";
    }
    else
    {
        cout << "0";
    }
}
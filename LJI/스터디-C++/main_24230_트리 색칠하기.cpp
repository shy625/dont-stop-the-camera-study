#include <iostream>
#include <vector>
using namespace std;

int N;
// 1번이 정점이므로 1번부터 출발해서 dfs 색이 바뀔때마다 정답+1

void DFS(int Node, int PrevColor, int &AnswerCnt,
         vector<vector<int>> &Tree, vector<int> &Color, vector<bool> &Visited)
{
    // 현재 노드색이 이전과 색이 다르면 AnswerCnt++;
    if (Color[Node] != PrevColor)
    {
        AnswerCnt++;
    }

    // 현재 노드와 이어진 노드를 방문 안했다면 DFS
    for (int Next : Tree[Node])
    {
        if (!Visited[Next])
        {
            Visited[Next] = true;
            DFS(Next, Color[Node], AnswerCnt, Tree, Color, Visited);
        }
    }
}
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N;

    vector<vector<int>> Tree(N + 1);
    vector<int> Color(N + 1, 0);

    for (int i = 1; i <= N; i++)
    {
        cin >> Color[i];
    }

    for (int i = 1; i < N; i++)
    {
        int a, b;
        cin >> a >> b;
        Tree[a].push_back(b);
        Tree[b].push_back(a);
    }

    vector<bool> Visited(N + 1, false);

    int AnswerCnt = 0;

    Visited[1] = true;
    DFS(1, 0, AnswerCnt, Tree, Color, Visited);

    cout << AnswerCnt;
}
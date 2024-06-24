#include <iostream>
#include <vector>
using namespace std;
// 자신보다 앞에선 사람들이 모두 나왔으면 나와도 된다.
// 따라서 자신보다 앞에 서있는 사람에 대한 정보가 필요하다

int N, M;
vector<vector<int>> Graph;
vector<bool> Visited;

// 만약 연결된 노드가 없거나 있더라도 이미 방문한 노드일시 방문 처리하고 출력하기
void Go(int idx)
{
    if (Visited[idx])
        return;

    if (Graph[idx].size() != 0)
    {
        // 연결된 그래프가 있으면 해당 먼저 해결
        for (int next : Graph[idx])
        {
            if (!Visited[next])
                Go(next);
        }
    }

    Visited[idx] = true;
    cout << idx << ' ';
}
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> M;

    // Graph[a][b]-> a는 b가 앞에 있어야한다.
    Graph = vector<vector<int>>(N + 1);

    int A, B;
    for (int i = 0; i < M; i++)
    {
        cin >> A >> B;
        Graph[B].push_back(A);
    }

    Visited = vector<bool>(N + 1, false);

    for (int i = 1; i <= N; i++)
    {
        if (!Visited[i])
        {
            Go(i);
        }
    }
}
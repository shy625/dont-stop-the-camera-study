#include <iostream>
#include <vector>
#include <queue>
using namespace std;
int N, M;
int answerCnt;

int Hacking(int Num, vector<vector<int>> &Graph, vector<bool> &visited)
{

    // 해킹 가능한 개수 세기
    // 자기 자신을 해킹 가능하므로 무조건 하나는 가능
    int Cnt = 1;

    // BFS로 연결된 노드들 개수 세기
    queue<int> que;
    que.push(Num);
    visited[Num] = true;

    while (!que.empty())
    {
        int cur = que.front();
        Cnt++;
        que.pop();
        for (int Next : Graph[cur])
        {
            if (visited[Next])
                continue;

            visited[Next] = true;
            que.push(Next);
        }
    }

    answerCnt = max(answerCnt, Cnt);
    return Cnt;
}

main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    cin >> N >> M;

    vector<vector<int>> Graph(N + 1);

    int a, b;
    for (int i = 0; i < M; i++)
    {
        cin >> a >> b;

        Graph[b].push_back(a);
    }

    // 해킹 가능한 컴퓨터 수를 세는 벡터
    vector<int> CntVec(N + 1, 0);
    vector<bool> visited(N + 1, false);
    answerCnt = 0;
    for (int i = 1; i <= N; i++)
    {
        // 이미 한번 갯수를 센 적이 있다.
        if (CntVec[i] != 0)
            continue;

        fill(visited.begin(), visited.end(), false);
        CntVec[i] = Hacking(i, Graph, visited);
    }

    vector<int> answerVec;
    for (int i = 1; i <= N; i++)
    {
        if (CntVec[i] == answerCnt)
        {
            answerVec.push_back(i);
        }
    }

    for (int cur : answerVec)
    {
        cout << cur << ' ';
    }
}

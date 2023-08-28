#include <iostream>
#include <vector>

using namespace std;

int N, removeNode;

void dfs(int node, vector<vector<int>> graph, int &answer)
{
    // 현재 노드의 자식 노드가 0개면 answer+1
    // 만약 자식이 있다해도 removeNode면 dfs들가지 말고 제거

    int childCnt = 0;

    int size;
    size = graph[node].size();
    for (int i = 0; i < size; i++)
    {
        int childNode = graph[node][i];

        // reomveNode면 그냥 넘기기
        if (childNode == removeNode)
            continue;

        // 아니면 dfs하고 chlidCnt+1
        dfs(childNode, graph, answer);
        childCnt++;
    }

    // 최종적으로 자식이 0명일시 리프노드
    if (childCnt == 0)
    {
        answer++;
    }
}

main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N;

    vector<vector<int>> graph;
    for (int i = 0; i < N; i++)
    {
        graph.push_back(vector<int>());
    }

    int startNode;

    for (int i = 0; i < N; i++)
    {
        int parentNode;
        cin >> parentNode;

        if (parentNode == -1)
        {
            startNode = i;
            continue;
        }

        graph[parentNode].push_back(i);
    }

    cin >> removeNode;

    int answer = 0;
    // 시작노드가 삭제 노드인 것은 체크 못하기에 여기서 체크
    if (startNode != removeNode)
        dfs(startNode, graph, answer);

    cout << answer;
}
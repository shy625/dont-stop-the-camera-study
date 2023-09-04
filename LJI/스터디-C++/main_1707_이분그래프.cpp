#include <iostream>
#include <vector>
#include <queue>
using namespace std;

int K, V, E;
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    cin >> K;

    for (int k = 0; k < K; k++)
    {
        cin >> V >> E;

        // 그래프 만들기
        vector<vector<int>> graph;
        for (int i = 0; i <= V; i++)
        {
            graph.push_back(vector<int>());
        }

        for (int i = 0; i < E; i++)
        {
            int a, b;
            cin >> a >> b;
            graph[a].push_back(b);
            graph[b].push_back(a);
        }

        // bfs하면서 그룹 분류해보다가 안되면 NO출력 전부 완료되면 YES출력
        //  어떤 그룹에 속해있는지 표시 0:속해있지 않음, 그룹:1,2
        int group[V + 1];
        fill(&group[0], &group[V + 1], 0);

        bool bBipartGraph = true;
        // 그룹마다 실행해야할 것
        for (int i = 1; i <= V; i++)
        {
            // 이미 그룹이 정해져있다면 실행 X
            if (group[i] != 0)
                continue;

            // 그룹이 없다면 현재 노드는 연결된 모든 노드들을 향해 BFS 실행
            // 현재 노드 그룹 1에 배정
            group[i] = 1;
            queue<int> que;
            que.push(i);
            // bfs해보기
            while (!que.empty())
            {
                int cur = que.front();
                que.pop();

                int curGroup = group[cur];
                // 연결된 그래프는 전부 반대 그룹이어야한다
                for (int next : graph[cur])
                {
                    // 연결된 노드가 그룹이 정해지지 않았다면
                    if (group[next] == 0)
                    {
                        // 현재 내가 1그룹
                        if (curGroup == 1)
                        {
                            group[next] = 2;
                        }
                        else
                        {
                            group[next] = 1;
                        }

                        que.push(next);
                    }
                    // 그룹이 정해져있다면
                    else
                    {
                        // 둘이 같은 그룹이면 불가하다
                        if (group[cur] == group[next])
                        {
                            bBipartGraph = false;
                            break;
                        }
                    }
                }

                if (!bBipartGraph)
                    break;
            }
            if (!bBipartGraph)
                break;
        }

        if (bBipartGraph)
            cout << "YES" << endl;
        else
            cout << "NO" << endl;
    }
}
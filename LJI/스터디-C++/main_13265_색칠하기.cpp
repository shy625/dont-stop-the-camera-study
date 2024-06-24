#include <iostream>
#include <vector>
#include <queue>
using namespace std;
int T, n, m;

main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> T;

    for (int t = 0; t < T; t++)
    {

        cin >> n >> m;

        vector<vector<int>> Graph(n + 1);

        for (int i = 0; i < m; i++)
        {
            int a, b;
            cin >> a >> b;

            Graph[a].push_back(b);
            Graph[b].push_back(a);
        }

        // 모든 노드를 BFS 하면서 색을 넣어보면서 체크
        // -1: 아직 색 없음 , 0:색 ,1: 다른 색

        vector<int> Node(n + 1, -1);

        bool Possible = true;
        for (int i = 1; i <= n; i++)
        {
            // 이미 불가능하다면 끝내기
            if (!Possible)
                break;
            // 이미 색이 지정됨
            if (Node[i] != -1)
                continue;

            // 색이 없다면 0색을 넣고 BFS로 색들 채우기
            Node[i] = 0;
            queue<pair<int, int>> que;
            que.push(make_pair(i, 0));
            while (!que.empty())
            {
                int idx = que.front().first;
                int color = que.front().second;
                que.pop();

                for (int next : Graph[idx])
                {
                    // 다음 위치 색이 현재 노드와 같다면 불가한 경우의 수
                    if (color == Node[next])
                    {
                        Possible = false;
                        break;
                    }

                    // 현재 색의 반대색을 넣고 que에 삽입
                    if (Node[next] == -1)
                    {
                        Node[next] = !color;
                        que.push(make_pair(next, Node[next]));
                    }
                }

                if (!Possible)
                {
                    break;
                }
            }
        }

        if (Possible)
        {
            cout << "possible\n";
        }
        else
        {
            cout << "impossible\n";
        }
    }
}
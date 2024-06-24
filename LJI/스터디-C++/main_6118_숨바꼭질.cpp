#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;

int N, M;
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> M;

    vector<vector<int>> graph;

    for (int i = 0; i <= N; i++)
    {
        graph.push_back(vector<int>());
    }

    // 양방향 길 표시
    for (int i = 0; i < M; i++)
    {
        int a, b;
        cin >> a >> b;

        graph[a].push_back(b);
        graph[b].push_back(a);
    }

    bool v[N + 1] = {false};
    v[1] = true;
    int num = 20001; // 헛간 번호
    int dis = -1;    // 1에서 부터 거리
    int cnt = 1;     // 같은 거리의 건물 갯수
    queue<int> que;
    que.push(1);

    while (!que.empty())
    {
        dis++;
        cnt = que.size();
        num = 20001;
        for (int i = 0; i < cnt; i++)
        {
            int now = que.front();
            que.pop();

            num = min(now, num);
            for (int tmp : graph[now])
            {
                if (v[tmp])
                    continue;

                v[tmp] = true;
                que.push(tmp);
            }
        }
    }

    cout << num << ' ' << dis << ' ' << cnt;
}
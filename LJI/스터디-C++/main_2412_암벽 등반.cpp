#include <iostream>
#include <map>
#include <queue>
using namespace std;
int n, T;

main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> n >> T;

    // 홈 정보
    map<pair<int, int>, bool> Wall;

    for (int i = 0; i < n; i++)
    {
        int a, b;
        cin >> a >> b;

        // 방문하지 않은 홈으로 표시
        Wall[make_pair(a, b)] = false;
    }

    // BFS
    int answer = -1;

    queue<pair<int, int>> que;

    // 0,0 시작
    que.push(make_pair(0, 0));
    int turn = 0;

    while (!que.empty())
    {
        int size = que.size();

        for (int i = 0; i < size; i++)
        {
            pair<int, int> cur = que.front();
            que.pop();

            // 현재 위치에서 정상에 도달 할 수 있는지 체크
            if (cur.second == T)
            {
                answer = turn;
                break;
            }

            // 갈 수 있는 다른 홈들 찾기
            for (int dr = -2; dr <= 2; dr++)
            {
                for (int dc = -2; dc <= 2; dc++)
                {
                    if (dr == 0 && dc == 0)
                        continue;

                    int nr = cur.first + dr;
                    int nc = cur.second + dc;

                    pair<int, int> next = make_pair(nr, nc);
                    if (Wall.find(next) != Wall.end())
                    {
                        if (!Wall[next])
                        {
                            Wall[next] = true;
                            que.push(next);
                        }
                    }
                }
            }
        }

        if (answer != -1)
            break;

        turn++;
    }

    cout << answer;
}
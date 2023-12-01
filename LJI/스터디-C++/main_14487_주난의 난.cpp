#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;
int N, M;
int x1, y1, x2, y2;

int dr[] = {-1, 0, 1, 0};
int dc[] = {0, 1, 0, -1};
bool Check(int r, int c)
{
    return r >= 0 && r < N && c >= 0 && c < M;
}
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    cin >> N >> M;
    cin >> x1 >> y1 >> x2 >> y2;
    x1--;
    y1--;
    x2--;
    y2--;
    vector<vector<int>> map(N, vector<int>(M, 0));
    vector<vector<bool>> visit(N, vector<bool>(M, false));

    for (int i = 0; i < N; i++)
    {
        string tmp;
        cin >> tmp;
        for (int j = 0; j < M; j++)
        {
            if (tmp[j] - '0' == 1)
                map[i][j] = 1;
        }
    }

    int answer = 1;

    while (true)
    {
        // 주난의 위치부터 bfs
        // 0위치면 진행 1이면 해당 위치 0으로 변경
        queue<pair<int, int>> que;
        que.push(make_pair(x1, y1));
        // visit 배열 초기화
        fill(visit.begin(), visit.end(), vector<bool>(M, false));
        visit[x1][y1] = true;
        bool bFind = false;
        pair<int, int> cur;
        while (!que.empty())
        {
            cur = que.front();
            que.pop();

            if (cur.first == x2 && cur.second == y2)
            {
                bFind = true;
                break;
            }

            for (int d = 0; d < 4; d++)
            {
                int nr = cur.first + dr[d];
                int nc = cur.second + dc[d];

                if (!Check(nr, nc) || visit[nr][nc])
                    continue;

                visit[nr][nc] = true;
                if (map[nr][nc] == 1)
                {
                    map[nr][nc] = 0;
                }
                else
                {

                    que.push(make_pair(nr, nc));
                }
            }
        }

        // 찾았으면 끝
        if (bFind)
        {
            break;
        }
        answer++;
    }
    cout << answer;
}
#include <iostream>
#include <queue>
using namespace std;
int T, I;
int sR, sC, eR, eC;
bool map[300][300];

int dr[8] = {-2, -1, 1, 2, 2, 1, -1, -2};
int dc[8] = {1, 2, 2, 1, -1, -2, -2, -1};

bool Check(int r, int c)
{
    return r >= 0 && r < I && c >= 0 && c < I;
}
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    cin >> T;

    for (int t = 0; t < T; t++)
    {
        cin >> I >> sR >> sC >> eR >> eC;
        for (int i = 0; i < I; i++)
        {
            for (int j = 0; j < I; j++)
            {
                map[i][j] = false;
            }
        }

        // bfs
        queue<pair<pair<int, int>, int>> que;

        // 시작 초기화
        que.push(make_pair(make_pair(sR, sC), 0));
        map[sR][sC] = true;

        int answer = 0;
        while (!que.empty())
        {
            int r = que.front().first.first;
            int c = que.front().first.second;
            int turn = que.front().second;
            que.pop();

            if (r == eR && c == eC)
            {
                answer = turn;
                break;
            }

            for (int d = 0; d < 8; d++)
            {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (!Check(nr, nc))
                    continue;

                if (!map[nr][nc])
                {
                    map[nr][nc] = true;
                    que.push(make_pair(make_pair(nr, nc), turn + 1));
                }
            }
        }

        cout << answer << endl;
    }
}
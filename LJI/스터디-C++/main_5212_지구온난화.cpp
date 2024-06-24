#include <iostream>
#include <queue>
using namespace std;

int R, C;
int dr[] = {-1, 0, 1, 0};
int dc[] = {0, 1, 0, -1};
int map[11][11];
bool check(int r, int c)
{
    return r >= 0 && r < R && c >= 0 && c < C;
}
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> R >> C;

    for (int i = 0; i < R; i++)
    {
        string str;
        cin >> str;
        for (int j = 0; j < C; j++)
        {
            if (str[j] == 'X')
                map[i][j] = 1;
            else
                map[i][j] = 0;
        }
    }

    bool v[R][C];
    fill(&v[0][0], &v[R - 1][C], false);

    int minR = 11;
    int minC = 11;
    int maxR = -1;
    int maxC = -1;
    for (int i = 0; i < R; i++)
    {
        for (int j = 0; j < C; j++)
        {
            if (!v[i][j] && map[i][j] == 1)
            {
                queue<pair<int, int>> que;
                que.push(make_pair(i, j));
                v[i][j] = true;
                while (!que.empty())
                {
                    int r = que.front().first;
                    int c = que.front().second;
                    que.pop();

                    int cnt = 0;
                    for (int d = 0; d < 4; d++)
                    {
                        int nr = r + dr[d];
                        int nc = c + dc[d];

                        if (!check(nr, nc))
                        {
                            cnt++;
                            continue;
                        }
                        if (v[nr][nc])
                            continue;

                        if (map[nr][nc] == 0)
                        {
                            cnt++;
                        }
                        else
                        {
                            v[nr][nc] = true;
                            que.push(make_pair(nr, nc));
                        }
                    }

                    if (cnt >= 3)
                    {
                        map[r][c] = 0;
                    }
                    else
                    {
                        // 아직 남아있는 땅
                        maxR = max(maxR, r);
                        maxC = max(maxC, c);
                        minR = min(minR, r);
                        minC = min(minC, c);
                    }
                }
            }
        }
    }

    for (int i = minR; i <= maxR; i++)
    {
        for (int j = minC; j <= maxC; j++)
        {
            if (map[i][j] == 0)
            {
                cout << '.';
            }
            else
            {
                cout << 'X';
            }
        }
        cout << endl;
    }
}
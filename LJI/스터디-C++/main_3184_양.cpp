#include <iostream>
#include <vector>
#include <queue>
using namespace std;
int R, C;
int dr[] = {-1, 0, 1, 0};
int dc[] = {0, 1, 0, -1};

bool Check(int r, int c)
{
    return r >= 0 && r < R && c >= 0 && c < C;
}
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> R >> C;

    vector<vector<int>> Map(R, vector<int>(C, 0));
    for (int i = 0; i < R; i++)
    {
        string tmp;
        cin >> tmp;
        for (int j = 0; j < C; j++)
        {
            if (tmp[j] == '.')
            {
                // 빈 공간,0
                Map[i][j] = 0;
            }
            else if (tmp[j] == '#')
            {
                // 벽,-1
                Map[i][j] = -1;
            }
            else if (tmp[j] == 'v')
            {
                // 늑대, 1
                Map[i][j] = 1;
            }
            else
            {
                // 양 , 2
                Map[i][j] = 2;
            }
        }
    }

    vector<vector<bool>> Visited(R, vector<bool>(C, false));

    int TotalSheepCnt = 0;
    int TotalWolfCnt = 0;
    for (int i = 0; i < R; i++)
    {
        for (int j = 0; j < C; j++)
        {
            if (Map[i][j] != -1 && Visited[i][j] == false)
            {
                // bfs

                int SheepCnt = 0;
                int WolfCnt = 0;
                queue<pair<int, int>> que;
                Visited[i][j] = true;
                que.push(make_pair(i, j));

                while (!que.empty())
                {
                    int r = que.front().first;
                    int c = que.front().second;
                    que.pop();

                    if (Map[r][c] == 1)
                    {
                        WolfCnt++;
                    }
                    else if (Map[r][c] == 2)
                    {
                        SheepCnt++;
                    }

                    for (int d = 0; d < 4; d++)
                    {
                        int nr = r + dr[d];
                        int nc = c + dc[d];

                        if (!Check(nr, nc) || Map[nr][nc] == -1)
                            continue;

                        if (Visited[nr][nc] == false)
                        {
                            Visited[nr][nc] = true;
                            que.push(make_pair(nr, nc));
                        }
                    }
                }

                if (SheepCnt > WolfCnt)
                {
                    TotalSheepCnt += SheepCnt;
                }
                else
                {
                    TotalWolfCnt += WolfCnt;
                }
            }
        }
    }

    cout << TotalSheepCnt << ' ' << TotalWolfCnt;
}
#include <iostream>
#include <vector>
#include <queue>
using namespace std;

int N, M;

bool Check(int r, int c)
{
    return r >= 0 && r < N && c >= 0 && c < M;
}
int dr[] = {-1, -1, 0, 1, 1, 1, 0, -1};
int dc[] = {0, 1, 1, 1, 0, -1, -1, -1};
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> M;
    vector<vector<int>> Map(N, vector<int>(M, 0));

    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < M; j++)
        {
            cin >> Map[i][j];
        }
    }

    int Color = Map[0][0];
    int JumpPower;
    cin >> JumpPower;
    vector<vector<bool>> Visited(N, vector<bool>(M, false));
    Visited[0][0] = true;

    if (Color != Map[N - 1][M - 1])
    {
        cout << "DEAD\n";
        return 0;
    }

    queue<pair<int, int>> Que;
    Que.push(make_pair(0, 0));
    while (!Que.empty())
    {
        int r = Que.front().first;
        int c = Que.front().second;
        Que.pop();

        for (int i = 0; i <= JumpPower; i++)
        {
            for (int j = 0; j <= JumpPower; j++)
            {
                if (i + j > JumpPower)
                    break;
                for (int d = 0; d < 8; d++)
                {
                    int nr = r + dr[d] * i;
                    int nc = c + dc[d] * j;

                    if (!Check(nr, nc))
                        continue;
                    if (!Visited[nr][nc] && Map[nr][nc] == Color)
                    {
                        Visited[nr][nc] = true;
                        Que.push(make_pair(nr, nc));
                    }
                }
            }
        }
    }

    if (Visited[N - 1][M - 1])
    {
        cout << "ALIVE\n";
    }
    else
    {
        cout << "DEAD\n";
    }
}
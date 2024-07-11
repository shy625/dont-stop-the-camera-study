#include <iostream>
#include <vector>
#include <queue>
using namespace std;
int N, M;

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

    vector<vector<int>> Map(N, vector<int>(M, 0));

    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < M; j++)
        {
            cin >> Map[i][j];
        }
    }

    // 각 위치부터 BFS

    int AnswerSum = 0;
    int AnswerDis = 0;

    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < M; j++)
        {
            if (Map[i][j] == 0)
                continue;
            vector<vector<bool>> Visited(N, vector<bool>(M, false));

            queue<pair<int, int>> que;
            que.push(make_pair(i, j));
            Visited[i][j] = true;

            int Dis = 0;

            while (!que.empty())
            {
                int size = que.size();

                for (int t = 0; t < size; t++)
                {
                    int r = que.front().first;
                    int c = que.front().second;
                    que.pop();

                    if (Dis > AnswerDis)
                    {
                        AnswerDis = Dis;
                        AnswerSum = Map[i][j] + Map[r][c];
                    }
                    else if (Dis == AnswerDis)
                    {
                        AnswerSum = max(AnswerSum, Map[i][j] + Map[r][c]);
                    }

                    for (int d = 0; d < 4; d++)
                    {
                        int nr = r + dr[d];
                        int nc = c + dc[d];

                        if (!Check(nr, nc))
                            continue;
                        if (Map[nr][nc] == 0)
                            continue;

                        if (!Visited[nr][nc])
                        {
                            Visited[nr][nc] = true;
                            que.push(make_pair(nr, nc));
                        }
                    }
                }

                Dis++;
            }
        }
    }

    cout << AnswerSum;
}
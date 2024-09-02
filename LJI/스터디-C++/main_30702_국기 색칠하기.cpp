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

    vector<vector<int>> A(N, vector<int>(M, 0));
    vector<vector<int>> B(N, vector<int>(M, 0));

    for (int i = 0; i < N; i++)
    {
        string Str;
        cin >> Str;

        for (int j = 0; j < M; j++)
        {
            A[i][j] = Str[j] - 'A';
        }
    }

    for (int i = 0; i < N; i++)
    {
        string Str;
        cin >> Str;

        for (int j = 0; j < M; j++)
        {
            B[i][j] = Str[j] - 'A';
        }
    }

    vector<vector<bool>> Visited(N, vector<bool>(M, false));
    bool Answer = true;
    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < M; j++)
        {
            if (Visited[i][j] && A[i][j] != B[i][j])
            {
                Answer = false;
                break;
            }

            if (!Visited[i][j])
            {
                int Color = B[i][j];
                int OriginColor = A[i][j];

                queue<pair<int, int>> Que;
                Que.push(make_pair(i, j));
                Visited[i][j] = true;
                A[i][j] = Color;

                while (!Que.empty())
                {
                    int r = Que.front().first;
                    int c = Que.front().second;
                    Que.pop();

                    for (int d = 0; d < 4; d++)
                    {
                        int nr = r + dr[d];
                        int nc = c + dc[d];

                        if (!Check(nr, nc))
                            continue;

                        if (!Visited[nr][nc] && A[nr][nc] == OriginColor)
                        {
                            Visited[nr][nc] = true;
                            A[nr][nc] = Color;
                            Que.push(make_pair(nr, nc));
                        }
                    }
                }
            }
        }

        if (!Answer)
            break;
    }

    if (Answer)
    {
        cout << "YES";
    }
    else
    {
        cout << "NO";
    }
}
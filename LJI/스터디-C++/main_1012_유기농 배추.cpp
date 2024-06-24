#include <iostream>
#include <vector>
#include <queue>
// bfs
using namespace std;
int T, M, N, K, X, Y;
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

    cin >> T;

    for (int t = 0; t < T; t++)
    {
        cin >> M >> N >> K;

        // 맵의 배추 위치 표기
        vector<vector<int>> Field(N, vector<int>(M, 0));

        for (int i = 0; i < K; i++)
        {
            cin >> X >> Y;

            Field[Y][X] = 1;
        }

        // bfs
        // 체크된 배추는 2로 변경
        int Answer = 0;
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < M; j++)
            {
                if (Field[i][j] == 1)
                {
                    Answer++;

                    queue<pair<int, int>> que;
                    que.push(make_pair(i, j));
                    Field[i][j] = 2;

                    while (!que.empty())
                    {
                        int r = que.front().first;
                        int c = que.front().second;
                        que.pop();

                        for (int d = 0; d < 4; d++)
                        {
                            int nr = r + dr[d];
                            int nc = c + dc[d];

                            if (!Check(nr, nc))
                                continue;

                            if (Field[nr][nc] == 1)
                            {
                                Field[nr][nc] = 2;
                                que.push(make_pair(nr, nc));
                            }
                        }
                    }
                }
            }
        }

        cout << Answer << '\n';
    }
}
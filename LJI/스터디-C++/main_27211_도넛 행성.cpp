#include <iostream>
#include <vector>
#include <queue>
using namespace std;
int N, M;
// 범위 밖으로 넘어가는 경우는 없다
int dr[] = {-1, 0, 1, 0};
int dc[] = {0, 1, 0, -1};
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

    int answer = 0;
    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < M; j++)
        {
            if (Map[i][j] == 0)
            {
                // 빈 구역 발견 // 정답에 포함
                answer++;

                // bfs
                queue<pair<int, int>> que;

                Map[i][j] = 1;
                que.push(make_pair(i, j));

                while (!que.empty())
                {
                    int r = que.front().first;
                    int c = que.front().second;
                    que.pop();

                    for (int d = 0; d < 4; d++)
                    {
                        // 넘어가는 범위를 반대로 이동
                        int nr = (N + r + dr[d]) % N;
                        int nc = (M + c + dc[d]) % M;

                        if (Map[nr][nc] == 0)
                        {
                            Map[nr][nc] = 1;
                            que.push(make_pair(nr, nc));
                        }
                    }
                }
            }
        }
    }

    cout << answer;
}
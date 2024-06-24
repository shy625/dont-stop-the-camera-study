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
    cin >> M >> N;

    // 0:W, 1:B
    vector<vector<int>> Map(N, vector<int>(M, 0));
    vector<vector<bool>> Visited(N, vector<bool>(M, false));

    string str;
    for (int i = 0; i < N; i++)
    {
        cin >> str;
        for (int j = 0; j < M; j++)
        {
            if (str[j] == 'B')
            {
                Map[i][j] = 1;
            }
        }
    }

    // BFS로 뭉쳐있는 숫자 구하기
    int WCnt = 0;
    int BCnt = 0;
    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < M; j++)
        {
            // 방문한 적 있다면 넘기기
            if (Visited[i][j])
                continue;

            int cnt = 0;

            queue<pair<int, int>> que;

            que.push(make_pair(i, j));
            Visited[i][j] = true;
            while (!que.empty())
            {
                int r = que.front().first;
                int c = que.front().second;
                que.pop();
                cnt++;

                for (int d = 0; d < 4; d++)
                {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if (!Check(nr, nc) || Visited[nr][nc] || (Map[nr][nc] != Map[r][c]))
                        continue;

                    Visited[nr][nc] = true;
                    que.push(make_pair(nr, nc));
                }
            }

            if (Map[i][j] == 0)
            {
                WCnt += cnt * cnt;
            }
            else
            {
                BCnt += cnt * cnt;
            }
        }
    }

    cout << WCnt << ' ' << BCnt;
}
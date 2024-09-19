#include <iostream>
#include <vector>
#include <queue>
using namespace std;
int I, N, K;
// 플레이어 좌표
int r, c;
int m, inkIdx;
string Ink;
// UDLR
int dr[] = {-1, 1, 0, 0};
int dc[] = {0, 0, -1, 1};
bool Check(int r, int c)
{
    return r >= 0 && r < N && c >= 0 && c < N;
}
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> I >> N >> K;
    cin >> Ink;

    vector<vector<char>> Map(N, vector<char>(N));

    for (int i = 0; i < N; i++)
    {
        string tmp;
        cin >> tmp;
        for (int j = 0; j < N; j++)
        {
            Map[i][j] = tmp[j];

            if (Map[i][j] == '@')
            {
                Map[i][j] = '.';
                r = i;
                c = j;
            }
        }
    }
    m = 0;
    inkIdx = 0;

    string Order;
    cin >> Order;
    for (int k = 0; k < K; k++)
    {
        if (Order[k] == 'j')
        {
            // 잉크 충전
            m++;
        }
        else if (Order[k] == 'J')
        {
            // 잉크 뿌리기
            // bfs
            vector<vector<bool>> Visited(N, vector<bool>(N, false));
            Visited[r][c] = true;
            queue<pair<int, int>> Que;
            Que.push(make_pair(r, c));

            while (!Que.empty())
            {
                int cr = Que.front().first;
                int cc = Que.front().second;
                Que.pop();

                if (Map[cr][cc] != '.')
                {
                    // 색 변환
                    Map[cr][cc] = Ink[inkIdx];
                }

                for (int d = 0; d < 4; d++)
                {
                    int nr = cr + dr[d];
                    int nc = cc + dc[d];

                    // 가려는 곳의 거리가 m보다 크면 패스
                    int dis = abs(nr - r) + abs(nc - c);
                    if (dis > m)
                        continue;
                    if (!Check(nr, nc))
                        continue;
                    if (Visited[nr][nc])
                        continue;

                    Visited[nr][nc] = true;
                    Que.push(make_pair(nr, nc));
                }
            }

            m = 0;
            inkIdx = (inkIdx + 1) % I;
        }
        else
        {
            // 4방 이동 UDLR
            int d = 0;
            if (Order[k] == 'U')
            {
                d = 0;
            }
            else if (Order[k] == 'D')
            {
                d = 1;
            }
            else if (Order[k] == 'L')
            {
                d = 2;
            }
            else if (Order[k] == 'R')
            {
                d = 3;
            }

            int nr = r + dr[d];
            int nc = c + dc[d];
            if (Check(nr, nc) && Map[nr][nc] == '.')
            {
                r = nr;
                c = nc;
            }
        }
    }

    Map[r][c] = '@';

    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < N; j++)
        {
            cout << Map[i][j];
        }
        cout << '\n';
    }
}
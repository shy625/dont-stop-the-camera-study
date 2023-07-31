#include <iostream>
#include <queue>
using namespace std;

int N, M;
int dr[] = {-1, 0, 1, 0};
int dc[] = {0, 1, 0, -1};
// 0: 두개 다 나감,1:한개만 나감(종료 시점),2:두개 다 in
int checkCnt(int r, int c, int r2, int c2)
{
    int cnt = 0;
    if (r >= 0 && r < N && c >= 0 && c < M)
    {
        cnt++;
    }
    if (r2 >= 0 && r2 < N && c2 >= 0 && c2 < M)
    {
        cnt++;
    }

    return cnt;
}
main()
{
    ios_base::sync_with_stdio(false);
    cout.tie(0);
    cin.tie(0);

    cin >> N >> M;

    int map[N][M];

    queue<pair<int, int>> coins;
    for (int i = 0; i < N; i++)
    {
        string str;
        cin >> str;
        for (int j = 0; j < M; j++)
        {
            if (str[j] == '#')
            {
                map[i][j] = 1;
            }
            else
            {
                if (str[j] == 'o')
                {
                    coins.push(make_pair(i, j));
                }
                map[i][j] = 0;
            }
        }
    }

    int X1 = coins.front().first;
    int Y1 = coins.front().second;
    coins.pop();
    int X2 = coins.front().first;
    int Y2 = coins.front().second;
    bool v[N][M][N][M]; // 방문여부 체크
    fill(&v[0][0][0][0], &v[N - 1][M - 1][N - 1][M], false);

    queue<pair<pair<int, int>, pair<int, int>>> que;
    que.push(make_pair(make_pair(X1, Y1), make_pair(X2, Y2)));
    v[X1][Y1][X2][Y2] = true;
    int turn = 0;
    while (!que.empty())
    {
        // 저번 턴에 종료안됐으므로 턴 한단계 증가
        turn++;
        if (turn > 10)
            break;
        int size = que.size();
        for (int i = 0; i < size; i++)
        {
            // 큐에서 꺼내서 이동
            int x1 = que.front().first.first;
            int y1 = que.front().first.second;
            int x2 = que.front().second.first;
            int y2 = que.front().second.second;
            que.pop();

            for (int d = 0; d < 4; d++)
            {
                int nx1 = x1 + dr[d];
                int ny1 = y1 + dc[d];
                int nx2 = x2 + dr[d];
                int ny2 = y2 + dc[d];

                int cnt = checkCnt(nx1, ny1, nx2, ny2);

                if (cnt == 2)
                { // 갈 수 있는 위치
                    if (v[nx1][ny1][nx2][ny2])
                    {
                        continue;
                    }
                    if (map[nx1][ny1] == 1 && map[nx2][ny2] == 1)
                    { // 동전 두개 다 벽에 막혀 움직이지 않는 경우
                        continue;
                    }
                    if (map[nx1][ny1] == 1)
                    {
                        nx1 = x1;
                        ny1 = y1;
                    }
                    if (map[nx2][ny2] == 1)
                    {
                        nx2 = x2;
                        ny2 = y2;
                    }
                    v[nx1][ny1][nx2][ny2] = true;
                    que.push(make_pair(make_pair(nx1, ny1), make_pair(nx2, ny2)));
                }
                else if (cnt == 1)
                { // 종료
                    cout << turn;
                    return 0;
                } // 0은 갈 수 없는 위치이므로 패스
            }
        }
    }

    cout << -1;
}
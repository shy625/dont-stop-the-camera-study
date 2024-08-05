#include <iostream>
#include <vector>
#include <queue>
using namespace std;
int N, M, R, C;

int dr[] = {-1, 0, 1, 0};
int dc[] = {0, 1, 0, -1};

int Check(int r, int c)
{
    return r >= 1 && r <= N && c >= 1 && c <= M;
}

// 한집에서 최소 거리의 편의점을 찾는 방식은 시간초과
// 모든 편의점에서 모든 방을 찾는 방식으로 BFS 사용
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> M >> R >> C;

    // 방 정보
    // 지도에 기록
    vector<vector<int>> Map(N + 1, vector<int>(M + 1, 0));

    for (int i = 0; i < R; i++)
    {
        int x, y, p;
        cin >> x >> y >> p;
        Map[x][y] = p;
    }

    // 편의점 정보
    // 큐에 등록
    queue<pair<int, int>> Que;
    vector<vector<bool>> Visited(N + 1, vector<bool>(M + 1, false));
    for (int i = 0; i < C; i++)
    {
        int x, y;
        cin >> x >> y;
        Que.push(make_pair(x, y));
        Visited[x][y] = true;
    }

    // 모든 편의점에서 출발해서 거리별로 닿는 집에 따라 값 갱신
    int Answer = INT32_MAX;
    int Dis = 0;
    while (!Que.empty())
    {
        int Size = Que.size();
        for (int i = 0; i < Size; i++)
        {
            int CurX = Que.front().first;
            int CurY = Que.front().second;
            Que.pop();

            if (Map[CurX][CurY] > 0)
            {
                Answer = min(Answer, Map[CurX][CurY] * Dis);
            }

            for (int d = 0; d < 4; d++)
            {
                int nr = CurX + dr[d];
                int nc = CurY + dc[d];

                if (!Check(nr, nc))
                    continue;

                if (!Visited[nr][nc])
                {
                    Visited[nr][nc] = true;
                    Que.push(make_pair(nr, nc));
                }
            }
        }

        Dis++;
    }

    cout << Answer;
}
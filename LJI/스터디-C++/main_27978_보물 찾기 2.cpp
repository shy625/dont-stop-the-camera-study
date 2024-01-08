#include <iostream>
#include <vector>
#include <queue>
using namespace std;
int H, W;
int INF = 10000000;
int dr[] = {-1, -1, 0, 1, 1, 1, 0, -1};
int dc[] = {0, 1, 1, 1, 0, -1, -1, -1};
int cost[] = {1, 0, 0, 0, 1, 1, 1, 1};

struct Info
{
    int dist;
    pair<int, int> point;

    Info(int dist, pair<int, int> point) : dist(dist), point(point) {}
    bool operator<(const Info other) const
    {
        return this->dist > other.dist;
    }
};

bool Check(int r, int c)
{
    return r >= 0 && r < H && c >= 0 && c < W;
}

main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    cin >> H >> W;

    vector<vector<int>> Map(H, vector<int>(W, 0));
    vector<vector<int>> Dijk(H, vector<int>(W, INF));
    pair<int, int> Start, End;
    for (int i = 0; i < H; i++)
    {
        string str;
        cin >> str;
        for (int j = 0; j < W; j++)
        {
            if (str[j] == '#')
            {
                Map[i][j] = 1;
            }
            else if (str[j] == 'K')
            {
                Start.first = i;
                Start.second = j;
            }
            else if (str[j] == '*')
            {
                End.first = i;
                End.second = j;
            }
        }
    }

    // 다익스트라 priority queue 거리 짧은순으로 정렬
    priority_queue<Info> pq;
    pq.push(Info(0, make_pair(Start.first, Start.second)));

    int answer = -1;
    while (!pq.empty())
    {
        Info cur = pq.top();
        int r = cur.point.first;
        int c = cur.point.second;
        int dist = cur.dist;
        pq.pop();

        if (r == End.first && c == End.second)
        {
            answer = dist;
            break;
        }

        // 먼저 값이 갱신될 때만
        if (Dijk[r][c] != INF)
            continue;

        Dijk[r][c] = dist;
        for (int d = 0; d < 8; d++)
        {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (!Check(nr, nc) || Map[nr][nc] == 1)
                continue;

            if (Dijk[nr][nc] == INF)
            {
                pq.push(Info(dist + cost[d], make_pair(nr, nc)));
            }
        }
    }

    cout << answer;
}
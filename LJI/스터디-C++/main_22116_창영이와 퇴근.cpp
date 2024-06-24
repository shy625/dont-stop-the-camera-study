#include <iostream>
#include <vector>
#include <queue>
using namespace std;
int N;
int dr[] = {-1, 0, 1, 0};
int dc[] = {0, 1, 0, -1};
int INF = 1000000000;
bool Check(int r, int c)
{
    return r >= 0 && r < N && c >= 0 && c < N;
}
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    cin >> N;

    vector<vector<int>> map(N, vector<int>(N));
    vector<vector<int>> answer(N, vector<int>(N, INF));
    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < N; j++)
        {
            cin >> map[i][j];
        }
    }

    priority_queue<pair<int, pair<int, int>>, vector<pair<int, pair<int, int>>>, greater<pair<int, pair<int, int>>>> PQ;

    // 현재까지의 최대 경사,현재의 좌표
    PQ.push(make_pair(0, make_pair(0, 0)));

    while (!PQ.empty())
    {
        int r = PQ.top().second.first;
        int c = PQ.top().second.second;
        int slope = PQ.top().first;
        PQ.pop();

        // 이미 처리된 건 넘긴다
        if (answer[r][c] != INF)
            continue;

        answer[r][c] = slope;
        if (r == N - 1 && c == N - 1)
            break;

        for (int d = 0; d < 4; d++)
        {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (!Check(nr, nc))
                continue;

            // 가는 것이 가능하면 갈 때 필요한 경사도 구하기
            // 현재까지 최대 경사도와 가는데 필요한 경사도 중 큰 값 채택
            int slope = max(answer[r][c], abs(map[nr][nc] - map[r][c]));

            // 가려는 곳의 현재까지 경사도보다 낮은 방법으로 갈 수 있다면 갱신
            if (answer[nr][nc] == INF)
            {
                PQ.push(make_pair(slope, make_pair(nr, nc)));
            }
        }
    }

    cout << answer[N - 1][N - 1];
}
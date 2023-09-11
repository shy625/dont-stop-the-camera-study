#include <iostream>
#include <queue>
using namespace std;
int N, M;

// 8방의 높이가 모두 자신보다 낮은 곳이 봉우리일 것
// bfs로 갔던 곳 체크하면서 더 높은 곳으로만 이동하면서 가면 될 것

int map[100][100];
int dr[] = {-1, -1, -1, 0, 1, 1, 1, 0};
int dc[] = {-1, 0, 1, 1, 1, 0, -1, -1};

bool Check(int r, int c)
{
    return r >= 0 && r < N && c >= 0 && c < M;
}
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    // map 입력 받기
    cin >> N >> M;
    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < M; j++)
        {
            cin >> map[i][j];
        }
    }

    //
    int answer = 0;
    bool v[N][M] = {
        false,
    };
    fill(&v[0][0], &v[N - 1][M], false);

    // 특정 점에서 자기보다 낮거나 같은 곳으로 bfs 이때 시작한 특정 점보다 높은 곳이 있다면 이 bfs는 봉우리는 아님
    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < M; j++)
        {
            // 방문했던 곳이면 패스
            if (v[i][j])
                continue;

            //  bfs를 하는 중에 현재 같은 높이인 주변에서 이 값보다 높은 곳이 주변에 있다면 봉우리가 아님
            int height = map[i][j];
            bool isPeak = true;
            queue<pair<int, int>> que;
            que.push(make_pair(i, j));
            v[i][j] = true;
            while (!que.empty())
            {
                int r = que.front().first;
                int c = que.front().second;
                que.pop();

                // 현재가 봉우리랑 같은 높이인지
                bool isHigher = map[r][c] == height ? true : false;

                for (int d = 0; d < 8; d++)
                {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    if (!Check(nr, nc))
                    {
                        continue;
                    }

                    // 만약 현재가 제일 높은 곳인데 더 높은 곳이 있다면? 이곳은 봉우리가 아니다
                    if (isHigher)
                    {
                        if (map[nr][nc] > map[r][c])
                        {
                            isPeak = false;
                        }
                    }

                    // 만약 map[nr][nc]가 방문하지 않았고 자기보다 작거나 같다면 bfs
                    if (v[nr][nc])
                        continue;
                    if (map[nr][nc] <= map[r][c])
                    {
                        v[nr][nc] = true;
                        que.push(make_pair(nr, nc));
                    }
                }
            }

            // 만약 이번 bfs가 봉우리 였으면 answer++;
            if (isPeak)
                answer++;
        }
    }

    cout << answer;
}
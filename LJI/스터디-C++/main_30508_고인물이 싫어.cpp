#include <iostream>
#include <vector>
#include <queue>
using namespace std;
int N, M;
int h, w;

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

    cin >> h >> w;

    vector<vector<int>> Map(N, vector<int>(M));

    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < M; j++)
        {
            cin >> Map[i][j];
        }
    }

    int K;
    cin >> K;

    // 물 존재 여부
    vector<vector<bool>> Water(N, vector<bool>(M, true));
    for (int i = 0; i < K; i++)
    {
        int r, c;
        cin >> r >> c;
        r--;
        c--;
        Water[r][c] = false;
        queue<pair<int, int>> Que;
        Que.push(make_pair(r, c));

        while (!Que.empty())
        {
            r = Que.front().first;
            c = Que.front().second;
            Que.pop();

            for (int d = 0; d < 4; d++)
            {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (!Check(nr, nc))
                    continue;

                // 현재 위치보다 높거나 같으면서 물이 안빠졌으면 해당 위치 물 제거 가능
                if (Map[nr][nc] >= Map[r][c] && Water[nr][nc])
                {
                    Water[nr][nc] = false;
                    Que.push(make_pair(nr, nc));
                }
            }
        }
    }

    vector<vector<int>> Sum(N, vector<int>(M, 0));
    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < M; j++)
        {
            if (Water[i][j])
            {
                Sum[i][j] = 1;
            }

            if (i - 1 >= 0 && j - 1 >= 0)
            {
                Sum[i][j] += Sum[i - 1][j];
                Sum[i][j] += Sum[i][j - 1];
                Sum[i][j] -= Sum[i - 1][j - 1];
            }
            else if (i - 1 >= 0)
            {
                Sum[i][j] += Sum[i - 1][j];
            }
            else if (j - 1 >= 0)
            {
                Sum[i][j] += Sum[i][j - 1];
            }
        }
    }

    int Answer = 0;

    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < M; j++)
        {
            // 최소한의 발자국 영역도 없다면 패스
            if (i - h + 1 < 0 || j - w + 1 < 0)
                continue;
            // 발자국 영역 외부의 범위들의 합
            int OtherSum = 0;
            if (i - h >= 0 && j - w >= 0)
            {
                OtherSum += Sum[i - h][j];
                OtherSum += Sum[i][j - w];
                OtherSum -= Sum[i - h][j - w];
            }
            else if (i - h >= 0)
            {
                OtherSum += Sum[i - h][j];
            }
            else if (j - w >= 0)
            {
                OtherSum += Sum[i][j - w];
            }

            // 발자국 제외 영역과 발자국 포함 영역의 물의 양이 변화 없다는 뜻이므로
            // 발자국 영역에 물이 아예 없다는 뜻
            if (OtherSum == Sum[i][j])
            {
                Answer++;
            }
        }
    }

    cout << Answer;
}
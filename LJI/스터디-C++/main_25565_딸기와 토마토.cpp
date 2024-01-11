#include <iostream>
#include <vector>
using namespace std;

// 1의 개수에 따라 처리가 가능

// 서로 겹치는 경우
// 2K-1 >= 1의 개수

// 교차되서 지나가는 지점 하나
// 2K-1== 1의 개수

// 아예 서로 안닿는 경우
// 2K== 1의 개수

// K==1인 경우도 처리해주어야한다.
int N, M, K;

int dr[] = {-1, 0, 1, 0};
int dc[] = {0, 1, 0, -1};
bool Check(int r, int c)
{
    return r >= 0 && r < N && c >= 0 && c < M;
}

int CntLine(int r, int c, vector<vector<int>> &Map)
{
    int cnt = 0;
    int nr, nc;
    for (int d = 0; d < 4; d++)
    {
        for (int i = 1; i < K; i++)
        {
            nr = r + dr[d] * i;
            nc = c + dc[d] * i;
            if (!Check(nr, nc))
                break;

            if (Map[nr][nc] == 0)
                break;

            cnt++;
        }
    }

    return cnt;
}
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> M >> K;

    vector<vector<int>> Map(N, vector<int>(M, 0));

    int cnt = 0;
    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < M; j++)
        {
            cin >> Map[i][j];
            if (Map[i][j] == 1)
                cnt++;
        }
    }

    if (K * 2 == cnt)
    {
        cout << "0\n";
    }
    else if (K == 1)
    {
        cout << "1\n";
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < M; j++)
            {
                if (Map[i][j] == 1)
                {
                    cout << i + 1 << ' ' << j + 1 << '\n';
                    return 0;
                }
            }
        }
    }
    else if (K * 2 - 1 == cnt)
    {
        // 교차 지점에서 사방을 K거리만큼 탐색해 얻은 개수가 K*2-1-1개면 교차점
        cout << "1\n";
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < M; j++)
            {
                if (Map[i][j] == 1)
                {
                    int lineCnt = CntLine(i, j, Map);
                    if (lineCnt == K * 2 - 1 - 1)
                    {
                        cout << i + 1 << ' ' << j + 1 << '\n';
                    }
                }
            }
        }
    }
    else if (K * 2 - 1 >= cnt)
    {
        // 겹치는 크기
        int overlap = K * 2 - cnt;
        // 시작할 번째 수
        int startIdx = (cnt - overlap) / 2;
        int idx = 0;

        cout << overlap << '\n';
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < M; j++)
            {
                if (Map[i][j] == 1)
                {
                    if (idx >= startIdx)
                    {
                        cout << i + 1 << ' ' << j + 1 << '\n';
                    }
                    idx++;
                    if (idx - startIdx == overlap)
                    {
                        return 0;
                    }
                }
            }
        }
    }
}
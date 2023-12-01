#include <iostream>
#include <vector>
using namespace std;
int N;

int dr[] = {1, 0};
int dc[] = {0, 1};
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

    vector<vector<char>> map(N, vector<char>(N, '0'));
    vector<vector<int>> maxMap(N, vector<int>(N, -10000000));
    vector<vector<int>> minMap(N, vector<int>(N, 10000000));

    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < N; j++)
        {
            cin >> map[i][j];
        }
    }

    maxMap[0][0] = map[0][0] - '0';
    minMap[0][0] = map[0][0] - '0';
    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < N; j++)
        {
            int num = map[i][j] - '0';
            // map의 현재 위치가 숫자일 때만 진행
            if (num >= 0 && num < 6)
            {
                for (int d = 0; d < 2; d++)
                {
                    int nr = i + dr[d];
                    int nc = j + dc[d];

                    if (!Check(nr, nc))
                        continue;

                    for (int d2 = 0; d2 < 2; d2++)
                    {
                        int nr2 = nr + dr[d2];
                        int nc2 = nc + dc[d2];

                        if (!Check(nr2, nc2))
                            continue;

                        int num2 = map[nr2][nc2] - '0';
                        if (map[nr][nc] == '+')
                        {
                            maxMap[nr2][nc2] = max(maxMap[nr2][nc2], maxMap[i][j] + num2);
                            minMap[nr2][nc2] = min(minMap[nr2][nc2], minMap[i][j] + num2);
                        }
                        else if (map[nr][nc] == '-')
                        {
                            maxMap[nr2][nc2] = max(maxMap[nr2][nc2], maxMap[i][j] - num2);
                            minMap[nr2][nc2] = min(minMap[nr2][nc2], minMap[i][j] - num2);
                        }
                        else
                        {
                            maxMap[nr2][nc2] = max(maxMap[nr2][nc2], maxMap[i][j] * num2);
                            minMap[nr2][nc2] = min(minMap[nr2][nc2], minMap[i][j] * num2);
                        }
                    }
                }
            }
        }
    }

    cout << maxMap[N - 1][N - 1] << ' ' << minMap[N - 1][N - 1];
}
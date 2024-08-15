#include <iostream>
#include <vector>
using namespace std;
int M, N;

int dr[] = {-1, 0, 1, 0};
int dc[] = {0, 1, 0, -1};
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> M;

    vector<vector<int>> Map(N, vector<int>(M, 0));
    for (int i = 0; i < N; i++)
    {
        string Tmp;
        cin >> Tmp;
        for (int j = 0; j < M; j++)
        {
            if (Tmp[j] == 'X')
            {
                Map[i][j] = 1;
            }
        }
    }

    // 현재 위치가 빈공간이고 오른쪽과 아래쪽이 각각 빈공간일 때 체크
    // 오른쪽이 비었다면? 상하 위치가 벽인지 체크
    // 아래쪽이 비었다면? 좌우 위치가 벽인지 체크
    // 벽이라면 그림을 이미 걸었는지 체크
    vector<vector<vector<bool>>> Picture(N, vector<vector<bool>>(M, vector<bool>(4, false)));

    int Answer = 0;
    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < M; j++)
        {
            if (Map[i][j] == 0)
            {

                // 오른쪽이 비었을 때
                if (Map[i][j + 1] == 0)
                {
                    // 위가 벽인지 체크
                    if (Map[i - 1][j] == 1 && Map[i - 1][j + 1] == 1)
                    {
                        if (!Picture[i][j][0] && !Picture[i][j + 1][0])
                        {
                            Picture[i][j][0] = true;
                            Picture[i][j + 1][0] = true;
                            Answer++;
                        }
                    }
                    // 아래가 벽인지
                    if (Map[i + 1][j] == 1 && Map[i + 1][j + 1] == 1)
                    {
                        if (!Picture[i][j][2] && !Picture[i][j + 1][2])
                        {
                            Picture[i][j][2] = true;
                            Picture[i][j + 1][2] = true;
                            Answer++;
                        }
                    }
                }

                // 아래가 비었을 때
                if (Map[i + 1][j] == 0)
                {
                    // 오른쪽 벽
                    if (Map[i][j + 1] == 1 && Map[i + 1][j + 1] == 1)
                    {
                        if (!Picture[i][j][1] && !Picture[i + 1][j][1])
                        {
                            Picture[i][j][1] = true;
                            Picture[i + 1][j][1] = true;
                            Answer++;
                        }
                    }
                    // 왼쪽 벽
                    if (Map[i][j - 1] == 1 && Map[i + 1][j - 1] == 1)
                    {
                        if (!Picture[i][j][3] && !Picture[i + 1][j][3])
                        {
                            Picture[i][j][3] = true;
                            Picture[i + 1][j][3] = true;
                            Answer++;
                        }
                    }
                }
            }
        }
    }

    cout << Answer;
}
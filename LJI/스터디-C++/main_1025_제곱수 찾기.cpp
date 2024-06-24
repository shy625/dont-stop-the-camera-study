#include <iostream>
#include <cmath>
using namespace std;
int N, M;
int map[9][9];

bool CheckSquare(int num)
{
    if (sqrt(num) - (int)(sqrt(num)) == 0)
    {
        return true;
    }
    else
        return false;
}

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

    for (int i = 0; i < N; i++)
    {
        string str;
        cin >> str;
        for (int j = 0; j < M; j++)
        {
            map[i][j] = str[j] - '0';
        }
    }

    int answer = -1;

    // i: 행의 차, j:열의 차
    for (int i = -N; i < N; i++)
    {
        for (int j = -M; j < M; j++)
        {
            // 등차가 안되면 한칸만 가지고 판단
            if (i == 0 && j == 0)
            {
                for (int r = 0; r < N; r++)
                {
                    for (int c = 0; c < M; c++)
                    {
                        if (CheckSquare(map[r][c]))
                        {
                            answer = max(map[r][c], answer);
                        }
                    }
                }
            }
            else
            {
                for (int r = 0; r < N; r++)
                {
                    for (int c = 0; c < M; c++)
                    {
                        int nr = r;
                        int nc = c;
                        int num = 0;
                        while (Check(nr, nc))
                        {
                            num *= 10;
                            num += map[nr][nc];
                            nr += i;
                            nc += j;
                            if (CheckSquare(num))
                                answer = max(answer, num);
                        }
                    }
                }
            }
        }
    }

    cout << answer;
}
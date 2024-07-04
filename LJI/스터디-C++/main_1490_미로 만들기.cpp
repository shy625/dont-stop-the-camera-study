#include <iostream>
#include <vector>
using namespace std;
int N, M;

// 왼쪽 위를 기준으로 쭉 변환 시도
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> M;

    vector<vector<int>> Origin(N, vector<int>(M, 0));
    vector<vector<int>> Target(N, vector<int>(M, 0));

    for (int i = 0; i < N; i++)
    {
        string tmp;
        cin >> tmp;

        for (int j = 0; j < M; j++)
        {
            Origin[i][j] = tmp[j] - '0';
        }
    }

    for (int i = 0; i < N; i++)
    {
        string tmp;
        cin >> tmp;

        for (int j = 0; j < M; j++)
        {
            Target[i][j] = tmp[j] - '0';
        }
    }

    int Answer = 0;
    for (int i = 0; i < N - 2; i++)
    {
        for (int j = 0; j < M - 2; j++)
        {
            // 변환
            if (Origin[i][j] != Target[i][j])
            {
                Answer++;
                for (int a = i; a < i + 3; a++)
                {
                    for (int b = j; b < j + 3; b++)
                    {
                        Origin[a][b] = (!Origin[a][b]);
                    }
                }
            }
        }
    }

    bool Same = true;
    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < M; j++)
        {
            if (Origin[i][j] != Target[i][j])
            {
                Same = false;
                break;
            }
        }
        if (!Same)
            break;
    }

    if (!Same)
    {
        cout << "-1";
    }
    else
    {
        cout << Answer;
    }
}
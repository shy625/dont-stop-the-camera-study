#include <iostream>
#include <vector>
using namespace std;
int H, W, L;

int dr[] = {-1, -1, -1, 0, 1, 1, 1, 0};
int dc[] = {-1, 0, 1, 1, 1, 0, -1, -1};

bool Check(int r, int c)
{
    return r >= 0 && r < H && c >= 0 && c < W;
}
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> H >> W >> L;

    vector<vector<char>> Map(H, vector<char>(W));

    for (int i = 0; i < H; i++)
    {
        string str;
        cin >> str;
        for (int j = 0; j < W; j++)
        {
            Map[i][j] = str[j];
        }
    }

    // 인접 글자수 기록
    // vector<vector<vector<int>>> NearWord(H, vector<vector<int>>(W, vector<int>(26, 0)));
    // for (int i = 0; i < H; i++)
    // {
    //     for (int j = 0; j < W; j++)
    //     {
    //         for (int d = 0; d < 8; d++)
    //         {
    //             int nr = i + dr[d];
    //             int nc = j + dc[d];
    //             if (!Check(nr, nc))
    //                 continue;

    //             NearWord[i][j][Map[nr][nc] - 'A']++;
    //         }
    //     }
    // }

    string str;
    cin >> str;
    vector<vector<vector<long long>>> DP(H, vector<vector<long long>>(W, vector<long long>(L, 0)));

    // 시작 단어 위치 초기화
    for (int i = 0; i < H; i++)
    {
        for (int j = 0; j < W; j++)
        {
            if (Map[i][j] == str[0])
                DP[i][j][0]++;
        }
    }

    for (int idx = 1; idx < L; idx++)
    {
        for (int i = 0; i < H; i++)
        {
            for (int j = 0; j < W; j++)
            {
                // 만약 현재 위치가 찾으려는 idx와 같다면 이전 주변 값+
                if (str[idx] == Map[i][j])
                {
                    for (int d = 0; d < 8; d++)
                    {
                        int nr = i + dr[d];
                        int nc = j + dc[d];
                        if (!Check(nr, nc))
                            continue;

                        DP[i][j][idx] += DP[nr][nc][idx - 1];
                    }
                }
            }
        }
    }

    long long answer = 0;
    for (int i = 0; i < H; i++)
    {
        for (int j = 0; j < W; j++)
        {
            answer += DP[i][j][L - 1];
        }
    }

    cout << answer;
}
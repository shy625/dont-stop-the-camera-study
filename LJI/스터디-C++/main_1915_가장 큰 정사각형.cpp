#include <iostream>
#include <vector>
using namespace std;
int n, m;

// 바로 이전까지의 길이를 가로 세로마다 DP로 기록

main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    cin >> n >> m;

    vector<vector<int>> map(n, vector<int>(m));

    //(한변의 길이)
    vector<vector<int>> DP(n, vector<int>(m, 0));

    string str;
    // answer의 한변의 길이 기록
    int answer = 0;
    for (int i = 0; i < n; i++)
    {
        cin >> str;
        for (int j = 0; j < m; j++)
        {
            map[i][j] = str[j] - '0';

            // DP 길이 갱신
            if (map[i][j] == 1)
            {
                // 최소값 갱신
                DP[i][j] = 1;
                answer = max(DP[i][j], answer);

                // i-1,j-1 좌표의 정사각형이 있다면? 더 늘릴 수 있는지 체크
                if (i - 1 >= 0 && j - 1 >= 0 && DP[i - 1][j - 1] > 0)
                {

                    int now = DP[i - 1][j - 1] + 1;

                    // 가로 길이 체크
                    int rLen = 0;
                    for (int c = j; c > j - now; c--)
                    {
                        if (map[i][c] == 0)
                        {
                            break;
                        }
                        else
                        {
                            rLen++;
                        }
                    }

                    // 세로 길이 체크
                    int cLen = 0;
                    for (int r = i; r > i - now; r--)
                    {
                        if (map[r][j] == 0)
                        {
                            break;
                        }
                        else
                        {
                            cLen++;
                        }
                    }

                    // 두 변 중 가능한 최소 길이로 길이 고정
                    DP[i][j] = min(rLen, cLen);
                    answer = max(answer, DP[i][j]);
                }
            }
        }
    }

    cout << answer * answer;
}
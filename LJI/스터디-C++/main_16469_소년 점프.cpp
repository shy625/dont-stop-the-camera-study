#include <iostream>
#include <vector>
#include <queue>
using namespace std;
int R, C;

// 세 악당 각자 갈 수 있는 곳에 도착하는 최소 시간을 기록
// 제자리 머물기가 가능하기 최소시간 중 최대값이 걸리는 최소 시간

int dr[] = {-1, 0, 1, 0};
int dc[] = {0, 1, 0, -1};

bool Check(int r, int c)
{
    return r >= 1 && r <= R && c >= 1 && c <= C;
}
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> R >> C;

    vector<vector<int>> Map(R + 1, vector<int>(C + 1, 0));

    for (int i = 1; i <= R; i++)
    {
        string str;
        cin >> str;
        for (int j = 1; j <= C; j++)
        {
            Map[i][j] = str[j - 1] - '0';
        }
    }

    // 전부 탐색후에도 -1이면 도달 불가능한 위치라는 소리
    vector<vector<vector<int>>> Villain(R + 1, vector<vector<int>>(C + 1, vector<int>(3, -1)));

    // 세 빌런의 각자 도착가능한 위치 탐색
    for (int V = 0; V < 3; V++)
    {
        // 빌런의 시작 위치
        int SR, SC;
        cin >> SR >> SC;

        // 시작 위치는 0초
        queue<pair<int, int>> Que;
        Que.push(make_pair(SR, SC));
        Villain[SR][SC][V] = 0;

        while (!Que.empty())
        {
            int R = Que.front().first;
            int C = Que.front().second;
            int T = Villain[R][C][V];
            Que.pop();

            for (int d = 0; d < 4; d++)
            {
                int NR = R + dr[d];
                int NC = C + dc[d];

                if (!Check(NR, NC))
                    continue;

                if (Map[NR][NC] == 1)
                    continue;

                if (Villain[NR][NC][V] != -1)
                    continue;

                Villain[NR][NC][V] = T + 1;
                Que.push(make_pair(NR, NC));
            }
        }
    }

    int MinTime = INT32_MAX;
    int Cnt = 0;

    for (int i = 1; i <= R; i++)
    {
        for (int j = 1; j <= C; j++)
        {
            // 빌런 중 가장 늦게 도착하는 사람 기준
            int VTime = -1;

            bool Possible = true;
            for (int v = 0; v < 3; v++)
            {
                if (!Possible)
                    break;
                // 만약 한명이라도 해당 위치 도착 못한 사람 있다면 해당 위치 집결 불가
                if (Villain[i][j][v] == -1)
                {
                    Possible = false;
                    break;
                }

                VTime = max(VTime, Villain[i][j][v]);
            }

            // 세 빌런이 모두 도착 가능할 때 값 갱신 시도
            if (Possible)
            {
                if (MinTime > VTime)
                {
                    MinTime = VTime;
                    Cnt = 1;
                }
                else if (MinTime == VTime)
                {
                    Cnt++;
                }
            }
        }
    }

    if (MinTime == INT32_MAX)
    {
        cout << "-1";
    }
    else
    {
        cout << MinTime << endl;
        cout << Cnt;
    }
}
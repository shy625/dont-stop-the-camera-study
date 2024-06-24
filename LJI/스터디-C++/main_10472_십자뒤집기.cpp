#include <iostream>
#include <vector>
#include <queue>
using namespace std;
int P;

int dr[] = {-1, 0, 1, 0};
int dc[] = {0, 1, 0, -1};

bool Check(int r, int c)
{
    return r >= 0 && r < 3 && c >= 0 && c < 3;
}

void TurnOn(int idx, int cnt, vector<vector<bool>> &OnOff, vector<vector<int>> &map, int &answer)
{
    if (idx == 9)
    {
        vector<vector<int>> tmp(3, vector<int>(3, 0));
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                tmp[i][j] = map[i][j];
            }
        }
        // OnOff에서 true곳마다 숫자 뒤집기하기
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if (OnOff[i][j])
                {
                    tmp[i][j] = (1 != tmp[i][j]);

                    for (int d = 0; d < 4; d++)
                    {
                        int nr = i + dr[d];
                        int nc = j + dc[d];

                        if (!Check(nr, nc))
                            continue;

                        tmp[nr][nc] = (1 != tmp[nr][nc]);
                    }
                }
            }
        }

        // 최종 형태가 0만 존재하면 정답 cnt

        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if (tmp[i][j] != 0)
                {
                    return;
                }
            }
        }

        answer = min(answer, cnt);

        return;
    }

    // onOff여부 설정
    int r = idx / 3;
    int c = idx % 3;

    // 스위치 On
    OnOff[r][c] = true;
    TurnOn(idx + 1, cnt + 1, OnOff, map, answer);
    OnOff[r][c] = false;
    // 스위치OFF
    TurnOn(idx + 1, cnt, OnOff, map, answer);
}
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> P;
    for (int T = 0; T < P; T++)
    {
        // 흰색은 0,검은색은 1
        // 맵 초기화
        vector<vector<int>> map(3, vector<int>(3, 0));

        for (int i = 0; i < 3; i++)
        {
            string tmp;
            cin >> tmp;
            for (int j = 0; j < 3; j++)
            {
                if (tmp[j] == '*')
                    map[i][j] = 1;
            }
        }

        // dfs로 들가면서 각 위치의 스위치 on off여부 기록

        vector<vector<bool>> OnOff(3, vector<bool>(3, false));
        int answer = 10000;
        TurnOn(0, 0, OnOff, map, answer);

        cout << answer << '\n';
    }
}
#include <iostream>
#include <vector>
#include <queue>
#include <cmath>
using namespace std;
int R, C;

int dr[] = {1, 1, 1, 0, 0, 0, -1, -1, -1};
int dc[] = {-1, 0, 1, -1, 0, 1, -1, 0, 1};

bool Check(int r, int c)
{
    return r >= 0 && r < R && c >= 0 && c < C;
}
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> R >> C;
    pair<int, int> JongSu;
    vector<pair<int, int>> Robots;

    vector<vector<char>> Map(R, vector<char>(C));

    // 먼저 맵 입력
    for (int i = 0; i < R; i++)
    {
        string str;
        cin >> str;

        for (int j = 0; j < C; j++)
        {
            Map[i][j] = str[j];

            if (Map[i][j] == 'I')
            {
                JongSu.first = i;
                JongSu.second = j;
            }
            else if (Map[i][j] == 'R')
            {
                Robots.push_back(make_pair(i, j));
            }
        }
    }

    // 종수의 이동 처리
    string str;
    cin >> str;

    bool Death = false;
    int turn = 0;

    vector<bool> RobotDeath(Robots.size(), false);

    for (int i = 0; i < str.length(); i++)
    {
        int order = str[i] - '0' - 1;
        turn++;

        int nr = 0;
        int nc = 0;
        // 종수 먼저 이동 좌표 얻기
        nr = JongSu.first + dr[order];
        nc = JongSu.second + dc[order];

        // 로봇과 겹치는지 확인
        if (Map[nr][nc] == 'R')
        {
            Death = true;
            break;
        }
        else
        {
            Map[JongSu.first][JongSu.second] = '.';
            Map[nr][nc] = 'I';
            JongSu = make_pair(nr, nc);
        }

        // 로봇들 현재 위치 지우고 새로 가려는 곳으로 좌표 갱신
        for (int i = 0; i < Robots.size(); i++)
        {
            if (RobotDeath[i])
                continue;

            pair<int, int> Robot = Robots[i];

            // 로봇 현재 위치 지우기
            Map[Robot.first][Robot.second] = '.';

            // 로봇 이동할 위치 구하기
            int MinDis = INT32_MAX;
            for (int d = 0; d < 9; d++)
            {
                int tmpR = Robot.first + dr[d];
                int tmpC = Robot.second + dc[d];
                if (MinDis > abs(JongSu.first - tmpR) + abs(JongSu.second - tmpC))
                {
                    MinDis = abs(JongSu.first - tmpR) + abs(JongSu.second - tmpC);
                    nr = tmpR;
                    nc = tmpC;
                }
            }

            // nr nc 위치로 이동할 것
            Robots[i].first = nr;
            Robots[i].second = nc;
        }

        // 로봇과 종수가 겹치는지 확인
        // 로봇끼리 겹치는지 확인 겹치면 폭발
        queue<pair<int, int>> CrushPoint;
        for (int i = 0; i < Robots.size(); i++)
        {
            if (RobotDeath[i])
                continue;

            pair<int, int> Robot = Robots[i];
            // 종수가 죽음
            if (Map[Robot.first][Robot.second] == 'I')
            {
                Death = true;
                break;
            }

            // 평범한 이동
            if (Map[Robot.first][Robot.second] == '.')
            {
                Map[Robot.first][Robot.second] = 'R';
            }
            // 충돌 위치 발생
            else if (Map[Robot.first][Robot.second] == 'R')
            {
                Map[Robot.first][Robot.second] = 'C';

                CrushPoint.push(make_pair(Robot.first, Robot.second));
            }
        }

        if (Death)
            break;

        // 충돌한 위치에 로봇이 있다면 해당 로봇 제거
        for (int i = 0; i < Robots.size(); i++)
        {
            if (RobotDeath[i])
                continue;

            pair<int, int> Robot = Robots[i];

            if (Map[Robot.first][Robot.second] == 'C')
            {
                RobotDeath[i] = true;
            }
        }

        // 충돌 위치 .으로 변경
        while (!CrushPoint.empty())
        {
            Map[CrushPoint.front().first][CrushPoint.front().second] = '.';
            CrushPoint.pop();
        }
    }

    if (Death)
    {
        cout << "kraj " << turn;
    }
    else
    {
        // 맵 출력

        for (int i = 0; i < R; i++)
        {
            for (int j = 0; j < C; j++)
            {
                cout << Map[i][j];
            }
            cout << '\n';
        }
    }
}
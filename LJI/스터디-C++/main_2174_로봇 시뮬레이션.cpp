#include <iostream>
#include <vector>
using namespace std;
int A, B, N, M;

int dr[] = {-1, 0, 1, 0};
int dc[] = {0, 1, 0, -1};
bool Check(int r, int c)
{
    return r >= 0 && r < A && c >= 0 && c < B;
}
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> B >> A;
    cin >> N >> M;

    // 로봇 정보 넣어놓기
    vector<pair<pair<int, int>, int>> Robot;
    // 0번 로봇 비워두기
    Robot.push_back(make_pair(make_pair(0, 0), 0));

    vector<vector<int>> Map(A, vector<int>(B, 0));
    int r, c, dir;
    for (int i = 1; i <= N; i++)
    {
        char ch;
        cin >> c >> r >> ch;
        r = A - r;
        c -= 1;

        if (ch == 'N')
        {
            dir = 0;
        }
        else if (ch == 'E')
        {
            dir = 1;
        }
        else if (ch == 'S')
        {
            dir = 2;
        }
        else if (ch == 'W')
        {
            dir = 3;
        }

        Robot.push_back(make_pair(make_pair(r, c), dir));
        Map[r][c] = i;
    }

    int num, repeat;
    char order;

    // answer는 조종하는 로봇,other는 상대 번호//둘 다 0이면 OK
    int answer = 0;
    int other = 0;
    for (int m = 0; m < M; m++)
    {
        cin >> num >> order >> repeat;

        if (order == 'F')
        {
            int nr, nc;
            int d = Robot[num].second;
            for (int i = 0; i < repeat; i++)
            {
                r = Robot[num].first.first;
                c = Robot[num].first.second;

                nr = r + dr[d];
                nc = c + dc[d];

                // 벽에 부딪힘
                if (!Check(nr, nc))
                {
                    answer = num;
                    break;
                }
                // 다른 로봇이 있다
                else if (Map[nr][nc] != 0)
                {
                    answer = num;
                    other = Map[nr][nc];
                    break;
                }

                // 이동하기
                Map[nr][nc] = num;
                Map[r][c] = 0;

                Robot[num].first.first = nr;
                Robot[num].first.second = nc;
            }
        }
        else if (order == 'R')
        {
            Robot[num].second = (Robot[num].second + repeat) % 4;
        }
        else
        {
            Robot[num].second = (100 + Robot[num].second - repeat) % 4;
        }

        if (answer != 0)
        {
            break;
        }
    }

    if (answer == 0)
    {
        cout << "OK";
    }
    else if (other == 0)
    {
        cout << "Robot " << answer << " crashes into the wall";
    }
    else
    {
        cout << "Robot " << answer << " crashes into robot " << other;
    }
}
#include <iostream>
#include <vector>
using namespace std;
int N, M;

vector<vector<char>> Map;

vector<char> Dir = {'U', 'R', 'D', 'L'};
vector<int> dr = {-1, 0, 1, 0};
vector<int> dc = {0, 1, 0, -1};

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
        Map.push_back(vector<char>());
        string str;
        cin >> str;
        for (int j = 0; j < M; j++)
        {
            Map[i].push_back(str[j]);
        }
    }

    pair<int, int> Start;
    cin >> Start.first >> Start.second;

    int answerDir = 0;
    int answerTime = -1;

    // 4방향으러 신호 쏘기
    for (int i = 0; i < 4; i++)
    {
        int r = Start.first - 1;
        int c = Start.second - 1;
        int d = i;
        int t = 0;

        // 신호가 끝날 때까지 계속 진행
        vector<vector<vector<bool>>> Visited(N, vector<vector<bool>>(M, vector<bool>(4, false)));
        while (true)
        {

            // 끝내는 경우
            // 맵 밖으로 나감

            if (!Check(r, c))
            {
                if (answerTime < t)
                {
                    answerTime = t;
                    answerDir = i;
                }
                break;
            }
            // C를 만남
            if (Map[r][c] == 'C')
            {
                if (answerTime < t)
                {
                    answerTime = t;
                    answerDir = i;
                }
                break;
            }

            // 무한히 순회 중
            if (Visited[r][c][d])
            {
                answerDir = i;
                answerTime = -1;
                break;
            }

            // 현재 위치 방문 처리

            Visited[r][c][d] = true;
            // '/'를 만남
            if (Map[r][c] == '/')
            {
                if (d == 0)
                {
                    d = 1;
                }
                else if (d == 1)
                {
                    d = 0;
                }
                else if (d == 2)
                {
                    d = 3;
                }
                else
                {
                    d = 2;
                }
            }
            // '\'를 만남
            if (Map[r][c] == '\\')
            {
                if (d == 0)
                {
                    d = 3;
                }
                else if (d == 1)
                {
                    d = 2;
                }
                else if (d == 2)
                {
                    d = 1;
                }
                else
                {
                    d = 0;
                }
            }
            // 현재 방향으로 진행, 시간 증가
            r = r + dr[d];
            c = c + dc[d];
            t++;
        }

        // 무한 순회 찾음
        if (answerTime == -1)
        {
            break;
        }
    }

    // 정답 출력
    cout << Dir[answerDir] << '\n';

    if (answerTime == -1)
    {
        cout << "Voyager";
    }
    else
    {
        cout << answerTime;
    }
}
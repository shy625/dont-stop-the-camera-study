#include <iostream>
#include <algorithm>
using namespace std;

int K;
int End;
int answer = 0;
bool Map[5][5];

int dr[] = {-1, 0, 1, 0};
int dc[] = {0, 1, 0, -1};
bool Check(int r, int c)
{
    return r >= 0 && r < 5 && c >= 0 && c < 5;
}

void Go(pair<int, int> a, pair<int, int> b, int cnt)
{
    if (a.first == b.first && a.second == b.second)
    {
        if (cnt == End)
        {
            answer++;
        }
        return;
    }
    for (int d1 = 0; d1 < 4; d1++)
    {
        int nr1 = a.first + dr[d1];
        int nc1 = a.second + dc[d1];

        if (!Check(nr1, nc1))
            continue;
        if (!Map[nr1][nc1])
            continue;

        Map[nr1][nc1] = false;
        for (int d2 = 0; d2 < 4; d2++)
        {
            int nr2 = b.first + dr[d2];
            int nc2 = b.second + dc[d2];

            if (nr1 == nr2 && nc1 == nc2)
            {
                Go(make_pair(nr1, nc1), make_pair(nr2, nc2), cnt + 1);
                continue;
            }
            if (!Check(nr2, nc2))
                continue;
            if (!Map[nr2][nc2])
                continue;

            Map[nr2][nc2] = false;

            Go(make_pair(nr1, nc1), make_pair(nr2, nc2), cnt + 2);

            Map[nr2][nc2] = true;
        }

        Map[nr1][nc1] = true;
    }
}
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    cin >> K;
    End = 25 - K;
    int r, c;

    fill(&Map[0][0], &Map[4][5], true);
    for (int i = 0; i < K; i++)
    {
        cin >> r >> c;
        Map[r - 1][c - 1] = false;
    }

    // 시작 위치 초기화
    Map[0][0] = false;
    Map[4][4] = false;
    Go(make_pair(0, 0), make_pair(4, 4), 2);

    cout << answer;
}
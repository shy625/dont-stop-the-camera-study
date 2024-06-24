#include <iostream>
#include <vector>
using namespace std;
int R, C;

int dr[] = {-1, 0, 1, 0};
int dc[] = {0, 1, 0, -1};

bool Check(int r, int c)
{
    return r >= 0 && r < R && c >= 0 && c < C;
}

// DFS
void Go(pair<int, int> now, vector<vector<int>> &map, vector<bool> &visit, int cnt, int &answer)
{
    if (cnt > answer)
    {
        answer = cnt;
    }

    for (int d = 0; d < 4; d++)
    {
        int nr = now.first + dr[d];
        int nc = now.second + dc[d];

        if (!Check(nr, nc))
            continue;

        if (!visit[map[nr][nc]])
        {
            visit[map[nr][nc]] = true;
            Go(make_pair(nr, nc), map, visit, cnt + 1, answer);
            visit[map[nr][nc]] = false;
        }
    }
}
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> R >> C;

    // 0~25 으로 채워질 것
    vector<vector<int>> map(R, vector<int>(C));
    for (int i = 0; i < R; i++)
    {
        string tmp;
        cin >> tmp;
        for (int j = 0; j < C; j++)
        {
            map[i][j] = tmp[j] - 'A';
        }
    }

    int answer = 0;

    // DFS
    vector<bool> visit(26, false);
    // 시작위치
    visit[map[0][0]] = true;
    Go(make_pair(0, 0), map, visit, 1, answer);
    cout << answer;
}
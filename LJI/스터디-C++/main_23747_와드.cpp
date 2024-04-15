#include <iostream>
#include <vector>
#include <queue>
using namespace std;

int R, C;

int dr[] = {-1, 0, 1, 0, 0};
int dc[] = {0, 1, 0, -1, 0};

bool Check(int r, int c)
{

    return r >= 0 && r < R && c >= 0 && c < C;
}
// 영역별로 숫자로 묶어서 표시
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> R >> C;
    vector<vector<char>> CharMap(R, vector<char>(C));
    vector<vector<int>> Map(R, vector<int>(C, 0));
    for (int i = 0; i < R; i++)
    {
        string str;
        cin >> str;
        for (int j = 0; j < C; j++)
        {
            CharMap[i][j] = str[j];
        }
    }
    // bfs로 같은 영역끼리 묶기
    int Num = 1;
    for (int i = 0; i < R; i++)
    {
        for (int j = 0; j < C; j++)
        {
            // 이미 영역이 정해짐
            if (Map[i][j] != 0)
                continue;

            char cha = CharMap[i][j];

            queue<pair<int, int>> que;
            que.push(make_pair(i, j));
            Map[i][j] = Num;

            while (!que.empty())
            {
                int r = que.front().first;
                int c = que.front().second;
                que.pop();

                for (int d = 0; d < 4; d++)
                {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if (!Check(nr, nc))
                        continue;

                    if (Map[nr][nc] == 0 && CharMap[nr][nc] == cha)
                    {
                        Map[nr][nc] = Num;
                        que.push(make_pair(nr, nc));
                    }
                }
            }

            Num++;
        }
    }
    // Map 세팅 끝

    // 와드로 밝혀진 영역 체크
    vector<bool> SearchArea(Num, false);
    vector<vector<int>> Visited(R, vector<int>(C, false));

    int r, c;
    cin >> r >> c;
    r--;
    c--;

    // 명령어 처리
    string str;
    cin >> str;

    for (char order : str)
    {

        // 와드 설치라면 현재 영역 와드 설치하고 끝
        if (order == 'W')
        {
            if (!SearchArea[Map[r][c]])
            {
                SearchArea[Map[r][c]] = true;
            }

            continue;
        }

        // URDL 각각에 맞춰 이동 후 영역 탐색까지
        int d = 0;
        if (order == 'U')
        {
            d = 0;
        }
        else if (order == 'R')
        {
            d = 1;
        }
        else if (order == 'D')
        {
            d = 2;
        }
        else
        {
            d = 3;
        }

        r += dr[d];
        c += dc[d];
    }

    // 현재 위치에서 주변 체크
    for (int d = 0; d < 5; d++)
    {
        int nr = r + dr[d];
        int nc = c + dc[d];
        if (!Check(nr, nc))
            continue;
        if (!Visited[nr][nc])
            Visited[nr][nc] = true;
    }

    // 출력
    for (int i = 0; i < R; i++)
    {
        for (int j = 0; j < C; j++)
        {
            if (Visited[i][j] || SearchArea[Map[i][j]])
            {
                cout << '.';
            }
            else
            {
                cout << '#';
            }
        }
        cout << '\n';
    }
}
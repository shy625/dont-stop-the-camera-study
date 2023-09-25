#include <iostream>
#include <vector>
using namespace std;

char map[101][101];
bool visit[101][101];
int N, M;
int dr[] = {-1, 0, 1, 0};
int dc[] = {0, 1, 0, -1};
bool check(int r, int c)
{
    return r > 0 && r <= N && c > 0 && c <= M;
}
// 십자가면 그 정보를 answerList에 넣고 visit도 true로 바꾸기
void MakeCross(int r, int c, vector<pair<pair<int, int>, int>> &answerList)
{
    int size = 0;
    while (true)
    {
        bool bCross = true;
        size++;
        for (int d = 0; d < 4; d++)
        {
            int nr = r + dr[d] * size;
            int nc = c + dc[d] * size;

            if (!check(nr, nc) || map[nr][nc] != '*')
            {
                bCross = false;
                break;
            }
        }

        if (!bCross)
        {
            size--;
            break;
        }
    }

    // cout << size << ' ' << r << ' ' << c << endl;
    if (size == 0)
        return;

    answerList.push_back(make_pair(make_pair(r, c), size));
    for (int i = 0; i <= size; i++)
    {
        for (int d = 0; d < 4; d++)
        {
            int nr = r + dr[d] * i;
            int nc = c + dc[d] * i;
            if (!visit[nr][nc])
                visit[nr][nc] = true;
        }
    }
}

main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> M;

    for (int i = 1; i <= N; i++)
    {
        string str;
        cin >> str;
        for (int j = 1; j <= M; j++)
        {
            map[i][j] = str[j - 1];
            visit[i][j] = false;
        }
    }

    // 십자가 채우기
    vector<pair<pair<int, int>, int>> answerList;
    for (int i = 1; i <= N; i++)
    {
        for (int j = 1; j <= M; j++)
        {
            // 별표시면 십자가 가능한지 체크
            if (map[i][j] == '*')
            {
                MakeCross(i, j, answerList);
            }
        }
    }

    // 십자가로 전부 채우기 가능한지 체크
    bool checkFill = true;
    for (int i = 1; i <= N; i++)
    {
        for (int j = 1; j <= M; j++)
        {
            if (!visit[i][j] && map[i][j] == '*')
            {
                checkFill = false;
                break;
            }
        }
        if (!checkFill)
            break;
    }

    // 출력
    if (!checkFill)
    {
        cout << "-1" << endl;
    }
    else
    {
        cout << answerList.size() << endl;
        for (pair<pair<int, int>, int> cur : answerList)
        {
            cout << cur.first.first << ' ' << cur.first.second << ' ' << cur.second << endl;
        }
    }
}
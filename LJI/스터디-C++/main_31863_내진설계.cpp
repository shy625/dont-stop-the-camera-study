#include <iostream>
#include <vector>
#include <queue>
using namespace std;
int N, M;

int dr[] = {-1, 0, 1, 0};
int dc[] = {0, 1, 0, -1};

bool Check(int r, int c)
{
    return r >= 0 && r < N && c >= 0 && c < M;
}

void Simul(vector<vector<int>> &Map, queue<pair<int, int>> &EarthQuake, int Power, int &BuildingCnt, int &CollapseBuildingCnt)
{
    pair<int, int> Point = EarthQuake.front();
    EarthQuake.pop();
    // cout << Point.first << ' ' << Point.second << endl;

    for (int d = 0; d < 4; d++)
    {
        for (int i = 1; i <= Power; i++)
        {
            int nr = Point.first + dr[d] * i;
            int nc = Point.second + dc[d] * i;

            if (!Check(nr, nc) || Map[nr][nc] == -1)
                break;

            if (Map[nr][nc] > 0)
            {
                Map[nr][nc]--;
                if (Map[nr][nc] == 0)
                {
                    BuildingCnt--;
                    CollapseBuildingCnt++;
                    EarthQuake.push(make_pair(nr, nc));
                }
            }
        }
    }
}

main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    cin >> N >> M;

    vector<vector<int>> Map(N, vector<int>(M, 0));

    queue<pair<int, int>> EarthQuake;
    int BuildingCnt = 0;
    int CollapseBuildingCnt = 0;
    for (int i = 0; i < N; i++)
    {
        string str;
        cin >> str;
        for (int j = 0; j < M; j++)
        {
            if (str[j] == '@')
            {

                EarthQuake.push(make_pair(i, j));
            }
            else if (str[j] == '.')
            {
            }
            else if (str[j] == '*')
            {
                Map[i][j] = 1;
                BuildingCnt++;
            }
            else if (str[j] == '#')
            {
                Map[i][j] = 2;
                BuildingCnt++;
            }
            else
            {
                Map[i][j] = -1;
            }
        }
    }

    Simul(Map, EarthQuake, 2, BuildingCnt, CollapseBuildingCnt);

    while (!EarthQuake.empty())
    {

        Simul(Map, EarthQuake, 1, BuildingCnt, CollapseBuildingCnt);
    }

    cout << CollapseBuildingCnt << ' ' << BuildingCnt << '\n';
}
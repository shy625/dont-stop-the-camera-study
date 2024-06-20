#include <iostream>
#include <vector>
using namespace std;
int T;

void DFS(int cnt, int total, vector<vector<int>> &Player, vector<bool> &CheckPos, int &Answer)
{
    if (cnt == 11)
    {
        Answer = max(total, Answer);
        return;
    }

    // cnt번째 선수가 들어갈 수 있는 Pos
    for (int i = 0; i < 11; i++)
    {
        if (!CheckPos[i] && Player[cnt][i] != 0)
        {
            CheckPos[i] = true;
            DFS(cnt + 1, total + Player[cnt][i], Player, CheckPos, Answer);
            CheckPos[i] = false;
        }
    }
}
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> T;
    for (int t = 0; t < T; t++)
    {
        vector<vector<int>> Player(11, vector<int>(11, 0));

        for (int i = 0; i < 11; i++)
        {
            for (int j = 0; j < 11; j++)
            {
                cin >> Player[i][j];
            }
        }

        vector<bool> CheckPos(11, false);
        int Answer = 0;
        DFS(0, 0, Player, CheckPos, Answer);

        cout << Answer << '\n';
    }
}
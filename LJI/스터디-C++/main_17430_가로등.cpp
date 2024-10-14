#include <iostream>
#include <vector>
#include <map>
#include <algorithm>
using namespace std;
int N, T;
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> T;
    for (int t = 0; t < T; t++)
    {
        cin >> N;
        // x축에 대한 y좌표
        map<int, vector<int>> LightMap;

        for (int i = 0; i < N; i++)
        {
            int x, y;
            cin >> x >> y;

            LightMap[x].push_back(y);
        }

        for (auto &cur : LightMap)
        {
            sort(cur.second.begin(), cur.second.end());
        }

        bool isBalanced = true;

        for (auto &i : LightMap)
        {
            for (auto &j : LightMap)
            {
                if (i.first != j.first && i.second != j.second)
                {
                    isBalanced = false;
                    break;
                }
            }
            if (!isBalanced)
                break;
        }

        if (isBalanced)
        {
            cout << "BALANCED\n";
        }
        else
        {
            cout << "NOT BALANCED\n";
        }
    }
}
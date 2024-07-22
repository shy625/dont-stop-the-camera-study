#include <iostream>
#include <vector>
#include <set>
#include <algorithm>
using namespace std;
int N;

main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N;

    vector<pair<int, int>> PointerVec;
    set<pair<int, int>> PointerSet;
    for (int i = 0; i < N; i++)
    {
        int x, y;
        cin >> x >> y;

        PointerVec.push_back(make_pair(x, y));
        PointerSet.insert(make_pair(x, y));
    }

    int Answer = 0;

    for (int i = 0; i < N; i++)
    {
        for (int j = i + 1; j < N; j++)
        {
            // x,y 좌표가 모두 다른 대각선일 때 진행
            int x1 = PointerVec[i].first;
            int y1 = PointerVec[i].second;

            int x2 = PointerVec[j].first;
            int y2 = PointerVec[j].second;
            if (x1 != x2 &&
                y1 != y2)
            {
                // 서로 상대 좌표인 점 두개가 모두 존재하는지 체크
                if (PointerSet.find(make_pair(x1, y2)) != PointerSet.end() &&
                    PointerSet.find(make_pair(x2, y1)) != PointerSet.end())
                {
                    Answer++;
                }
            }
        }
    }

    // 반대 대각선 체크 제거
    Answer /= 2;

    cout << Answer;
}
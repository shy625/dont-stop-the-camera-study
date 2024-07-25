#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;
int N;
// 많이 이긴 사람을 앞으로 배치해서 최소한의 코인 지급
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N;

    vector<pair<int, int>> Player;
    for (int i = 0; i < N; i++)
    {
        int Power, MagicRing;
        cin >> Power >> MagicRing;

        Player.push_back(make_pair(Power, MagicRing));
    }

    vector<int> PlayerWin(N, 0);

    for (int i = 0; i < N; i++)
    {
        for (int j = i + 1; j < N; j++)
        {
            // i와 j의 매치

            // i의 승리
            if (Player[i].first + Player[j].first * Player[i].second > Player[j].first + Player[i].first * Player[j].second)
            {
                PlayerWin[i]++;
            }
            else
            {
                PlayerWin[j]++;
            }
        }
    }

    vector<pair<int, int>> Line;
    for (int i = 0; i < N; i++)
    {
        // 오름차순 정렬로 해결 가능하게 -1 곱하기
        Line.push_back(make_pair(-1 * PlayerWin[i], i + 1));
    }

    sort(Line.begin(), Line.end());

    for (int i = 0; i < N; i++)
    {
        cout << Line[i].second << '\n';
    }
}
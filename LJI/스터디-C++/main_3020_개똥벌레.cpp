#include <iostream>
#include <vector>
using namespace std;
int N, H;
// 높이에 따른 누적합으로 계산 후 각 높이에 있는 개수로 계산
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> H;

    vector<int> Ceiling(H + 1, 0);
    vector<int> Floor(H + 1, 0);

    for (int i = 0; i < N; i++)
    {
        int size;
        cin >> size;

        // 석순
        if (i % 2 == 0)
        {
            Floor[size]++;
        }
        // 종유석
        else
        {
            // 천장 높이-크기+1
            Ceiling[H - size + 1]++;
        }
    }

    // 천장은 아래서부터 위로 누적합
    for (int i = 2; i <= H; i++)
    {
        Ceiling[i] += Ceiling[i - 1];
    }

    // 바닥은 위에서부터 아래로 누적합
    for (int i = H - 1; i >= 1; i--)
    {
        Floor[i] += Floor[i + 1];
    }

    int Answer = INT32_MAX;
    int Cnt = 0;

    for (int i = 1; i <= H; i++)
    {
        int Obs = Floor[i] + Ceiling[i];
        if (Answer > Obs)
        {
            Answer = Obs;
            Cnt = 1;
        }
        else if (Answer == Obs)
        {
            Cnt++;
        }
    }

    cout << Answer << ' ' << Cnt;
}
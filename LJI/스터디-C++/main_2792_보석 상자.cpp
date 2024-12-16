#include <iostream>
#include <vector>
using namespace std;
int N, M;

bool CheckDist(int Val, const vector<int> &Color)
{
    int Cnt = 0;
    for (int Jewel : Color)
    {
        Cnt += Jewel / Val;
        if (Jewel % Val)
        {
            Cnt++;
        }
        if (Cnt > N)
        {
            return false;
        }
    }

    return true;
}
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> M;

    vector<int> Color(M, 0);
    int Min = 1;
    int Max = 0;

    for (int i = 0; i < M; i++)
    {
        cin >> Color[i];
        Max = max(Max, Color[i]);
    }

    // 이분탐색
    int Ans = Max;
    while (Min <= Max)
    {
        int Mid = (Min + Max) / 2;

        if (CheckDist(Mid, Color))
        {
            Ans = min(Ans, Mid);
            Max = Mid - 1;
        }
        else
        {
            Min = Mid + 1;
        }
    }

    cout << Ans;
}
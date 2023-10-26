#include <iostream>

using namespace std;

main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    int X;
    cin >> X;
    string Line;
    cin >> Line;
    int Len = Line.length();

    int MCnt = 0;
    int WCnt = 0;

    int Diff;
    for (int i = 0; i < Len; i++)
    {
        Diff = MCnt - WCnt;
        char Now = Line[i];
        if (Line[i] == 'M')
            Diff++;
        else
            Diff--;

        // 제한범위 넘어섬
        if (abs(Diff) > X)
        {
            // 마지막이면 뒤와 교체가 불가
            if (i == Len - 1)
                break;

            // 마지막이 아니면 뒤와 다를 시 교체
            if (Now != Line[i + 1])
            {
                Now = Line[i + 1];
                swap(Line[i], Line[i + 1]);
            }
            else
            {
                break;
            }
        }

        if (Now == 'M')
            MCnt++;
        else
            WCnt++;
    }

    cout << MCnt + WCnt;
}
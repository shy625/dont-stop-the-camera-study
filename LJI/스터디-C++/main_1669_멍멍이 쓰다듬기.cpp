#include <iostream>

using namespace std;

// 1
// 2
// 33
// 44
// 555
// 666
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    int X, Y;

    cin >> X >> Y;

    int Diff = Y - X;

    int Day = 0;

    // 반복 두번 했으면 Max 늘리기
    int Repeat = 0;
    int Max = 1;
    // Cnt가 0되면 반복 횟수 늘리기
    int Cnt = Max;
    for (int i = 0; i < Diff; i++)
    {
        if (Cnt == Max)
        {
            Day++;
        }
        --Cnt;

        if (Cnt == 0)
        {
            Repeat++;
            if (Repeat == 2)
            {
                Max++;
                Repeat = 0;
            }

            Cnt = Max;
        }
    }

    cout << Day;
}
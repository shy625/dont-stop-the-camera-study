#include <iostream>
#include <vector>
#include <cmath>
using namespace std;
long long N;

bool CheckNum(long long Origin, vector<bool> &Num)
{
    for (int i = 1; i <= 9; i++)
    {
        if (Num[i])
        {
            if (Origin % i != 0)
            {
                return false;
            }
        }
    }

    return true;
}
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N;

    // 각 자리 수 체크
    vector<bool> Num(10, false);

    int tmp = N;

    while (tmp != 0)
    {
        Num[tmp % 10] = true;
        tmp /= 10;
    }

    int Cnt = 0;
    long long NewNum = N;
    long long PowNum = 1;
    while (true)
    {
        NewNum = N * PowNum;

        for (int i = 0; i < PowNum; i++)
        {
            long long Tmp = NewNum + i;

            if (CheckNum(Tmp, Num))
            {
                cout << Tmp;
                return 0;
            }
        }

        PowNum *= 10;
    }
}
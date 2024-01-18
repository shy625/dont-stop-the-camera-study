#include <iostream>

using namespace std;
int X;
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> X;
    string str;
    cin >> str;

    int len = str.length();
    int lastIdx;
    // 짝수일 시 마지막
    // 홀수일 시 마지막 -1
    if (len % 2 == 0)
    {
        lastIdx = len - 1;
    }
    else
    {
        lastIdx = len - 2;
    }

    string tmp = str;
    int cycle = 0;
    for (int x = 1; x <= X; x++)
    {

        string front = "";
        string last = "";

        for (int i = 0; i < len; i += 2)
        {
            front += tmp[i];
        }

        for (int i = lastIdx; i >= 0; i -= 2)
        {
            last += tmp[i];
        }
        tmp = front + last;

        if (tmp == str)
        {
            cycle = x;
            break;
        }
    }

    // 사이클이 생겼을 시 나머지 계산
    if (cycle != 0)
    {
        X %= cycle;
        for (int i = 0; i < X; i++)
        {
            string front = "";
            string last = "";

            for (int i = 0; i < len; i += 2)
            {
                front += tmp[i];
            }

            for (int i = lastIdx; i >= 0; i -= 2)
            {
                last += tmp[i];
            }
            tmp = front + last;
        }
    }

    cout << tmp;
}
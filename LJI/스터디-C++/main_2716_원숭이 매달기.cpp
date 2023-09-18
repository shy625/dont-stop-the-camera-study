#include <iostream>
#include <cmath>
using namespace std;
int N;
//[]가 가장 많이 중첨된 수의 2^n 값이 필요한 원숭이 양
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N;
    cin.ignore();
    for (int t = 0; t < N; t++)
    {
        string str;
        // 안되면 getline(cin,str);
        // cin.ignore();
        getline(cin, str, '\n');

        int cnt = 0;
        int maxCnt = 0;
        for (char c : str)
        {
            if (c == '[')
            {
                cnt++;
                maxCnt = max(maxCnt, cnt);
            }
            else if (c == ']')
            {
                cnt--;
            }
        }

        cout << (int)pow(2, maxCnt) << endl;
    }
}
#include <iostream>
#include <vector>
#include <set>
using namespace std;
int n;
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> n;
    // 가장 큰 질량부터 처리
    // 질량은 최대 2n까지 가능

    set<int> SquareSet;

    int tmp = 2;
    while (tmp <= 2 * n)
    {
        SquareSet.insert(tmp);
        tmp *= 2;
    }

    vector<int> Vec(n + 1, 0);
    vector<bool> Check(n + 1, false);

    for (int i = n; i >= 1; i--)
    {
        for (int j = n; j >= 1; j--)
        {
            // 해당 값이 거듭제곱 안에 있고 사용안했으면 체크
            if (!Check[j] && SquareSet.find(i + j) != SquareSet.end())
            {
                Check[j] = true;
                Vec[i] = j;
                break;
            }
        }
    }

    for (int i = 1; i <= n; i++)
    {
        cout << Vec[i] << '\n';
    }
}
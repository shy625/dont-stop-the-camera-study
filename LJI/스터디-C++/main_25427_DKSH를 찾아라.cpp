#include <iostream>
#include <vector>
using namespace std;
int N;
string S;
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> S;

    vector<long long> DP(4, 0);

    for (int i = 0; i < S.length(); i++)
    {
        if (S[i] == 'D')
        {
            DP[0]++;
        }
        else if (S[i] == 'K')
        {
            DP[1] += DP[0];
        }
        else if (S[i] == 'S')
        {
            DP[2] += DP[1];
        }
        else if (S[i] == 'H')
        {
            DP[3] += DP[2];
        }
    }

    cout << DP[3];
}
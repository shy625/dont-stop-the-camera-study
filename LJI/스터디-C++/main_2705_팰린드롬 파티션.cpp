#include <iostream>
#include <vector>
using namespace std;

// 특정 수 n(n>3)일 때 이것을 팰린드롬 형태로 나누는 방법은?
// {n/2,n/2}(홀수라면 {n/2,1,n/2}) 이거나 {1,n-2,1}의 형태
// 이를 활용해서 DP
int N, T;
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> T;

    vector<int> DP(1001, 0);

    // 1:1
    DP[1] = 1;
    // 2: {1,1},{2}
    DP[2] = 2;

    for (int i = 3; i <= 1000; i++)
    {

        DP[i] = DP[i - 2] + DP[i / 2];
    }

    for (int t = 0; t < T; t++)
    {
        cin >> N;

        cout << DP[N] << '\n';
    }
}
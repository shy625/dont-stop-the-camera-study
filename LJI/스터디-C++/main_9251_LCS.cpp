#include <iostream>
#include <vector>
using namespace std;

// 두 문자열을 한 글자씩 비교
// 같다면 현재까지 완성된 가장 긴 부분수열값 +1
// 다르면 두 완성된 최장 수열 중 더 긴값 입력
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    string str1, str2;
    cin >> str1 >> str2;

    vector<vector<int>> DP(1001, vector<int>(1001, 0));

    for (int i = 1; i <= str1.length(); i++)
    {
        for (int j = 1; j <= str2.length(); j++)
        {
            // 1번 문자가 문자열에선 0번 인덱스
            if (str1[i - 1] == str2[j - 1])
            {
                DP[i][j] = DP[i - 1][j - 1] + 1;
            }
            else
            {
                DP[i][j] = max(DP[i - 1][j], DP[i][j - 1]);
            }
        }
    }

    cout << DP[str1.length()][str2.length()];
}
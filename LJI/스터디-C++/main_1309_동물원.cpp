#include <iostream>
#include <vector>
#define MAX 9901
// 가로 칸 기준으로 항상 한 곳에 사자가 있으면 반대쪽은 불가하다
// 즉 가로 기준으로 세가지 경우만 가능, 왼쪽 사자, 오른쪽 사자, 양쪽 다 사자 없음
using namespace std;
int N;
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N;

    // 뒷 인덱스 0:왼쪽 사자, 1:오른쪽 사자,2:양쪽 사자 없음
    vector<vector<int>> DP(N + 1, vector<int>(3, 0));

    DP[1][0] = 1;
    DP[1][1] = 1;
    DP[1][2] = 1;

    for (int i = 2; i <= N; i++)
    {
        // i에 왼쪽 사자만 배치
        DP[i][0] = (DP[i - 1][1] + DP[i - 1][2]) % MAX;

        // i에 오른쪽 사자 배치
        DP[i][1] = (DP[i - 1][0] + DP[i - 1][2]) % MAX;

        // i에 사자 X
        DP[i][2] = (DP[i - 1][0] + DP[i - 1][1] + DP[i - 1][2]) % MAX;
    }

    cout << (DP[N][0] + DP[N][1] + DP[N][2]) % MAX;
}
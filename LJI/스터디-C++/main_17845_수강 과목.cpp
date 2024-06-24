#include <iostream>
#include <vector>
using namespace std;
int N, K, I, T;

// 배낭 문제 방식으로 푼다
// K x N 배열로 k과목을 포함한 것과 포함하지 않은 것 중 더 큰값 기록
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> K;

    // 과목 정보
    vector<pair<int, int>> Sub(K + 1);
    for (int i = 1; i <= K; i++)
    {
        cin >> I >> T;
        Sub[i] = make_pair(I, T);
    }

    // 배낭 값
    vector<vector<int>> Vec(K + 1, vector<int>(N + 1, 0));

    for (int i = 1; i <= K; i++)
    {
        for (int j = 1; j <= N; j++)
        {
            if (j - Sub[i].second >= 0)
            {
                Vec[i][j] = max(Vec[i - 1][j], Vec[i - 1][j - Sub[i].second] + Sub[i].first);
            }
            else
            {
                Vec[i][j] = Vec[i - 1][j];
            }
        }
    }

    cout << Vec[K][N];
}
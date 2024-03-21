#include <iostream>
#include <vector>
using namespace std;
int N;
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N;

    vector<int> Height(1000001, 0);

    int H;
    int answer = 0;
    for (int i = 0; i < N; i++)
    {
        cin >> H;

        // 만약 현재H 보다 +1높이의 풍선이 0개가 아니면 같은 화살로 처리 가능
        // 아니면 다른 화살 사용

        if (Height[H + 1] != 0)
        {
            Height[H + 1]--;
            Height[H]++;
        }
        else
        {
            Height[H]++;
            answer++;
        }
    }

    cout << answer;
}
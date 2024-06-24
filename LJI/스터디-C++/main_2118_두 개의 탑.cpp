#include <iostream>
#include <vector>
using namespace std;
int N;
// 거리를 누적합으로 구하고
// 두 지점을 설정해 거리 양쪽값 비교

main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N;

    vector<int> Vec;
    // 처음 위치값 받기
    int tmp;
    cin >> tmp;
    Vec.push_back(tmp);

    for (int i = 0; i < N - 1; i++)
    {
        cin >> tmp;
        Vec.push_back(Vec[i] + tmp);
    }

    int answer = 0;

    for (int i = 0; i < N; i++)
    {
        for (int j = i + 1; j < N; j++)
        {
            tmp = min(Vec[j] - Vec[i], (Vec[i] + (Vec[N - 1] - Vec[j])));
            answer = max(answer, tmp);
        }
    }

    cout << answer;
}
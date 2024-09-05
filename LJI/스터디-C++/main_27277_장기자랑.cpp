#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int N;

// 가장 잘하는 인원을 맨 앞에 두고 나머지 인원들을 차이나는 순으로 배치
//  1,4,3,5,6,2 -> 6,1,5,2,4,3
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N;

    vector<int> Vec(N);

    for (int i = 0; i < N; i++)
    {
        cin >> Vec[i];
    }

    int Answer = 0;

    sort(Vec.begin(), Vec.end());

    int Start = 0;
    int End = N - 1;

    // 가장 높은 실력 인원 먼저 맨앞 배치로 자동 포함
    Answer += Vec[End--];

    // 나머지 인원들 가장 차이나는 순으로 차례대로 배치
    while (Start < End)
    {
        Answer += Vec[End--] - Vec[Start++];
    }

    cout << Answer;
}
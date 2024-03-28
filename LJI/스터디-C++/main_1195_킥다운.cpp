#include <iostream>

using namespace std;

// 이가 2개가 서로 부딪히지만 않으면 된다

// diff+gear2와 gear1 중 더 긴 것이 기준
// 2,2로 서로 부딪히지 않으면 끝
int MatchingGear(string gear1, string gear2, int diff)
{
    int size1 = gear1.size();
    int size2 = gear2.size();

    for (int i = 0; i < size2; i++)
    {
        int idx1 = i + diff;
        int idx2 = i;

        // gear1의 길이가 다 끝나서 자연스럽게 매치
        if (idx1 >= size1)
            break;

        // 두 기어가 다 이여서 부딪힘
        // 현재론 안되니 한칸 더 이동
        if (gear1[idx1] == '2' && gear2[idx2] == '2')
        {
            return MatchingGear(gear1, gear2, diff + 1);
        }
    }

    return max(size1, size2 + diff);
}
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    string gear1, gear2;

    cin >> gear1 >> gear2;

    int answer = INT32_MAX;

    // 뒤에 있는 기어를 한칸씩 이동하면서 테스트
    answer = min(answer, MatchingGear(gear1, gear2, 0));
    answer = min(answer, MatchingGear(gear2, gear1, 0));

    cout << answer;
}
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int N;
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    cin >> N;

    vector<int> vec;
    for (int i = 0; i < N; i++)
    {
        int tmp;
        cin >> tmp;
        vec.push_back(tmp);
    }

    // 낮은 곳부터 높은 순으로 정렬
    sort(vec.begin(), vec.end(), less<int>());

    int low = 0, high = N - 1;
    pair<int, int> answer;
    // 나온 것 중 가장 0의 가까운 특성값
    int min = INT32_MAX;

    while (low < high)
    {
        // 먼저 특성값 계산
        int value = vec[low] + vec[high];

        // 이 값이 0과 제일 가깝다면 갱신
        if (abs(value) < min)
        {
            answer = make_pair(vec[low], vec[high]);
            min = abs(value);
        }

        if (value < 0)
        {
            low++;
        }
        else if (value > 0)
        {
            high--;
        }
        else // value==0,이미 정답이다.
        {
            break;
        }
    }

    cout << answer.first << endl
         << answer.second;
}
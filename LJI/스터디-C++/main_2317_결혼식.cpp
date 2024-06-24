#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int N, K;

// 사자 가족 가장 큰키와 작은키 사이의 값은 모두 0로 처리 가능하다
// 그 외의 최저값과 최대값은 사자쪽 값에 따라 *2차이로 처리할지 *1 차이로 처리할지 계산
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> K;

    int High = -1;
    int Low = INT32_MAX;

    vector<int> LionFam;

    int answer = 0;
    for (int i = 0; i < K; i++)
    {
        int tmp;
        cin >> tmp;

        if (tmp > High)
        {
            High = tmp;
        }

        if (tmp < Low)
        {
            Low = tmp;
        }
        LionFam.push_back(tmp);

        if (i != 0)
        {
            answer += abs(LionFam[i] - LionFam[i - 1]);
        }
    }

    vector<int> Others;
    for (int i = 0; i < N - K; i++)
    {
        int tmp;
        cin >> tmp;
        Others.push_back(tmp);
    }

    sort(Others.begin(), Others.end());

    // Others의 최댓값 처리
    // 양 끝에 붙이는 경우랑 가장 큰 값에 붙이고 *2 하는 것 중 더 낮은 값 사용
    if (Others.size() > 0 && Others[N - K - 1] > High)
    {
        // 중앙 배치
        int diff = (Others[N - K - 1] - High) * 2;

        // 좌우 끝 배치

        diff = min(diff, abs(Others[N - K - 1] - LionFam[0]));
        diff = min(diff, abs(Others[N - K - 1] - LionFam[K - 1]));

        answer += diff;
    }

    // Others의 최소값 처리
    if (Others.size() > 0 && Others[0] < Low)
    {
        // 중앙 배치
        int diff = (Low - Others[0]) * 2;

        diff = min(diff, abs(Others[0] - LionFam[0]));
        diff = min(diff, abs(Others[0] - LionFam[K - 1]));

        answer += diff;
    }

    cout << answer;
}
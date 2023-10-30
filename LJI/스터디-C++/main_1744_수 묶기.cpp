#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int N;
// 묶은 값이 최소 더하기보다 작아서는 안될 것
// 무조건 높은수끼리,-일 경우 낮은 수 끼리 묶었을 시 제일 클 것
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    cin >> N;

    vector<int> vec;
    for (int i = 0; i < N; i++)
    {
        int n;
        cin >> n;
        vec.push_back(n);
    }

    sort(vec.begin(), vec.end());

    long long answer = 0;
    //-값들 처리
    for (int i = 0; i < N; i++)
    {
        if (vec[i] >= 0)
            continue;

        // 곱하기 불가
        if (i == N - 1)
        {
            answer += vec[i];
            continue;
        }

        // 곱하기가 0보다 크거나 같다면
        if (vec[i] * vec[i + 1] >= vec[i] + vec[i + 1])
        {
            answer += vec[i] * vec[i + 1];
            i++;
        }
        else
        {
            answer += vec[i];
        }
    }

    //+값들 처리
    for (int i = N - 1; i >= 0; i--)
    {
        if (vec[i] <= 0)
            continue;

        // 곱하기 불가
        if (i == 0)
        {
            answer += vec[i];
            continue;
        }

        // 곱하기가 0보다 크면
        if (vec[i] * vec[i - 1] > vec[i] + vec[i - 1])
        {
            answer += vec[i] * vec[i - 1];
            i--;
        }
        else
        {
            answer += vec[i];
        }
    }
    cout << answer;
}
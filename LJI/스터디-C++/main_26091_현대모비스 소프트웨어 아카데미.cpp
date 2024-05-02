#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int N, M;

// 두명이어야하기에 최소를 최대와 엮어서 처리 시도
// 투 포인터
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> M;

    vector<int> vec(N);

    for (int i = 0; i < N; i++)
    {
        cin >> vec[i];
    }

    sort(vec.begin(), vec.end());

    int low = 0;
    int high = N - 1;

    int answer = 0;
    while (low < high)
    {
        if (vec[low] + vec[high] >= M)
        {
            low++;
            high--;
            answer++;
        }
        else
        {
            low++;
        }
    }

    cout << answer;
}
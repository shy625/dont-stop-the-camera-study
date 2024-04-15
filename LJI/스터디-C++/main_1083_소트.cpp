#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int N, S;

// 남은 S가 허용하는 내에서 가장 큰 값이랑 교환
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N;

    vector<int> vec(N);

    for (int i = 0; i < N; i++)
    {
        cin >> vec[i];
    }

    cin >> S;

    for (int i = 0; i < N; i++)
    {
        // 교환 횟수 다 썼으면 끝
        if (S == 0)
            break;

        // 정렬되지 않은 벡터 S까지나 끝 중에서 최대값 찾기
        int Max = vec[i];
        int MaxIdx = i;

        for (int j = i + 1; j <= min(N - 1, S + i); j++)
        {
            if (Max < vec[j])
            {
                Max = vec[j];
                MaxIdx = j;
            }
        }

        // max값의 위치의 값을 현재 idx 에 삽입 max 위치의 값은 삭제
        if (Max > vec[i])
        {
            vec.erase(vec.begin() + MaxIdx);
            vec.insert(vec.begin() + i, Max);

            S -= MaxIdx - i;
        }
    }

    for (int num : vec)
    {
        cout << num << ' ';
    }
}
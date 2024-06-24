#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int N, M;

// 길이가 20이면 한번 자를때 2개가 나온다
// 길이가 30이면 두번 자를때 3개
// 10배수인 길이일 때 효율이 좋다

main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> M;

    vector<int> vec;
    vector<int> vec10;

    for (int i = 0; i < N; i++)
    {
        int tmp;
        cin >> tmp;

        if (tmp % 10 == 0)
        {
            vec10.push_back(tmp);
        }
        else if (tmp > 10)
        {
            vec.push_back(tmp);
        }
    }

    sort(vec.begin(), vec.end());
    sort(vec10.begin(), vec10.end());

    int answer = 0;
    for (int i = 0; i < vec10.size(); i++)
    {
        if (M == 0)
            break;

        int now = vec10[i];

        int cut = (now / 10) - 1;

        // 자르는 회수가 모자란경우
        if (cut > M)
        {
            answer += M;
            M = 0;
        }
        else
        {
            answer += cut + 1;
            M -= cut;
        }
    }

    for (int i = 0; i < vec.size(); i++)
    {
        if (M == 0)
            break;

        int now = vec[i];

        int cut = now / 10;

        // 자르는 회수가 모자란경우
        if (cut > M)
        {
            answer += M;
            M = 0;
        }
        else
        {
            answer += cut;
            M -= cut;
        }
    }

    cout << answer;
}
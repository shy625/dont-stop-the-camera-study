#include <iostream>
#include <vector>

using namespace std;

int N;

// N=1일 때는 예외 적용

// 연결된 3개의 면의 최소값 *4
// 연결된 2개 면의 최소값 (N-1)*4+(N-2)*4
// 가장 작은 한개의 면 (N-1)*N-2*4 + (N-2)*(N-2)

// 5-i가 반대면
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N;
    vector<int> Dice(5);

    int Min1 = 1000;
    int Min2 = 1000;
    int Min3 = 1000;
    int Max1 = 0;
    long long Sum = 0;
    for (int i = 0; i < 6; i++)
    {
        cin >> Dice[i];
        Min1 = min(Min1, Dice[i]);
        Max1 = max(Max1, Dice[i]);
        Sum += Dice[i];
    }

    if (N == 1)
    {
        cout << Sum - Max1;
        return 0;
    }

    for (int i = 0; i < 6; i++)
    {
        for (int j = i + 1; j < 6; j++)
        {
            // 반대면 제외
            if (5 - j != i)
            {
                Min2 = min(Min2, Dice[i] + Dice[j]);
            }

            // 3면 배치
            for (int k = j + 1; k < 6; k++)
            {
                if (5 - k == i || 5 - k == j || 5 - j == i)
                    continue;

                Min3 = min(Min3, Dice[i] + Dice[j] + Dice[k]);
            }
        }
    }

    Sum = 0;
    // 연결된 3개의 면의 최소값 *4
    Sum += 4 * Min3;
    // 연결된 2개 면의 최소값 (N-1)*4+(N-2)*4
    Sum += (long long)((N - 1) * 4 + (N - 2) * 4) * Min2;
    // 가장 작은 한개의 면 (N-1)*N-2*4 + (N-2)*(N-2)
    Sum += ((long long)(N - 1) * (N - 2) * 4 + (long long)(N - 2) * (N - 2)) * Min1;

    cout << Sum;
}
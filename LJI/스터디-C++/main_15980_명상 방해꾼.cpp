#include <iostream>
#include <vector>
#include <cmath>
using namespace std;
int N, M;
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> M;

    vector<vector<int>> Bird(N + 1, vector<int>(M, 0));

    char LR;

    for (int i = 1; i <= N; i++)
    {
        cin >> LR;

        string tmp;
        cin >> tmp;
        int Val = 0;
        if (LR == 'L')
        {
            Val = -1;
        }
        else
        {
            Val = 1;
        }

        for (int j = 0; j < M; j++)
        {
            if (tmp[j] - '0' == 1)
            {
                Bird[i][j] = Val;
            }
        }
    }

    // 정신에 누적합 기록
    vector<int> Mind(M, 0);
    for (int i = 0; i < M; i++)
    {
        if (i != 0)
        {
            Mind[i] += Mind[i - 1];
        }

        for (int j = 1; j <= N; j++)
        {
            Mind[i] += Bird[j][i];
        }
    }

    // 새 한마리씩 제거하면서 체크
    int AnswerBirdNum = 0;
    int AnswerMind = INT32_MAX;

    for (int i = 1; i <= N; i++)
    {
        // i번째 새를 제거
        // 각 초의 누적한 값
        int Sum = 0;
        // 가장 크게 방해받았을 때
        int MaxMind = 0;
        for (int j = 0; j < M; j++)
        {
            if (Bird[i][j] == 1)
            {
                Sum -= 1;
            }
            else if (Bird[i][j] == -1)
            {
                Sum += 1;
            }

            MaxMind = max(MaxMind, abs(Mind[j] + Sum));
        }

        if (MaxMind < AnswerMind)
        {
            AnswerBirdNum = i;
            AnswerMind = MaxMind;
        }
    }

    cout << AnswerBirdNum << '\n'
         << AnswerMind;
}
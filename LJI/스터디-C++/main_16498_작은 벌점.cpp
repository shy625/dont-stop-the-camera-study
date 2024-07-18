#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int ANum, BNum, CNum;

bool CheckC(int Max, int Min, vector<int> &C)
{
    int Left = 0;
    int Right = C.size() - 1;
    int Mid = 0;

    while (Left <= Right)
    {
        Mid = (Left + Right) / 2;

        if (Min <= C[Mid] && C[Mid] <= Max)
        {
            return true;
        }
        else if (C[Mid] < Min)
        {
            Left = Mid + 1;
        }
        else
        {
            Right = Mid - 1;
        }
    }

    return false;
}

// A,B를 최대 최소로 설정하고 사이에 C의 값이 들어갈 수 있는지 확인
void Find(vector<int> &A, vector<int> &B, vector<int> &C, int &Answer)
{
    for (int i = 0; i < A.size(); i++)
    {
        for (int j = 0; j < B.size(); j++)
        {
            int Max = max(A[i], B[j]);
            int Min = min(A[i], B[j]);

            // 굳이 할 필요 없음
            if (Answer <= Max - Min)
                continue;

            // C가 사이에 들어갈 수 있는지 확인
            if (CheckC(Max, Min, C))
            {
                Answer = Max - Min;
            }
        }
    }
}
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> ANum >> BNum >> CNum;

    vector<int> A(ANum, 0);
    for (int i = 0; i < ANum; i++)
    {
        cin >> A[i];
    }

    vector<int> B(BNum, 0);
    for (int i = 0; i < BNum; i++)
    {
        cin >> B[i];
    }
    vector<int> C(CNum, 0);

    for (int i = 0; i < CNum; i++)
    {
        cin >> C[i];
    }

    sort(A.begin(), A.end());
    sort(B.begin(), B.end());
    sort(C.begin(), C.end());

    // 세 사람이 가장 가까운 경우를 찾아야한다.

    // A와 B를 고르고 C가 그 사이에 들어갈 수 있다면?

    int Answer = INT32_MAX;

    // 서로 안해본 조합으로 앞에 두개 채우기
    Find(A, B, C, Answer);
    Find(A, C, B, Answer);
    Find(C, B, A, Answer);

    cout << Answer;
}
#include <iostream>
#include <vector>
using namespace std;

int N, M;

bool CheckSize(int Size, vector<int> &Lecture)
{
    int Cnt = 0;
    int Sum = 0;
    for (int i = 0; i < N; i++)
    {

        if (Sum + Lecture[i] <= Size)
        {
            Sum += Lecture[i];
        }
        else
        {
            Cnt++;
            Sum = Lecture[i];
        }

        if (Cnt > M)
        {
            return false;
        }
    }

    // 나머지 처리
    Cnt++;
    if (Cnt > M)
    {
        return false;
    }
    return true;
}

main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> M;

    vector<int> Lecture(N, 0);

    int Min = 0;
    int Max = 0;
    for (int i = 0; i < N; i++)
    {
        cin >> Lecture[i];
        Max += Lecture[i];
        Min = max(Min, Lecture[i]);
    }

    int Answer = INT32_MAX;

    int Mid = 0;
    while (Min <= Max)
    {
        Mid = (Min + Max) / 2;

        // Mid 사이즈로 블루레이 묶기 가능하다면 정답 갱신하고 사이즈 줄여보기
        // 불가능하다면 사이즈 늘리기
        if (CheckSize(Mid, Lecture))
        {
            Answer = min(Mid, Answer);
            Max = Mid - 1;
        }
        else
        {
            Min = Mid + 1;
        }
    }

    cout << Answer;
}
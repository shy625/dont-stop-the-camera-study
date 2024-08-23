#include <iostream>

using namespace std;
int T;

// 중간을 기준으로 좌우가 반대로 대칭되는 형태가 반복해서 일어나야한다

bool CheckPaper(string &Paper, int S, int E)
{
    if (S == E)
    {
        return true;
    }

    int Start = S;
    int End = E;
    while (Start != End)
    {
        if (Paper[Start] == Paper[End])
        {
            return false;
        }
        else
        {
            Start++;
            End--;
        }
    }

    return CheckPaper(Paper, S, End - 1);
}

main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> T;

    for (int t = 0; t < T; t++)
    {
        string Paper;
        cin >> Paper;

        int Len = Paper.length();
        int Half = Len / 2;

        int isPossible = true;

        isPossible = CheckPaper(Paper, 0, Paper.length() - 1);

        if (isPossible)
        {
            cout << "YES\n";
        }
        else
        {
            cout << "NO\n";
        }
    }
}
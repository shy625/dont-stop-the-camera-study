#include <iostream>
#include <vector>
using namespace std;

string S;
int N, Len;
vector<string> A;
vector<bool> Check;
void CheckWord(int idx)
{
    for (string Word : A)
    {
        int WordLen = Word.length();
        if (WordLen + idx <= Len)
        {
            string SubStr = S.substr(idx, WordLen);

            if (SubStr == Word)
            {
                Check[WordLen + idx - 1] = true;
            }
        }
    }
}
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> S >> N;

    for (int i = 0; i < N; i++)
    {
        string tmp;
        cin >> tmp;
        A.push_back(tmp);
    }

    Len = S.length();

    Check = vector<bool>(Len, false);

    CheckWord(0);

    for (int i = 0; i < Len; i++)
    {
        if (Check[i])
        {
            CheckWord(i + 1);
        }
    }

    if (Check[Len - 1])
    {
        cout << "1";
    }
    else
    {
        cout << "0";
    }
}
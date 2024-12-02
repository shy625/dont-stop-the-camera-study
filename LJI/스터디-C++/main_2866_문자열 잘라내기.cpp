#include <iostream>
#include <vector>
#include <map>
#include <set>
using namespace std;

int R, C;
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> R >> C;

    vector<string> Origin;
    for (int i = 0; i < R; i++)
    {
        string Str;
        cin >> Str;
        Origin.push_back(Str);
    }

    set<string> WordSet;
    for (int i = 0; i < C; i++)
    {
        string Temp = "";
        for (int j = 0; j < R; j++)
        {
            Temp = Temp + Origin[j][i];
        }

        WordSet.insert(Temp);
    }

    // int Answer = 0;

    for (int i = 0; i < R - 1; i++)
    {

        set<string> NewWordSet;
        for (string Word : WordSet)
        {
            string Tmp = Word.substr(1);

            if (NewWordSet.find(Tmp) != NewWordSet.end())
            {
                cout << i;
                return 0;
            }
            NewWordSet.insert(Tmp);
        }

        WordSet = NewWordSet;
    }

    cout << R - 1;
}
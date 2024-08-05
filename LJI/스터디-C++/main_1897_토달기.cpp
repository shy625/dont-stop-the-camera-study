#include <iostream>
#include <map>
#include <queue>
using namespace std;
int d;

string StartWord;
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> d >> StartWord;

    map<string, bool> Dict;
    for (int i = 0; i < d; i++)
    {
        string tmp;
        cin >> tmp;

        // 한번도 방문하지 않았기에 false체크
        Dict[tmp] = false;
    }

    string Answer = "";

    queue<string> Que;
    Que.push(StartWord);
    Dict[StartWord] = true;

    while (!Que.empty())
    {
        string Now = Que.front();
        Que.pop();

        if (Answer.size() < Now.size())
        {
            Answer = Now;
        }

        for (int i = 0; i <= Now.size(); i++)
        {
            for (int j = 0; j < 26; j++)
            {
                char Ch = 'a' + j;
                string New = Now;

                New.insert(i, 1, Ch);

                if (Dict.find(New) != Dict.end() && Dict[New] == false)
                {
                    Dict[New] = true;
                    Que.push(New);
                }
            }
        }
    }

    cout << Answer;
}
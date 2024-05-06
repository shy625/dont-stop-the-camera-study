#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int N, K;
struct Character
{
    int Str;
    int Dex;
    int Int;
};

main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> K;

    vector<Character> Soldiers;
    vector<int> StrList;
    vector<int> DexList;
    vector<int> IntList;
    for (int i = 0; i < N; i++)
    {
        int Str, Dex, Int;
        cin >> Str >> Dex >> Int;
        Character Tmp = {Str, Dex, Int};

        StrList.push_back(Str);
        DexList.push_back(Dex);
        IntList.push_back(Int);

        Soldiers.push_back(Tmp);
    }

    sort(IntList.begin(), IntList.end());
    int answer = INT32_MAX;

    // 모든 힘과 민첩 경우의 수 중 Int가 가장 적게 들어가는 경우
    for (int STR = 0; STR < N; STR++)
    {
        for (int DEX = 0; DEX < N; DEX++)
        {
            for (int INT = 0; INT < N; INT++)
            {
                int cnt = 0;
                bool Found = false;
                for (int i = 0; i < N; i++)
                {
                    if (StrList[STR] >= Soldiers[i].Str && DexList[DEX] >= Soldiers[i].Dex && IntList[INT] >= Soldiers[i].Int)
                    {
                        cnt++;
                    }

                    // K개를 깼으면 갱신
                    if (cnt == K)
                    {
                        Found = true;
                        answer = min(answer, StrList[STR] + DexList[DEX] + IntList[INT]);
                        break;
                    }
                }

                if (Found)
                    break;
            }
        }
    }

    cout << answer;
}
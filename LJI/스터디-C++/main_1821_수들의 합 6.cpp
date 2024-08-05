#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int N, Last;

bool CheckLast(vector<int> &NowVec)
{
    if (NowVec.size() == 1)
    {
        if (NowVec[0] == Last)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    int Size = NowVec.size();

    vector<int> NextVec;

    for (int i = 0; i < Size - 1; i++)
    {
        NextVec.push_back(NowVec[i] + NowVec[i + 1]);
    }

    return CheckLast(NextVec);
}
// 1~N까지의 vector 순서 변경하면서 목표값이 되는지 체크
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> Last;

    vector<int> Vec;
    for (int i = 1; i <= N; i++)
    {
        Vec.push_back(i);
    }

    do
    {
        if (CheckLast(Vec))
        {
            break;
        }

    } while (next_permutation(Vec.begin(), Vec.end()));

    for (int Now : Vec)
    {
        cout << Now << ' ';
    }
}
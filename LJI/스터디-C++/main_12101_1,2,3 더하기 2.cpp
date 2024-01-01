#include <iostream>
#include <vector>
using namespace std;
int n, k;
vector<string> answerVec;

void DFS(int sum, string str)
{
    if (sum == n)
    {
        answerVec.push_back(str);
        return;
    }

    // 1,2,3 더하기
    for (int i = 1; i <= 3; i++)
    {
        if (sum + i > n)
            break;

        if (sum == 0)
        {
            DFS(sum + i, to_string(i));
        }
        else
        {
            DFS(sum + i, str + "+" + to_string(i));
        }
    }
}
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> n >> k;

    DFS(0, "");

    if (answerVec.size() < k)
    {
        cout << "-1" << '\n';
    }
    else
    {
        cout << answerVec[k - 1] << '\n';
    }
}
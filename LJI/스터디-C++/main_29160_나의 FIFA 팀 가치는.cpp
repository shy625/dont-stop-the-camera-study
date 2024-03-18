#include <iostream>
#include <vector>
#include <queue>
using namespace std;
int N, K;
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> K;

    vector<priority_queue<int>> PlayerList(12, priority_queue<int>());

    int pos, val;
    for (int i = 0; i < N; i++)
    {
        cin >> pos >> val;

        PlayerList[pos].push(val);
    }

    // K년 반복
    // 각 포지션마다 한명 있다면 0이상일 시 -1
    for (int k = 0; k < K; k++)
    {
        for (int i = 1; i <= 11; i++)
        {
            if (!PlayerList[i].empty() && PlayerList[i].top() != 0)
            {
                int tmp = PlayerList[i].top();
                PlayerList[i].pop();

                PlayerList[i].push(tmp - 1);
            }
        }
    }

    int answer = 0;

    for (int i = 1; i <= 11; i++)
    {
        if (!PlayerList[i].empty() && PlayerList[i].top() != 0)
        {
            answer += PlayerList[i].top();
        }
    }

    cout << answer;
}
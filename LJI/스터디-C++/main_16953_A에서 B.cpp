#include <iostream>
#include <vector>
#include <queue>
using namespace std;
int A, B;
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> A >> B;

    vector<bool> visited(B + 1, false);

    // 값,턴
    queue<pair<int, int>> que;
    que.push(make_pair(A, 1));
    visited[A] = true;

    while (!que.empty())
    {
        int cur = que.front().first;
        int turn = que.front().second;
        que.pop();

        if (cur == B)
        {
            cout << turn;
            return 0;
        }

        long next;
        for (int t = 0; t < 2; t++)
        {
            if (t == 0)
            {
                // 2 곱하기
                next = cur * (long)2;
            }
            else
            { // 1추가
                next = cur * (long)10 + 1;
            }

            if (next > B)
                continue;

            if (visited[next])
                continue;

            visited[next] = true;
            que.push(make_pair(next, turn + 1));
        }
    }

    cout << "-1";
}
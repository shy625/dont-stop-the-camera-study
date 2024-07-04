#include <iostream>
#include <vector>
#include <queue>
using namespace std;
int N;
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N;

    vector<int> Vec(N + 1, 0);
    for (int i = 1; i <= N; i++)
    {
        cin >> Vec[i];
    }

    int A, B;
    cin >> A >> B;

    vector<int> Answer(N + 1, 1000000);

    Answer[A] = 0;
    queue<int> que;
    que.push(A);
    int turn = 0;

    while (!que.empty())
    {
        int size = que.size();

        turn++;
        for (int i = 0; i < size; i++)
        {
            int now = que.front();
            que.pop();

            // now에서 오른쪽
            int next = now + Vec[now];
            while (next <= N)
            {
                if (Answer[next] > turn)
                {
                    Answer[next] = turn;
                    que.push(next);
                }
                next += Vec[now];
            }

            // now에서 왼쪽
            next = now - Vec[now];
            while (next >= 1)
            {
                if (Answer[next] > turn)
                {
                    Answer[next] = turn;
                    que.push(next);
                }

                next -= Vec[now];
            }
        }
    }

    if (Answer[B] == 1000000)
    {
        cout << "-1\n";
    }
    else
    {
        cout << Answer[B] << "\n";
    }
}
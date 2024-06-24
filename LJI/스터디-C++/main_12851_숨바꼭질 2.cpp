#include <iostream>
#include <queue>
using namespace std;
int N, K, X;
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    cin >> N >> K;

    int time = 0, cnt = 0;

    queue<int> que;
    que.push(N);
    vector<int> vec(100001, 100001);
    while (!que.empty())
    {
        int size = que.size();

        for (int i = 0; i < size; i++)
        {
            X = que.front();
            que.pop();

            if (X == K)
            {
                cnt++;
            }

            // 3방향 진행
            if (X + 1 <= 100000 && vec[X + 1] >= time + 1)
            {
                vec[X + 1] = time + 1;
                que.push(X + 1);
            }

            if (X - 1 >= 0 && vec[X - 1] >= time + 1)
            {
                vec[X - 1] = time + 1;
                que.push(X - 1);
            }

            if (X * 2 <= 100000 && vec[X * 2] >= time + 1)
            {
                vec[X * 2] = time + 1;
                que.push(X * 2);
            }
        }

        if (cnt != 0)
        {
            break;
        }
        time++;
    }

    cout << time << '\n'
         << cnt;
}
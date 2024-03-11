#include <iostream>
#include <vector>
#include <queue>
using namespace std;
int A, B, C;
vector<int> ABC(3, 0);
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> A >> B >> C;
    ABC[0] = A;
    ABC[1] = B;
    ABC[2] = C;
    vector<vector<vector<bool>>> Visited(A + 1, vector<vector<bool>>(B + 1, vector<bool>(C + 1, false)));

    vector<bool> answer(201, false);
    queue<vector<int>> que;
    vector<int> abc{0, 0, C};

    Visited[abc[0]][abc[1]][abc[2]] = true;

    que.push(vector<int>{abc[0], abc[1], abc[2]});

    while (!que.empty())
    {
        vector<int> cur = que.front();
        que.pop();

        if (cur[0] == 0 && !answer[cur[2]])
        {
            answer[cur[2]] = true;
        }

        // 물 이동
        // i->j,j->i
        for (int i = 0; i < 3; i++)
        {
            for (int j = i + 1; j < 3; j++)
            {
                // 옮길 수 있는 물의 양
                int water = 0;
                // i->j
                water = max(water, min(cur[i], ABC[j] - cur[j]));
                cur[i] -= water;
                cur[j] += water;
                if (!Visited[cur[0]][cur[1]][cur[2]])
                {
                    Visited[cur[0]][cur[1]][cur[2]] = true;
                    que.push(vector<int>{cur[0], cur[1], cur[2]});
                }
                cur[i] += water;
                cur[j] -= water;
                // j->i

                water = 0;
                water = max(water, min(cur[j], ABC[i] - cur[i]));
                cur[i] += water;
                cur[j] -= water;
                if (!Visited[cur[0]][cur[1]][cur[2]])
                {
                    Visited[cur[0]][cur[1]][cur[2]] = true;
                    que.push(vector<int>{cur[0], cur[1], cur[2]});
                }
                cur[i] -= water;
                cur[j] += water;
            }
        }
    }

    for (int i = 0; i <= 200; i++)
    {
        if (answer[i])
            cout << i << ' ';
    }
}
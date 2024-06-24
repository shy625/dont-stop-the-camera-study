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

    vector<int> stair(N + 1, 1000001);

    int answer = 1000001;

    queue<int> que;
    que.push(0);
    stair[0] = 0;
    while (!que.empty())
    {
        int cur = que.front();
        que.pop();

        if (cur == N)
        {
            // answer = min(answer, stair[N]);
            answer = stair[N];
            break;
        }

        // 1. 한칸 오르기
        if (cur + 1 <= N && (stair[cur] + 1 < stair[cur + 1]))
        {
            stair[cur + 1] = stair[cur] + 1;
            que.push(cur + 1);
        }
        // 2. i+i/2로 순간이동
        if (cur + cur / 2 <= N && (stair[cur] + 1 < stair[cur + cur / 2]))
        {
            stair[cur + cur / 2] = stair[cur] + 1;
            que.push(cur + cur / 2);
        }
    }

    if (K >= answer)
    {
        cout << "minigimbob";
    }
    else
    {
        cout << "water";
    }
}
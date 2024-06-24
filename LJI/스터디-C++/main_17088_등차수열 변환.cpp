#include <iostream>
#include <vector>
#include <queue>
using namespace std;
int N;

struct Info
{
    int idx;  // 현재 인덱스
    int cnt;  // 연산 회수
    int diff; // 등차값
    int val;
};
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    cin >> N;
    vector<int> origin(N);
    for (int i = 0; i < N; i++)
    {
        cin >> origin[i];
    }

    if (N <= 2)
    {
        cout << 0;
        return 0;
    }
    queue<Info> que;
    // 0번 인덱스
    for (int i = -1; i <= 1; i++)
    {
        que.push(Info({0, i == 0 ? 0 : 1, 0, origin[0] + i}));
    }

    // 1번 인덱스//여기서 등차값 계산
    for (int i = 0; i < 3; i++)
    {
        Info cur = que.front();
        que.pop();

        for (int j = -1; j <= 1; j++)
        {
            que.push(Info({1, j == 0 ? cur.cnt : cur.cnt + 1, origin[1] + j - cur.val, origin[1] + j}));
        }
    }

    //
    int answer = 1000000;
    bool check = false;
    while (!que.empty())
    {
        Info cur = que.front();
        que.pop();

        if (cur.idx == N - 1)
        {
            check = true;
            answer = min(answer, cur.cnt);
            continue;
        }

        for (int i = -1; i <= 1; i++)
        {
            if (cur.val + cur.diff == origin[cur.idx + 1] + i)
            {

                que.push(Info({cur.idx + 1,
                               i == 0 ? cur.cnt : cur.cnt + 1,
                               cur.diff,
                               origin[cur.idx + 1] + i}));
                break;
            }
        }
    }

    if (!check)
        answer = -1;
    cout << answer;
}
#include <iostream>
#include <queue>
using namespace std;

main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    int sbit, ebit;
    cin >> sbit >> ebit;
    int start = 0, end = 0;
    int idx = 0;
    while (10 <= sbit)
    {
        int tmp = sbit % 10;
        sbit = sbit / 10;
        if (tmp)
        {
            start += 1 << idx;
        }
        idx++;
    }
    start += 1 << idx;

    idx = 0;
    while (10 <= ebit)
    {
        int tmp = ebit % 10;
        ebit = ebit / 10;
        if (tmp)
        {
            end += 1 << idx;
        }
        idx++;
    }
    end += 1 << idx;

    queue<pair<int, int>> que;
    vector<bool> visit(1025, false);
    que.push(make_pair(start, 0));
    int answer = 0;
    while (!que.empty())
    {
        int now = que.front().first;
        int turn = que.front().second;
        que.pop();

        if (now == end)
        {
            answer = turn;
            break;
        }

        // 보수로 변경
        idx = 0;
        // 현재 인덱스를 바꾸고 싶다면 그 앞에 값이 존재해야만 한다
        while (now >= (1 << (idx + 1)))
        {
            if (now ^ (1 << idx) < 1024 && !visit[(now ^ (1 << idx))])
            {
                visit[(now ^ (1 << idx))] = true;
                que.push(make_pair(now ^ (1 << idx), turn + 1));
            }

            idx++;
        }

        //+1
        if (now + 1 < 1024 && !visit[now + 1])
        {
            visit[now + 1] = true;
            que.push(make_pair(now + 1, turn + 1));
        }

        //-1
        if (now > 0 && !visit[now - 1])
        {
            visit[now - 1] = true;
            que.push(make_pair(now - 1, turn + 1));
        }
    }

    cout << answer;
}
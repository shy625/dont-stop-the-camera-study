#include <iostream>
#include <queue>
using namespace std;
int n, m, d, s, e;

struct Game
{
    bool isStart; // 시작하는 게임인지 끝나는 게임인지
    int d, s, e;  // 게임 시간
    int cnt;      // 게임 수
};

struct cmp
{
    bool operator()(Game a, Game b)
    {
        return a.d == b.d ? a.s == b.s ? a.e > b.e : a.s > b.s : a.d > b.d;
    }
};

main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> n;

    for (int N = 1; N <= n; N++)
    {
        cin >> m;

        // 시간 정보 입력
        priority_queue<Game, vector<Game>, cmp> pq;
        for (int i = 0; i < m; i++)
        {
            cin >> d >> s >> e;
            pq.push(Game({true, d, s, e, 0}));
        }

        // 정답 찾기
        // 경기 끝날 때 answer 갱신
        int answer = 0;
        while (!pq.empty())
        {
            Game cur = pq.top();
            pq.pop();
            // 경기 시작
            if (cur.isStart)
            {
                pq.push(Game({false, cur.d, cur.e, 0, answer + 1}));
            }
            // 경기 끝나는 시간
            else
            {
                answer = max(answer, cur.cnt);
            }
        }

        cout << "Scenario #" << N << ":" << endl;
        cout << answer << endl
             << endl;
    }
}
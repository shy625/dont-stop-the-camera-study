#include <iostream>
#include <queue>
using namespace std;

// 0분부터 59분까지 최소한의 버튼 방법만 찾으면 된다
int N, T;

struct Oven
{
    // 시간
    int time;
    // 버튼 누른 회수
    int ADDH, ADDT, MINT, ADDO, MINO;
};

main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    // 시간 BFS로 찾아보기
    Oven oven[70];
    bool v[70];
    queue<Oven> que;

    // bfs 시작
    que.push({0, 0, 0, 0, 0, 0});
    while (!que.empty())
    {
        Oven cur = que.front();
        que.pop();

        if (cur.time >= 0 && cur.time < 70 && !v[cur.time])
        {
            v[cur.time] = true;
            oven[cur.time] = cur;

            // 비교 순위 때문에 순서 유념해서 넣어야한다.
            que.push({cur.time - 1, cur.ADDH, cur.ADDT, cur.MINT, cur.ADDO, cur.MINO + 1});
            que.push({cur.time + 1, cur.ADDH, cur.ADDT, cur.MINT, cur.ADDO + 1, cur.MINO});
            que.push({cur.time - 10, cur.ADDH, cur.ADDT, cur.MINT + 1, cur.ADDO, cur.MINO});
            que.push({cur.time + 10, cur.ADDH, cur.ADDT + 1, cur.MINT, cur.ADDO, cur.MINO});
            que.push({cur.time + 60, cur.ADDH + 1, cur.ADDT, cur.MINT, cur.ADDO, cur.MINO});
        }
    }

    // T케이스 반복

    cin >> T;

    for (int t = 0; t < T; t++)
    {
        cin >> N;

        int hour = N / 60;
        int minute = N % 60;
        cout << oven[minute].ADDH + hour << " " << oven[minute].ADDT << " " << oven[minute].MINT << " " << oven[minute].ADDO << ' ' << oven[minute].MINO << endl;
    }
}
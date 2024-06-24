#include <iostream>
#include <queue>
using namespace std;
int N, M;
// 능력을 내림차순으로 정렬하여 필요할 때마다 해당 일 수+능력값 더해서 Q를 넘는지 확인
// 마지막 날을 기준으로 남은 능력+마지막일 해서 최대값 늘리기
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> M;

    priority_queue<int, vector<int>, less<int>> pq;

    int tmp;
    for (int i = 0; i < N; i++)
    {
        cin >> tmp;
        pq.push(tmp);
    }

    // T=Day
    int T, Q;
    long long answer = 0;
    for (int i = 0; i < M; i++)
    {
        cin >> T >> Q;

        // 이미 문제 퀄리티 합을 넘어섰으면 굳이 손해볼 필요 없다
        if (answer >= Q)
            continue;

        // 문제 가능할 때까지 추가
        while (!pq.empty() && (answer < Q))
        {
            tmp = pq.top();
            pq.pop();

            answer += tmp + T;
        }

        // 불가능 체크
        if (answer < Q)
        {
            answer = -1;
            break;
        }
    }

    if (answer != -1)
    {
        while (!pq.empty())
        {
            answer += pq.top() + T;
            pq.pop();
        }
    }
    cout << answer;
}
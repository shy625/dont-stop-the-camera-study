#include <iostream>
#include <vector>
#include <queue>
using namespace std;
int N, S, E, Q;
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N;

    vector<int> Time(1000001, 0);

    vector<bool> Stu(N, false);

    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;

    for (int i = 0; i < N; i++)
    {
        cin >> S >> E;
        pq.push(make_pair(S, i));
        pq.push(make_pair(E, i));
    }

    int cnt = 0;
    for (int i = 0; i <= 1000000; i++)
    {
        // pq에 있는 현재 시간 처리
        // 단 종료, 시작 카운트 따로 처리 , 종료는 계산 후에
        int StartCnt = 0;
        int EndCnt = 0;
        while (!pq.empty() && pq.top().first == i)
        {
            // 이용 시작
            if (!Stu[pq.top().second])
            {
                Stu[pq.top().second] = true;
                StartCnt++;
            }
            else
            {
                Stu[pq.top().second] = false;
                EndCnt++;
            }
            pq.pop();
        }

        // 이번 시간 최종 정산
        Time[i] = cnt + StartCnt;

        cnt += StartCnt - EndCnt;
    }

    cin >> Q;

    for (int i = 0; i < Q; i++)
    {
        int t;
        cin >> t;
        cout << Time[t] << '\n';
    }
}
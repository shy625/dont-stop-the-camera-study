#include <iostream>
#include <queue>
using namespace std;
// 우선순위 큐로 정렬?
// 같은 시간대 처리만 잘 해주면 문제 없지 않을까
int N;
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N;

    // 우선순위 큐에 <시간,회의 번호> 기입
    // 회의 시작 여부를 체크할 bool배열 선언

    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
    bool bStart[N] = {
        false,
    };

    for (int i = 0; i < N; i++)
    {
        int startT, endT;
        cin >> startT >> endT;

        pq.push(make_pair(startT, i));
        pq.push(make_pair(endT, i));
    }

    // 최대 회의 개수
    int answer = 0;
    // 현재 회의 진행 개수
    int cnt = 0;

    while (!pq.empty())
    {
        // 처음 뽑은 시간과 같은 시간이 계속 나오면 계속 진행해야할 것
        int nowTime = pq.top().first;
        // pq가 더이상 비지 않으면서 현재 시간이  같은 시간일때 계속 진행
        // 최소 회의니 이 반복이 끝난뒤 정답 갱신
        while (!pq.empty() && nowTime == pq.top().first)
        {
            pair<int, int> cur = pq.top();
            pq.pop();

            // 회의가 진행 중이었으므로 끝내야한다
            if (bStart[cur.second])
            {
                bStart[cur.second] = false;
                // 회의 개수 하나 종료
                cnt--;
            }
            else
            {
                // 회의 하나 추가
                bStart[cur.second] = true;
                cnt++;
            }
        }

        answer = max(answer, cnt);
    }

    cout << answer;
}
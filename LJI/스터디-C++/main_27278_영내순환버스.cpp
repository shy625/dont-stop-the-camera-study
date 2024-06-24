#include <iostream>
#include <vector>
using namespace std;
int N, M;

// 한사이클을 도는 데 각 위치마다 걸리는 시간을 누적합
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    cin >> N >> M;
    vector<int> bus(N + 1, 0);
    for (int i = 1; i <= N; i++)
    {
        int tmp;
        cin >> tmp;
        bus[i] = bus[i - 1] + tmp;
    }

    int answer = 0; // 최대 시간 계산

    int loopTime = bus[N];
    int s, e, t;
    for (int i = 0; i < M; i++)
    {
        cin >> s >> e >> t;
        s--;
        e--;
        int cycle = t / loopTime;
        int least = t % loopTime;
        if (bus[s] < least)
        {
            cycle++;
        }

        // 도착지가 출발지보다 적으면 한바퀴 더 돌아야함
        if (e < s)
            cycle++;

        answer = max(answer, cycle * loopTime + bus[e]);
    }
    cout << answer;
}
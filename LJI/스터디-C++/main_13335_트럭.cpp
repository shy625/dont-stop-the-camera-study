#include <iostream>
#include <queue>
using namespace std;

// 트럭의 순서를 바꿀순 없다!
// 무게 여유가 있더라도 한번에 하나의 차만 들어갈 수 있다!
int n, w, L;
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    cin >> n >> w >> L;

    // 대기 줄 받기
    queue<int> waitQue;
    for (int i = 0; i < n; i++)
    {
        int weight;
        cin >> weight;
        waitQue.push(weight);
    }

    // 다리 위에 있는 차의 정보 <차의 무게,차의 위치>
    queue<pair<int, int>> que;

    // 대기차랑 다리 위 차 모두 비어야 끝
    int time = 0;
    while (!waitQue.empty() || !que.empty())
    {
        time++;
        // 다리 위 차 이동
        int size = que.size();
        int totalL = 0;
        for (int i = 0; i < size; i++)
        {
            pair<int, int> nowCar = que.front();
            que.pop();

            // 차 한칸 이동
            nowCar.second++;
            // 아직 다리위라면
            if (nowCar.second <= w)
            {
                totalL += nowCar.first;
                que.push(nowCar);
            }
        }

        // totalL에 여유가 있고 대기차(waitQue)가 있고 무게 여유가 되면 하나 차 넣기
        if (!waitQue.empty())
        {
            if (totalL + waitQue.front() <= L)
            {
                que.push(make_pair(waitQue.front(), 1));
                waitQue.pop();
            }
        }
    }
    cout << time;
}
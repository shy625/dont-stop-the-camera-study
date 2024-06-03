#include <iostream>
#include <queue>
using namespace std;
int N, M;

// 내림차순으로 정렬된 우선순위 큐에 한명씩 넣다가 임계값 M을 넘어가면 M보다 줄어들 때까지 꺼내서 가지 주기 처리
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> M;

    priority_queue<int, vector<int>, less<int>> PQ;

    int Sum = 0;
    int Answer = 0;
    for (int i = 0; i < N; i++)
    {
        int tmp;
        cin >> tmp;

        PQ.push(tmp);
        Sum += tmp;

        if (Sum >= M)
        {
            while (Sum >= M)
            {
                tmp = PQ.top();
                PQ.pop();
                // 올랐던 불만도를 내림 처리
                Sum -= 2 * tmp;

                Answer++;
            }
        }
    }

    cout << Answer;
}
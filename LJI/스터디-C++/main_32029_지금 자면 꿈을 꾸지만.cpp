#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int N, A, B;
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> A >> B;

    // 빨리 끝내야하는 순으로 과제 정렬
    vector<int> Task(N, 0);
    for (int i = 0; i < N; i++)
    {
        cin >> Task[i];
    }
    sort(Task.begin(), Task.end());

    int Answer = 0;
    // turn번 idx보다 전에 자기
    for (int turn = 0; turn < N; turn++)
    {
        // sleep 시간만큼 자기
        for (int sleep = 0; sleep < A; sleep++)
        {
            // 총 풀 수 있는 과제 개수 구해서 정답과 비교
            int time = 0;
            int cnt = 0;
            for (int idx = 0; idx < N; idx++)
            {
                // 잠 잔 이후
                if (idx >= turn)
                {
                    // 잠 자기
                    if (idx == turn)
                    {
                        time += B * sleep;
                    }

                    if (time + (A - sleep) <= Task[idx])
                    {
                        cnt++;
                        time += (A - sleep);
                    }
                } // 잠 자기전
                else
                {
                    if (time + A <= Task[idx])
                    {
                        cnt++;
                        time += A;
                    }
                }
            }

            Answer = max(Answer, cnt);
        }
    }

    cout << Answer;
}
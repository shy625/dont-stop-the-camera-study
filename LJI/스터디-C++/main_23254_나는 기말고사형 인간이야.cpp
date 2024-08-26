#include <iostream>
#include <vector>
#include <queue>
using namespace std;
int N, M;

/// 올릴 수 있는 점수가 높은 순으로 정렬
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> M;

    priority_queue<pair<int, int>, vector<pair<int, int>>> PQ;
    int Answer = 0;

    vector<int> Score(M, 0);
    vector<int> Add(M, 0);
    for (int i = 0; i < M; i++)
    {
        cin >> Score[i];
    }
    for (int i = 0; i < M; i++)
    {
        cin >> Add[i];
    }

    for (int i = 0; i < M; i++)
    {
        // int Score, Add;
        // cin >> Score >> Add;

        Answer += Score[i];
        int Remain = 100 - Score[i];
        if (Remain < Add[i])
        {
            Add[i] = Remain;
        }
        PQ.push(make_pair(Add[i], Score[i]));
    }

    for (int i = 0; i < N * 24; i++)
    {
        if (PQ.empty())
            break;

        int Score = PQ.top().second;
        int Add = PQ.top().first;
        PQ.pop();
        Answer += Add;

        Score += Add;

        if (Score != 100)
        {
            int Remain = 100 - Score;
            if (Remain < Add)
            {
                Add = Remain;
            }
            PQ.push(make_pair(Add, Score));
        }
    }

    cout << Answer;
}
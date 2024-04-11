#include <iostream>
#include <stack>
#include <vector>
using namespace std;
int N;
// 4개의 스택 중 자신보다 낮은 곳 중 가장 큰 값있는 곳에 삽입

// 꺼낼 때 4개의 스택 중 가장 큰 값부터 꺼내서 계속해서 낮아지면 청소 가능 아니면 불가
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N;

    vector<stack<int>> StackVec(4, stack<int>());

    bool Answer = true;

    for (int i = 0; i < N; i++)
    {
        int tmp;
        cin >> tmp;

        int Max = 0;
        int MaxIdx = -1;

        bool Empty = false;
        int EmptyIdx = -1;
        for (int j = 0; j < 4; j++)
        {
            if (StackVec[j].empty())
            {
                if (!Empty)
                {
                    Empty = true;
                    EmptyIdx = j;
                }
                continue;
            }

            if (StackVec[j].top() < tmp)
            {
                if (Max < StackVec[j].top())
                {
                    Max = StackVec[j].top();
                    MaxIdx = j;
                }
            }
        }

        // MaxIdx 가 있다면 해당 위치에 삽입
        if (MaxIdx != -1)
        {
            StackVec[MaxIdx].push(tmp);
        }
        // 없다면 Empty가 있는지 확인 후 삽입
        else if (Empty)
        {
            StackVec[EmptyIdx].push(tmp);
        }
        // 그것도 없다면 불가
        else
        {
            Answer = false;
            break;
        }
    }

    if (Answer)
        cout << "YES";
    else
        cout << "NO";
}
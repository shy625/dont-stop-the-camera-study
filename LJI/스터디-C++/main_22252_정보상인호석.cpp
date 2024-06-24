#include <iostream>
#include <map>
#include <queue>
#include <algorithm>
using namespace std;

main()
{
    ios_base::sync_with_stdio(false);
    cout.tie(0);
    cin.tie(0);

    int Q;
    cin >> Q;

    map<string, priority_queue<int, vector<int>, less<int>>> gorillas;

    long answer = 0;
    for (int q = 0; q < Q; q++)
    {
        int order, n;
        string name;
        cin >> order >> name >> n;
        // 고릴라 정보 입수
        if (order == 1)
        {
            // 처음 등장하는 고릴라일시 priorityQueue 추가
            if (gorillas.find(name) == gorillas.end())
            {
                gorillas.insert({name, priority_queue<int, vector<int>, less<int>>()});
            }

            for (int i = 0; i < n; i++)
            {
                int temp;
                cin >> temp;
                gorillas[name].push(temp);
            }
        }
        // 휘석이 정보 구매
        else
        {
            // 구매하려는 고릴라가 아예 없으면?
            if (gorillas.find(name) == gorillas.end())
                continue;

            // 고릴라가 존재할시
            int turn = 0;
            while ((!gorillas[name].empty()) && turn < n)
            {
                int cost = gorillas[name].top();
                gorillas[name].pop();
                answer += cost;
                turn++;
            }
        }
    }
    cout << answer;
}
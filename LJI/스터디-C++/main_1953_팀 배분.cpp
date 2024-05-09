#include <iostream>
#include <vector>
#include <queue>
using namespace std;
int N;

void PrintTeam(vector<int> &Team)
{
    cout << Team.size() << '\n';
    for (int Member : Team)
    {
        cout << Member << ' ';
    }
    cout << '\n';
}

main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N;

    vector<bool> Stu(N + 1, false);

    vector<vector<int>> Graph(N + 1);

    for (int i = 1; i <= N; i++)
    {
        int num;
        cin >> num;
        for (int j = 0; j < num; j++)
        {
            int other;
            cin >> other;
            Graph[i].push_back(other);
        }
    }

    vector<bool> Visited(N + 1, false);
    for (int i = 1; i <= N; i++)
    {
        if (Visited[i])
            continue;

        queue<int> que;
        que.push(i);
        Visited[i] = true;
        
        bool NowTeam = true;
        while (!que.empty())
        {
            int size = que.size();
            for (int i = 0; i < size; i++)
            {
                int now = que.front();
                que.pop();
                Stu[now] = NowTeam;

                for (int next : Graph[now])
                {
                    if (!Visited[next])
                    {
                        Visited[next] = true;
                        que.push(next);
                    }
                }
            }
            NowTeam = !NowTeam;
        }
    }

    // Stu 값에 따라 team 분류
    vector<int> BlueTeam;
    vector<int> WhiteTeam;

    for (int i = 1; i <= N; i++)
    {
        if (Stu[i])
        {
            BlueTeam.push_back(i);
        }
        else
        {
            WhiteTeam.push_back(i);
        }
    }

    // 블루팀 출력
    PrintTeam(BlueTeam);

    // 화이트팀 출력
    PrintTeam(WhiteTeam);
}
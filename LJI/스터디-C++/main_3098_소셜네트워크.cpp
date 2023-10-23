#include <iostream>
#include <queue>
#include <algorithm>
using namespace std;
int N, M;

// 자신과 거리 2인 친구들에게 새로운 관계 형성

bool graph[51][51];
bool visit[51][51];
bool CheckAllFriends()
{
    for (int i = 1; i <= N; i++)
    {
        for (int j = 1; j <= N; j++)
        {
            if (i == j)
                continue;
            if (!graph[i][j])
            {
                return false;
            }
        }
    }
    return true;
}
void MakeFriends(queue<int> &friendsQueue)
{
    fill(&visit[0][0], &visit[50][51], false);
    int newFriendsCnt = 0;
    for (int i = 1; i <= N; i++)
    {
        for (int j = 1; j <= N; j++)
        {
            if (i == j)
                continue;
            // i->j가 이번에 친구가 됐거나 친구가 아니면은 패스
            if (visit[i][j] || !graph[i][j])
                continue;

            // i->j가 원래 친구였다면?
            // j의 원래 친구 중 i와 친구가 아닌 사람들과 새로운 친구 맺기
            for (int k = 1; k <= N; k++)
            {
                if (i == k || j == k)
                    continue;
                // i와 k가 현재 친구가 아니며 j와 k는 원래 친구였다
                if (!graph[i][k] && graph[j][k] && !visit[j][k])
                {
                    visit[i][k] = true;
                    visit[k][i] = true;

                    graph[i][k] = true;
                    graph[k][i] = true;

                    newFriendsCnt++;
                }
            }
        }
    }

    friendsQueue.push(newFriendsCnt);
}
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> M;

    fill(&graph[0][0], &graph[50][51], false);

    // 친구 관계 입력
    for (int i = 0; i < M; i++)
    {
        int a, b;
        cin >> a >> b;
        graph[a][b] = true;
        graph[b][a] = true;
    }

    int days = 0;
    queue<int> friendsQueue;
    while (true)
    {
        if (CheckAllFriends())
            break;

        MakeFriends(friendsQueue);
        days++;
    }

    cout << days << endl;
    while (!friendsQueue.empty())
    {
        cout << friendsQueue.front() << endl;
        friendsQueue.pop();
    }
}
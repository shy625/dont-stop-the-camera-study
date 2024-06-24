#include <iostream>
#include <vector>
#include <algorithm>
#define INF 10000000
using namespace std;
int N, M;
vector<vector<int>> graph;
pair<int, int> answerPair;
void Pick(int cnt, int idx, vector<int> pick, int &answer)
{
    if (cnt == 2)
    {
        int sum = 0;
        for (int i = 1; i <= N; i++)
        {
            // 두 치킨 집 중 짧은 거리 구하기
            int dis = min(graph[i][pick[0]], graph[i][pick[1]]);
            sum += dis;
        }

        if (answer > sum)
        {
            answer = sum;
            answerPair = make_pair(pick[0], pick[1]);
        }
        return;
    }

    for (int i = idx; i <= N; i++)
    {
        pick[cnt] = i;
        Pick(cnt + 1, i + 1, pick, answer);
    }
}
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    cin >> N >> M;

    for (int i = 0; i <= N; i++)
    {
        graph.push_back(vector<int>(N + 1));
    }
    fill(graph.begin(), graph.end(), vector<int>(N + 1, INF));
    for (int i = 0; i <= N; i++)
    {
        graph[i][i] = 0;
    }

    for (int i = 0; i < M; i++)
    {
        int a, b;
        cin >> a >> b;
        graph[a][b] = 1;
        graph[b][a] = 1;
    }

    // 플로이드 워셜
    for (int k = 1; k <= N; k++)
    {
        for (int i = 1; i <= N; i++)
        {
            for (int j = 1; j <= N; j++)
            {
                if (i == j || i == k || k == j)
                    continue;

                if (graph[i][j] > graph[i][k] + graph[k][j])
                {
                    graph[i][j] = graph[i][k] + graph[k][j];
                }
            }
        }
    }

    // 작은 것부터 두개 뽑아서 거리값 갱신
    int answer = INF;
    Pick(0, 1, vector<int>(2), answer);

    cout << answerPair.first << " " << answerPair.second << " " << answer * 2;
}

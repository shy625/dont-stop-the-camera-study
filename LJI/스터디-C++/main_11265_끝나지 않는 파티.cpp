#include <iostream>
#include <algorithm>
using namespace std;
int N, M;
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    cin >> N >> M;

    int graph[N + 1][N + 1];
    int tmp;
    for (int i = 1; i <= N; i++)
    {
        for (int j = 1; j <= N; j++)
        {
            cin >> tmp;
            graph[i][j] = tmp;
        }
    }

    // 플로이드 워셜
    for (int k = 1; k <= N; k++)
    {
        for (int i = 1; i <= N; i++)
        {
            if (k == i)
                continue;
            for (int j = 1; j <= N; j++)
            {
                if (k == j || i == j)
                    continue;

                if (graph[i][j] > graph[i][k] + graph[k][j])
                {
                    graph[i][j] = graph[i][k] + graph[k][j];
                }
            }
        }
    }

    //
    int A, B, C;
    for (int i = 0; i < M; i++)
    {
        cin >> A >> B >> C;

        if (graph[A][B] <= C)
        {
            cout << "Enjoy other party" << endl;
        }
        else
        {
            cout << "Stay here" << endl;
        }
    }
}
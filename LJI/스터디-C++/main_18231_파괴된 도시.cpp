#include <iostream>
#include <vector>
using namespace std;
int N, M, K;

// 특정 도시와 연결된 모든 도시가 파괴되어 있다면 해당 도시의 폭탄 드랍

// 해당 도시와 주변 도시 모두 파괴 표시하고 지나가고 만약 끝나고 파괴되었는데
// 파괴 방문표시가 되지 않은 도시가 있다면 -1출력
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> M;

    vector<vector<int>> Graph(N + 1);

    for (int i = 0; i < M; i++)
    {
        int u, v;
        cin >> u >> v;

        Graph[u].push_back(v);
        Graph[v].push_back(u);
    }

    // 파괴된 도시 체크
    vector<bool> DestroiedCity(N + 1, false);
    cin >> K;
    for (int i = 0; i < K; i++)
    {
        int City;
        cin >> City;

        DestroiedCity[City] = true;
    }

    // 폭탄 체크
    int AnswerCnt = 0;
    vector<int> AnswerList;
    vector<bool> CheckCity(N + 1, false);

    for (int i = 1; i <= N; i++)
    {
        // 파괴되지 않은 도시는 패스
        if (!DestroiedCity[i])
            continue;

        bool DropBomb = true;
        for (int Near : Graph[i])
        {
            // 주변에 파괴되지 않은 도시 있다면 폭탄 드랍 장소가 아님
            if (!DestroiedCity[Near])
            {
                DropBomb = false;
            }
        }

        // 폭탄 투하 장소
        if (DropBomb)
        {
            CheckCity[i] = true;
            AnswerCnt++;
            AnswerList.push_back(i);

            // 주변도 파괴 처리
            for (int Near : Graph[i])
            {
                CheckCity[Near] = true;
            }
        }
    }

    // 현재 맵이 가능한지 체크
    bool Possible = true;
    for (int i = 1; i <= N; i++)
    {
        // 만약 불가능한 부분이 있다면 불가능 체크
        if (DestroiedCity[i] != CheckCity[i])
        {
            Possible = false;
            break;
        }
    }

    if (Possible)
    {
        cout << AnswerCnt << '\n';
        for (int City : AnswerList)
        {
            cout << City << ' ';
        }
        cout << '\n';
    }
    else
    {
        cout << "-1\n";
    }
}
#include <iostream>
#include <vector>
using namespace std;

main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    // 0: 시작 위치,1: 종료 위치
    // 2: warp1,3:warp1 의 상대 워프
    // 4,5
    // 6,7
    vector<pair<int, int>> Warp;

    vector<vector<long long>> Dist(8, vector<long long>(8, 2000000001));
    int r, c;
    // 시작 ,종료 위치 받기
    for (int i = 0; i < 2; i++)
    {
        cin >> r >> c;
        Warp.push_back(make_pair(r, c));
    }

    // 워프 위치들 받기
    int x1, y1, x2, y2;
    for (int i = 1; i <= 3; i++)
    {
        cin >> x1 >> y1 >> x2 >> y2;
        Warp.push_back(make_pair(x1, y1));
        Warp.push_back(make_pair(x2, y2));
        // 워프 비용 10으로 이동
        Dist[i * 2][i * 2 + 1] = 10;
        Dist[i * 2 + 1][i * 2] = 10;
    }

    // 단순 이동으로 거리 비용 초기화하기
    // 워프 비용보다 단순 이동이 더 짧을 때도 대비해준다
    for (int i = 0; i < 8; i++)
    {
        for (int j = 0; j < 8; j++)
        {
            if (i == j)
                continue;

            long long Distance = abs(Warp[i].first - Warp[j].first) + abs(Warp[i].second - Warp[j].second);

            Dist[i][j] = min(Dist[i][j], Distance);
            Dist[j][i] = min(Dist[j][i], Distance);
        }
    }

    // 크루스칼로 0->1로 가는 최소 비용 계산
    for (int k = 0; k < 8; k++)
    {
        for (int i = 0; i < 8; i++)
        {
            if (k == i)
                continue;
            for (int j = 0; j < 8; j++)
            {
                if (i == j || k == j)
                    continue;
                if (Dist[i][j] > Dist[i][k] + Dist[k][j])
                {
                    Dist[i][j] = Dist[i][k] + Dist[k][j];
                }
            }
        }
    }

    cout << Dist[0][1];
}
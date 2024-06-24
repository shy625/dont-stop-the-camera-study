#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int N, M, H;

main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    cin >> N >> M >> H;

    // 시작 위치
    pair<int, int> Home;
    vector<pair<int, int>> Mint;

    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < N; j++)
        {
            int tmp;
            cin >> tmp;
            if (tmp == 1)
            {
                Home = make_pair(i, j);
            }

            if (tmp == 2)
            {
                Mint.push_back(make_pair(i, j));
            }
        }
    }

    // Mint초코 방문 순서 바꿔가면서 시도
    int answer = 0;
    do
    {
        pair<int, int> Cur = Home;
        int Health = M;
        int Cnt = 0;
        // Mint를 순서대로 방문하고 돌아오는게 가능하면 Cnt증가
        for (int i = 0; i < Mint.size(); i++)
        {
            // 민트초코 위치까지 거리 측정
            int Dis = abs(Mint[i].first - Cur.first) + abs(Mint[i].second - Cur.second);
            if (Health < Dis)
            {
                break;
            }

            // 민트초코 위치로 이동
            Cur = Mint[i];
            Health = Health - Dis + H;

            // 집 위치 이동 가능한지 체크
            int HomeDis = abs(Home.first - Cur.first) + abs(Home.second - Cur.second);
            if (HomeDis <= Health)
            {
                // 가능하면 현재 위치까지 먹은 민트초코로 값 변환
                Cnt = i + 1;
            }
        }

        answer = max(answer, Cnt);

    } while (next_permutation(Mint.begin(), Mint.end()));

    cout << answer;
}
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int N, M;
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N;

    vector<int> Crane(N);
    for (int i = 0; i < N; i++)
    {
        cin >> Crane[i];
    }

    sort(Crane.begin(), Crane.end(), greater<int>());

    cin >> M;
    vector<int> Box(M);

    for (int i = 0; i < M; i++)
    {
        cin >> Box[i];
    }
    sort(Box.begin(), Box.end(), greater<int>());

    if (Crane[0] < Box[0])
    {
        cout << "-1";
        return 0;
    }

    int Turn = 0;
    int Cnt = 0;
    int BoxIdx = 0;
    int CraneIdx = 0;
    vector<bool> MovedBox(M, false);
    while (Cnt < M)
    {
        Turn++;

        CraneIdx = 0;
        // 각 크레인은 자기가 옮길 수 있는 최대한 무거운 박스들 옮기기
        for (int i = 0; i < M; i++)
        {
            if (MovedBox[i])
                continue;

            if (Crane[CraneIdx] >= Box[i])
            {
                CraneIdx++;
                MovedBox[i] = true;
                Cnt++;
                // 모든 크레인 소비
                if (CraneIdx == N)
                    break;
            }
        }
    }

    cout << Turn;
}
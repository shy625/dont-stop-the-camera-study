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

    cin >> N >> M;

    vector<int> HI(N, 0);
    vector<int> ARC(M, 0);

    for (int i = 0; i < N; i++)
    {
        cin >> HI[i];
    }

    for (int i = 0; i < M; i++)
    {
        cin >> ARC[i];
    }

    sort(HI.begin(), HI.end());
    sort(ARC.begin(), ARC.end());

    // HI 팀을 기준으로 승리,무승부 횟수를 알면 ARC팀의 승리 횟수도 계산가능

    long long HIWin = 0;
    long long ARCWin = 0;
    long long Draw = 0;

    for (int i = 0; i < N; i++)
    {
        int low = 0;
        int high = M - 1;

        // 이분탐색

        // Hi[i]와 비기거나 이기는 최소 위치
        int LowIdx = M;
        while (low <= high)
        {
            int mid = (low + high) / 2;

            if (ARC[mid] >= HI[i])
            {
                LowIdx = mid;
                high = mid - 1;
            }
            else
            {
                low = mid + 1;
            }
        }

        // Hi[i]와 비기거나 지는 최대 위치
        int HighIdx = -1;
        low = 0;
        high = M - 1;
        while (low <= high)
        {
            int mid = (low + high) / 2;

            if (ARC[mid] <= HI[i])
            {
                HighIdx = mid;
                low = mid + 1;
            }
            else
            {
                high = mid - 1;
            }
        }

        HIWin += LowIdx;
        ARCWin += M - HighIdx - 1;
        Draw += (HighIdx - LowIdx + 1);
    }

    cout << HIWin << ' ' << ARCWin << ' ' << Draw;
}
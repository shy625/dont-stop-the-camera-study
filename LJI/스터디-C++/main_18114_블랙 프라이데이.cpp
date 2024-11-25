#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int N, C;
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> C;

    vector<int> Products(N, 0);
    bool bFound = false;

    for (int i = 0; i < N; i++)
    {
        int W;
        cin >> Products[i];
        if (Products[i] == C)
        {
            bFound = true;
        }
    }

    // 1개 조합으로 못찾았을시
    if (!bFound)
    {
        sort(Products.begin(), Products.end());
        // 양 끝점에서 이분 탐색
        int Left = 0;
        int Right = N - 1;

        // First는 첫번째 요소로 가장 낮은 위치 -1일 때는 0으로 생각
        for (int First = -1; First < N - 2; First++)
        {
            int FirstW = First == -1 ? 0 : Products[First];

            for (int Second = First + 1; Second < N - 1; Second++)
            {
                int SecondW = Products[Second];

                // 이분탐색
                Left = Second + 1;
                Right = N - 1;
                while (Left <= Right)
                {
                    int Mid = (Left + Right) / 2;
                    int W = FirstW + SecondW + Products[Mid];

                    if (W == C)
                    {
                        bFound = true;
                        break;
                    }
                    else if (W < C)
                    {
                        Left = Mid + 1;
                    }
                    else
                    {
                        Right = Mid - 1;
                    }
                }

                if (bFound)
                {
                    break;
                }
            }
            if (bFound)
            {
                break;
            }
        }
    }
    if (bFound)
    {
        cout << 1;
    }
    else
    {
        cout << 0;
    }
}
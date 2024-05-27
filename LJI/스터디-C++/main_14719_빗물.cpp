#include <iostream>
#include <vector>
using namespace std;

main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    int H, W;

    cin >> H >> W;

    vector<int> Vec(W);

    for (int i = 0; i < W; i++)
    {
        cin >> Vec[i];
    }

    // 빗물 구하기
    int Sum = 0;
    for (int i = 0; i < W; i++)
    {
        int LeftTop = 0;
        int RightTop = 0;

        for (int j = i - 1; j >= 0; j--)
        {
            LeftTop = max(LeftTop, Vec[j]);
            if (LeftTop == H)
                break;
        }

        if (LeftTop == 0)
            continue;

        for (int j = i + 1; j < W; j++)
        {
            RightTop = max(RightTop, Vec[j]);
            if (RightTop == H)
                break;
        }

        Sum += max(0, min(RightTop, LeftTop) - Vec[i]);
    }

    cout << Sum;
}
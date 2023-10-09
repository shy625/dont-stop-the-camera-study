#include <iostream>

using namespace std;

int N, bUse;
int map[31][31];

main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> bUse;

    int answerCnt = 0;
    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < N; j++)
        {
            cin >> map[i][j];
            if (map[i][j] != bUse)
            {
                answerCnt++;
            }
        }
    }

    bool bChange = true;
    int cnt = 0;
    int half = N / 2;
    while (bChange)
    {
        bChange = false;

        for (int i = 0; i < N; i++)
        {
            // 가로 줄
            int rCnt = 0;
            // 세로 줄
            int cCnt = 0;

            for (int j = 0; j < N; j++)
            {
                if (map[i][j] != bUse)
                {
                    rCnt++;
                }

                if (map[j][i] != bUse)
                {
                    cCnt++;
                }
            }

            if (rCnt != 0 && rCnt <= half)
            {
                for (int j = 0; j < N; j++)
                {
                    if (bUse != map[i][j])
                    {
                        map[i][j] = bUse;
                        cnt++;
                        if (!bChange)
                        {
                            bChange = true;
                        }
                    }
                }
            }

            if (cCnt != 0 && cCnt <= half)
            {
                for (int j = 0; j < N; j++)
                {
                    if (bUse != map[j][i])
                    {
                        map[j][i] = bUse;
                        cnt++;
                        if (!bChange)
                        {
                            bChange = true;
                        }
                    }
                }
            }
        }
    }

    if (cnt == answerCnt)
    {
        cout << "1" << endl;
    }
    else
    {
        cout << "0" << endl;
    }
}
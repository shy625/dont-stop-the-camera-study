#include <iostream>
#include <vector>
#include <cctype>
#define MAX 1000000
using namespace std;
int N;

// a => b;
void PrintProp(int aInt, int bInt)
{
    char aChar, bChar;

    if (aInt <= 25)
    {
        aChar = 'A' + aInt;
    }
    else
    {
        aChar = 'a' + (aInt - 26);
    }

    if (bInt <= 25)
    {
        bChar = 'A' + bInt;
    }
    else
    {
        bChar = 'a' + (bInt - 26);
    }

    cout << aChar << " => " << bChar << '\n';
}

// 0~25 : A~Z , 26~51: a~z
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    cin >> N;

    vector<vector<int>> Prop(52, vector<int>(52, MAX));

    int AnswerCnt = 0;
    for (int i = 0; i < N; i++)
    {
        char A, B;
        string Tmp;

        cin >> A >> Tmp >> B;

        int AIdx, BIdx;

        if (isupper(A))
        {
            AIdx = A - 'A';
        }
        else
        {
            AIdx = A - 'a' + 26;
        }

        if (isupper(B))
        {
            BIdx = B - 'A';
        }
        else
        {
            BIdx = B - 'a' + 26;
        }

        // 중복 명제 방지// P->P 명제 방지
        if (AIdx != BIdx && Prop[AIdx][BIdx] == MAX)
        {
            Prop[AIdx][BIdx] = 1;
            AnswerCnt++;
        }
    }

    // 플로이드 워셜

    for (int k = 0; k < 52; k++)
    {
        for (int i = 0; i < 52; i++)
        {
            if (i == k)
                continue;
            for (int j = 0; j < 52; j++)
            {
                if (i == j || k == j)
                    continue;

                if (Prop[i][j] > Prop[i][k] + Prop[k][j])
                {
                    // 명제가 생겼으면 정답 갯수 추가

                    if (Prop[i][j] == MAX)
                    {
                        AnswerCnt++;
                    }
                    Prop[i][j] = Prop[i][k] + Prop[k][j];
                }
            }
        }
    }

    cout << AnswerCnt << '\n';
    for (int i = 0; i < 52; i++)
    {
        for (int j = 0; j < 52; j++)
        {
            if (i == j)
                continue;
            if (Prop[i][j] != MAX)
            {
                PrintProp(i, j);
            }
        }
    }
}
#include <iostream>

using namespace std;
// 1. 이번에 착수하는 플레이어를 알아내고
// 2. 그 플레이어가 이기는 경우가 먼저 나오면 승
// 3. 반대 플레이거가 이기는 경우가 먼저 나오면 패
// 4. 모든 칸이 다 차면 무승부

// X ,O 개수가 동일하면 X턴
// 다르면 O턴
int Map[3][3];

// 누가 승리했는지 계산하는 함수
int CheckWinner() //-1:게임 안끝남 ,0:무승부,1:1승리,2:2 승리
{

    // 1이면 +1 ,2이면 -1 -> 최종적으로 3이나 -3 이면 승리가 정해진 것
    int RCnt[3] = {
        0,
    };
    int CCnt[3] = {
        0,
    };
    // 대각선 판정
    int CrossCnt[2] = {
        0,
    };

    fill(&RCnt[0], &RCnt[3], 0);
    fill(&CCnt[0], &CCnt[3], 0);
    fill(&CrossCnt[0], &CrossCnt[2], 0);
    // 0 존재 여부
    bool bEnd = true;

    for (int i = 0; i < 3; i++)
    {
        for (int j = 0; j < 3; j++)
        {
            int Value = 0;
            if (Map[i][j] == 1)
            {
                Value = 1;
            }
            else if (Map[i][j] == 2)
            {
                Value = -1;
            }
            else if (bEnd)
            {
                bEnd = false;
            }

            RCnt[i] += Value;
            CCnt[j] += Value;

            if (i == j)
            {
                CrossCnt[0] += Value;
            }
            if (i == (2 - j))
            {
                CrossCnt[1] += Value;
            }
        }
    }

    int Winner = -1;
    for (int i = 0; i < 3; i++)
    {
        if (abs(RCnt[i]) == 3)
        {
            if (RCnt[i] == 3)
                Winner = 1;
            else
                Winner = 2;

            return Winner;
        }
        if (abs(CCnt[i]) == 3)
        {
            if (CCnt[i] == 3)
                Winner = 1;
            else
                Winner = 2;

            return Winner;
        }
    }
    // 대각선
    for (int i = 0; i < 2; i++)
    {
        if (abs(CrossCnt[i]) == 3)
        {
            if (CrossCnt[i] == 3)
                Winner = 1;
            else
                Winner = 2;

            return Winner;
        }
    }

    // 무승부 경우
    if (Winner == -1 && bEnd)
    {
        Winner = 0;
    }

    return Winner;
}

// 한턴한턴 각 플레이어의 최적의 수를 플레이하기
int Play(int Player)
{
    int Best = -1;
    int NextPlayer;
    if (Player == 1)
    {
        NextPlayer = 2;
    }
    else
    {
        NextPlayer = 1;
    }
    for (int i = 0; i < 3; i++)
    {
        for (int j = 0; j < 3; j++)
        {
            if (Map[i][j] == 0)
            {
                Map[i][j] = Player;

                int Now = CheckWinner();
                if (Now == -1)
                { // 게임이 끝나지 않았다면 다음 수로 넘어가기
                    Now = Play(NextPlayer);
                }

                // Best 갱신//이기는 경우의 수가 나왔으니 끝
                if (Now == Player)
                {
                    Best = Now;
                }

                // 비기는 경우
                if (Now == 0 && (Best == -1 || Best == NextPlayer))
                {
                    Best = 0;
                }

                // 지는 경우
                if (Now == NextPlayer && Best == -1)
                {
                    Best = NextPlayer;
                }
                Map[i][j] = 0;
            }
        }
    }

    return Best;
}
main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    // 맵 입력
    int XCnt = 0;
    int OCnt = 0;
    for (int i = 0; i < 3; i++)
    {
        for (int j = 0; j < 3; j++)
        {
            cin >> Map[i][j];
            if (Map[i][j] == 1)
            {
                XCnt++;
            }
            else if (Map[i][j] == 2)
            {
                OCnt++;
            }
        }
    }

    // 선턴 플레이어 알아내기
    int Player;
    if (XCnt == OCnt)
    {
        Player = 1;
    }
    else
    {
        Player = 2;
    }

    // 선턴부터 플레이 시작

    int answer = CheckWinner();
    if (answer == -1)
        answer = Play(Player);

    if (answer == Player)
    {
        cout << 'W';
    }
    else if (answer == 0)
    {
        cout << 'D';
    }
    else
    {
        cout << 'L';
    }
}
#include <iostream>

using namespace std;

// 색종이가 정사각형인지 검사하고 아니면 쪼개고 반복하는 DFS로 구현하자
int map[128][128];
// 색종이가 정사각형인지 검사하는 함수
bool CheckSquare(int startR, int startC, int n, int color)
{
    for (int i = startR; i < startR + n; i++)
    {
        for (int j = startC; j < startC + n; j++)
        {
            if (map[i][j] != color)
            {
                return false;
            }
        }
    }
    return true;
}

// 색종이를 검사하고 정사각형이면 개수 카운트 아니면 4개로 쪼개서 재귀로 들어가기
void CheckPaper(int startR, int startC, int n, int &blueCnt, int &whiteCnt)
{
    // 기준이 될 색깔을 정하기
    int color = map[startR][startC];

    // 정사각형 검사
    if (CheckSquare(startR, startC, n, color)) // 정사각형일때
    {
        if (color == 1)
            blueCnt++;
        else
            whiteCnt++;
    }
    else // 정사각형이 아닐때
    {
        // 현재 사각형을 4개로 쪼개서 다시 CheckPaper
        int newN = n / 2;
        for (int i = 0; i < 2; i++)
        {
            for (int j = 0; j < 2; j++)
            {
                CheckPaper(startR + i * newN, startC + j * newN, newN, blueCnt, whiteCnt);
            }
        }
    }
}

main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);

    // 맵에 정보 입력
    int N;
    cin >> N;
    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < N; j++)
        {
            cin >> map[i][j];
        }
    }

    // 재귀함수 시작
    int blueCnt = 0, whiteCnt = 0;
    CheckPaper(0, 0, N, blueCnt, whiteCnt);

    cout << whiteCnt << endl
         << blueCnt;
}